package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研表问题选项业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireOptionSer extends Ser<SurveyQuestionnaireOption, SurveyQuestionnaireOptionDTO> {


    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 添加
     *
     * @param to 调研问题选项传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireOptionBO save(SurveyQuestionnaireOptionTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研问题选项传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireOptionBO update(SurveyQuestionnaireOptionTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研问题选项数据id
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireOptionBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据调研问题查询选项
     *
     * @param questionnaire_id 调研问题id
     * @return
     * @throws SerException
     */
    default List<SurveyQuestionnaireOptionBO> findByQuestion(String questionnaire_id) throws SerException {
        return null;
    }
}