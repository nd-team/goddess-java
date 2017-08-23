package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.dto.AttainmentTypeDTO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.entity.AttainmentType;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.excel.SonPermissionObject;
import com.bjike.goddess.attainment.to.AttainmentTypeTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 调研类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:49 ]
 * @Description: [ 调研类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class AttainmentTypeSerImpl extends ServiceImpl<AttainmentType, AttainmentTypeDTO> implements AttainmentTypeSer {

    @Autowired
    private SurveyDemandSer surveyDemandSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private AttainmentWaySer attainmentWaySer;
    @Autowired
    private SkillAnalyseSer skillAnalyseSer;
    @Autowired
    private DemandTypeSer demandTypeSer;
    @Autowired
    private SurveyActualizeSer surveyActualizeSer;
    @Autowired
    private SurveyAnalyseSer surveyAnalyseSer;

    @Autowired
    private SurveyPlanAuditSer surveyPlanAuditSer;
    @Autowired
    private SurveyPlanSer surveyPlanSer;
    @Autowired
    private SurveyQuestionnaireOptionSer surveyQuestionnaireOptionSer;
    @Autowired
    private SurveyQuestionnaireOptionUserSer surveyQuestionnaireOptionUserSer;
    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;
    @Autowired
    private SurveyQuestionnaireUserSer surveyQuestionnaireUserSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentTypeBO save(AttainmentTypeTO to) throws SerException {
        AttainmentType entity = BeanTransform.copyProperties(to, AttainmentType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentTypeBO update(AttainmentTypeTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                AttainmentType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentTypeBO delete(String id) throws SerException {
        AttainmentType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        SurveyDemandDTO dto = new SurveyDemandDTO();
        dto.getConditions().add(Restrict.eq("type.id", entity.getId()));
        if (surveyDemandSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentTypeBO congeal(String id) throws SerException {
        AttainmentType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.CONGEAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AttainmentTypeBO thaw(String id) throws SerException {
        AttainmentType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.THAW);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
    }

    @Override
    public List<AttainmentTypeBO> findThaw() throws SerException {
        AttainmentTypeDTO dto = new AttainmentTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<AttainmentType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AttainmentTypeBO.class);
    }

    @Override
    public List<AttainmentTypeBO> findRegular(Boolean regular) throws SerException {
        AttainmentTypeDTO dto = new AttainmentTypeDTO();
        dto.getConditions().add(Restrict.eq("regular", !regular));
        List<AttainmentType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AttainmentTypeBO.class);
    }

    @Override
    public List<AttainmentTypeBO> maps(AttainmentTypeDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), AttainmentTypeBO.class);
    }

    @Override
    public AttainmentTypeBO getById(String id) throws SerException {
        AttainmentType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        AttainmentTypeDTO dto = new AttainmentTypeDTO();
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("attainmenttype");
        obj.setDescribesion("调研类型");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = attainmentWaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("attainmentway");
        obj.setDescribesion("调研方式");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = demandTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("demandtype");
        obj.setDescribesion("调研需求类型");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate1 = skillAnalyseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("skillanalyse");
        obj.setDescribesion("技能分析表");
        if (flagSeeCate1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate2 = surveyActualizeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyactualize");
        obj.setDescribesion("调研实施记录");
        if (flagSeeCate2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate3 = surveyAnalyseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyanalyse");
        obj.setDescribesion("调研分析");
        if (flagSeeCate3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate4 = surveyDemandSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveydemand");
        obj.setDescribesion("调研需求");
        if (flagSeeCate4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate5 = surveyPlanAuditSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyplanaudit");
        obj.setDescribesion("调研计划审核记录");
        if (flagSeeCate5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate6 = surveyPlanSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyplan");
        obj.setDescribesion("调研计划");
        if (flagSeeCate6) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate7 = surveyQuestionnaireOptionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyquestionnaireoption");
        obj.setDescribesion("调研表问题选项");
        if (flagSeeCate7) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate8 = surveyQuestionnaireOptionUserSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyquestionnaireoptionuserser");
        obj.setDescribesion("问卷填写信息表");
        if (flagSeeCate8) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate9 = surveyQuestionnaireSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyquestionnaire");
        obj.setDescribesion("调研表问题");
        if (flagSeeCate9) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCatesurvey = surveyQuestionnaireUserSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("surveyquestionnaireuser");
        obj.setDescribesion("问卷调查历史记录");
        if (flagSeeCatesurvey) {
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
}