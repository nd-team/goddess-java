package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireBO;
import com.bjike.goddess.attainment.service.SurveyQuestionnaireSer;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研表问题业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyQuestionnaireApiImpl")
public class SurveyQuestionnaireApiImpl implements SurveyQuestionnaireAPI {

    @Autowired
    private SurveyQuestionnaireSer surveyQuestionnaireSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return surveyQuestionnaireSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return surveyQuestionnaireSer.guidePermission(guidePermissionTO);
    }


    @Override
    public SurveyQuestionnaireBO save(SurveyQuestionnaireTO to) throws SerException {
        return surveyQuestionnaireSer.save(to);
    }

    @Override
    public SurveyQuestionnaireBO update(SurveyQuestionnaireTO to) throws SerException {
        return surveyQuestionnaireSer.update(to);
    }

    @Override
    public SurveyQuestionnaireBO delete(String id) throws SerException {
        return surveyQuestionnaireSer.delete(id);
    }

    @Override
    public List<SurveyQuestionnaireBO> findByActualize(String actualize_id) throws SerException {
        return surveyQuestionnaireSer.findByActualize(actualize_id);
    }
}