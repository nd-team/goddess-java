package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireUserBO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireUserTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 问卷调查历史记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyQuestionnaireUserAPI {

    /**
     * 保存
     *
     * @param to 问卷调查历史记录数据传输对象
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireUserBO save(SurveyQuestionnaireUserTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 问卷调查历史记录数据id
     * @return
     * @throws SerException
     */
    default SurveyQuestionnaireUserBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据实施记录查询问卷调查历史记录
     *
     * @param actualize_id 实施记录数据id
     * @return
     * @throws SerException
     */
    default List<SurveyQuestionnaireUserBO> findByActualize(String actualize_id) throws SerException {
        return null;
    }

}