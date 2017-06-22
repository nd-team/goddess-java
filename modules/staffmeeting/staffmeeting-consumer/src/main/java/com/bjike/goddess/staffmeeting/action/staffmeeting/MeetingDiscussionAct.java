package com.bjike.goddess.staffmeeting.action.staffmeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingDiscussionAPI;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.staffmeeting.vo.MeetingDiscussionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通告反馈投诉
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("discussion")
public class MeetingDiscussionAct {

    @Autowired
    private MeetingDiscussionAPI meetingDiscussionAPI;

    /**
     * 新增交流讨论
     *
     * @param to 会议讨论意见
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MeetingDiscussionTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.add(to), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 意见详情列表
     *
     * @param summaryId 纪要id
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @GetMapping("v1/list/{summaryId}")
    public Result find(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {
            List<MeetingDiscussionVO> vo = BeanTransform.copyProperties(meetingDiscussionAPI.listBySummaryId(summaryId), MeetingDiscussionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}