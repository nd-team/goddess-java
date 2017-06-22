package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.MeetingDiscussionAPI;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.allmeeting.vo.MeetingDiscussionVO;
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
 * 会议讨论意见
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("concisediscussion")
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

            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.addByCon(to), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 意见详情列表
     *
     * @param summaryId 会议讨论意见id
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