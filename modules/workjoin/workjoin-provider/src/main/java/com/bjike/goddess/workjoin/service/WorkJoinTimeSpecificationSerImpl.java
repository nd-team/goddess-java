package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.workjoin.bo.WorkJoinTimeSpecificationBO;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;
import com.bjike.goddess.workjoin.enums.GuideAddrStatus;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTimeSpecificationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接时间规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinTimeSpecificationSerImpl extends ServiceImpl<WorkJoinTimeSpecification, WorkJoinTimeSpecificationDTO> implements WorkJoinTimeSpecificationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
        return flag;
    }

    @Override
    public Long countWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        Long count = super.count(workJoinTimeSpecificationDTO);
        return count;
    }

    @Override
    public WorkJoinTimeSpecificationBO getOne(String id) throws SerException {
        WorkJoinTimeSpecification workJoinTimeSpecification = super.findById(id);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }

    @Override
    public List<WorkJoinTimeSpecificationBO> findListWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        checkSeeIdentity();
        workJoinTimeSpecificationDTO.getSorts().add("createTime=desc");
        List<WorkJoinTimeSpecification> workJoinTimeSpecifications = super.findByPage(workJoinTimeSpecificationDTO);
        List<WorkJoinTimeSpecificationBO> workJoinTimeSpecificationBOS = BeanTransform.copyProperties(workJoinTimeSpecifications, WorkJoinTimeSpecificationBO.class);
        return workJoinTimeSpecificationBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinTimeSpecificationBO insertWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        checkAddIdentity();
        WorkJoinTimeSpecification workJoinTimeSpecification = BeanTransform.copyProperties(workJoinTimeSpecificationTO, WorkJoinTimeSpecification.class, true);
        workJoinTimeSpecification.setCreateTime(LocalDateTime.now());
        super.save(workJoinTimeSpecification);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkJoinTimeSpecificationBO editWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        checkAddIdentity();
        WorkJoinTimeSpecification workJoinTimeSpecification = super.findById(workJoinTimeSpecificationTO.getId());
        LocalDateTime creatTime = workJoinTimeSpecification.getCreateTime();
        workJoinTimeSpecification =  BeanTransform.copyProperties(workJoinTimeSpecificationTO, WorkJoinTimeSpecification.class, true);
        workJoinTimeSpecification.setCreateTime(creatTime);
        workJoinTimeSpecification.setModifyTime(LocalDateTime.now());
        super.update(workJoinTimeSpecification);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWorkJoinTimeSpecification(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}