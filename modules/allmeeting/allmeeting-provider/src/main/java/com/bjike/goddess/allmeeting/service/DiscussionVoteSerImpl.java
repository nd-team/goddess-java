package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.DiscussionVoteBO;
import com.bjike.goddess.allmeeting.bo.SummaryVoteBO;
import com.bjike.goddess.allmeeting.dto.DiscussionVoteDTO;
import com.bjike.goddess.allmeeting.entity.ConciseSummary;
import com.bjike.goddess.allmeeting.entity.DiscussionVote;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见投票业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class DiscussionVoteSerImpl extends ServiceImpl<DiscussionVote, DiscussionVoteDTO> implements DiscussionVoteSer {

    @Autowired
    private MeetingDiscussionSer meetingDiscussionSer;
    @Autowired
    private ConciseSummarySer conciseSummarySer;
    @Autowired
    private MultiwheelSummarySer multiwheelSummarySer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public DiscussionVoteBO insertModel(DiscussionVoteTO to) throws SerException {

        UserBO userBO = userAPI.currentUser();
        String currentUser = userBO.getUsername();
        String currentNum = userBO.getEmployeeNumber();
        ConciseSummary summary = conciseSummarySer.findById(to.getSummaryId());
        String actualUsers = summary.getActualUsers();
        if (actualUsers.contains(currentNum)) {
            DiscussionVoteDTO dto = new DiscussionVoteDTO();
            dto.getConditions().add(Restrict.eq("summaryId", to.getSummaryId()));
            dto.getConditions().add(Restrict.eq("voteUserNum", currentNum));
            dto.setLimit(1);
            List<DiscussionVote> list = super.findByPage(dto);
            if (list != null && !list.isEmpty()) {
                throw new SerException("亲，您已经投过票了!");
            }
            DiscussionVote model = BeanTransform.copyProperties(to, DiscussionVote.class);
            super.save(model);
            return BeanTransform.copyProperties(model, DiscussionVoteBO.class);
        } else {
            throw new SerException("只有参会人员可填写!");
        }
    }

    @Override
    public List<SummaryVoteBO> listBySummary(String summaryId) throws SerException {

        String sql = "select dis.id as id, dis.user as user, dis.finalDis as finalDis, count(vote.discussionId) as voteSum from allmeeting_discussion dis" +
                " left JOIN allmeeting_vote vote  ON dis.id =vote.discussionId  " +
                "WHERE dis.summaryId ='" + summaryId +
                "' " +
                "GROUP BY id, discussionId";
        String[] fields = new String[]{"id","user","finalDis","voteSum"};
        List<SummaryVoteBO> boList = super.findBySql(sql,SummaryVoteBO.class,fields);

        return boList;
    }


}