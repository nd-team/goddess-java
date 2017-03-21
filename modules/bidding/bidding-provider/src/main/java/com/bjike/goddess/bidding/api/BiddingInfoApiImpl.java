package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.service.BiddingInfoSer;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招标信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.989 ]
 * @Description: [ 招标信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("biddingInfoApiImpl")
public class BiddingInfoApiImpl implements BiddingInfoAPI {
    @Autowired
    private BiddingInfoSer biddingInfoSer;

    @Override
    public BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return biddingInfoSer.insertBiddingInfo(biddingInfoTO);
    }

    @Override
    public BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return biddingInfoSer.editBiddingInfo(biddingInfoTO);
    }

    @Override
    public void removeBiddingInfo(String id) throws SerException {
        biddingInfoSer.remove(id);
    }

    @Override
    public List<BiddingInfo> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return biddingInfoSer.findListBiddingInfo(biddingInfoDTO);
    }

    @Override
    public String exportExcel(String projectName) throws SerException {
        return biddingInfoSer.exportExcel(projectName);
    }

    @Override
    public BiddingInfoBO search(String webName, String url, String provinces, String cities) throws SerException {
        return biddingInfoSer.search(webName, url, provinces, cities);
    }

    @Override
    public void upload() throws SerException {
        biddingInfoSer.upload();

    }
    @Override
    public BiddingInfoBO sendBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return biddingInfoSer.sendBiddingInfo(biddingInfoTO);

    }

    @Override
    public BiddingInfoBO collectBiddingInfo(String[] cities) throws SerException {
        return biddingInfoSer.collectBiddingInfo(cities);
    }

}