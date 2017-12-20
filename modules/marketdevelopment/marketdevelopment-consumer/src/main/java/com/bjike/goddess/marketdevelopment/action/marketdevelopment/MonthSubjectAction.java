package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MonthSubjectAPI;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectDTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectUpdateTO;
import com.bjike.goddess.marketdevelopment.vo.MonthMonthMoneyVO;
import com.bjike.goddess.marketdevelopment.vo.MonthSubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//import com.bjike.goddess.marketdevelopment.vo.MonthMonthMoneyVO;

/**
 * 月计划的业务科目
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-06 05:04 ]
 * @Description: [ 月计划的业务科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("monthsubject")
public class MonthSubjectAction {
    @Autowired
    private MonthSubjectAPI monthSubjectAPI;

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

            Boolean isHasPermission = monthSubjectAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 月计划数据传输对象
     * @return class MonthMonthMoneyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(MonthSubjectDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthSubjectAPI.maps(dto), MonthMonthMoneyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 月计划传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) MonthSubjectTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            monthSubjectAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改月计划数据
     *
     * @param to 月计划传输对象
     * @version v1
     */
    @PutMapping("v1/update/{subjectDataId}")
    public Result update(@Validated(EDIT.class) MonthSubjectUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            monthSubjectAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除月计划数据
     *
     * @param subjectDataId 月计划传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{subjectDataId}")
    public Result delete(@PathVariable String subjectDataId, HttpServletRequest request) throws ActException {
        try {
            monthSubjectAPI.delete(subjectDataId);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取月计划数据
     *
     * @param subjectDataId 业务方向数据id
     * @return class MonthSubjectVO
     * @version v1
     */
    @GetMapping("v1/findById/{subjectDataId}")
    public Result findById(@PathVariable String subjectDataId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthSubjectAPI.getById(subjectDataId), MonthSubjectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(MonthSubjectDTO dto) throws ActException {
        try {
            return ActResult.initialize(monthSubjectAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @version v1
     */
    @PutMapping("v1/congeal/{subjectDataId}")
    public Result congeal(@PathVariable String subjectDataId) throws ActException {
        try {
            monthSubjectAPI.congeal(subjectDataId);
            return ActResult.initialize("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @version v1
     */
    @PutMapping("v1/thaw/{subjectDataId}")
    public Result thaw(@PathVariable String subjectDataId) throws ActException {
        try {
            monthSubjectAPI.thaw(subjectDataId);
            return ActResult.initialize("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}