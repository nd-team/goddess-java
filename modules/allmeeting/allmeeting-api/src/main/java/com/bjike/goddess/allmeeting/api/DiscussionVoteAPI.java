package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.DiscussionVoteBO;
import com.bjike.goddess.allmeeting.bo.SummaryVoteBO;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 意见投票业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DiscussionVoteAPI {

    /**
     * 投票
     * @param to
     * @return
     * @throws SerException
     */
    DiscussionVoteBO add(DiscussionVoteTO to) throws SerException;

    /**
     * 查询投票列表
     * @param summaryId
     * @return
     * @throws SerException
     */
    List<SummaryVoteBO> listBySummary(String summaryId) throws SerException;

    /**
     * 查询投票结果
     * @param summaryId
     * @return
     * @throws SerException
     */
    List<SummaryVoteBO> listByConSummary(String summaryId) throws SerException;
}