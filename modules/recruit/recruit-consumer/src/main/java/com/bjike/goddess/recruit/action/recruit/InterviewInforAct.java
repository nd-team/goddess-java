package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FirstPhoneRecordAPI;
import com.bjike.goddess.recruit.api.InterviewInforAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import com.bjike.goddess.recruit.vo.InterviewInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("interviewInfor")
public class InterviewInforAct {

    @Autowired
    private InterviewInforAPI interviewInforAPI;
    @Autowired
    private FirstPhoneRecordAPI firstPhoneRecordAPI;

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

            Boolean isHasPermission = interviewInforAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询面试信息
     *
     * @param id 面试信息唯一标识
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/interviewInfor/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InterviewInforBO bo = interviewInforAPI.findById(id);
            InterviewInforVO vo = BeanTransform.copyProperties(bo, InterviewInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 面试信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InterviewInforDTO dto, BindingResult result) throws ActException {
        try {
            Long count = interviewInforAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 面试信息dto
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InterviewInforDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InterviewInforBO> boList = interviewInforAPI.list(dto);
            List<InterviewInforVO> voList = BeanTransform.copyProperties(boList, InterviewInforVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加面试信息
     *
     * @param to 面试信息to信息
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InterviewInforTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InterviewInforBO bo = interviewInforAPI.save(to);
            InterviewInforVO vo = BeanTransform.copyProperties(bo, InterviewInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除面试信息
     *
     * @param id 面试信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            interviewInforAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑面试信息
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InterviewInforTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有第一次电访记录的姓名名单
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allFirstName")
    public Result allFirstName() throws ActException {
        try {
            return ActResult.initialize(firstPhoneRecordAPI.allFirstName());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
