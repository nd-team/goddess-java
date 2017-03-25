package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;

import java.util.List;

/**
 * 供应商基本信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.051 ]
 * @Description: [ 供应商基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInformationAPI {

    /**
     * 保存供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商基本信息数据
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO update(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商详细信息数据
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 查询根据供应商名称排序的供应商信息
     *
     * @return
     * @throws SerException
     */
    default List<SupplierInformationBO> findOrderName() throws SerException {
        return null;
    }

}