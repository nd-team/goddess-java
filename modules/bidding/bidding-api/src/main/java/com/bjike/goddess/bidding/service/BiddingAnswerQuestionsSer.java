package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;

import java.util.List;

/**
 * 投标答疑问题记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.901 ]
 * @Description: [ 投标答疑问题记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingAnswerQuestionsSer extends Ser<BiddingAnswerQuestions, BiddingAnswerQuestionsDTO> {
    /**
     * 投标答疑问题记录列表总条数
     */
    default Long countBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        return null;
    }
    /**
     * 一个投标答疑问题记录
     *
     * @return class BiddingAnswerQuestionsBO
     */
    default BiddingAnswerQuestionsBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 投标答疑问题记录
     *
     * @param biddingAnswerQuestionsDTO 投标答疑问题记录dto
     * @return class BiddingAnswerQuestionsBO
     * @throws SerException
     */
    default List<BiddingAnswerQuestionsBO> findListBiddingAnswerQuestions(BiddingAnswerQuestionsDTO biddingAnswerQuestionsDTO) throws SerException {
        return null;
    }

    /**
     * 添加投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录数据to
     * @return class BiddingAnswerQuestionsBO
     * @throws SerException
     */
    default BiddingAnswerQuestionsBO insertBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        return null;
    }

    /**
     * 编辑投标答疑问题记录
     *
     * @param biddingAnswerQuestionsTO 投标答疑问题记录数据to
     * @return class BiddingAnswerQuestionsBO
     * @throws SerException
     */
    default BiddingAnswerQuestionsBO editBiddingAnswerQuestions(BiddingAnswerQuestionsTO biddingAnswerQuestionsTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除投标答疑问题记录
     *
     * @param id
     * @throws SerException
     */
    default void removeBiddingAnswerQuestions(String id) throws SerException {

    }

    /**
     * 导出
     *
     * @param projectName
     * @throws SerException
     */
    default String exportExcel(String projectName) throws SerException {
        return null;
    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

}