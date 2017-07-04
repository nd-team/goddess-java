package com.bjike.goddess.bonusmoneyperparepay.api;

import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.service.WaitingPaySer;
import com.bjike.goddess.bonusmoneyperparepay.to.WaitingPayTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitingPayApiImpl")
public class WaitingPayApiImpl implements WaitingPayAPI {

    @Autowired
    private WaitingPaySer waitingPaySer;

    @Override
    public Long countWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.countWaiting(waitingPayDTO);
    }

    @Override
    public WaitingPayBO getOneById(String id) throws SerException {
        return waitingPaySer.getOneById(id);
    }

    @Override
    public List<WaitingPayBO> listWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.listWaiting(waitingPayDTO);
    }

    @Override
    public WaitingPayBO addWaiting(WaitingPayTO moneyPerpareTO) throws SerException {
        return waitingPaySer.addWaiting(moneyPerpareTO);
    }

    @Override
    public WaitingPayBO editWaiting(WaitingPayTO moneyPerpareTO) throws SerException {
        return waitingPaySer.editWaiting(moneyPerpareTO);
    }

    @Override
    public void deleteWaiting(String id) throws SerException {
        waitingPaySer.deleteWaiting(id);
    }
}