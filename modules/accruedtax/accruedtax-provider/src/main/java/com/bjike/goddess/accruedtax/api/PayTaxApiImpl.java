package com.bjike.goddess.accruedtax.api;

import com.bjike.goddess.accruedtax.bo.PayTaxBO;
import com.bjike.goddess.accruedtax.dto.PayTaxDTO;
import com.bjike.goddess.accruedtax.service.PayTaxSer;
import com.bjike.goddess.accruedtax.to.GuidePermissionTO;
import com.bjike.goddess.accruedtax.to.PayTaxTO;
import com.bjike.goddess.accruedtax.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应交税金业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("payTaxApiImpl")
public class PayTaxApiImpl implements PayTaxAPI {

    @Autowired
    private PayTaxSer payTaxSer;

    @Override
    public Long countPayTax(PayTaxDTO payTaxDTO) throws SerException {
        return payTaxSer.countPayTax( payTaxDTO);
    }

    @Override
    public PayTaxBO getOneById(String id) throws SerException {
        return payTaxSer.getOneById(id);
    }

    @Override
    public List<PayTaxBO> listPayTax(PayTaxDTO payTaxDTO) throws SerException {
        return payTaxSer.listPayTax( payTaxDTO );
    }

    @Override
    public PayTaxBO addPayTax(PayTaxTO payTaxTO) throws SerException {
        return payTaxSer.addPayTax( payTaxTO );
    }

    @Override
    public PayTaxBO editPayTax(PayTaxTO payTaxTO) throws SerException {
        return payTaxSer.editPayTax( payTaxTO );
    }

    @Override
    public void deletePayTax(String id) throws SerException {
        payTaxSer.deletePayTax( id );
    }

    @Override
    public PayTaxBO splitTax(PayTaxTO payTaxTO) throws SerException {
        return payTaxSer.splitTax( payTaxTO);
    }

    @Override
    public List<PayTaxBO> collectCompany(PayTaxDTO payTaxDTO) throws SerException {
        return payTaxSer.collectCompany( payTaxDTO);
    }

    @Override
    public List<PayTaxBO> collectTaxType(PayTaxDTO payTaxDTO) throws SerException {
        return payTaxSer.collectTaxType(payTaxDTO);
    }

    @Override
    public List<String> listCompany() throws SerException {
        return payTaxSer.listCompany();
    }

    @Override
    public List<String> listTaxType() throws SerException {
        return payTaxSer.listTaxType();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return payTaxSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return payTaxSer.guidePermission(guidePermissionTO);
    }
}