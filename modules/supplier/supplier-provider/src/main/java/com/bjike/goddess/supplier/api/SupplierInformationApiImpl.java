package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.service.SupplierInformationSer;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商基本信息业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.059 ]
 * @Description: [ 供应商基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("supplierInformationApiImpl")
public class SupplierInformationApiImpl implements SupplierInformationAPI {

    @Autowired
    private SupplierInformationSer supplierInformationSer;

    @Override
    public SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        return supplierInformationSer.save(to);
    }

    @Override
    public SupplierInformationBO update(SupplierInformationTO to) throws SerException {
        return supplierInformationSer.update(to);
    }

    @Override
    public SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
        return supplierInformationSer.updateDetail(to);
    }

    @Override
    public List<SupplierInformationBO> findOrderName() throws SerException {
        return supplierInformationSer.findOrderName();
    }

    @Override
    public SupplierInformationBO delete(String id) throws SerException {
        return supplierInformationSer.delete(id);
    }

    @Override
    public List<SupplierInformationBO> maps(SupplierInformationDTO dto) throws SerException {
        return supplierInformationSer.maps(dto);
    }

    @Override
    public SupplierInformationBO getById(String id) throws SerException {
        return supplierInformationSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        SupplierInformationDTO dto = new SupplierInformationDTO();
        return supplierInformationSer.count(dto);
    }

    @Override
    public void changeEnclosure(String id) throws SerException {
        supplierInformationSer.changeEnclosure(id);
    }

    @Override
    public List<SupplierInfoCollectBO> collect(CollectTo to) throws SerException {
        return supplierInformationSer.collect(to);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return supplierInformationSer.sonPermission();
    }

    @Override
    public List<SupplierInformationBO> findByName(String name) throws SerException {
        return supplierInformationSer.findByName(name);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return supplierInformationSer.guidePermission(guidePermissionTO);
    }
}