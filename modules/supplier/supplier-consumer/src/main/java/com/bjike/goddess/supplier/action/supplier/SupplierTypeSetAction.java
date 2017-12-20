package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.SupplierTypeSetAPI;
import com.bjike.goddess.supplier.bo.SupplierTypeSetBO;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.entity.SupplierTypeSet;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;
import com.bjike.goddess.supplier.vo.SupplierTypeSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 供应商类型设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("suppliertypeset")
public class SupplierTypeSetAction {
    @Autowired
    private SupplierTypeSetAPI supplierTypeSetAPI;
    /**
     * 根据id查询供应商类型设置
     *
     * @param id 供应商类型设置唯一标识
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/supplierTypeSet/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SupplierTypeSetBO bo = supplierTypeSetAPI.getOneById(id);
            SupplierTypeSetVO vo = BeanTransform.copyProperties(bo, SupplierTypeSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 供应商类型设置dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated SupplierTypeSetDTO dto, BindingResult result) throws ActException {
        try {
            Long count = supplierTypeSetAPI.countSupplierTypeSet(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 供应商类型设置dto
     * @return class SupplierTypeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated SupplierTypeSetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<SupplierTypeSetBO> boList = supplierTypeSetAPI.listSupplierTypeSet(dto);
            List<SupplierTypeSetVO> voList = BeanTransform.copyProperties(boList, SupplierTypeSetVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加供应商类型设置
     *
     * @param to 供应商类型设置to信息
     * @return class SupplierTypeSetVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) SupplierTypeSetTO to, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            SupplierTypeSetBO bo = supplierTypeSetAPI.addSupplierTypeSet(to);
            SupplierTypeSetVO vo = BeanTransform.copyProperties(bo, SupplierTypeSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除供应商类型设置
     *
     * @param id 供应商类型设置唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            supplierTypeSetAPI.deleteSupplierInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑供应商类型设置
     *
     * @param to 供应商类型设置to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) SupplierTypeSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SupplierTypeSetBO bo = supplierTypeSetAPI.editSupplierTypeSet(to);
            SupplierTypeSetVO vo = BeanTransform.copyProperties(bo, SupplierTypeSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}