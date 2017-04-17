package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.DayPlanAPI;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import com.bjike.goddess.marketdevelopment.vo.DayPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 天计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dayplan")
public class DayPlanAction {

    @Autowired
    private DayPlanAPI dayPlanAPI;

    /**
     * 保存天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DayPlanTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.save(to), DayPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DayPlanTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.update(to), DayPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除天计划数据
     *
     * @param to 天计划传输对象
     * @return class DayPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(DayPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.delete(to), DayPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间范围查询天计划数据
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDateCycle")
    public Result findByDate(String start, String end) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.findByDate(start, end), DayPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间查询天计划数据
     *
     * @param date 时间
     * @return class DayPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDate")
    public Result findByDate(String date) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dayPlanAPI.findByDate(date), DayPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}