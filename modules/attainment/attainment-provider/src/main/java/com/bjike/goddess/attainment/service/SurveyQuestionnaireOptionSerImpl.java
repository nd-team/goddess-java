package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.QuestionCheckBO;
import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 调研表问题选项业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyQuestionnaireOptionSerImpl extends ServiceImpl<SurveyQuestionnaireOption, SurveyQuestionnaireOptionDTO> implements SurveyQuestionnaireOptionSer {

    @Autowired
    private SurveyQuestionnaireOptionUserSer surveyQuestionnaireOptionUserSer;

    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    private SurveyQuestionnaireOptionBO transformBO(SurveyQuestionnaireOption entity) throws SerException {
        SurveyQuestionnaireOptionBO bo = BeanTransform.copyProperties(entity, SurveyQuestionnaireOptionBO.class);
        bo.setQuestionnaireId(entity.getQuestionnaire().getId());
        bo.setQuestionnaireName(entity.getQuestionnaire().getQuestionnaire());
        return bo;
    }

    private List<SurveyQuestionnaireOptionBO> transformBOList(List<SurveyQuestionnaireOption> list) throws SerException {
        List<SurveyQuestionnaireOptionBO> bos = new ArrayList<>(list.size());
        for (SurveyQuestionnaireOption entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireOptionBO save(SurveyQuestionnaireOptionTO to) throws SerException {
        SurveyQuestionnaireOption entity = BeanTransform.copyProperties(to, SurveyQuestionnaireOption.class);
        entity.setBallot(0);
        entity.setQuestionnaire(surveyQuestionnaireSer.findById(to.getQuestionnaireId()));
        if (null == entity.getQuestionnaire())
            throw new SerException("问题对象不存在,无法保存");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireOptionBO update(SurveyQuestionnaireOptionTO to) throws SerException {
        SurveyQuestionnaireOption entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setQuestionnaire(surveyQuestionnaireSer.findById(to.getQuestionnaireId()));
        if (null == entity.getQuestionnaire())
            throw new SerException("问题对象不存在,无法保存");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireOptionBO delete(String id) throws SerException {
        SurveyQuestionnaireOption entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if (surveyQuestionnaireOptionUserSer.findByOption(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyQuestionnaireOptionBO> findByQuestion(String questionnaire_id) throws SerException {
        SurveyQuestionnaireOptionDTO dto = new SurveyQuestionnaireOptionDTO();
        dto.getConditions().add(Restrict.eq("questionnaire.id", questionnaire_id));
        List<SurveyQuestionnaireOption> list = super.findByCis(dto);
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

    @Override
    public List<QuestionCheckBO> findQuesCheck() throws SerException {
        List<SurveyQuestionnaireOption> surveyQuestionnaireOptions = super.findAll();
        return BeanTransform.copyProperties(surveyQuestionnaireOptions,QuestionCheckBO.class);
    }
}