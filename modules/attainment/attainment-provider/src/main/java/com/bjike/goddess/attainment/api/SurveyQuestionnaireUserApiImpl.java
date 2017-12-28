package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireUserBO;
import com.bjike.goddess.attainment.service.SurveyQuestionnaireUserSer;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问卷调查历史记录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyQuestionnaireUserApiImpl")
public class SurveyQuestionnaireUserApiImpl implements SurveyQuestionnaireUserAPI {

    @Autowired
     private SurveyQuestionnaireUserSer surveyQuestionnaireUserSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return surveyQuestionnaireUserSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return surveyQuestionnaireUserSer.guidePermission(guidePermissionTO);
    }
    @Override
    public SurveyQuestionnaireUserBO save(SurveyQuestionnaireUserTO to) throws SerException {
        return surveyQuestionnaireUserSer.save(to);
    }

    @Override
    public SurveyQuestionnaireUserBO delete(String id) throws SerException {
        return surveyQuestionnaireUserSer.delete(id);
    }

    @Override
    public List<SurveyQuestionnaireUserBO> findByActualize(String actualize_id) throws SerException {
        return surveyQuestionnaireUserSer.findByActualize(actualize_id);
    }
}