package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.CompareCollectBO;
import com.bjike.goddess.foreigntax.bo.IncomeInvoiceBO;
import com.bjike.goddess.foreigntax.dto.IncomeCollectDTO;
import com.bjike.goddess.foreigntax.dto.IncomeInvoiceDTO;
import com.bjike.goddess.foreigntax.service.IncomeInvoiceSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.IncomeInvoiceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进项发票业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("incomeInvoiceApiImpl")
public class IncomeInvoiceApiImpl implements IncomeInvoiceAPI {
    @Autowired
    private IncomeInvoiceSer incomeInvoiceSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return incomeInvoiceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return incomeInvoiceSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(IncomeInvoiceDTO dto) throws SerException {
        return incomeInvoiceSer.count(dto);
    }

    @Override
    public IncomeInvoiceBO getOne(String id) throws SerException {
        return incomeInvoiceSer.getOne(id);
    }

    @Override
    public List<IncomeInvoiceBO> list(IncomeInvoiceDTO dto) throws SerException {
        return incomeInvoiceSer.list(dto);
    }

    @Override
    public IncomeInvoiceBO insert(IncomeInvoiceTO to) throws SerException {
        return incomeInvoiceSer.insert(to);
    }

    @Override
    public IncomeInvoiceBO edit(IncomeInvoiceTO to) throws SerException {
        return incomeInvoiceSer.edit(to);
    }
    @Override
    public void remove(String id) throws SerException {
        incomeInvoiceSer.remove(id);
    }
    @Override
    public List<IncomeInvoiceBO> collect(IncomeCollectDTO dto) throws SerException {
        return incomeInvoiceSer.collect(dto);
    }
    @Override
    public List<IncomeInvoiceBO> checkCollect(IncomeInvoiceDTO dto) throws SerException {
        return incomeInvoiceSer.checkCollect(dto);
    }

    @Override
    public List<CompareCollectBO> compareCollect(IncomeCollectDTO dto) throws SerException {
        return incomeInvoiceSer.compareCollect(dto);
    }
}