package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.ConcisePermissionAPI;
import com.bjike.goddess.allmeeting.api.ConciseSummaryAPI;
import com.bjike.goddess.allmeeting.api.DiscussionVoteAPI;
import com.bjike.goddess.allmeeting.api.MeetingDiscussionAPI;
import com.bjike.goddess.allmeeting.dto.ConcisePermissionDTO;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.allmeeting.to.DiscussionVoteTO;
import com.bjike.goddess.allmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.allmeeting.vo.*;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
 * 简洁交流讨论纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("concisesummary")
public class ConciseSummaryAct {

    @Autowired
    private ConciseSummaryAPI conciseSummaryAPI;
    @Autowired
    private DiscussionVoteAPI voteAPI;
    @Autowired
    private MeetingDiscussionAPI meetingDiscussionAPI;
    @Autowired
    private ConcisePermissionAPI concisePermissionAPI;

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
            OrganizeForSummaryVO vo = BeanTransform.copyProperties(conciseSummaryAPI.organize(id), OrganizeForSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 纪要内容
     *
     * @param to 简洁交流讨论纪要
     * @return class ConciseSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ConciseSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ConciseSummaryVO vo = BeanTransform.copyProperties(conciseSummaryAPI.edit(to), ConciseSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 简洁交流讨论纪要ID
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            conciseSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 简洁交流讨论纪要ID
     * @version v1
     */
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            conciseSummaryAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class ConciseSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ConciseSummaryDTO dto) throws ActException {
        try {
            List<ConciseSummaryVO> voList = BeanTransform.copyProperties(conciseSummaryAPI.pageList(dto), ConciseSummaryVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询纪要总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ConciseSummaryDTO dto) throws ActException {
        try {
            if (dto.getStatus() != null) {
                dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            }
            Long count = conciseSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询简洁交流讨论纪要
     *
     * @param id 简洁交流讨论纪要id
     * @return class ConciseSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ConciseSummaryVO vo = BeanTransform.copyProperties(conciseSummaryAPI.findById(id), ConciseSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 交流讨论编辑
     *
     * @param to 会议讨论意见
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/discuss/add")
    public Result first(@Validated({ADD.class}) MeetingDiscussionTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ConciseDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.addFirstByCon(to), ConciseDiscussionVO.class, request);
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
     * 投票结果
     *
     * @param summaryId 纪要id
     * @return class SummaryVoteVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/vote/list/{summaryId}")
    public Result listBySummary(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {

            List<SummaryVoteVO> voList = BeanTransform.copyProperties(voteAPI.listByConSummary(summaryId), SummaryVoteVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请
     *
     * @return class ConcisePermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/add")
    public Result permission(HttpServletRequest request) throws ActException {
        try {
            ConcisePermissionVO vo = BeanTransform.copyProperties(concisePermissionAPI.add(), ConcisePermissionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 同意
     *
     * @return class ConcisePermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/agree/{id}")
    public Result agree(@PathVariable String id) throws ActException {
        try {
            concisePermissionAPI.agree(id);
            return new ActResult("审核通过!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请调阅列表
     *
     * @return class ConcisePermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/list")
    public Result permission(ConcisePermissionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ConcisePermissionVO> voList = BeanTransform.copyProperties(concisePermissionAPI.pageList(dto), ConcisePermissionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}