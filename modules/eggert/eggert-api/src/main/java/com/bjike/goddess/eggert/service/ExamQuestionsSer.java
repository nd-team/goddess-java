package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.entity.ExamQuestions;
import com.bjike.goddess.eggert.dto.ExamQuestionsDTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;

/**
 * 设置考题业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:09 ]
 * @Description: [ 设置考题业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExamQuestionsSer extends Ser<ExamQuestions, ExamQuestionsDTO> {
    /**
     * 接受评估
     *
     * @param examQuestionsTO 接受评估数据to
     * @return class examQuestionsBO
     * @throws SerException
     */
    default ExamQuestionsBO evaluatedExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        return null;
    }
    /**
     * 设置考题
     *
     * @param examQuestionsTO 设置考题数据to
     * @return class ExamQuestionsBO
     * @throws SerException
     */
    default ExamQuestionsBO setExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        return null;
    }

}