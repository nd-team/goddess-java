package com.bjike.goddess.negotiatemeeting.action.negotiatemeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.negotiatemeeting.api.DesginAPI;
import com.bjike.goddess.negotiatemeeting.bo.DesginBO;
import com.bjike.goddess.negotiatemeeting.dto.DesginDTO;
import com.bjike.goddess.negotiatemeeting.to.DesginTO;
import com.bjike.goddess.negotiatemeeting.vo.DesginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 协商会议组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 05:39 ]
 * @Description: [ 协商会议组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("desgin")
public class DesginAct {
    @Autowired
    private DesginAPI desginAPI;

    /**
     * 协商会议组织内容设计列表总条数
     *
     * @param dto 协商会议组织内容设计dto
     * @des 获取所有协商会议组织内容设计总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(DesginDTO dto) throws ActException {
        try {
            Long count = desginAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个协商会议组织内容设计
     *
     * @param id id
     * @return class DesginVO
     * @des 获取一个协商会议组织内容设计
     * @version v1
     */
    @GetMapping("v1/desgin/{id}")
    public Result desgin(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DesginBO bo = desginAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DesginVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 协商会议组织内容设计列表
     *
     * @param dto 协商会议组织内容设计信息dto
     * @return class DesginVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DesginDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DesginVO> VOS = BeanTransform.copyProperties
                    (desginAPI.list(dto), DesginVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加协商会议组织内容设计信息
     *
     * @param to 协商会议组织内容设计信息数据to
     * @return class DesginVO
     * @version v1
     */
    //  @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) DesginTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DesginBO bo = desginAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DesginVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑协商会议组织内容设计信息
     *
     * @param to 协商会议组织内容设计信息数据to
     * @des 编辑协商会议组织内容设计信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DesginTO to, BindingResult bindingResult) throws ActException {
        try {
            desginAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有会议议题
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/meetingTopics")
    public Result meetingTopics() throws ActException {
        try {
            Set<String> set = desginAPI.meetingTopics();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据会议议题查找所有会议层面
     *
     * @param meetingTopic 会议议题
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/meetingLevels/{meetingTopic}")
    public Result meetingLevels(@PathVariable String meetingTopic) throws ActException {
        try {
            Set<String> set = desginAPI.meetingLevels(meetingTopic);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据会议议题和会议层面查找所有未冻结的计划参会岗位
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/normalPlanJobs/{meetingTopic}/{meetingLevel}")
    public Result normalPlanJobs(@PathVariable String meetingTopic, @PathVariable String meetingLevel) throws ActException {
        try {
            Set<String> set = desginAPI.normalPlanJobs(meetingTopic, meetingLevel);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据会议议题和会议层面查找所有未冻结的计划会议时间
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/normalPlanTimes/{meetingTopic}/{meetingLevel}")
    public Result normalPlanTimes(@PathVariable String meetingTopic, @PathVariable String meetingLevel) throws ActException {
        try {
            Set<String> set = desginAPI.normalPlanTimes(meetingTopic, meetingLevel);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据会议议题和会议层面查找所有冻结的计划参会岗位
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/freezePlanJobs/{meetingTopic}/{meetingLevel}")
    public Result freezePlanJobs(@PathVariable String meetingTopic, @PathVariable String meetingLevel) throws ActException {
        try {
            Set<String> set = desginAPI.freezePlanJobs(meetingTopic, meetingLevel);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据会议议题和会议层面查找所有冻结的计划会议时间
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/freezePlanTimes/{meetingTopic}/{meetingLevel}")
    public Result freezePlanTimes(@PathVariable String meetingTopic, @PathVariable String meetingLevel) throws ActException {
        try {
            Set<String> set = desginAPI.freezePlanTimes(meetingTopic, meetingLevel);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结计划参会岗位
     *
     * @param id 协商会议组织内容设计id
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/freezeJob/{id}")
    public Result freezeJob(@PathVariable String id) throws ActException {
        try {
            desginAPI.freezeJob(id);
            return new ActResult("冻结成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻计划参会岗位
     *
     * @param id 协商会议组织内容设计id
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/thawJob/{id}")
    public Result thawJob(@PathVariable String id) throws ActException {
        try {
            desginAPI.thawJob(id);
            return new ActResult("解冻成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑计划参会岗位
     *
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/editJob")
    public Result editJob(@Validated DesginTO to, BindingResult result) throws ActException {
        try {
            desginAPI.editJob(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结计划会议时间
     *
     * @param id 协商会议组织内容设计id
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/freezeTime/{id}")
    public Result freezeTime(@PathVariable String id) throws ActException {
        try {
            desginAPI.freezeTime(id);
            return new ActResult("冻结成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻计划会议时间
     *
     * @param id 协商会议组织内容设计id
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/thawTime/{id}")
    public Result thawTime(@PathVariable String id) throws ActException {
        try {
            desginAPI.thawTime(id);
            return new ActResult("解冻成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑计划会议时间
     *
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/editTime")
    public Result editTime(@Validated DesginTO to, BindingResult result) throws ActException {
        try {
            desginAPI.editTime(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}