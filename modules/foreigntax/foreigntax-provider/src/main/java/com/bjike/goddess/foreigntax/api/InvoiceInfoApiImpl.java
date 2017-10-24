package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.InvoiceInfoBO;
import com.bjike.goddess.foreigntax.dto.InvoiceInfoDTO;
import com.bjike.goddess.foreigntax.service.InvoiceInfoSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 发票基本登记业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("invoiceInfoApiImpl")
public class InvoiceInfoApiImpl implements InvoiceInfoAPI {
    @Autowired
    private InvoiceInfoSer invoiceInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return invoiceInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return invoiceInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(InvoiceInfoDTO dto) throws SerException {
        return invoiceInfoSer.count(dto);
    }

    @Override
    public InvoiceInfoBO getOne(String id) throws SerException {
        return invoiceInfoSer.getOne(id);
    }

    @Override
    public List<InvoiceInfoBO> list(InvoiceInfoDTO dto) throws SerException {
        return invoiceInfoSer.list(dto);
    }

    @Override
    public InvoiceInfoBO insert(InvoiceInfoTO to) throws SerException {
        return invoiceInfoSer.insert(to);
    }

    @Override
    public InvoiceInfoBO edit(InvoiceInfoTO to) throws SerException {
        return invoiceInfoSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        invoiceInfoSer.remove(id);
    }

    @Override
    public Set<String> fullTicket() throws SerException {
        return invoiceInfoSer.fullTicket();
    }

    @Override
    public Set<String> ticketWay() throws SerException {
        return invoiceInfoSer.ticketWay();
    }

}