package com.bjike.goddess.eggert.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.bo.AssessmentInfoBO;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.dto.AssessmentInfoDTO;
import com.bjike.goddess.eggert.service.AssessmentInfoSer;
import com.bjike.goddess.eggert.to.AnalysisTO;
import com.bjike.goddess.eggert.to.AssessmentInfoTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评估信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:19 ]
 * @Description: [ 评估信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assessmentInfoApiImpl")
public class AssessmentInfoApiImpl implements AssessmentInfoAPI {
    @Autowired
    private AssessmentInfoSer assessmentInfoSer;

    @Override
    public List<AssessmentInfoBO> findListAssessmentInfo(AssessmentInfoDTO assessmentInfoDTO) throws SerException {
        return assessmentInfoSer.findListAssessmentInfo(assessmentInfoDTO);
    }

    @Override
    public ExamQuestionsBO evaluatedExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        return assessmentInfoSer.evaluatedExamQuestions(examQuestionsTO);
    }

    @Override
    public AnalysisBO makeAnalysis(AnalysisTO analysisTO) throws SerException {
        return assessmentInfoSer.makeAnalysis(analysisTO);
    }

    @Override
    public List<AnalysisBO> getAnalysis(AnalysisDTO analysisDTO) throws SerException {
        return assessmentInfoSer.getAnalysis(analysisDTO);
    }


}