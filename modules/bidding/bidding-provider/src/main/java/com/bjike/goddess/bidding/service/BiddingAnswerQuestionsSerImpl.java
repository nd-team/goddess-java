package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投标答疑问题记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.913 ]
 * @Description: [ 投标答疑问题记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingAnswerQuestionsSerCache")
@Service
public class BiddingAnswerQuestionsSerImpl extends ServiceImpl<BiddingAnswerQuestions, BiddingAnswerQuestionsDTO> implements BiddingAnswerQuestionsSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO insertBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        BiddingAnswerQuestions biddingAnswerQuestions = BeanTransform.copyProperties(biddingAnswerQuestionsTO, BiddingAnswerQuestions.class, true);
        try {
            biddingAnswerQuestions.setCreateTime(LocalDateTime.now());
            super.save(biddingAnswerQuestions);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(biddingAnswerQuestions, BiddingAnswerQuestionsBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingAnswerQuestionsBO editBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        BiddingAnswerQuestions biddingAnswerQuestions = BeanTransform.copyProperties(biddingAnswerQuestionsTO, BiddingAnswerQuestions.class, true);
        try {
            biddingAnswerQuestions.setModifyTime(LocalDateTime.now());
            super.update(biddingAnswerQuestions);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(biddingAnswerQuestions, BiddingAnswerQuestionsBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingAnswerQuestions(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<BiddingAnswerQuestionsBO> findListBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        List<BiddingAnswerQuestions> biddingAnswerQuestionss = super.findByCis(biddingAnswerQuestionsDTO,true);
        return BeanTransform.copyProperties(biddingAnswerQuestionss,BiddingAnswerQuestionsBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String projectName) throws SerException {
        //TODO: xiazhili 2017-03-17 未做导出
        return null;
    }

    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-17 未做上传
        return;

    }

}