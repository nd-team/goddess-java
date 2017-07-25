package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmove.bo.StaffMovementApplyBO;
import com.bjike.goddess.staffmove.dto.StaffMovementApplyDTO;
import com.bjike.goddess.staffmove.entity.StaffMovementApply;
import com.bjike.goddess.staffmove.enums.AuditorType;
import com.bjike.goddess.staffmove.enums.GuideAddrStatus;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMovementApplyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员调动申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class StaffMovementApplySerImpl extends ServiceImpl<StaffMovementApply, StaffMovementApplyDTO> implements StaffMovementApplySer {
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
     * 核对规划模块审核权限（部门级别）
     */
    private void checkPlanAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应规划模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }
    /**
     * 核对预算模块审核权限（部门级别）
     */
    private void checkBudgetAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
            if (!flag) {
                throw new SerException("您不是相应预算模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }
    /**
     * 核对原决策层审核权限（层级级别）
     */
    private void checkOraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应原决策层的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }
    /**
     * 核对调往决策层审核权限（层级级别）
     */
    private void checkTraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
            if (!flag) {
                throw new SerException("您不是相应调往决策层的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }
    /**
     * 核对总经办审核权限（部门级别）
     */
    private void checkGenAduditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("7");
            if (!flag) {
                throw new SerException("您不是相应总经办的人员，不可以操作");
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
     * 导航栏核对添加修改删除权限（岗位级别）
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
    /**
     * 核对规划模块审核权限（部门级别）
     */
    private Boolean guidePlanAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 核对预算模块审核权限（部门级别）
     */
    private Boolean guideBudgetAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 核对原决策层审核权限（层级级别）
     */
    private Boolean guideOraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 核对调往决策层审核权限（层级级别）
     */
    private Boolean guideTraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 核对总经办审核权限（部门级别）
     */
    private Boolean guideGenAduditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();
        Boolean flagPlanAudit = guidePlanAuditIdentity();
        Boolean flagBudgetAudit = guideBudgetAuditIdentity();
        Boolean flagOraAudit = guideOraAuditIdentity();
        Boolean flagTraAudit = guideTraAuditIdentity();
        Boolean flagGenAdudit = guideGenAduditIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("staffmove");
        obj.setDescribesion("人员调动");
        if (flagSeeInfo || flagAddInfo || flagPlanAudit || flagBudgetAudit || flagOraAudit || flagTraAudit || flagGenAdudit) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
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
            case PLANAUDIT:
                flag = guidePlanAuditIdentity();
                break;
            case BUDGETAUDIT:
                flag = guideBudgetAuditIdentity();
                break;
            case ORANAUDIT:
                flag = guideOraAuditIdentity();
                break;
            case TRAAUDIT:
                flag = guideTraAuditIdentity();
                break;
            case GENAUDIT:
                flag = guideGenAduditIdentity();
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
    public Long countStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        staffMovementApplyDTO.getSorts().add("createTime=desc");
        Long count = super.count(staffMovementApplyDTO);
        return count;
    }

    @Override
    public StaffMovementApplyBO getOne(String id) throws SerException {
        StaffMovementApply staffMovementApply = super.findById(id);
        return BeanTransform.copyProperties(staffMovementApply, StaffMovementApplyBO.class);
    }

    @Override
    public List<StaffMovementApplyBO> findListStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        checkSeeIdentity();
        staffMovementApplyDTO.getSorts().add("createTime=desc");
        List<StaffMovementApply> staffMovementApplies = super.findByCis(staffMovementApplyDTO, true);
        List<StaffMovementApplyBO> staffMovementApplyBOS = BeanTransform.copyProperties(staffMovementApplies, StaffMovementApplyBO.class);
        return staffMovementApplyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMovementApplyBO insertStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        checkAddIdentity();
        StaffMovementApply staffMovementApply = BeanTransform.copyProperties(staffMovementApplyTO, StaffMovementApply.class, true);
        staffMovementApply.setCreateTime(LocalDateTime.now());
        super.save(staffMovementApply);
        return BeanTransform.copyProperties(staffMovementApply, StaffMovementApplyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public StaffMovementApplyBO editStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        checkAddIdentity();
        StaffMovementApply staffMovementApply = super.findById(staffMovementApplyTO.getId());
        BeanTransform.copyProperties(staffMovementApplyTO, staffMovementApply, true);
        staffMovementApply.setModifyTime(LocalDateTime.now());
        super.update(staffMovementApply);
        return BeanTransform.copyProperties(staffMovementApply, StaffMovementApplyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStaffMovementApply(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public StaffMovementApplyBO planAudit(StaffMovementApplyTO to) throws SerException {
        checkPlanAuditIdentity();
        StaffMovementApply staffMovementApply = super.findById(to.getId());
        BeanTransform.copyProperties(to, staffMovementApply, true);
        staffMovementApply.setPlanAuditor(to.getPlanAuditor());
        staffMovementApply.setPlanAuditOpinion(to.getPlanAuditOpinion());
        super.update(staffMovementApply);
        return BeanTransform.copyProperties(staffMovementApply,StaffMovementApplyBO.class);
    }
    @Override
    public StaffMovementApplyBO budgetAudit(StaffMovementApplyTO to) throws SerException {
        checkBudgetAuditIdentity();
        StaffMovementApply staffMovementApply = super.findById(to.getId());
        BeanTransform.copyProperties(to, staffMovementApply, true);
        staffMovementApply.setBudgetAuditor(to.getBudgetAuditor());
        staffMovementApply.setBudgetAuditOpinion(to.getBudgetAuditOpinion());
        return BeanTransform.copyProperties(staffMovementApply,StaffMovementApplyBO.class);
    }
    @Override
    public StaffMovementApplyBO originalAudit(StaffMovementApplyTO to) throws SerException {
        checkOraAuditIdentity();
        StaffMovementApply staffMovementApply = super.findById(to.getId());
        BeanTransform.copyProperties(to, staffMovementApply, true);
        staffMovementApply.setOriginalAuditor(to.getOriginalAuditor());
        staffMovementApply.setOriginalAuditOpinion(to.getOriginalAuditOpinion());
        staffMovementApply.setOriginalMove(to.getOriginalMove());
        super.update(staffMovementApply);
        return BeanTransform.copyProperties(staffMovementApply,StaffMovementApplyBO.class);
    }
    @Override
    public StaffMovementApplyBO transferAudit(StaffMovementApplyTO to) throws SerException {
        checkTraAuditIdentity();
        StaffMovementApply staffMovementApply = super.findById(to.getId());
        BeanTransform.copyProperties(to, staffMovementApply, true);
        if("是".equals(to.getOriginalMove())){

            staffMovementApply.setTransferAuditor(to.getTransferAuditor());
            staffMovementApply.setTransferAuditOpinion(to.getTransferAuditOpinion());
            staffMovementApply.setTransferMove(to.getTransferMove());
            super.update(staffMovementApply);
        }else {
            throw new SerException("原决策未通过,不可以进行操作");
        }

        return BeanTransform.copyProperties(staffMovementApply,StaffMovementApplyBO.class);
    }
    @Override
    public StaffMovementApplyBO generalAudit(StaffMovementApplyTO to) throws SerException {
        checkGenAduditIdentity();
        StaffMovementApply staffMovementApply = super.findById(to.getId());
        BeanTransform.copyProperties(to, staffMovementApply, true);
        if("是".equals(to.getTransferMove())){

            staffMovementApply.setGeneralAuditor(to.getGeneralAuditor());
            staffMovementApply.setGeneralAuditOpinion(to.getGeneralAuditOpinion());
            super.update(staffMovementApply);
        }else {
            throw new SerException("调往决策未通过,不可以进行操作");
        }

        return BeanTransform.copyProperties(staffMovementApply,StaffMovementApplyBO.class);
    }

}