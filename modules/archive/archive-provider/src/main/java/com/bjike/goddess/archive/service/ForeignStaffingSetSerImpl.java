package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingSetBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.entity.ForeignStaffingSet;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
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
 * 对外人员基本信息设置业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ForeignStaffingSetSerImpl extends ServiceImpl<ForeignStaffingSet, ForeignStaffingSetDTO> implements ForeignStaffingSetSer {

    @Autowired
    private ForeignStaffingSer foreignStaffingSer;
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
    public ForeignStaffingSetBO save(ForeignStaffingSetTO to) throws SerException {
        ForeignStaffingSet entity = BeanTransform.copyProperties(to, ForeignStaffingSet.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingSetBO update(ForeignStaffingSetTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ForeignStaffingSet entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ForeignStaffingSetBO delete(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        ForeignStaffingDTO dto = new ForeignStaffingDTO();
        dto.getConditions().add(Restrict.eq("type.id", entity.getId()));
        if (foreignStaffingSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingSetBO congeal(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingSetBO thaw(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public List<ForeignStaffingSetBO> findByStatus(Status status) throws SerException {
        ForeignStaffingSetDTO dto = new ForeignStaffingSetDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<ForeignStaffingSet> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ForeignStaffingSetBO.class);
    }

    @Override
    public List<ForeignStaffingSetBO> maps(ForeignStaffingSetDTO dto) throws SerException {
        dto.getSorts().add("status");
        return BeanTransform.copyProperties(super.findByPage(dto), ForeignStaffingSetBO.class);
    }

    @Override
    public ForeignStaffingSetBO getById(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ForeignStaffingSetDTO dto = new ForeignStaffingSetDTO();
        return super.count(dto);
    }

}