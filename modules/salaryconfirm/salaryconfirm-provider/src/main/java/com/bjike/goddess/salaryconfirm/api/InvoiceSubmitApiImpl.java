package com.bjike.goddess.salaryconfirm.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.excel.SonPermissionObject;
import com.bjike.goddess.salaryconfirm.service.InvoiceSubmitSer;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 上交发票业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("invoiceSubmitApiImpl")
public class InvoiceSubmitApiImpl implements InvoiceSubmitAPI {

    @Autowired
    private InvoiceSubmitSer invoiceSubmitSer;

    @Override
    public InvoiceSubmitBO add(InvoiceSubmitTO to) throws SerException {
        return invoiceSubmitSer.insertModel(to);
    }

    @Override
    public InvoiceSubmitBO edit(InvoiceSubmitTO to) throws SerException {
        return invoiceSubmitSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        invoiceSubmitSer.remove(id);
    }

    @Override
    public List<InvoiceSubmitBO> pageList(InvoiceSubmitDTO dto) throws SerException {
        return invoiceSubmitSer.pageList(dto);
    }

    @Override
    public List<InvoiceSubmitBO> findByCondition(String name, Integer year, Integer month) throws SerException {
        return invoiceSubmitSer.findByCondition(name,year,month);
    }

    @Override
    public InvoiceSubmitBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(invoiceSubmitSer.findById(id),InvoiceSubmitBO.class);
    }

    @Override
    public Long count(InvoiceSubmitDTO dto) throws SerException {
        return invoiceSubmitSer.count(dto);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return invoiceSubmitSer.guidePermission(to);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return invoiceSubmitSer.sonPermission();
    }
}