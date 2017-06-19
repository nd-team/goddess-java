package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.bidding.service.BiddingWebInfoSer;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招投标网站信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.329 ]
 * @Description: [ 招投标网站信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("biddingWebInfoApiImpl")
public class BiddingWebInfoApiImpl implements BiddingWebInfoAPI {
    @Autowired
    private BiddingWebInfoSer biddingWebInfoSer;
    @Override
    public Long countBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        return biddingWebInfoSer.countBiddingWebInfo(biddingWebInfoDTO);
    }
    @Override
    public BiddingWebInfoBO getOne(String id) throws SerException {
        return biddingWebInfoSer.getOne(id);
    }

    @Override
    public BiddingWebInfoBO insertBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        return biddingWebInfoSer.insertBiddingWebInfo(biddingWebInfoTO);
    }

    @Override
    public BiddingWebInfoBO editBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        return biddingWebInfoSer.editBiddingWebInfo(biddingWebInfoTO);
    }

    @Override
    public void removeBiddingWebInfo(String id) throws SerException {
        biddingWebInfoSer.remove(id);
    }

    @Override
    public List<BiddingWebInfoBO> findListBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        return biddingWebInfoSer.findListBiddingWebInfo(biddingWebInfoDTO);
    }
    @Override
    public List<String> getWebName() throws SerException {
        return biddingWebInfoSer.getWebName();
    }
    @Override
    public List<String> getUrl() throws SerException {
        return biddingWebInfoSer.getUrl();
    }


}