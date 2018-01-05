package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.bo.AssessmentInfoBO;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.entity.Analysis;
import com.bjike.goddess.eggert.entity.AssessmentInfo;
import com.bjike.goddess.eggert.to.AnalysisTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分析记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:23 ]
 * @Description: [ 分析记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class AnalysisSerImpl extends ServiceImpl<Analysis, AnalysisDTO> implements AnalysisSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnalysisBO makeAnalysis(AnalysisTO analysisTO) throws SerException {
        Analysis analysis = BeanTransform.copyProperties(analysisTO, Analysis.class);
        analysis.setModifyTime(LocalDateTime.now());
        super.save(analysis);
        return BeanTransform.copyProperties(analysis, AnalysisBO.class);
    }

    @Cacheable
    @Override
    public List<AnalysisBO> getAnalysis(AnalysisDTO analysisDTO) throws SerException {
        List<Analysis> analyses = super.findByCis(analysisDTO, true);
        return BeanTransform.copyProperties(analyses, AssessmentInfoBO.class);
    }
}