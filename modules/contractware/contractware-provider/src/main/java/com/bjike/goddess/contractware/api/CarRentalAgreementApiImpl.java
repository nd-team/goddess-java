package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.CarRentalAgreementBO;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.entity.CarRentalAgreement;
import com.bjike.goddess.contractware.service.CarRentalAgreementSer;
import com.bjike.goddess.contractware.to.CarRentalAgreementTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租车协议业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("carRentalAgreementApiImpl")
public class CarRentalAgreementApiImpl implements CarRentalAgreementAPI {

    @Autowired
    private CarRentalAgreementSer carRentalAgreementSer;
    @Override
    public Long countCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        return carRentalAgreementSer.countCarRentalAgreement(carRentalAgreementDTO);
    }

    @Override
    public CarRentalAgreementBO getOne(String id) throws SerException {
        return carRentalAgreementSer.getOne(id);
    }

    @Override
    public List<CarRentalAgreementBO> findListCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        return carRentalAgreementSer.findListCarRentalAgreement(carRentalAgreementDTO);
    }

    @Override
    public CarRentalAgreementBO insertCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        return carRentalAgreementSer.insertCarRentalAgreement(carRentalAgreementTO);
    }

    @Override
    public CarRentalAgreementBO editCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        return carRentalAgreementSer.editCarRentalAgreement(carRentalAgreementTO);
    }

    @Override
    public void removeCarRentalAgreement(String id) throws SerException {
        carRentalAgreementSer.removeCarRentalAgreement(id);
    }

}