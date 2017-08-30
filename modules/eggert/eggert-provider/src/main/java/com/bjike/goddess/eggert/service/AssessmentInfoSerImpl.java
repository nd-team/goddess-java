package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.bo.AssessmentInfoBO;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.dto.AssessmentInfoDTO;
import com.bjike.goddess.eggert.entity.Analysis;
import com.bjike.goddess.eggert.entity.AssessmentInfo;
import com.bjike.goddess.eggert.entity.ExamQuestions;
import com.bjike.goddess.eggert.to.AnalysisTO;
import com.bjike.goddess.eggert.to.AssessmentInfoTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评估信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:19 ]
 * @Description: [ 评估信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class AssessmentInfoSerImpl extends ServiceImpl<AssessmentInfo, AssessmentInfoDTO> implements AssessmentInfoSer {

    @Autowired
    private ExamQuestionsSer examQuestionsAPI;
    @Autowired
    private AnalysisSer analysisAPI;

    @Cacheable
    @Override
    public List<AssessmentInfoBO> findListAssessmentInfo(AssessmentInfoDTO assessmentInfoDTO) throws SerException {
        List<AssessmentInfo> assessmentInfos = super.findByCis(assessmentInfoDTO, true);
        return BeanTransform.copyProperties(assessmentInfos, AssessmentInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExamQuestionsBO evaluatedExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        ExamQuestions examQuestions = BeanTransform.copyProperties(examQuestionsTO, ExamQuestions.class);
        examQuestionsAPI.save(examQuestions);
        return BeanTransform.copyProperties(examQuestions, ExamQuestionsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnalysisBO makeAnalysis(AnalysisTO analysisTO) throws SerException {
        Analysis analysis = BeanTransform.copyProperties(analysisTO, Analysis.class);
        analysisAPI.save(analysis);
        return BeanTransform.copyProperties(analysis, AnalysisBO.class);
    }

    @Cacheable
    @Override
    public List<AnalysisBO> getAnalysis(AnalysisDTO analysisDTO) throws SerException {
        List<Analysis> analyses = analysisAPI.findByCis(analysisDTO, true);
        return BeanTransform.copyProperties(analyses, AssessmentInfoBO.class);
    }

}