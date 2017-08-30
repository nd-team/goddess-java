package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.HandlePlanStageAPI;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;
import com.bjike.goddess.qualifications.vo.HandlePlanStageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 资质办理计划阶段划分
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:55 ]
 * @Description: [ 资质办理计划阶段划分 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("handleplanstage")
public class HandlePlanStageAct {

    @Autowired
    private HandlePlanStageAPI handlePlanStageAPI;

    /**
     * 保存
     *
     * @param to 资质办理计划阶段传输对象
     * @return class HandlePlanStageVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HandlePlanStageTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.save(to), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质办理计划阶段传输对象
     * @return class HandlePlanStageVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) HandlePlanStageTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.update(to), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质办理计划阶段id
     * @return class HandlePlanStageVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.delete(id), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据办理计划id集合查询计划阶段
     *
     * @param ids
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByPlan")
    public Result findByPlanIds(String[] ids, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByPlanIds(ids), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据办理计划id查询计划阶段
     *
     * @param id 办理计划ID
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByPlan/{id}")
    public Result findByPlan(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByPlan(id), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据资质办理ID查询计划阶段
     *
     * @param id 资质办理ID
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByHandle/{id}")
    public Result findByHandle(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByHandle(id), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.getById(id), HandlePlanStageVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = handlePlanStageAPI.guidePermission(guidePermissionTO);
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
}