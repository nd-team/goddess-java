package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyPlanAuditBO;
import com.bjike.goddess.attainment.dto.SurveyPlanAuditDTO;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.attainment.entity.SurveyPlanAudit;
import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.bo.PositionUserDetailBO;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研计划审核记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyPlanAuditSerImpl extends ServiceImpl<SurveyPlanAudit, SurveyPlanAuditDTO> implements SurveyPlanAuditSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private SurveyPlanSer surveyPlanSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private SurveyPlanAuditBO transformBO(SurveyPlanAudit entity) throws SerException {
        SurveyPlanAuditBO bo = BeanTransform.copyProperties(entity, SurveyPlanAuditBO.class);
        bo.setPlanId(entity.getPlan().getId());
        return bo;
    }

    private List<SurveyPlanAuditBO> transformBOList(List<SurveyPlanAudit> list) throws SerException {
        List<SurveyPlanAuditBO> bos = new ArrayList<>(list.size());
        for (SurveyPlanAudit entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanAuditBO update(SurveyPlanAuditTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        UserDetailBO userDetail = userDetailAPI.findByUserId(user.getId());
        PositionDetailUserBO detailUserBO = positionDetailUserAPI.findOneByUser(user.getId());
        SurveyPlanAuditBO auditBO = this.findByUserPlan(to.getPlanId(), user.getUsername());
        SurveyPlanAudit entity;
        SurveyPlan plan = surveyPlanSer.findById(to.getPlanId());
        if (null == plan)
            throw new SerException("研究计划不能为空");
        if (null == auditBO) {
            entity = BeanTransform.copyProperties(to, SurveyPlanAudit.class, true);
            entity.setAuditor(user.getUsername());
            entity.setAuditTime(LocalDateTime.now());
            if (null != detailUserBO) {
                List<PositionUserDetailBO> positionUserDetailBOSList = detailUserBO.getDetailS();
                if (null != positionUserDetailBOSList) {
                    for (PositionUserDetailBO p : positionUserDetailBOSList) {
                        if (WorkStatus.MAIN.equals(p.getWorkStatus())) {
                            entity.setPosition(p.getPosition());
                        }

                    }
                }
            }
            if (null != userDetail)
                entity.setDepartment(userDetail.getDepartmentName());
            entity.setPlan(plan);
            super.save(entity);
        } else {
            entity = super.findById(auditBO.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setPlan(plan);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
        if (entity.isPass())
            plan.setAudit(AuditType.ALLOWED);
        else
            plan.setAudit(AuditType.DENIED);
        surveyPlanSer.update(plan);
        return this.transformBO(entity);
    }

    @Override
    public SurveyPlanAuditBO findByUserPlan(String plan_id, String auditor) throws SerException {
        SurveyPlanAuditDTO dto = new SurveyPlanAuditDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        dto.getConditions().add(Restrict.eq("auditor", auditor));
        dto.getSorts().add("auditTime=desc");
        List<SurveyPlanAudit> list = super.findByCis(dto);
        if (list.size() > 0)
            return this.transformBO(list.get(0));
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanAuditBO delete(String id) throws SerException {
        SurveyPlanAudit entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyPlanAuditBO> findByPlan(String plan_id) throws SerException {
        SurveyPlanAuditDTO dto = new SurveyPlanAuditDTO();
        dto.getConditions().add(Restrict.eq("plan.id", plan_id));
        dto.getSorts().add("auditTime=desc");
        List<SurveyPlanAudit> list = super.findByCis(dto);
        return this.transformBOList(list);
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