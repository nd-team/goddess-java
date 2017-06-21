package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.DiscussionVoteBO;
import com.bjike.goddess.allmeeting.bo.SummaryVoteBO;
import com.bjike.goddess.allmeeting.dto.DiscussionVoteDTO;
import com.bjike.goddess.allmeeting.entity.DiscussionVote;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface DiscussionVoteSer extends Ser<DiscussionVote, DiscussionVoteDTO> {

    DiscussionVoteBO insertModel(DiscussionVoteTO to) throws SerException;

    List<SummaryVoteBO> listBySummary(String summaryId) throws SerException;
}