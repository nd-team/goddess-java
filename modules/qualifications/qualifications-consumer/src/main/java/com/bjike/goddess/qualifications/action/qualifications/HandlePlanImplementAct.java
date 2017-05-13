package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.HandlePlanImplementAPI;
import com.bjike.goddess.qualifications.to.HandlePlanImplementTO;
import com.bjike.goddess.qualifications.vo.HandlePlanImplementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 资质办理计划阶段实施工作记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 05:00 ]
 * @Description: [ 资质办理计划阶段实施工作记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("handleplanimplement")
public class HandlePlanImplementAct {

    @Autowired
    private HandlePlanImplementAPI handlePlanImplementAPI;

    /**
     * 保存
     *
     * @param to 实施工作记录传输对象
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HandlePlanImplementTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.save(to), HandlePlanImplementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 实施工作记录传输对象
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) HandlePlanImplementTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.update(to), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 实施工作记录id
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.delete(id), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据计划阶段ID集合查询实施工作记录
     *
     * @param stageIds 计划阶段ID
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @GetMapping("v1/findByStage")
    public Result findByStageIds(String[] stageIds, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.findByStageIds(stageIds), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据计划阶段ID查询实施工作记录
     *
     * @param id 计划阶段ID
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @GetMapping("v1/findByStage/{id}")
    public Result findByStage(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.findByStage(id), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据资质办理查询实施工作记录
     *
     * @param id 资质办理ID
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @GetMapping("v1/findByHandle/{id}")
    public Result findByHandle(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.findByHandle(id), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return class HandlePlanImplementVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handlePlanImplementAPI.getById(id), HandlePlanImplementVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}