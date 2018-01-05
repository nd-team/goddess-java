package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityExecuteInfoAPI;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.ActivityExecuteInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动执行信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("activityexecuteinfo")
public class ActivityExecuteInfoAct {

    @Autowired
    private ActivityExecuteInfoAPI activityExecuteInfoAPI;

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

            Boolean isHasPermission = activityExecuteInfoAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @return class ActivityExecuteInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/activityexecuteinfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ActivityExecuteInfoBO bo = activityExecuteInfoAPI.findById(id);
            ActivityExecuteInfoVO vo = BeanTransform.copyProperties(bo, ActivityExecuteInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 活动执行信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ActivityExecuteInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = activityExecuteInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivityExecuteInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ActivityExecuteInfoBO> boList = activityExecuteInfoAPI.list(dto);
            List<ActivityExecuteInfoVO> voList = BeanTransform.copyProperties(boList, ActivityExecuteInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加活动执行信息
     *
     * @param to 活动执行信息to
     * @return class ActivityExecuteInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ActivityExecuteInfoTO to, HttpServletRequest request) throws ActException {
        try {
            ActivityExecuteInfoBO bo = activityExecuteInfoAPI.save(to);
            ActivityExecuteInfoVO vo = BeanTransform.copyProperties(bo, ActivityExecuteInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activityExecuteInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动执行信息
     *
     * @param to 活动执行信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ActivityExecuteInfoTO to) throws ActException {
        try {
            activityExecuteInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}