package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.CarRentalAgreementBO;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.entity.CarRentalAgreement;
import com.bjike.goddess.contractware.to.CarRentalAgreementTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租车协议业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class CarRentalAgreementSerImpl extends ServiceImpl<CarRentalAgreement, CarRentalAgreementDTO> implements CarRentalAgreementSer {

    @Override
    public Long countCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        Long count = super.count(carRentalAgreementDTO);
        return count;
    }

    @Override
    public CarRentalAgreementBO getOne(String id) throws SerException {
        CarRentalAgreement carRentalAgreement = super.findById(id);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }

    @Override
    public List<CarRentalAgreementBO> findListCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        List<CarRentalAgreement> carRentalAgreement = super.findByPage(carRentalAgreementDTO);
        List<CarRentalAgreementBO> carRentalAgreementBOS = BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
        return carRentalAgreementBOS;
    }

    @Override
    public CarRentalAgreementBO insertCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        CarRentalAgreement carRentalAgreement = BeanTransform.copyProperties(carRentalAgreementTO,CarRentalAgreement.class,true);
        carRentalAgreement.setCreateTime(LocalDateTime.now());
        super.save(carRentalAgreement);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }

    @Override
    public CarRentalAgreementBO editCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        CarRentalAgreement carRentalAgreement = super.findById(carRentalAgreementTO.getId());
        BeanTransform.copyProperties(carRentalAgreementTO,carRentalAgreement,true);
        carRentalAgreement.setModifyTime(LocalDateTime.now());
        super.update(carRentalAgreement);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }

    @Override
    public void removeCarRentalAgreement(String id) throws SerException {
        super.remove(id);
    }

}