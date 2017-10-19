package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.OutputInvoiceBO;
import com.bjike.goddess.foreigntax.dto.OutputInvoiceDTO;
import com.bjike.goddess.foreigntax.entity.OutputInvoice;
import com.bjike.goddess.foreigntax.service.OutputInvoiceSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.OutputInvoiceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销项发票业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:43 ]
 * @Description: [ 销项发票业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("outputInvoiceApiImpl")
public class OutputInvoiceApiImpl implements OutputInvoiceAPI {

    @Autowired
    private OutputInvoiceSer outputInvoiceSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return outputInvoiceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return outputInvoiceSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(OutputInvoiceDTO dto) throws SerException {
        return outputInvoiceSer.count(dto);
    }

    @Override
    public OutputInvoiceBO getOne(String id) throws SerException {
        return outputInvoiceSer.getOne(id);
    }

    @Override
    public List<OutputInvoiceBO> list(OutputInvoiceDTO dto) throws SerException {
        return outputInvoiceSer.list(dto);
    }

    @Override
    public OutputInvoiceBO insert(OutputInvoiceTO to) throws SerException {
        return outputInvoiceSer.insert(to);
    }

    @Override
    public OutputInvoiceBO edit(OutputInvoiceTO to) throws SerException {
        return outputInvoiceSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        outputInvoiceSer.remove(id);
    }

    @Override
    public List<OutputInvoiceBO> collect(Integer year, Integer month) throws SerException {
        return outputInvoiceSer.collect(year,month);
    }

}