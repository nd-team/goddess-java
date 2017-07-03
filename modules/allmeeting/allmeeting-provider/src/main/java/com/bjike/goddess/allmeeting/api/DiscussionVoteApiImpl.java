package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.DiscussionVoteBO;
import com.bjike.goddess.allmeeting.bo.SummaryVoteBO;
import com.bjike.goddess.allmeeting.service.DiscussionVoteSer;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见投票业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("discussionVoteApiImpl")
public class DiscussionVoteApiImpl implements DiscussionVoteAPI {

    @Autowired
    private DiscussionVoteSer voteSer;

    @Override
    public DiscussionVoteBO add(DiscussionVoteTO to) throws SerException {
        return voteSer.insertModel(to);
    }

    @Override
    public List<SummaryVoteBO> listBySummary(String summaryId) throws SerException {
        return voteSer.listBySummary(summaryId);
    }

    @Override
    public List<SummaryVoteBO> listByConSummary(String summaryId) throws SerException {
        return voteSer.listByConSummary(summaryId);
    }
}