package com.bjike.goddess.staffmeeting.action.staffmeeting;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingDiscussionAPI;
import com.bjike.goddess.staffmeeting.api.MeetingSummaryAPI;
import com.bjike.goddess.staffmeeting.api.ReferPermissionAPI;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.dto.ReferPermissionDTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import com.bjike.goddess.staffmeeting.vo.MeetingDiscussionVO;
import com.bjike.goddess.staffmeeting.vo.MeetingSummaryVO;
import com.bjike.goddess.staffmeeting.vo.ReferPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工代表大会纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summary")
public class MeetingSummaryAct {

    @Autowired
    private MeetingSummaryAPI meetingSummaryAPI;
    @Autowired
    private MeetingDiscussionAPI meetingDiscussionAPI;
    @Autowired
    private ReferPermissionAPI referPermissionAPI;
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

            Boolean isHasPermission = meetingSummaryAPI.guidePermission(guidePermissionTO);
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
     * 编辑
     *
     * @param to 员工代表大会纪要
     * @return class MeetingSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingSummaryVO vo = BeanTransform.copyProperties(meetingSummaryAPI.edit(to), MeetingSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 员工代表大会纪要ID
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            meetingSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            meetingSummaryAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MeetingSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(@Validated({MeetingSummaryDTO.SelectStatus.class}) MeetingSummaryDTO dto) throws ActException {
        try {
            List<MeetingSummaryVO> voList = BeanTransform.copyProperties(meetingSummaryAPI.pageList(dto), MeetingSummaryVO.class);
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
    public Result count(@Validated({MeetingSummaryDTO.SelectStatus.class}) MeetingSummaryDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            Long count = meetingSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询员工代表大会纪要
     *
     * @param id 员工代表大会纪要id
     * @return class MeetingSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingSummaryVO vo = BeanTransform.copyProperties(meetingSummaryAPI.findById(id), MeetingSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流讨论
     *
     * @param to 会议讨论意见
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/discuss/add")
    public Result add(@Validated({ADD.class}) MeetingDiscussionTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.add(to), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id查询当前用户的讨论意见
     *
     * @param summaryId id
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/discuss/find/{summaryId}")
    public Result discussFind(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {

            MeetingDiscussionVO voList = BeanTransform.copyProperties(meetingDiscussionAPI.discussFind(summaryId), MeetingDiscussionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流讨论详情
     *
     * @param summaryId id
     * @return class MeetingDiscussionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/discuss/list/{summaryId}")
    public Result discussList(@PathVariable String summaryId, HttpServletRequest request) throws ActException {
        try {
            List<MeetingDiscussionVO> vo = BeanTransform.copyProperties(meetingDiscussionAPI.listBySummaryId(summaryId), MeetingDiscussionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请
     *
     * @return class ReferPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/add")
    public Result permission(HttpServletRequest request) throws ActException {
        try {
            ReferPermissionVO vo = BeanTransform.copyProperties(referPermissionAPI.add(), ReferPermissionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 同意
     *
     * @param id 申请调阅列表Id
     * @return class ReferPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/agree/{id}")
    public Result agree(@PathVariable String id) throws ActException {
        try {
            referPermissionAPI.agree(id);
            return new ActResult("审核通过!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请调阅列表
     *
     * @return class ReferPermissionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/permission/list")
    public Result permission(ReferPermissionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ReferPermissionVO> voList = BeanTransform.copyProperties(referPermissionAPI.pageList(dto), ReferPermissionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}