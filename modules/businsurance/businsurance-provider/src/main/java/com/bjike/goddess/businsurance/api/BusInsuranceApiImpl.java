package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.service.BusInsuranceSer;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商业保险方案业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("busInsuranceApiImpl")
public class BusInsuranceApiImpl implements BusInsuranceAPI {

    @Autowired
    private BusInsuranceSer busInsuranceSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return busInsuranceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return busInsuranceSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        return busInsuranceSer.count(busInsuranceDTO);
    }

    @Override
    public List<BusInsuranceBO> listBusInsurance(BusInsuranceDTO busInsuranceDTO) throws SerException {
        return busInsuranceSer.listBusInsurance(busInsuranceDTO);
    }

    @Override
    public BusInsuranceBO addBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.addBusInsurance(busInsuranceTO);
    }

    @Override
    public BusInsuranceBO editBusInsurance(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.editBusInsurance(busInsuranceTO);
    }

    @Override
    public void deleteBusInsurance(String id) throws SerException {
        busInsuranceSer.deleteBusInsurance(id);
    }

    @Override
    public BusInsuranceBO editBuyCondition(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.editBuyCondition(busInsuranceTO);
    }

    @Override
    public BusInsuranceBO editAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.editAdvice(busInsuranceTO);
    }

    @Override
    public BusInsuranceBO editBusAdvice(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.editBusAdvice(busInsuranceTO);
    }

    @Override
    public BusInsuranceBO audit(BusInsuranceTO busInsuranceTO) throws SerException {
        return busInsuranceSer.audit(busInsuranceTO);
    }

    @Override
    public BusInsuranceBO getBusInsurance(String id) throws SerException {
        return busInsuranceSer.getBusInsurance(id);
    }

    @Override
    public byte[] exportExcel(BusInsuranceDTO busInsuranceDTO) throws SerException {
        return busInsuranceSer.exportExcel(busInsuranceDTO);
    }

    @Override
    public List<String> getAllInsureComapny() throws SerException {
        return busInsuranceSer.getAllInsureComapny();
    }

    @Override
    public List<String> getAllInsureType() throws SerException {
        return busInsuranceSer.getAllInsureType();
    }

    @Override
    public List<String> getAllInsureCondition() throws SerException {
        return busInsuranceSer.getAllInsureCondition();
    }
}