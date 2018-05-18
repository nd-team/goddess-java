package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.service.BuyTicketRecordSer;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车票购买记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyTicketRecordApiImpl")
public class BuyTicketRecordApiImpl implements BuyTicketRecordAPI {
    @Autowired
    private BuyTicketRecordSer buyTicketRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return buyTicketRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return buyTicketRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        return buyTicketRecordSer.countBuyTicketRecord(buyTicketRecordDTO);
    }
    @Override
    public BuyTicketRecordBO getOne(String id) throws SerException {
        return buyTicketRecordSer.getOne(id);
    }

    @Override
    public List<BuyTicketRecordBO> findListBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        return buyTicketRecordSer.findListBuyTicketRecord(buyTicketRecordDTO);
    }

    @Override
    public BuyTicketRecordBO editBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        return buyTicketRecordSer.editBuyTicketRecord(buyTicketRecordTO);
    }

    @Override
    public void removeBuyTicketRecord(String id) throws SerException {
        buyTicketRecordSer.removeBuyTicketRecord(id);
    }
}