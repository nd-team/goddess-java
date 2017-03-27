package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.WeekPlanAPI;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;
import com.bjike.goddess.marketdevelopment.vo.WeekPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 周计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketdevelopment/weekplan")
public class WeekPlanAction {

    @Autowired
    private WeekPlanAPI weekPlanAPI;

    /**
     * 保存周计划数据
     *
     * @param to 周计划传输对象
     * @return class WeekPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(WeekPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.save(to), WeekPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改周计划数据
     *
     * @param to 周计划传输对象
     * @return class WeekPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(WeekPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.update(to), WeekPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除周计划数据
     *
     * @param to 周计划传输对象
     * @return class WeekPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(WeekPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.delete(to), WeekPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据月计划ID查询周计划数据
     *
     * @param month_id 月计划ID
     * @return class WeekPlanVO
     * @version v1
     */
    @GetMapping("v1/findByMonth/{month_id}")
    public Result findByMonth(@PathVariable String month_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.findByMonth(month_id), WeekPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间范围查询周计划数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return class WeekPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDate")
    public Result findByDate(String startDate, String endDate) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.findByDate(startDate, endDate), WeekPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}