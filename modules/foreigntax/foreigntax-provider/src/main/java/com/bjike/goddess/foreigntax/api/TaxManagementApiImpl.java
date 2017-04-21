package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.TaxCollectBO;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.entity.TaxManagement;
import com.bjike.goddess.foreigntax.service.TaxManagementSer;
import com.bjike.goddess.foreigntax.to.CollectTo;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
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
    public Long countTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return taxManagementSer.countTaxManagement(taxManagementDTO);
    }
    @Override
    public List<TaxManagementBO> findListTaxManagement(TaxManagementDTO taxManagementDTO) throws SerException {
        return  taxManagementSer.findListTaxManagement(taxManagementDTO);
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
    public void upload() throws SerException {
        taxManagementSer.upload();

    }
    @Override
    public TaxManagementBO viewTaxManagement(String company,String taxType,String month) throws SerException {
        return taxManagementSer.viewTaxManagement(company, taxType, month);

    }
    @Override
    public List<TaxCollectBO> collectTaxManagement(CollectTo to) throws SerException {
        return taxManagementSer.collectTaxManagement(to);

    }

}