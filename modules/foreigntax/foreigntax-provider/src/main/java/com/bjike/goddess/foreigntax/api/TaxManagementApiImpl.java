package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.bo.VoucherDataBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.service.TaxManagementSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.foreigntax.to.VoucherDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 税金管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("taxManagementApiImpl")
public class TaxManagementApiImpl implements TaxManagementAPI {
    @Autowired
    private TaxManagementSer taxManagementSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return taxManagementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return taxManagementSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(TaxManagementDTO dto) throws SerException {
        return taxManagementSer.count(dto);
    }

    @Override
    public TaxManagementBO getOne(String id) throws SerException {
        return taxManagementSer.getOne(id);
    }

    @Override
    public List<TaxManagementBO> list(TaxManagementDTO dto) throws SerException {
        return taxManagementSer.list(dto);
    }

    @Override
    public TaxManagementBO insert(TaxManagementTO to) throws SerException {
        return taxManagementSer.insert(to);
    }

    @Override
    public TaxManagementBO edit(TaxManagementTO to) throws SerException {
        return taxManagementSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        taxManagementSer.remove(id);
    }
    @Override
    public Map<String,String> getDead(String taxEnd)throws SerException{
        return taxManagementSer.getDead(taxEnd);
    }

    @Override
    public List<TaxManagementBO> collect(TaxManagementDTO dto) throws SerException {
        return taxManagementSer.collect(dto);
    }

    @Override
    public List<String> getTaxType() throws SerException {
        return taxManagementSer.getTaxType();
    }

    @Override
    public List<String> getCompany() throws SerException {
        return taxManagementSer.getCompany();
    }
    @Override
    public VoucherDataBO vGenerate(String[] ids) throws SerException {
        return taxManagementSer.vGenerate(ids);
    }
    @Override
    public VoucherDataBO generate(VoucherDataTO to) throws SerException {
        return taxManagementSer.generate(to);
    }

    @Override
    public List<TaxManagementBO> listByCompany(String company, String monthStart, String monthEnd) throws SerException {
        return taxManagementSer.listByCompany(company, monthStart, monthEnd);
    }
}