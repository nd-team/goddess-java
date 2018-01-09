package com.bjike.goddess.business.api;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.bo.BusinessRegisterListBO;
import com.bjike.goddess.business.bo.RegisterNaTypeCaBO;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.excel.SonPermissionObject;
import com.bjike.goddess.business.service.BusinessRegisterSer;
import com.bjike.goddess.business.to.BusinessRegisterExcelTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工商注册业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessRegisterApiImpl")
public class BusinessRegisterApiImpl implements BusinessRegisterAPI {
    @Autowired
    private BusinessRegisterSer businessRegisterSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return businessRegisterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessRegisterSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return businessRegisterSer.countBusinessRegister(businessRegisterDTO);
    }

    @Override
    public BusinessRegisterBO getOne(String id) throws SerException {
        return businessRegisterSer.getOne(id);
    }

    @Override
    public List<BusinessRegisterListBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return businessRegisterSer.findListBusinessRegister(businessRegisterDTO);
    }

    @Override
    public BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return businessRegisterSer.insertBusinessRegister(businessRegisterTO);
    }

    @Override
    public BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return businessRegisterSer.editBusinessRegister(businessRegisterTO);
    }

    @Override
    public void removeBusinessRegister(String id) throws SerException {
        businessRegisterSer.removeBusinessRegister(id);
    }

    @Override
    public List<RegisterNaTypeCaBO> findRegiNaTyCa() throws SerException {
        return businessRegisterSer.findRegiNaTyCa();
    }

    @Override
    public List<String> findAddress() throws SerException {
        return businessRegisterSer.findAddress();
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return businessRegisterSer.exportExcel();
    }

    @Override
    public void importExcel(List<BusinessRegisterExcelTO> businessRegisterExcelTOS) throws SerException {
        businessRegisterSer.importExcel(businessRegisterExcelTOS);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return businessRegisterSer.templateExport();
    }

    @Override
    public BusinessRegisterListBO findOneById(String id) throws SerException {
        return businessRegisterSer.findOneById(id);
    }
}