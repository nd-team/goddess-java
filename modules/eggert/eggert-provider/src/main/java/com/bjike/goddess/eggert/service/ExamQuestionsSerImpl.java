package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.dto.ExamQuestionsDTO;
import com.bjike.goddess.eggert.entity.ExamQuestions;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 设置考题业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:09 ]
 * @Description: [ 设置考题业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "eggertSerCache")
@Service
public class ExamQuestionsSerImpl extends ServiceImpl<ExamQuestions, ExamQuestionsDTO> implements ExamQuestionsSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExamQuestionsBO evaluatedExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        ExamQuestions examQuestions = BeanTransform.copyProperties(examQuestionsTO, ExamQuestions.class);
        examQuestions.setModifyTime(LocalDateTime.now());
        super.save(examQuestions);
        return BeanTransform.copyProperties(examQuestions, ExamQuestionsBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ExamQuestionsBO setExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        ExamQuestions examQuestions = BeanTransform.copyProperties(examQuestionsTO,ExamQuestions.class);
        examQuestions.setModifyTime(LocalDateTime.now());
        if(examQuestions.getScoring()){
              examQuestions.setScoring(true);
        }else{
            super.save(examQuestions);
        }

        return BeanTransform.copyProperties(examQuestions,ExamQuestionsBO.class);
    }
}