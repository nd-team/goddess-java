package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceManagementBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.service.InvoiceManagementSer;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.InvoiceManagementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 发票管理业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("invoiceManagementApiImpl")
public class InvoiceManagementApiImpl implements InvoiceManagementAPI  {
    @Autowired
    private InvoiceManagementSer invoiceManagementSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return invoiceManagementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return invoiceManagementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(InvoiceManagementTO invoiceManagementTO) throws SerException {
        invoiceManagementSer.add(invoiceManagementTO);
    }

    @Override
    public void delete(String id) throws SerException {
        invoiceManagementSer.delete(id);
    }

    @Override
    public void modify(InvoiceManagementTO invoiceManagementTO) throws SerException {
        invoiceManagementSer.modify(invoiceManagementTO);
    }

    @Override
    public List<InvoiceManagementBO> pageList(InvoiceManagementDTO invoiceManagementDTO) throws SerException {
        return invoiceManagementSer.pageList(invoiceManagementDTO);
    }

    @Override
    public Long count(InvoiceManagementDTO invoiceManagementDTO) throws SerException {
        return invoiceManagementSer.count(invoiceManagementDTO);
    }

    @Override
    public InvoiceManagementBO findOne(String id) throws SerException {
        return invoiceManagementSer.findOne(id);
    }

    @Override
    public ContractManagementBO findByNumber(String number) throws SerException {
        return invoiceManagementSer.findByNumber(number);
    }

    @Override
    public void updateElectronic(String id) throws SerException {
        invoiceManagementSer.updateElectronic(id);
    }
}