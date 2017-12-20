package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierTypeSetBO;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;

import java.util.List;

/**
 * 供应商类型设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierTypeSetAPI {
    /**
     * 供应商类型设置总条数
     */
    default Long countSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商类型设置
     *
     * @return class SupplierTypeSetBO
     */
    default SupplierTypeSetBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 供应商类型设置列表
     *
     * @return class SupplierTypeSetBO
     */
    default List<SupplierTypeSetBO> listSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param supplierTypeSetTO 供应商类型设置
     * @return class SupplierTypeSetBO
     */
    default SupplierTypeSetBO addSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param supplierTypeSetTO 供应商类型设置
     * @return class SupplierTypeSetBO
     */
    default SupplierTypeSetBO editSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSupplierInfo(String id) throws SerException {
        return;
    }

    /**
     * 获取所有的供应商类型
     */
    default List<String> findAllType() throws SerException {
        return null;
    }
}