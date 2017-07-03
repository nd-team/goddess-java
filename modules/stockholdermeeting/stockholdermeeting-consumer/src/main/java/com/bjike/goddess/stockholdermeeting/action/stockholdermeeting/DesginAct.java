package com.bjike.goddess.stockholdermeeting.action.stockholdermeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.stockholdermeeting.api.DesginAPI;
import com.bjike.goddess.stockholdermeeting.bo.DesginBO;
import com.bjike.goddess.stockholdermeeting.dto.DesginDTO;
import com.bjike.goddess.stockholdermeeting.to.DesginTO;
import com.bjike.goddess.stockholdermeeting.vo.DesginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("desgin")
public class DesginAct {
    @Autowired
    private DesginAPI desginAPI;

    /**
     * 股东大会组织内容设计列表总条数
     *
     * @param dto 股东大会组织内容设计dto
     * @des 获取所有股东大会组织内容设计总条数
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
     * 一个股东大会组织内容设计
     *
     * @param id id
     * @return class DesginVO
     * @des 获取一个股东大会组织内容设计
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
     * 股东大会组织内容设计列表
     *
     * @param dto 股东大会组织内容设计信息dto
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
     * 添加股东大会组织内容设计信息
     *
     * @param to 股东大会组织内容设计信息数据to
     * @return class DesginVO
     * @version v1
     */
    @LoginAuth
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
     * 编辑股东大会组织内容设计信息
     *
     * @param to 股东大会组织内容设计信息数据to
     * @des 编辑股东大会组织内容设计信息
     * @version v1
     */
    @LoginAuth
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
     * 根据会议议题和会议层面查找计划会议时间
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/planTime/{meetingTopic}/{meetingLevel}")
    public Result planTime(@PathVariable String meetingTopic, @PathVariable String meetingLevel) throws ActException {
        try {
            String planTime = desginAPI.planTime(meetingTopic, meetingLevel);
            return ActResult.initialize(planTime);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}