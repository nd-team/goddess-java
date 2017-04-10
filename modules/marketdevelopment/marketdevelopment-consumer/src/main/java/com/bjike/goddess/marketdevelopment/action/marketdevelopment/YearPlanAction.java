package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.YearPlanAPI;
import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
import com.bjike.goddess.marketdevelopment.vo.YearPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 年计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("yearplan")
public class YearPlanAction {

    @Autowired
    private YearPlanAPI yearPlanAPI;

    /**
     * 保存年计划数据
     *
     * @param to 年计划传输对象
     * @return class YearPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(YearPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(yearPlanAPI.save(to), YearPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改年计划数据
     *
     * @param to 年计划传输对象
     * @return class YearPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(YearPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(yearPlanAPI.update(to), YearPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年计划对象
     *
     * @param to 年计划传输对象
     * @return class YearPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(YearPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(yearPlanAPI.delete(to), YearPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询本年年计划数据
     *
     * @return class YearPlanVO
     * @version v1
     */
    @GetMapping("v1/findThisYear")
    public Result findThisYear() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(yearPlanAPI.findThisYear(), YearPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年份查询年计划数据
     *
     * @param year 年份
     * @return class YearPlanVO
     * @version v1
     */
    @GetMapping("v1/findByYear")
    public Result findByYear(Integer year) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(yearPlanAPI.findByYear(year), YearPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}