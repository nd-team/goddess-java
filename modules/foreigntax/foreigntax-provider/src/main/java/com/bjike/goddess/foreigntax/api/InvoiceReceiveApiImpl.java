package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.InvoiceReceiveBO;
import com.bjike.goddess.foreigntax.dto.InvoiceReceiveDTO;
import com.bjike.goddess.foreigntax.entity.InvoiceReceive;
import com.bjike.goddess.foreigntax.service.InvoiceReceiveSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceReceiveTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发票领用业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("invoiceReceiveApiImpl")
public class InvoiceReceiveApiImpl implements InvoiceReceiveAPI {

    @Autowired
    private InvoiceReceiveSer invoiceReceiveSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return invoiceReceiveSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return invoiceReceiveSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(InvoiceReceiveDTO dto) throws SerException {
        return invoiceReceiveSer.count(dto);
    }

    @Override
    public InvoiceReceiveBO getOne(String id) throws SerException {
        return invoiceReceiveSer.getOne(id);
    }

    @Override
    public List<InvoiceReceiveBO> list(InvoiceReceiveDTO dto) throws SerException {
        return invoiceReceiveSer.list(dto);
    }

    @Override
    public InvoiceReceiveBO insert(InvoiceReceiveTO to) throws SerException {
        return invoiceReceiveSer.insert(to);
    }

    @Override
    public InvoiceReceiveBO edit(InvoiceReceiveTO to) throws SerException {
        return invoiceReceiveSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        invoiceReceiveSer.remove(id);
    }
}