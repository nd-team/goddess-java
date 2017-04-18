package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.SupplierTypeAPI;
import com.bjike.goddess.supplier.to.SupplierTypeTO;
import com.bjike.goddess.supplier.vo.SupplierTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商类型管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.988 ]
 * @Description: [ 供应商类型管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("suppliertype")
public class SupplierTypeAction {

    @Autowired
    private SupplierTypeAPI supplierTypeAPI;


    /**
     * 查询未冻结的供应商类型
     *
     * @return class SupplierTypeVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.findStatus(), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return class SupplierTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SupplierTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.save(to), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return class SupplierTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SupplierTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.update(to), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return class SupplierTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(SupplierTypeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.delete(to), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return class SupplierTypeVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(SupplierTypeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.congeal(to), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻供应商类型数据
     *
     * @param to 供应商类型传输对象
     * @return class SupplierTypeVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(SupplierTypeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierTypeAPI.thaw(to), SupplierTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}