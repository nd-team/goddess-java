package com.bjike.goddess.bonus.action.bonus;

import com.bjike.goddess.bonus.api.PerformanceIndicatorAPI;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.bonus.vo.PerformanceIndicatorVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 绩效指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("performanceindicator")
public class PerformanceIndicatorAction {

    @Autowired
    private PerformanceIndicatorAPI performanceIndicatorAPI;

    /**
     * 添加
     *
     * @param to 绩效指标传输对象
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PerformanceIndicatorTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.save(to), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 绩效指标传输对象
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PerformanceIndicatorTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.update(to), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.delete(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 启动
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PatchMapping("v1/start/{id}")
    public Result start(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.start(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PatchMapping("v1/close/{id}")
    public Result close(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.close(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据指标状态查询绩效指标数据
     *
     * @param status 绩效指标状态
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Boolean status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.findByStatus(status), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询启动状态绩效指标数据
     *
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/findStart")
    public Result findStart() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.findByStatus(Boolean.TRUE), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}