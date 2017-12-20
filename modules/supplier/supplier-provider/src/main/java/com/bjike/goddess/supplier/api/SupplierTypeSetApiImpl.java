package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierTypeSetBO;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.service.SupplierTypeSetSer;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商类型设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supplierTypeSetApiImpl")
public class SupplierTypeSetApiImpl implements SupplierTypeSetAPI {
    @Autowired
    private SupplierTypeSetSer supplierTypeSetSer;

    @Override
    public Long countSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        return supplierTypeSetSer.countSupplierTypeSet(supplierTypeSetDTO);
    }

    @Override
    public SupplierTypeSetBO getOneById(String id) throws SerException {
        return supplierTypeSetSer.getOneById(id);
    }

    @Override
    public List<SupplierTypeSetBO> listSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        return supplierTypeSetSer.listSupplierTypeSet(supplierTypeSetDTO);
    }

    @Override
    public SupplierTypeSetBO addSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        return supplierTypeSetSer.addSupplierTypeSet(supplierTypeSetTO);
    }

    @Override
    public SupplierTypeSetBO editSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        return supplierTypeSetSer.editSupplierTypeSet(supplierTypeSetTO);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        supplierTypeSetSer.deleteSupplierInfo(id);
    }

    @Override
    public List<String> findAllType() throws SerException {
        return supplierTypeSetSer.findAllType();
    }
}