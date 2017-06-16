package com.bjike.goddess.projectcost.action.projectcost;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.api.CarCostAPI;
import com.bjike.goddess.projectcost.dto.CarCostDTO;
import com.bjike.goddess.projectcost.to.CarCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.vo.CarCostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 车辆费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:14 ]
 * @Description: [ 车辆费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("carcost")
public class CarCostAct {

    @Autowired
    private CarCostAPI carCostAPI;

    /**
     * 保存
     *
     * @param to 车辆费用传输对象
     * @return class CarCostVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CarCostTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.save(to), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 车辆费用传输对象
     * @return class CarCostVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CarCostTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.update(to), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 车辆费用数据id
     * @return class CarCostVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.delete(id), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 车辆费用数据传输对象
     * @return class CarCostVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CarCostDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.maps(dto), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改实际车次
     *
     * @param to 车辆费用传输对象
     * @return class CarCostVO
     * @version v1
     */
    @PutMapping("v1/updateActual/{id}")
    public Result updateActual(CarCostTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.updateActual(to), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id 车辆费用数据id
     * @return class CarCostVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.getById(id), CarCostVO.class, request));
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
            return ActResult.initialize(carCostAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据查询条件查询数据
     *
     * @param to 查询条件传输对象
     * @return class CarCostVO
     * @version v1
     */
    @GetMapping("v1/findByTo")
    public Result findByTO(FindTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(carCostAPI.findByTO(to), CarCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}