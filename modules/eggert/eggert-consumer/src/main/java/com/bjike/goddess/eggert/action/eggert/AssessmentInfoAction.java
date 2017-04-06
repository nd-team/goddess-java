package com.bjike.goddess.eggert.action.eggert;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.api.AssessmentInfoAPI;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.bo.AssessmentInfoBO;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.bo.ResearchSettingsInfoBO;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.dto.AssessmentInfoDTO;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.to.AnalysisTO;
import com.bjike.goddess.eggert.to.AssessmentInfoTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import com.bjike.goddess.eggert.vo.AnalysisVO;
import com.bjike.goddess.eggert.vo.AssessmentInfoVO;
import com.bjike.goddess.eggert.vo.ResearchSettingsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评估信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:19 ]
 * @Description: [ 评估信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("eggert/assessmentinfo")
public class AssessmentInfoAction {
    @Autowired
    private AssessmentInfoAPI assessmentInfoAPI;

    /**
     * 查看评估详情
     *
     * @param assessmentInfoDTO 查看评估详情dto
     * @return class AssessmentInfoVO
     * @des 获取所有查看评估详情
     * @version v1
     */
    @GetMapping("v1/listAssessmentInfo")
    public Result findListAssessmentInfo(AssessmentInfoDTO assessmentInfoDTO) throws ActException {
        try {
            List<AssessmentInfoVO> assessmentInfoVOS = BeanTransform.copyProperties
                    (assessmentInfoAPI.findListAssessmentInfo(assessmentInfoDTO), AssessmentInfoVO.class);
            return ActResult.initialize(assessmentInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 接受评估
     *
     * @param examQuestionsTO 接受评估数据to
     * @des 接受评估
     * @version v1
     */
    @PostMapping("v1/evaluated")
    public Result evaluatedExamQuestions(ExamQuestionsTO examQuestionsTO) throws ActException {
        try {
            ExamQuestionsBO examQuestionsBO = assessmentInfoAPI.evaluatedExamQuestions(examQuestionsTO);
            return ActResult.initialize(examQuestionsBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 制作评估结果
     *
     * @param analysisTO 制作评估结果数据to
     * @return class AnalysisVO
     * @des 制作评估结果
     * @version v1
     */
    @PostMapping("v1/make")
    public Result makeAnalysis(AnalysisTO analysisTO) throws ActException {
        try {
            AnalysisBO analysisBO = assessmentInfoAPI.makeAnalysis(analysisTO);
            return ActResult.initialize(analysisBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看评估结果
     *
     * @param analysisDTO 查看评估结果数据dto
     * @return class AnalysisVO
     * @des 查看评估结果
     * @version v1
     */
    @PostMapping("v1/get")
    public Result getAnalysis(AnalysisDTO analysisDTO) throws ActException {
        try {
            List<AnalysisVO> analysisVOS = BeanTransform.copyProperties
                    (assessmentInfoAPI.getAnalysis(analysisDTO), AnalysisVO.class);
            return ActResult.initialize(analysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}