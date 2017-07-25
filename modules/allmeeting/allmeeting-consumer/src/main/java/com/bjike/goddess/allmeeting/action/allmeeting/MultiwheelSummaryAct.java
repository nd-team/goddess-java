package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.DiscussionVoteAPI;
import com.bjike.goddess.allmeeting.api.MeetingDiscussionAPI;
import com.bjike.goddess.allmeeting.api.MultiPermissionAPI;
import com.bjike.goddess.allmeeting.api.MultiwheelSummaryAPI;
import com.bjike.goddess.allmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.allmeeting.dto.MultiPermissionDTO;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.to.*;
import com.bjike.goddess.allmeeting.vo.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
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
 * 多轮交流讨论纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 多轮交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("multisummary")
public class MultiwheelSummaryAct {

    @Autowired
    private MultiwheelSummaryAPI multiwheelSummaryAPI;
    @Autowired
    private DiscussionVoteAPI voteAPI;
    @Autowired
    private MeetingDiscussionAPI meetingDiscussionAPI;
    @Autowired
    private MultiPermissionAPI multiPermissionAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = multiwheelSummaryAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 组织内容
     *
     * @param id id
     * @return class ConciseSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/organize/{id}")
    public Result organize(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            OrganizeForSummaryVO vo = BeanTransform.copyProperties(multiwheelSummaryAPI.organize(id), OrganizeForSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 纪要内容
     *
     * @param to 多轮交流讨论纪要
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MultiwheelSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MultiwheelSummaryVO vo = BeanTransform.copyProperties(multiwheelSummaryAPI.edit(to), MultiwheelSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id Id
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            multiwheelSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id Id
     * @version v1
     */
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            multiwheelSummaryAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated(MultiwheelSummaryDTO.SelectStatus.class) MultiwheelSummaryDTO dto) throws ActException {
        try {
            List<MultiwheelSummaryVO> voList = BeanTransform.copyProperties(multiwheelSummaryAPI.pageList(dto), MultiwheelSummaryVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(MultiwheelSummaryDTO.SelectStatus.class) MultiwheelSummaryDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = multiwheelSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询纪要信息
     *
     * @param id id
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MultiwheelSummaryVO vo = BeanTransform.copyProperties(multiwheelSummaryAPI.findById(id), MultiwheelSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流讨论一轮意见
     *
     * @param to 会议讨论意见
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/discuss/first")
    public Result first(@Validated({ADD.class}) FirstDiscussionTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.addFirst(to), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流讨论二轮意见
     *
     * @param to 会议讨论意见
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/discuss/second")
    public Result second(@Validated({EDIT.class}) SecondDiscussionTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.addSecond(to), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询交流讨论总记录数
     *
     * @param summaryId 纪要Id
     * @version v1
     */
    @GetMapping("v1/discuss/count/{summaryId}")
    public Result count(@PathVariable String summaryId) throws ActException {
        try {
            MeetingDiscussionDTO dto = new MeetingDiscussionDTO();
            dto.getConditions().add(Restrict.eq("summaryId", summaryId));
            Long count = meetingDiscussionAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据交流讨论Id查询交流讨论信息
     *
     * @param id 交流讨论Id
     * @return class ConciseSummaryVO
     * @version v1
     */
    @GetMapping("v1/discuss/find/{id}")
    public Result findDis(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingDiscussionVO vo = BeanTransform.copyProperties(meetingDiscussionAPI.findById(id), MeetingDiscussionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流讨论列表
     *
     * @param summaryId 会议讨论意见id
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @GetMapping("v1/discuss/list/{summaryId}")
    public Result discussionList(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {
            List<MeetingDiscussionVO> vo = BeanTransform.copyProperties(meetingDiscussionAPI.listBySummaryId(summaryId), MeetingDiscussionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 投票
     *
     * @param to 投票信息
     * @return class DiscussionVoteVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/vote/add")
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
    @GetMapping("v1/vote/list/{summaryId}")
    public Result listBySummary(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {

            List<SummaryVoteVO> voList = BeanTransform.copyProperties(voteAPI.listBySummary(summaryId), SummaryVoteVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请
     *
     * @return class MultiPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/add")
    public Result permission(HttpServletRequest request) throws ActException {
        try {
            MultiPermissionVO voList = BeanTransform.copyProperties(multiPermissionAPI.add(), MultiPermissionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 同意
     *
     * @return class MultiPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/agree/{id}")
    public Result agree(@PathVariable String id) throws ActException {
        try {
            multiPermissionAPI.agree(id);
            return new ActResult("审核通过!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请调阅列表
     *
     * @return class MultiPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/list")
    public Result permission(MultiPermissionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MultiPermissionVO> voList = BeanTransform.copyProperties(multiPermissionAPI.pageList(dto), MultiPermissionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}