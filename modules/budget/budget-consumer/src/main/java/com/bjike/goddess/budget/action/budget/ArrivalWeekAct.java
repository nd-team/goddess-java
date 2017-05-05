package com.bjike.goddess.budget.action.budget;

import com.bjike.goddess.budget.api.ArrivalWeekAPI;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.bo.ArrivalWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.vo.ArrivalWeekCountVO;
import com.bjike.goddess.budget.vo.ArrivalWeekVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("arrivalweek")
public class ArrivalWeekAct {
    @Autowired
    private ArrivalWeekAPI arrivalWeekAPI;

    /**
     * 添加
     *
     * @param to      地区收入周信息
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) ArrivalWeekTO to, HttpServletRequest request) throws ActException {
        try {
            ArrivalWeekBO bo = arrivalWeekAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 地区收入周信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ArrivalWeekTO to) throws ActException {
        try {
            arrivalWeekAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 地区收入周id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            arrivalWeekAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     地区收入周分页信息
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ArrivalWeekDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekBO> list = arrivalWeekAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      地区收入周id
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/arrivalweek/{id}")
    public Result arrivalweek(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ArrivalWeekBO bo = arrivalWeekAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param request 请求对象
     * @return class ArrivalWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekCountBO> list = arrivalWeekAPI.count();
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分地区汇总
     *
     * @param arrivals 地区数组
     * @param request  请求对象
     * @return class ArrivalWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/conditionsCount/{arrivals}")
    public Result conditionsCount(@PathVariable String[] arrivals, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekCountBO> list = arrivalWeekAPI.conditionsCount(arrivals);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}