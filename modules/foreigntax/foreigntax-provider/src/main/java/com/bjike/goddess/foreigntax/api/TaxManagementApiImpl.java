package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.TaxCollectBO;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.service.TaxManagementSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long countTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return taxManagementSer.countTaxManagement(taxManagementDTO);
    }

    @Override
    public TaxManagementBO getOne(String id) throws SerException {
        return taxManagementSer.getOne(id);
    }

    @Override
    public List<TaxManagementBO> findListTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return taxManagementSer.findListTaxManagement(taxManagementDTO);
    }

    @Override
    public TaxManagementBO insertTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        return taxManagementSer.insertTaxManagement(taxManagementTO);
    }

    @Override
    public TaxManagementBO editTaxManagement(TaxManagementTO taxManagementTO) throws SerException {
        return taxManagementSer.editTaxManagement(taxManagementTO);
    }

    @Override
    public void removeTaxManagement(String id) throws SerException {
        taxManagementSer.removeTaxManagement(id);
    }

    @Override
    public List<TaxCollectBO> collectTaxManagement(String[] company) throws SerException {
        return taxManagementSer.collectTaxManagement(company);
    }
    @Override
    public List<String> getCompany() throws SerException {
        return taxManagementSer.getCompany();
    }

    @Override
    public List<TaxManagementBO> listByCompany(String company, String monthStart, String monthEnd) throws SerException {
        return taxManagementSer.listByCompany(company, monthStart, monthEnd);
    }
}