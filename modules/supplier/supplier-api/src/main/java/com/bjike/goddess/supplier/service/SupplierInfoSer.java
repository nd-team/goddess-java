package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.entity.SupplierInfo;
import com.bjike.goddess.supplier.to.SupplierInfoTO;

import java.util.List;

/**
 * 供应商信息管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInfoSer extends Ser<SupplierInfo, SupplierInfoDTO> {
    /**
     * 供应商信息管理总条数
     */
    default Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商信息管理
     *
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 供应商信息管理列表
     *
     * @return class SupplierInfoBO
     */
    default List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO headersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
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
     * 导出Excel
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     *  导入
     * @param supplierInfoTOList 工龄补助
     */
    void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException;

}