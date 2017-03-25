package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MonthPlanAPI;
import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;
import com.bjike.goddess.marketdevelopment.vo.MonthPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 月计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketdevelopment/monthplan")
public class MonthPlanAction {

    @Autowired
    private MonthPlanAPI monthPlanAPI;

    /**
     * 保存月计划数据
     *
     * @param to 月计划传输对象
     * @return class MonthPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(MonthPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.save(to), MonthPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改月计划数据
     *
     * @param to 月计划传输对象
     * @return class MonthPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(MonthPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.update(to), MonthPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除月计划数据
     *
     * @param to 月计划传输书对象
     * @return class MonthPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(MonthPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.delete(to), MonthPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年计划ID查询月计划数据
     *
     * @param year_id 年计划ID
     * @return class MonthPlanVO
     * @version v1
     */
    @GetMapping("v1/findByYearId/{year_id}")
    public Result findByYearID(@PathVariable String year_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.findByYearID(year_id), MonthPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年份查询月计划数据
     *
     * @param year 年份
     * @return class MonthPlanVO
     * @version v1
     */
    @GetMapping("v1/findByYear")
    public Result findByYear( Integer year) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthPlanAPI.findByYear(year), MonthPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}