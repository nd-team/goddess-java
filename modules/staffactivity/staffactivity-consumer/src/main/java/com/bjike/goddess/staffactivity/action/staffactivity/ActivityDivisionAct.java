package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityDivisionAPI;
import com.bjike.goddess.staffactivity.api.ActivitySchemeAPI;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.ActivityDivisionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动分工
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("activitydivision")
public class ActivityDivisionAct {

    @Autowired
    private ActivityDivisionAPI activityDivisionAPI;
    @Autowired
    private ActivitySchemeAPI activitySchemeAPI;

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

            Boolean isHasPermission = activityDivisionAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询活动分工
     *
     * @param id 活动分工唯一标识
     * @return class ActivityDivisionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/activitydivision/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ActivityDivisionBO bo = activityDivisionAPI.findById(id);
            ActivityDivisionVO vo = BeanTransform.copyProperties(bo, ActivityDivisionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 活动分工dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ActivityDivisionDTO dto, BindingResult result) throws ActException {
        try {
            Long count = activityDivisionAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 活动分工dto
     * @return class ActivityDivisionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivityDivisionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ActivityDivisionBO> boList = activityDivisionAPI.list(dto);
            List<ActivityDivisionVO> voList = BeanTransform.copyProperties(boList, ActivityDivisionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加活动分工
     *
     * @param to 活动分工to
     * @return class ActivityDivisionVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ActivityDivisionTO to, HttpServletRequest request) throws ActException {
        try {
            ActivityDivisionBO bo = activityDivisionAPI.save(to);
            ActivityDivisionVO vo = BeanTransform.copyProperties(bo, ActivityDivisionVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除活动分工
     *
     * @param id 活动分工唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activityDivisionAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动分工
     *
     * @param to 活动分工to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ActivityDivisionTO to) throws ActException {
        try {
            activityDivisionAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有活动主题
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allTheme")
    public Result allTheme() throws ActException {
        try {
            return ActResult.initialize(activitySchemeAPI.allTheme());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据活动主题获取活动方案id
     *
     * @param theme 活动主题
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findIdByTheme/{theme}")
    public Result findIdByTheme(@PathVariable String theme) throws ActException {
        try {
            return ActResult.initialize(activitySchemeAPI.findIdByTheme(theme));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}