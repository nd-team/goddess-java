package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能晋升申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillPromotionApplySerImpl extends ServiceImpl<SkillPromotionApply, SkillPromotionApplyDTO> implements SkillPromotionApplySer {

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对模块负责人审核权限（模块级别）
     */
    private void checkHeadIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应模块负责人的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对预算模块审核权限（模块级别）
     */
    private void checkBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是相应预算模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对项目经理审核权限（岗位级别）
     */
    private void checkManagerIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是项目经理人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对规划模块审核权限（模块级别）
     */
    private void checkPlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是规划模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private void checkGeneralIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
            if (!flag) {
                throw new SerException("您不是总经办的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
     * 核对模块负责人审核权限（模块级别）
     */
    private Boolean guideHeadAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对预算模块审核权限（模块级别）
     */
    private Boolean guideBudgetAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对项目经理审核权限（岗位级别）
     */
    private Boolean guideManagerAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对规划模块审核权限（模块级别）
     */
    private Boolean guidePlanAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private Boolean guideGeneralAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
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
        Boolean flagHead = guideHeadAuditIdentity();
        Boolean flagBudget = guideBudgetAuditIdentity();
        Boolean flagManager = guideManagerAuditIdentity();
        Boolean flagPlan = guidePlanAuditIdentity();
        Boolean flagGeneral = guideGeneralAuditIdentity();
        if (flagSee || flagAdd || flagHead || flagBudget || flagManager || flagPlan || flagGeneral) {
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
            case HEADAUDIT:
                flag = guideHeadAuditIdentity();
                break;
            case BUDGETAUDIT:
                flag = guideBudgetAuditIdentity();
                break;
            case MANAGERAUDIT:
                flag = guideManagerAuditIdentity();
                break;
            case PLANAUDIT:
                flag = guidePlanAuditIdentity();
                break;
            case GENERALAUDIT:
                flag = guideGeneralAuditIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;

            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        Long count = super.count(skillPromotionApplyDTO);
        return count;
    }

    @Override
    public SkillPromotionApplyBO getOne(String id) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(id);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public List<SkillPromotionApplyBO> findListSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        checkSeeIdentity();
        skillPromotionApplyDTO.getSorts().add("createTime=desc");
        List<SkillPromotionApply> skillPromotionApplies = super.findByPage(skillPromotionApplyDTO);
        List<SkillPromotionApplyBO> skillPromotionApplyBOS = BeanTransform.copyProperties(skillPromotionApplies, SkillPromotionApplyBO.class);
        return skillPromotionApplyBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO insertSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkAddIdentity();
        SkillPromotionApply skillPromotionApply = BeanTransform.copyProperties(skillPromotionApplyTO, SkillPromotionApply.class, true);
        skillPromotionApply.setCreateTime(LocalDateTime.now());
        super.save(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO editSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkAddIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        BeanTransform.copyProperties(skillPromotionApplyTO, skillPromotionApply, true);
        skillPromotionApply.setModifyTime(LocalDateTime.now());
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO headAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkHeadIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());

        skillPromotionApply.setHeadOpinion(skillPromotionApplyTO.getHeadOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO budgetAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkBudgetIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        skillPromotionApply.setBudgetOpinion(skillPromotionApplyTO.getBudgetOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO projectManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkManagerIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getHeadOpinion() == null;
        boolean b2 = skillPromotionApply.getBudgetOpinion() == null;
        if (b1 && b2) {
            throw new SerException("负责人或预算模块还未审核");
        } else {
            skillPromotionApply.setProjectManagerOpinion(skillPromotionApplyTO.getProjectManagerOpinion());
            skillPromotionApply.setPhase(2);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO planAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkPlanIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getProjectManagerOpinion() == null;
        if (b1) {
            throw new SerException("项目经理还未审核");
        } else {
            skillPromotionApply.setPlanOpinion(skillPromotionApplyTO.getPlanOpinion());
            skillPromotionApply.setPhase(3);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO generalManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkGeneralIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getPlanOpinion() == null;
        if (b1) {
            throw new SerException("规划模块还未审核");
        } else {
            skillPromotionApply.setManagerOpinion(skillPromotionApplyTO.getManagerOpinion());
            skillPromotionApply.setPhase(4);
            skillPromotionApply.setAuditStatus(skillPromotionApplyTO.getAuditStatus());
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }
}