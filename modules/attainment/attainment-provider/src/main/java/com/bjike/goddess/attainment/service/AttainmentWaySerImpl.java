package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.bo.AttainmentWayBO;
import com.bjike.goddess.attainment.dto.AttainmentWayDTO;
import com.bjike.goddess.attainment.entity.AttainmentWay;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研方式业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class AttainmentWaySerImpl extends ServiceImpl<AttainmentWay, AttainmentWayDTO> implements AttainmentWaySer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentWayBO save(AttainmentWayTO to) throws SerException {
        AttainmentWay entity = BeanTransform.copyProperties(to, AttainmentWay.class);
        AttainmentWayDTO dto = new AttainmentWayDTO();
        dto.getConditions().add(Restrict.eq("type", to.getType()));
        if (super.count(dto) != 0)
            throw new SerException(to.getType() + ":已存在");
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentWayBO update(AttainmentWayTO to) throws SerException {
        AttainmentWay entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        if (!to.getType().equals(entity.getType())) {
            AttainmentWayDTO dto = new AttainmentWayDTO();
            dto.getConditions().add(Restrict.eq("type", to.getType()));
            if (super.count(dto) != 0)
                throw new SerException(to.getType() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setDescription(to.getDescription());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentWayBO delete(String id) throws SerException {
        AttainmentWay entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentWayBO congeal(String id) throws SerException {
        AttainmentWay entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentWayBO thaw(String id) throws SerException {
        AttainmentWay entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);
    }

    @Override
    public List<AttainmentWayBO> findThaw() throws SerException {
        AttainmentWayDTO dto = new AttainmentWayDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<AttainmentWay> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AttainmentWayBO.class);
    }

    @Override
    public List<AttainmentWayBO> maps(AttainmentWayDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), AttainmentWayBO.class);
    }

    @Override
    public AttainmentWayBO getById(String id) throws SerException {
        AttainmentWay entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, AttainmentWayBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        AttainmentWayDTO dto = new AttainmentWayDTO();
        return super.count(dto);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
}