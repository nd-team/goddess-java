package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.bo.ResearchSettingsInfoBO;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.entity.ExamQuestions;
import com.bjike.goddess.eggert.entity.ResearchSettingsInfo;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import com.bjike.goddess.eggert.to.ResearchSettingsInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研设置信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class ResearchSettingsInfoSerImpl extends ServiceImpl<ResearchSettingsInfo, ResearchSettingsInfoDTO> implements ResearchSettingsInfoSer {
    @Autowired
    private ExamQuestionsSer examQuestionsAPI;

    @Cacheable
    @Override
    public List<ResearchSettingsInfoBO> findListResearchSettingsInfo(ResearchSettingsInfoDTO researchSettingsInfoDTO) throws SerException {
        List<ResearchSettingsInfo> researchSettingsInfos = super.findByCis(researchSettingsInfoDTO, true);
        return BeanTransform.copyProperties(researchSettingsInfos, ResearchSettingsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResearchSettingsInfoBO insertResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        ResearchSettingsInfo researchSettingsInfo = BeanTransform.copyProperties(researchSettingsInfoTO, ResearchSettingsInfo.class);
        researchSettingsInfo.setCreateTime(LocalDateTime.now());
        super.save(researchSettingsInfo);
        return BeanTransform.copyProperties(researchSettingsInfo, ResearchSettingsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResearchSettingsInfoBO editResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        if (!StringUtils.isEmpty(researchSettingsInfoTO.getId())) {
            ResearchSettingsInfo researchSettingsInfo = super.findById(researchSettingsInfoTO.getId());
            BeanTransform.copyProperties(researchSettingsInfoTO, researchSettingsInfo, true);
            researchSettingsInfo.setModifyTime(LocalDateTime.now());
            super.update(researchSettingsInfo);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(researchSettingsInfoTO, ResearchSettingsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeResearchSettingsInfo(String id) throws SerException {
        super.remove(id);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExamQuestionsBO setExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        ExamQuestions examQuestions = BeanTransform.copyProperties(examQuestionsTO, ExamQuestions.class);
        examQuestions.setModifyTime(LocalDateTime.now());
        examQuestionsAPI.setExamQuestions(examQuestionsTO);
        return BeanTransform.copyProperties(examQuestions,ExamQuestionsBO.class);
    }
}