package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.HandlePlanStageAPI;
import com.bjike.goddess.qualifications.to.HandlePlanStageTO;
import com.bjike.goddess.qualifications.vo.HandlePlanStageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("qualifications/handleplanstage")
public class HandlePlanStageAction {

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
    public Result save(@Validated(ADD.class) HandlePlanStageTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.save(to), HandlePlanStageVO.class));
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
    public Result update(@Validated(EDIT.class) HandlePlanStageTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.update(to), HandlePlanStageVO.class));
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
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.delete(id), HandlePlanStageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据办理计划id集合查询计划阶段
     *
     * @param plan_ids
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByPlan")
    public Result findByPlanIds(String[] plan_ids) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByPlanIds(plan_ids), HandlePlanStageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据办理计划id查询计划阶段
     *
     * @param plan_id 办理计划ID
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByPlan/{plan_id}")
    public Result findByPlan(@PathVariable String plan_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByPlan(plan_id), HandlePlanStageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据资质办理ID查询计划阶段
     *
     * @param handle_id 资质办理ID
     * @return class HandlePlanStageVO
     * @version v1
     */
    @GetMapping("v1/findByHandle/{handle_id}")
    public Result findByHandle(@PathVariable String handle_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanStageAPI.findByHandle(handle_id), HandlePlanStageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}