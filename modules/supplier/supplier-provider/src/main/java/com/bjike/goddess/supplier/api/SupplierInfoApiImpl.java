package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.service.SupplierInfoSer;
import com.bjike.goddess.supplier.to.SupplierInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商信息管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supplierInfoApiImpl")
public class SupplierInfoApiImpl implements SupplierInfoAPI {
    @Autowired
    private SupplierInfoSer supplierInfoSer;

    @Override
    public Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        return supplierInfoSer.countSupplierInfo(supplierInfoDTO);
    }

    @Override
    public SupplierInfoBO getOneById(String id) throws SerException {
        return supplierInfoSer.getOneById(id);
    }

    @Override
    public List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO headersCustomDTO) throws SerException {
        return supplierInfoSer.listSupplierInfo(headersCustomDTO);
    }

    @Override
    public SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return supplierInfoSer.addSupplierInfo(supplierInfoTO);
    }

    @Override
    public SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return supplierInfoSer.editSupplierInfo(supplierInfoTO);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        supplierInfoSer.deleteSupplierInfo(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return supplierInfoSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return supplierInfoSer.templateExport();
    }

    @Override
    public void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException {
        supplierInfoSer.importExcel(supplierInfoTOList);
    }
}