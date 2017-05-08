package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.service.BuyTicketApplySer;
import com.bjike.goddess.buyticket.service.BuyTicketRecordSer;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车票购买申请业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyTicketApplyApiImpl")
public class BuyTicketApplyApiImpl implements BuyTicketApplyAPI {

    @Autowired
    private BuyTicketApplySer buyTicketApplySer;
    @Override
    public Long countBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        return buyTicketApplySer.countBuyTicketApply(buyTicketApplyDTO);
    }
    @Override
    public BuyTicketApplyBO getOne(String id) throws SerException {
        return buyTicketApplySer.getOne(id);
    }


    @Override
    public List<BuyTicketApplyBO> findListBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        return buyTicketApplySer.findListBuyTicketApply(buyTicketApplyDTO);
    }

    @Override
    public BuyTicketApplyBO insertBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return buyTicketApplySer.insertBuyTicketApply(buyTicketApplyTO);
    }

    @Override
    public BuyTicketApplyBO editBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return buyTicketApplySer.editBuyTicketApply(buyTicketApplyTO);
    }

    @Override
    public void removeBuyTicketApply(String id) throws SerException {
        buyTicketApplySer.removeBuyTicketApply(id);
    }

    @Override
    public BuyTicketApplyBO auditBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return buyTicketApplySer.auditBuyTicketApply(buyTicketApplyTO);
    }

    @Override
    public BuyTicketApplyBO sendBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return buyTicketApplySer.sendBuyTicketApply(buyTicketApplyTO);
    }
}