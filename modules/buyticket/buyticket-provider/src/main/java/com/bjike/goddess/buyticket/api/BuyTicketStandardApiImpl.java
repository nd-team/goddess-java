package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketStandardBO;
import com.bjike.goddess.buyticket.dto.BuyTicketStandardDTO;
import com.bjike.goddess.buyticket.service.BuyTicketStandardSer;
import com.bjike.goddess.buyticket.to.BuyTicketStandardTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购票标准业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyTicketStandardApiImpl")
public class BuyTicketStandardApiImpl implements BuyTicketStandardAPI {

    @Autowired
    private BuyTicketStandardSer buyTicketStandardSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return buyTicketStandardSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return buyTicketStandardSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        return buyTicketStandardSer.countBuyTicketStandard(buyTicketStandardDTO);
    }
    @Override
    public BuyTicketStandardBO getOne(String id) throws SerException {
        return buyTicketStandardSer.getOne(id);
    }
    @Override
    public List<BuyTicketStandardBO> findListBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        return buyTicketStandardSer.findListBuyTicketStandard(buyTicketStandardDTO);
    }

    @Override
    public BuyTicketStandardBO insertBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        return buyTicketStandardSer.insertBuyTicketStandard(buyTicketStandardTO);
    }

    @Override
    public BuyTicketStandardBO editBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        return buyTicketStandardSer.editBuyTicketStandard(buyTicketStandardTO);
    }

    @Override
    public void removeBuyTicketStandard(String id) throws SerException {
        buyTicketStandardSer.removeBuyTicketStandard(id);
    }
}