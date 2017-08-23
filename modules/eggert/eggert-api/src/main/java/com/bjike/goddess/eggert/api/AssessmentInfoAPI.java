package com.bjike.goddess.eggert.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.bo.AssessmentInfoBO;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.dto.AssessmentInfoDTO;
import com.bjike.goddess.eggert.to.AnalysisTO;
import com.bjike.goddess.eggert.to.AssessmentInfoTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;

import java.util.List;

/**
 * 评估信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:19 ]
 * @Description: [ 评估信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssessmentInfoAPI {
    /**
     * 查看评估详情
     *
     * @param assessmentInfoDTO 查看评估详情数据dto
     * @return class assessmentInfoBO
     * @throws SerException
     */
    default List<AssessmentInfoBO> findListAssessmentInfo(AssessmentInfoDTO assessmentInfoDTO) throws SerException {
        return null;
    }
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
     * 制作评估结果
     *
     * @param analysisTO 制作评估结果数据to
     * @return class analysisBO
     * @throws SerException
     */
    default AnalysisBO makeAnalysis(AnalysisTO analysisTO) throws SerException {
        return null;
    }
    /**
     * 查看评估结果
     *
     * @param analysisDTO 查看评估结果数据dto
     * @return class analysisBO
     * @throws SerException
     */
    default List<AnalysisBO> getAnalysis(AnalysisDTO analysisDTO) throws SerException {
        return null;
    }



}