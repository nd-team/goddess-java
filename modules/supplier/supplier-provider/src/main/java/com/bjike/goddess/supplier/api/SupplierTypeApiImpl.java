package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierTypeBO;
import com.bjike.goddess.supplier.service.SupplierTypeSer;
import com.bjike.goddess.supplier.to.SupplierTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商类型管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T14:12:54.997 ]
 * @Description: [ 供应商类型管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supplierTypeApiImpl")
public class SupplierTypeApiImpl implements SupplierTypeAPI {

    @Autowired
    private SupplierTypeSer supplierTypeSer;

    @Override
    public List<SupplierTypeBO> findStatus() throws SerException {
        return supplierTypeSer.findStatus();
    }

    @Override
    public SupplierTypeBO save(SupplierTypeTO to) throws SerException {
        return supplierTypeSer.save(to);
    }

    @Override
    public SupplierTypeBO update(SupplierTypeTO to) throws SerException {
        return supplierTypeSer.update(to);
    }

    @Override
    public SupplierTypeBO delete(SupplierTypeTO to) throws SerException {
        return supplierTypeSer.delete(to);
    }

    @Override
    public SupplierTypeBO congeal(SupplierTypeTO to) throws SerException {
        return supplierTypeSer.congeal(to);
    }

    @Override
    public SupplierTypeBO thaw(SupplierTypeTO to) throws SerException {
        return supplierTypeSer.thaw(to);
    }
}