package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.service.BiddingAnswerQuestionsSer;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("biddingAnswerQuestionsApiImpl")
public class BiddingAnswerQuestionsApiImpl implements BiddingAnswerQuestionsAPI {
    @Autowired
    private BiddingAnswerQuestionsSer biddingAnswerQuestionsSer;

    @Override
    public BiddingAnswerQuestionsBO insertBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        return biddingAnswerQuestionsSer.insertBiddingAnswerQuestions(biddingAnswerQuestionsTO);
    }

    @Override
    public BiddingAnswerQuestionsBO editBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        return biddingAnswerQuestionsSer.editBiddingAnswerQuestions(biddingAnswerQuestionsTO);
    }

    @Override
    public void removeBiddingAnswerQuestions(String id) throws SerException {
        biddingAnswerQuestionsSer.remove(id);
    }

    @Override
    public List<BiddingAnswerQuestionsBO> findListBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        return biddingAnswerQuestionsSer.findListBiddingAnswerQuestions(biddingAnswerQuestionsDTO);
    }

    @Override
    public String exportExcel(String projectName) throws SerException {
        return biddingAnswerQuestionsSer.exportExcel(projectName);
    }

    @Override
    public void upload() throws SerException {
        biddingAnswerQuestionsSer.upload();

    }

}