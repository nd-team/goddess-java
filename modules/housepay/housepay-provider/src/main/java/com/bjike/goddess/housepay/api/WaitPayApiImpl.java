package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.WaitPay;
import com.bjike.goddess.housepay.enums.PayStatus;
import com.bjike.goddess.housepay.service.WaitPaySer;
import com.bjike.goddess.housepay.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitPayApiImpl")
public class WaitPayApiImpl implements WaitPayAPI {
    @Autowired
    private WaitPaySer waitPaySer;
    @Override
    public Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return waitPaySer.countWaitPay(waitPayDTO);
    }

    @Override
    public WaitPayBO getOne(String id) throws SerException {
        return waitPaySer.getOne(id);
    }

    @Override
    public List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return waitPaySer.findListWaitPay(waitPayDTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.insertWaitPay(waitPayTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.editWaitPay(waitPayTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeWaitPay(String id) throws SerException {
        waitPaySer.removeWaitPay(id);
    }

}