package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.WeekCycleAPI;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleUpdateTO;
import com.bjike.goddess.marketdevelopment.vo.WeekMonthMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 周计划的周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("weekcycle")
public class WeekCycleAction {

    @Autowired
    private WeekCycleAPI weekCycleAPI;

    /**
     * 列表
     *
     * @param dto 周计划数据传输对象
     * @return class WeekMonthMoneyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WeekCycleDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekCycleAPI.maps(dto), WeekMonthMoneyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 周计划传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) WeekCycleTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            weekCycleAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改周计划数据
     *
     * @param to 周计划传输对象
     * @version v1
     */
    @PutMapping("v1/update/{weekCycleId}")
    public Result update(@Validated(EDIT.class) WeekCycleUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            weekCycleAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 删除周计划数据
//     *
//     * @param weekCycleId 周计划传输对象
//     * @version v1
//     */
//    @DeleteMapping("v1/delete/{weekCycleId}")
//    public Result delete(@PathVariable String weekCycleId, HttpServletRequest request) throws ActException {
//        try {
//            weekCycleAPI.delete(weekCycleId);
//            return ActResult.initialize("delete success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据id获取周计划数据
//     *
//     * @param weekCycleId 业务方向数据id
//     * @return class WeekCycleVO
//     * @version v1
//     */
//    @GetMapping("v1/findById/{weekCycleId}")
//    public Result findById(@PathVariable String weekCycleId, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(weekCycleAPI.getById(weekCycleId), WeekMonthMoneyVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取总条数
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getTotal")
//    public Result getTotal(WeekCycleDTO dto) throws ActException {
//        try {
//            return ActResult.initialize(weekCycleAPI.getTotal(dto));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}