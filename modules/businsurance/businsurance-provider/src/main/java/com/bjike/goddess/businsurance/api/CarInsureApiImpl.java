package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.service.CarInsureSer;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车险信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("carInsureApiImpl")
public class CarInsureApiImpl implements CarInsureAPI {

    @Autowired
    private CarInsureSer carInsureSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return carInsureSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return carInsureSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        return carInsureSer.count(carInsureDTO);
    }

    @Override
    public List<CarInsureBO> listCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        return carInsureSer.listCarInsure(carInsureDTO);
    }

    @Override
    public CarInsureBO addCarInsure(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.addCarInsure(carInsureTO);
    }

    @Override
    public CarInsureBO editCarInsure(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.editCarInsure(carInsureTO);
    }

    @Override
    public void deleteCarInsure(String id) throws SerException {
        carInsureSer.deleteCarInsure(id);
    }

    @Override
    public CarInsureBO editCar(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.editCar(carInsureTO);
    }

    @Override
    public CarInsureBO editContext(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.editContext(carInsureTO);
    }

    @Override
    public CarInsureBO editAppoint(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.editAppoint(carInsureTO);
    }
    @Override
    public CarInsureBO editSale(CarInsureTO carInsureTO) throws SerException {
        return carInsureSer.editSale(carInsureTO);
    }

    @Override
    public CarInsureBO getCarInsure(String id) throws SerException {
        return carInsureSer.getCarInsure(id);
    }
    
}