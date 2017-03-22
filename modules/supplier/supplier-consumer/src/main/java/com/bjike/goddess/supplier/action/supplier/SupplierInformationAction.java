package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.SupplierInformationAPI;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SupplierInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("supplier/supplierinformation")
public class SupplierInformationAction {

    @Autowired
    private SupplierInformationAPI supplierInformationAPI;

    /**
     * 保存供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return class SupplierInformationVO
     */
    @PostMapping("save")
    public Result save(@Validated SupplierInformationTO to) throws ActException {
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
     */
    @PutMapping("update/{id}")
    public Result update(@Validated SupplierInformationTO to) throws ActException {
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
     */
    @PutMapping("updateDetail/{id}")
    public Result updateDetail(@Validated SupplierInformationTO to) throws ActException {
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
     */
    @GetMapping("findOrderName")
    public Result findOrderName() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(supplierInformationAPI.findOrderName(), SupplierInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}