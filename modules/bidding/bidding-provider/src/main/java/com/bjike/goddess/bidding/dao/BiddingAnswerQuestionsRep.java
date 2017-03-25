package com.bjike.goddess.bidding.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.bidding.dto.BiddingAnswerQuestionsDTO;
import com.bjike.goddess.bidding.entity.BiddingAnswerQuestions;

/**
 * 投标答疑问题记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.914 ]
 * @Description: [ 投标答疑问题记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingAnswerQuestionsRep extends JpaRep<BiddingAnswerQuestions, BiddingAnswerQuestionsDTO> {

}