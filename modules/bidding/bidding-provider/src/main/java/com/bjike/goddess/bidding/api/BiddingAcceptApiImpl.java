package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingAcceptBO;
import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.entity.BiddingAccept;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.service.BiddingAcceptSer;
import com.bjike.goddess.bidding.to.BiddingAcceptTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招标问题受理和处理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("biddingAcceptApiImpl")
public class BiddingAcceptApiImpl implements BiddingAcceptAPI {
    @Autowired
    private BiddingAcceptSer biddingAcceptSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return biddingAcceptSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return biddingAcceptSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(BiddingAcceptDTO dto) throws SerException {
        return biddingAcceptSer.count(dto);
    }

    @Override
    public BiddingAcceptBO getOne(String id) throws SerException {
        return biddingAcceptSer.getOne(id);
    }

    @Override
    public List<BiddingAcceptBO> list(BiddingAcceptDTO dto) throws SerException {
        return biddingAcceptSer.list(dto);
    }

    @Override
    public BiddingAcceptBO save(BiddingAcceptTO to) throws SerException {
        return biddingAcceptSer.save(to);
    }

    @Override
    public BiddingAcceptBO edit(BiddingAcceptTO to) throws SerException {
        return biddingAcceptSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        biddingAcceptSer.remove(id);
    }

    @Override
    public BiddingAcceptBO notification(BiddingAcceptTO to) throws SerException {
        return biddingAcceptSer.notification(to);
    }

}