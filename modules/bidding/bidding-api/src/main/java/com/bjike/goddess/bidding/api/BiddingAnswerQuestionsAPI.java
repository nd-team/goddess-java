package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingAnswerQuestionsBO;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;
import com.bjike.goddess.bidding.to.BiddingAnswerQuestionsTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 投标答疑问题记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.900 ]
 * @Description: [ 投标答疑问题记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingAnswerQuestionsAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
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
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(BiddingAnswerQuestionsDTO dto) throws SerException;

}