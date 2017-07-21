package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityEvaluateAPI;
import com.bjike.goddess.staffactivity.api.ActivityExecuteInfoAPI;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateBO;
import com.bjike.goddess.staffactivity.dto.ActivityEvaluateDTO;
import com.bjike.goddess.staffactivity.to.ActivityEvaluateTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.ActivityEvaluateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动评价
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("activityevaluate")
public class ActivityEvaluateAct {

    @Autowired
    private ActivityEvaluateAPI activityEvaluateAPI;
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

            Boolean isHasPermission = activityEvaluateAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询活动评价
     *
     * @param id 活动评价唯一标识
     * @return class ActivityEvaluateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/activityevaluate/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ActivityEvaluateBO bo = activityEvaluateAPI.findById(id);
            ActivityEvaluateVO vo = BeanTransform.copyProperties(bo, ActivityEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 活动评价dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ActivityEvaluateDTO dto, BindingResult result) throws ActException {
        try {
            Long count = activityEvaluateAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 活动评价dto
     * @return class ActivityEvaluateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivityEvaluateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ActivityEvaluateBO> boList = activityEvaluateAPI.list(dto);
            List<ActivityEvaluateVO> voList = BeanTransform.copyProperties(boList, ActivityEvaluateVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加活动评价
     *
     * @param to 活动评价to
     * @return class ActivityEvaluateVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ActivityEvaluateTO to, HttpServletRequest request) throws ActException {
        try {
            ActivityEvaluateBO bo = activityEvaluateAPI.save(to);
            ActivityEvaluateVO vo = BeanTransform.copyProperties(bo, ActivityEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除活动评价
     *
     * @param id 活动评价唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activityEvaluateAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动评价
     *
     * @param to 活动评价to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ActivityEvaluateTO to) throws ActException {
        try {
            activityEvaluateAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有活动方案
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allActivityScheme")
    public Result allActivityScheme() throws ActException {
        try {
            return ActResult.initialize(activityExecuteInfoAPI.allActivityScheme());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}