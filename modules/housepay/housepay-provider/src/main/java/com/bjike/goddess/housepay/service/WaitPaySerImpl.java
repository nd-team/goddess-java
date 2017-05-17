package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import com.bjike.goddess.housepay.entity.WaitPay;
import com.bjike.goddess.housepay.enums.PayStatus;
import com.bjike.goddess.housepay.to.WaitPayTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 等待付款业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "housepaySerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {

    @Autowired
    private PayRecordSer payRecordSer;

    @Override
    public Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        Long count = super.count(waitPayDTO);
        return count;
    }

    @Override
    public WaitPayBO getOne(String id) throws SerException {
        WaitPay waitPay = super.findById(id);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Override
    public List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        List<WaitPay> waitPays = super.findByPage(waitPayDTO);
        List<WaitPayBO> waitPayBOS = BeanTransform.copyProperties(waitPays, WaitPayBO.class);
        return waitPayBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        WaitPay waitPay = BeanTransform.copyProperties(waitPayTO, WaitPay.class, true);
        if (PayStatus.IS.equals(waitPay.getPay())) {
            throw new SerException("添加失败，未做付款操作都是否");
        }
        waitPay.setCreateTime(LocalDateTime.now());
        Double total = waitPay.getRent() + waitPay.getWater() + waitPay.getEnergy() + waitPay.getOtherFee();
        waitPay.setTotal(total);
        super.save(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        WaitPay waitPay = super.findById(waitPayTO.getId());
        BeanTransform.copyProperties(waitPayTO, waitPay, true);
        waitPay.setModifyTime(LocalDateTime.now());
        super.update(waitPay);
        return BeanTransform.copyProperties(waitPay, WaitPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWaitPay(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public PayRecordBO payment(WaitPayTO waitPayTO) throws SerException {
        WaitPay waitPay = super.findById(waitPayTO.getId());
        BeanTransform.copyProperties(waitPayTO, waitPay, true);
        if (PayStatus.NO.equals(waitPay.getPay())) {
            waitPay.setPay(PayStatus.IS);
            super.update(waitPay);
        }

        PayRecord payRecord = new PayRecord();
        BeanUtils.copyProperties(waitPay,payRecord);
        payRecordSer.save(payRecord);
        return BeanTransform.copyProperties(payRecord, PayRecordBO.class);
    }
}