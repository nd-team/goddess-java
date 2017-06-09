package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.api.WeightAllocationAPI;
import com.bjike.goddess.projectroyalty.dto.WeightAllocationDTO;
import com.bjike.goddess.projectroyalty.to.WeightAllocationTO;
import com.bjike.goddess.projectroyalty.vo.WeightAllocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目提成权重分配
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:12 ]
 * @Description: [ 项目提成权重分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("weightallocation")
public class WeightAllocationAction {

    @Autowired
    private WeightAllocationAPI weightAllocationAPI;

    /**
     * 保存项目提成目标权重分配
     *
     * @param to 项目提成权重分配传输对象
     * @return class WeightAllocationVO
     * @version v1
     */
    @PostMapping("v1/target/save")
    public Result targetSave(@Validated(ADD.class) WeightAllocationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.saveTarget(to), WeightAllocationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存项目提成实际权重分配
     *
     * @param to 项目提成权重分配传输对象
     * @return class WeightAllocationVO
     * @version v1
     */
    @PostMapping("v1/actual/save")
    public Result actualSave(@Validated(ADD.class) WeightAllocationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.saveActual(to), WeightAllocationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 项目提成目标权重分配传输对象
     * @return class WeightAllocationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) WeightAllocationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.update(to), WeightAllocationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目提成权重分配数据id
     * @return class WeightAllocationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.delete(id), WeightAllocationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取业务提成权重分配数据
     *
     * @param id 项目提成权重分配数据id
     * @return class WeightAllocationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.getById(id), WeightAllocationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成目标权重分配列表
     *
     * @param dto 项目提成权重分配数据传输对象
     * @return class WeightAllocationVO
     * @version v1
     */
    @GetMapping("v1/target/maps")
    public Result targetMaps(WeightAllocationDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.targetMaps(dto), WeightAllocationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成实际权重分配列表
     *
     * @param dto 项目提成权重分配数据传输对象
     * @return class WeightAllocationVO
     * @version v1
     */
    @GetMapping("v1/actual/maps")
    public Result actualMaps(WeightAllocationDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weightAllocationAPI.actualMaps(dto), WeightAllocationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成目标权重分配总条数
     *
     * @version v1
     */
    @GetMapping("v1/target/getTotal")
    public Result getTargetTotal() throws ActException {
        try {
            return ActResult.initialize(weightAllocationAPI.getTargetTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成实际权重分配总条数
     *
     * @version v1
     */
    @GetMapping("v1/actual/getTotal")
    public Result getActualTotal() throws ActException {
        try {
            return ActResult.initialize(weightAllocationAPI.getActualTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}