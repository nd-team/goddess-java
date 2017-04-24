package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.entity.CarInsure;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.entity.CarInsure;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 车险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class CarInsureSerImpl extends ServiceImpl<CarInsure, CarInsureDTO> implements CarInsureSer {

    @Override
    public Long countCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        Long count = super.count(carInsureDTO);
        return count;
    }

    @Override
    public List<CarInsureBO> listCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        carInsureDTO.getSorts().add("createTime=desc");
        List<CarInsure> list = super.findByCis(carInsureDTO,true);

        return BeanTransform.copyProperties(list, CarInsureBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO addCarInsure(CarInsureTO carInsureTO) throws SerException {
        CarInsure carInsure = BeanTransform.copyProperties(carInsureTO,CarInsure.class,true);
        carInsure.setCreateTime(LocalDateTime.now());
        super.save( carInsure );
        return BeanTransform.copyProperties(carInsure, CarInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editCarInsure(CarInsureTO carInsureTO) throws SerException {
        CarInsure carInsure = BeanTransform.copyProperties(carInsureTO,CarInsure.class,true);
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        BeanUtils.copyProperties(carInsure , cusLevel ,"id","createTime");
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(carInsure, CarInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCarInsure(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editCar(CarInsureTO carInsureTO) throws SerException {
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setInsurer( carInsureTO.getInsurer());
        cusLevel.setInsureIdCard( carInsureTO.getInsureIdCard());
        cusLevel.setInsureAddr( carInsureTO.getInsureAddr());
        cusLevel.setTel( carInsureTO.getTel());
        cusLevel.setCarNumber( carInsureTO.getCarNumber());
        cusLevel.setBrand( carInsureTO.getBrand());
        cusLevel.setPriceChoice( carInsureTO.getPriceChoice());
        cusLevel.setOwnNature( carInsureTO.getOwnNature());
        cusLevel.setUseNature( carInsureTO.getUseNature());
        cusLevel.setCarType( carInsureTO.getCarType());
        cusLevel.setEngineNumber( carInsureTO.getEngineNumber());
        cusLevel.setTransferCar( carInsureTO.getTransferCar());
        cusLevel.setIdentityCode( carInsureTO.getIdentityCode());
        cusLevel.setApprovePassenger( carInsureTO.getApprovePassenger());
        cusLevel.setApproveLoad( carInsureTO.getApproveLoad());
        cusLevel.setOutputVolume( carInsureTO.getOutputVolume());
        cusLevel.setPower( carInsureTO.getPower());
        cusLevel.setCarInitialDate( LocalDate.parse( carInsureTO.getCarInitialDate()));
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editContext(CarInsureTO carInsureTO) throws SerException {
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setInsureType( carInsureTO.getInsureType());
        cusLevel.setOrNotFee( carInsureTO.getOrNotFee());
        cusLevel.setRateFloat( carInsureTO.getRateFloat());
        cusLevel.setInsureLimitFee( carInsureTO.getInsureLimitFee());
        cusLevel.setInsureFee( carInsureTO.getInsureFee());
        cusLevel.setInsureTotalFee( carInsureTO.getInsureTotalFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editAppoint(CarInsureTO carInsureTO) throws SerException {
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setContentDetail( carInsureTO.getContentDetail());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editSale(CarInsureTO carInsureTO) throws SerException {
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setOrganName( carInsureTO.getOrganName());
        cusLevel.setOperateName( carInsureTO.getOperateName());
        cusLevel.setOrganAddr( carInsureTO.getOrganAddr());
        cusLevel.setOrganContact( carInsureTO.getOrganContact());
        cusLevel.setInterAddr( carInsureTO.getInterAddr());
        cusLevel.setPostCode( carInsureTO.getPostCode());
        cusLevel.setSignDate( LocalDate.parse(carInsureTO.getSignDate()));
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }

    @Override
    public CarInsureBO getCarInsure(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( id  );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }
    
}