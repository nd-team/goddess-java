package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.WeekPlanAPI;
import com.bjike.goddess.marketdevelopment.dto.WeekPlanDTO;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;
import com.bjike.goddess.marketdevelopment.vo.WeekPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("weekplan")
public class WeekPlanAct {

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
    public Result save(@Validated(ADD.class) WeekPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.save(to), WeekPlanVO.class, request));
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
    public Result update(@Validated(EDIT.class) WeekPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.update(to), WeekPlanVO.class, request));
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
    public Result delete(WeekPlanTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.delete(to), WeekPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据月计划ID查询周计划数据
     *
     * @param id 月计划ID
     * @return class WeekPlanVO
     * @version v1
     */
    @GetMapping("v1/findByMonth/{id}")
    public Result findByMonth(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.findByMonth(id), WeekPlanVO.class, request));
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
    public Result findByDate(String startDate, String endDate, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.findByDate(startDate, endDate), WeekPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取周计划
     *
     * @param id 周计划数据id
     * @return class WeekPlanVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.getById(id), WeekPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 周计划数据传输对象
     * @return class WeekPlanVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WeekPlanDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekPlanAPI.maps(dto), WeekPlanVO.class, request));
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
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(weekPlanAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}