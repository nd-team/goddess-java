package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.*;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.entity.SurveyActualize;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.entity.SurveyPlan;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;
import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.to.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 调研计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyPlanSerImpl extends ServiceImpl<SurveyPlan, SurveyPlanDTO> implements SurveyPlanSer {

    @Autowired
    private SurveyDemandSer demandSer;
    @Autowired
    private SurveyActualizeSer surveyActualizeSer;
    @Autowired
    private SurveyAnalyseSer surveyAnalyseSer;
    @Autowired
    private SurveyPlanAuditSer surveyPlanAuditSer;
    @Autowired
    private SurveyQuestionnaireOptionSer surveyQuestionnaireOptionSer;
    @Autowired
    private SurveyQuestionnaireOptionUserSer surveyQuestionnaireOptionUserSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;

    private SurveyPlanBO transformBO(SurveyPlan entity) throws SerException {
        SurveyPlanBO bo = BeanTransform.copyProperties(entity, SurveyPlanBO.class);
        SurveyDemand demand = entity.getDemand();
        bo.setDemandId(demand.getId());
        bo.setDemandName(demand.getDemand().getType());
        bo.setPurpose(demand.getPurpose());
        bo.setScope(demand.getScope());
        bo.setTypeName(demand.getType().getType());
        bo.setUsername(demand.getUsername());
        bo.isRegular(demand.getType().isRegular());
        bo.setTheme(demand.getTheme());
        bo.setPurpose(demand.getPurpose());
        bo.setScope(demand.getScope());
        bo.setScopeName(demand.getScopeName());
        return bo;
    }

    private List<SurveyPlanBO> transformBOList(List<SurveyPlan> list) throws SerException {
        List<SurveyPlanBO> bos = new ArrayList<>(list.size());
        for (SurveyPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO save(SurveyPlanTO to) throws SerException {
        SurveyPlan entity = BeanTransform.copyProperties(to, SurveyPlan.class, true);
        entity.setDemand(demandSer.findById(to.getDemandId()));
        if (null == entity.getDemand())
            throw new SerException("选择的调研需求不存在,无法保存");
        entity.setAudit(AuditType.NONE);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO update(SurveyPlanTO to) throws SerException {
        SurveyPlan entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setDemand(demandSer.findById(to.getDemandId()));
        if (null == entity.getDemand())
            throw new SerException("选择的调研需求不存在,无法保存");
        entity.setRemark(to.getRemark());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyPlanBO delete(String id) throws SerException {
        SurveyPlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        SurveyActualizeDTO dto = new SurveyActualizeDTO();
        dto.getConditions().add(Restrict.eq("plan.id", entity.getId()));
        if (surveyActualizeSer.findByCis(dto).size() != 0 || surveyAnalyseSer.findByPlan(entity.getId()).size() != 0 || surveyPlanAuditSer.findByPlan(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyPlanBO> findByDemand(String demand_id) throws SerException {
        SurveyPlanDTO dto = new SurveyPlanDTO();
        dto.getConditions().add(Restrict.eq("demand.id", demand_id));
        List<SurveyPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public SurveyPlanBO findBOById(String id) throws SerException {
        SurveyPlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyPlanBO> maps(SurveyPlanDTO dto) throws SerException {
        dto.getSorts().add("startTime=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        SurveyPlanDTO dto = new SurveyPlanDTO();
        return super.count(dto);
    }

    @Override
    public List<SurPlanBO> getSurveyPlan() throws SerException {
        List<SurveyPlan> surveyPlanList = super.findAll();
        List<SurPlanBO> list = new ArrayList<>();
        if (null != surveyPlanList && surveyPlanList.size() > 0) {
            for (SurveyPlan entity : surveyPlanList) {
                SurPlanBO surPlanbo = new SurPlanBO();
//                surPlanbo.setTypeName(entity.getId());
                surPlanbo.setSerialNumber(entity.getSerialNumber());
                list.add(surPlanbo);
            }
        }
        return list;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<SurveyActualizesBO> questionnaire(SurveyActualizesTO to) throws SerException {
        List<SurveyActualizesBO> list = new ArrayList<>();
        if (null != to) {
            SurveyActualizeTO surveyActualizeTO = BeanTransform.copyProperties(to, SurveyActualizeTO.class, "surveyQuestionnairesTOs");
            SurveyActualizeBO surveyActualizeBO = surveyActualizeSer.save(surveyActualizeTO);
            SurveyActualize surveyActualize = BeanTransform.copyProperties(surveyActualizeBO, SurveyActualize.class, true);
            String actualizeId = surveyActualize.getId();

            List<SurveyQuestionnairesTO> surveyQuestionnairesTOs = to.getSurveyQuestionnairesTOs();
            if (null != surveyQuestionnairesTOs && surveyQuestionnairesTOs.size() > 0) {
                for (SurveyQuestionnairesTO surveyQuestionnairesTO : surveyQuestionnairesTOs) {
                    SurveyQuestionnaireTO surveyQuestionnaireTO = BeanTransform.copyProperties(surveyQuestionnairesTO, SurveyQuestionnaireTO.class, "surveyQuestionnaireOptionTOs");
                    surveyQuestionnaireTO.setActualizeId(actualizeId);
                    SurveyQuestionnaireBO surveyQuestionnaireBO = surveyQuestionnaireSer.save(surveyQuestionnaireTO);
                    SurveyQuestionnaire surveyQuestionnaire = BeanTransform.copyProperties(surveyQuestionnaireBO, SurveyQuestionnaire.class);
                    List<SurveyQuestionnaireOptionTO> surveyQuestionnaireOptionTOs = surveyQuestionnairesTO.getSurveyQuestionnaireOptionTOs();
                    if (null != surveyQuestionnaireOptionTOs && surveyQuestionnaireOptionTOs.size() > 0) {
                        for (SurveyQuestionnaireOptionTO surveyQuestionnaireOptionTO : surveyQuestionnaireOptionTOs) {
                            String questionnaireId = surveyQuestionnaire.getId();
                            surveyQuestionnaireOptionTO.setQuestionnaireId(questionnaireId);
                            if (StringUtils.isNotBlank(surveyQuestionnaireOptionTO.getContent())) {
                                surveyQuestionnaireOptionSer.save(surveyQuestionnaireOptionTO);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<SurveyQuestionnairesBO> getQuestionnaire(String id) throws SerException {
        List<SurveyQuestionnaireBO> surveyQuestionnaireBOs = surveyQuestionnaireSer.findByActualize(id);
        if (null != surveyQuestionnaireBOs && surveyQuestionnaireBOs.size() > 0) {
            List<SurveyQuestionnairesBO> list = new ArrayList<>();
            List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOList = new ArrayList<>();
            if (null != surveyQuestionnaireBOs && surveyQuestionnaireBOs.size() > 0) {
                for (SurveyQuestionnaireBO surveyQuestionnaireBO : surveyQuestionnaireBOs) {
                    SurveyQuestionnairesBO surveyQuestionnairesBO = new SurveyQuestionnairesBO();
                    surveyQuestionnairesBO.setActualizeId(surveyQuestionnaireBO.getActualizeId());
                    surveyQuestionnairesBO.setMultiple(surveyQuestionnaireBO.getMultiple());
                    surveyQuestionnairesBO.setNum(surveyQuestionnaireBO.getNum());
                    surveyQuestionnairesBO.setQuestionnaire(surveyQuestionnaireBO.getQuestionnaire());
                    surveyQuestionnairesBO.setId(surveyQuestionnaireBO.getId());
                    //根据问题id获取问题选项
                    List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOs = surveyQuestionnaireOptionSer.findByQuestion(surveyQuestionnaireBO.getId());
                    if (null != surveyQuestionnaireOptionBOs && surveyQuestionnaireOptionBOs.size() > 0) {
                        surveyQuestionnairesBO.setSurveyQuestionnaireOptionBOs(surveyQuestionnaireOptionBOs);
                    }
                    list.add(surveyQuestionnairesBO);
                }
            }
            return list;
        }
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<SurveyQuestionnaireOptionUsersBO> editQuestionnaire(SurveyQuestionnaireOptionUsersTO to) throws SerException {
        //前端传过来选项的id,表名,员工的list(选项id不能为空)
        List<SurveyQuestionnaireOptionUserTO> surveyQuestionnaireOptionUserTOs = to.getSurveyQuestionnaireOptionUserTOs();
        if (null != surveyQuestionnaireOptionUserTOs && surveyQuestionnaireOptionUserTOs.size() > 0) {
            for (SurveyQuestionnaireOptionUserTO to1 : surveyQuestionnaireOptionUserTOs) {
                String userToken = RpcTransmit.getUserToken();
                RpcTransmit.transmitUserToken(userToken);
                UserBO user = userAPI.currentUser();
                to1.setUser(user.getUsername());
                RpcTransmit.transmitUserToken(userToken);
                surveyQuestionnaireOptionUserSer.save(to1);
            }
        }
        return null;
    }

    @Override
    public List<SurveyActualizesBO> edit(SurveyActualizesTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("调研实施记录id不能为空");
        }
        //根据调研实施记录id查看问卷
        List<SurveyQuestionnairesBO> surveyQuestionnairesBOList = this.getQuestionnaire(to.getId());
        if (null != surveyQuestionnairesBOList && surveyQuestionnairesBOList.size() > 0) {
            for (SurveyQuestionnairesBO surveyQuestionnairesBO : surveyQuestionnairesBOList) {
                List<SurveyQuestionnairesTO> surveyQuestionnairesTOList = to.getSurveyQuestionnairesTOs();
                if (null != surveyQuestionnairesTOList && surveyQuestionnairesTOList.size() > 0) {
                    for (SurveyQuestionnairesTO surveyQuestionnairesTO : surveyQuestionnairesTOList) {
                        if (StringUtils.isBlank(surveyQuestionnairesTO.getId())) {
                            throw new SerException("调研表id不能为空");
                        }
                        //调研表id,问题id一样,修改问题
                        if (surveyQuestionnairesBO.getId().equals(surveyQuestionnairesTO.getId())
//                                && surveyQuestionnairesBO.getActualizeId().equals(surveyQuestionnairesTO.getActualizeId())
                                ) {
                            SurveyQuestionnaireTO surveyQuestionnaireTO = BeanTransform.copyProperties(surveyQuestionnairesTO, SurveyQuestionnaireTO.class, "surveyQuestionnaireOptionTOs");
                            surveyQuestionnaireTO.setActualizeId(to.getId());
                            surveyQuestionnaireSer.update(surveyQuestionnaireTO);
                            //问题选项id一样,修改问题选项
                            List<SurveyQuestionnaireOptionTO> surveyQuestionnaireOptionTOList = surveyQuestionnairesTO.getSurveyQuestionnaireOptionTOs();
                            if (null != surveyQuestionnaireOptionTOList && surveyQuestionnaireOptionTOList.size() > 0) {
                                for (SurveyQuestionnaireOptionTO surveyQuestionnaireOptionTO : surveyQuestionnaireOptionTOList) {
                                    List<SurveyQuestionnaireOptionBO> surveyQuestionnaireOptionBOList = surveyQuestionnairesBO.getSurveyQuestionnaireOptionBOs();
                                    if (null != surveyQuestionnaireOptionBOList && surveyQuestionnaireOptionBOList.size() > 0) {
                                        for (SurveyQuestionnaireOptionBO surveyQuestionnaireOptionBO : surveyQuestionnaireOptionBOList) {
                                            if (StringUtils.isBlank(surveyQuestionnaireOptionTO.getId())) {
                                                throw new SerException("问题选型id不能为空");
                                            }
                                            if (surveyQuestionnaireOptionBO.getId().equals(surveyQuestionnaireOptionTO.getId())) {
                                                surveyQuestionnaireOptionTO.setQuestionnaireId(surveyQuestionnairesTO.getId());
                                                surveyQuestionnaireOptionSer.update(surveyQuestionnaireOptionTO);
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } else {
            throw new SerException("数据不存在");
        }
        return null;
//
//        SurveyPlan entity = super.findById(to.getId());
//        if (null == entity)
//            throw new SerException("数据不存在");
//        BeanTransform.copyProperties(to, entity, true);
//        entity.setModifyTime(LocalDateTime.now());
//        entity.setDemand(demandSer.findById(to.getDemandId()));
//        if (null == entity.getDemand())
//            throw new SerException("选择的调研需求不存在,无法保存");
//        super.update(entity);
//        return this.transformBO(entity);

    }


    @Override
    public List<String> getName() throws SerException {
        List<SurveyPlan> list = super.findAll();
        return null;
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