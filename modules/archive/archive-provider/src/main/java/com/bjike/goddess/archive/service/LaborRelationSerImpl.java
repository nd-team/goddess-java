package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.LaborRelationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.entity.LaborRelation;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 劳动关系类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class LaborRelationSerImpl extends ServiceImpl<LaborRelation, LaborRelationDTO> implements LaborRelationSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;
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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LaborRelationBO save(LaborRelationTO to) throws SerException {
        LaborRelation entity = BeanTransform.copyProperties(to, LaborRelation.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LaborRelationBO update(LaborRelationTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                LaborRelation entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setDescription(to.getDescription());
                super.update(entity);
                return BeanTransform.copyProperties(entity, LaborRelationBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public LaborRelationBO delete(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LaborRelationBO congeal(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LaborRelationBO thaw(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public List<LaborRelationBO> findByStatus(Status status) throws SerException {
        LaborRelationDTO dto = new LaborRelationDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<LaborRelation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, LaborRelationBO.class);
    }

    @Override
    public List<LaborRelationBO> maps(LaborRelationDTO dto) throws SerException {
        dto.getSorts().add("status");
        return BeanTransform.copyProperties(super.findByPage(dto), LaborRelationBO.class);
    }

    @Override
    public LaborRelationBO getById(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        LaborRelationDTO dto = new LaborRelationDTO();
        return super.count(dto);
    }


}