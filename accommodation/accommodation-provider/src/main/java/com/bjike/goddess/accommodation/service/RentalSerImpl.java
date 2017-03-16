package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租房信息 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房信息 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RentalSerImpl extends ServiceImpl<Rental,RentalDTO> implements RentalSer{
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalBO insertRental(RentalTO rentalTO) throws SerException {
        Rental rental = BeanTransform.copyProperties(rentalTO,Rental.class,true);
        try {
            rental.setCreateTime(LocalDateTime.now());
            super.save(rental);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(rental, RentalBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalBO editRental(RentalTO rentalTO) throws SerException {

        Rental rental = BeanTransform.copyProperties(rentalTO, Rental.class, true);
        try {
            rental.setModifyTime(LocalDateTime.now());
            super.update(rental);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        return BeanTransform.copyProperties(rental, RentalBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRental(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
    @Cacheable
    @Override
    public List<Rental> listRental(RentalDTO rentalDTO) throws SerException {

        //TODO: xiazhili 2017-03-10 未做根据 rentalDTO 分页查询所有
        List<Rental> rentals = super.findByPage(rentalDTO);
        return rentals;
    }

    /**
     * 上传附件
     */
    @Override
    public void uploadAttachments() throws SerException {
        //TODO: xiazhili 2017-03-10 未做上传附件
        return ;
    }
    /**
     * 附件
     */
    @Override
    public void attachments() throws SerException {
        //TODO: xiazhili 2017-03-10 未做附件
        return ;
    }
    /**
     * 上传
     */
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-10 未做上传
        return ;
    }
    /**
     *租房状态
     */
    @Override
    public RentalBO rentalStatus(RentalTO rentalTO)throws SerException {
        //TODO: xiazhili 2017-03-10 未做租房状态
        return null;
    }

    /**
     *租房信息导出明细
     */
    @Override
    public String exportExcel(String area)throws SerException {
        //TODO: xiazhili 2017-03-10 未做导出明细
        return null;
    }


}
