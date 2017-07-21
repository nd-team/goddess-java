package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaire;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研表问题业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireSer extends Ser<SurveyQuestionnaire, SurveyQuestionnaireDTO> {


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
     * 保存
     *
     * @param to 调研问题数据传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireBO save(SurveyQuestionnaireTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研问题数据传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireBO update(SurveyQuestionnaireTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研问题数据id
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据调研实施查询调研问题
     *
     * @param actualize_id 调研实施id
     * @return
     * @throws SerException
     */
    default List<SurveyQuestionnaireBO> findByActualize(String actualize_id) throws SerException {
        return null;
    }
}