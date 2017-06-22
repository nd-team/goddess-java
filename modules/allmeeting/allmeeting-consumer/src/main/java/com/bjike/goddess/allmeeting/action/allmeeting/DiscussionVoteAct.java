package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.DiscussionVoteAPI;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.allmeeting.vo.DiscussionVoteVO;
import com.bjike.goddess.allmeeting.vo.SummaryVoteVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 意见投票
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("discussionvote")
public class DiscussionVoteAct {
    @Autowired
    private DiscussionVoteAPI voteAPI;

    /**
     * 投票
     *
     * @param to 投票信息
     * @return class DiscussionVoteVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DiscussionVoteTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            DiscussionVoteVO vo = BeanTransform.copyProperties(voteAPI.add(to), DiscussionVoteVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投票结果查询
     *
     * @param summaryId 纪要id
     * @return class SummaryVoteVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list/{summaryId}")
    public Result listBySummary(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {

            List<SummaryVoteVO> voList = BeanTransform.copyProperties(voteAPI.listBySummary(summaryId), SummaryVoteVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}