package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.excel.SonPermissionObject;
import com.bjike.goddess.buyticket.service.BuyTicketApplySer;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return buyTicketApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        return buyTicketApplySer.guidePermission(guidePermissionTO);
    }

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
    public List<String> findAddAllDetails() throws SerException {
        return buyTicketApplySer.findAddAllDetails();
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        return buyTicketApplySer.findallMonUser();
    }

    @Override
    public void planAuditBuyTicketApply(String id, AuditType planAuditOpinion) throws SerException {
        buyTicketApplySer.planAuditBuyTicketApply(id,planAuditOpinion);
    }

    @Override
    public void welfAuditBuyTicketApply(String id, AuditType welfAuditOpinion) throws SerException {
        buyTicketApplySer.welfAuditBuyTicketApply(id,welfAuditOpinion);
    }
}