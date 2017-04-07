package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionUserBO;
import com.bjike.goddess.attainment.service.SurveyQuestionnaireOptionUserSer;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUserTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问卷填写信息表业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyQuestionnaireOptionUserApiImpl")
public class SurveyQuestionnaireOptionUserApiImpl implements SurveyQuestionnaireOptionUserAPI {

    @Autowired
    private SurveyQuestionnaireOptionUserSer surveyQuestionnaireOptionUserSer;

    @Override
    public SurveyQuestionnaireOptionUserBO save(SurveyQuestionnaireOptionUserTO to) throws SerException {
        return surveyQuestionnaireOptionUserSer.save(to);
    }

    @Override
    public SurveyQuestionnaireOptionUserBO delete(String id) throws SerException {
        return surveyQuestionnaireOptionUserSer.delete(id);
    }

    @Override
    public List<SurveyQuestionnaireOptionUserBO> findByOption(String option_id) throws SerException {
        return surveyQuestionnaireOptionUserSer.findByOption(option_id);
    }
}