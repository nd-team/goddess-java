package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionUserBO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUserTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 问卷填写信息表业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireOptionUserAPI {

    /**
     * 保存
     *
     * @param to 问卷填写信息传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireOptionUserBO save(SurveyQuestionnaireOptionUserTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 问卷填写信息数据id
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireOptionUserBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据选项查询问卷填写记录
     *
     * @param option_id 调研问卷选项数据id
     * @return
     * @throws SerException
     */
    default List<SurveyQuestionnaireOptionUserBO> findByOption(String option_id) throws SerException {
        return null;
    }

}