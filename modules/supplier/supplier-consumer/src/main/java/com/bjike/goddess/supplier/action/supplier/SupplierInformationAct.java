package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.SupplierInformationAPI;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SupplierInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 供应商基本信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.055 ]
 * @Description: [ 供应商基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplierinformation")
public class SupplierInformationAct {

    @Autowired
    private SupplierInformationAPI supplierInformationAPI;

    /**
     * 保存供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.save(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.update(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商详细信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @PutMapping("v1/updateDetail/{id}")
    public Result updateDetail(@Validated(ADD.class) SupplierInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.updateDetail(to), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询根据供应商名称排序的供应商信息
     *
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/findOrderName")
    public Result findOrderName() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.findOrderName(), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 供应商基本信息数据id
     * @return class SupplierInformationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.delete(id), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 供应商基本信息数据传输对象
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SupplierInformationDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.maps(dto), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取供应商基本信息数据
     *
     * @param id 供应商基本信息数据id
     * @return class SupplierInformationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.getById(id), SupplierInformationVO.class, request));
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
            return ActResult.initialize(supplierInformationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}