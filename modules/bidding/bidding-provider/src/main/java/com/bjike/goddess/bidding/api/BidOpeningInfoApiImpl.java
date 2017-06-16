package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BidOpeningCollectBO;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;
import com.bjike.goddess.bidding.service.BidOpeningInfoSer;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 开标信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.826 ]
 * @Description: [ 开标信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bidOpeningInfoApiImpl")
public class BidOpeningInfoApiImpl implements BidOpeningInfoAPI {
    @Autowired
    private BidOpeningInfoSer bidOpeningInfoSer;

    @Override
    public Long countBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return bidOpeningInfoSer.countBidOpeningInfo(bidOpeningInfoDTO);
    }
    @Override
    public BidOpeningInfoBO getOne(String id) throws SerException {
        return bidOpeningInfoSer.getOne(id);
    }
    @Override
    public BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return bidOpeningInfoSer.insertBidOpeningInfo(bidOpeningInfoTO);
    }

    @Override
    public BidOpeningInfoBO editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return bidOpeningInfoSer.editBidOpeningInfo(bidOpeningInfoTO);
    }

    @Override
    public void removeBidOpeningInfo(String id) throws SerException {
        bidOpeningInfoSer.removeBidOpeningInfo(id);
    }

    @Override
    public List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return bidOpeningInfoSer.findListBidOpeningInfo(bidOpeningInfoDTO);
    }


    @Override
    public List<BidOpeningInfoBO> searchBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return bidOpeningInfoSer.searchBidOpeningInfo(bidOpeningInfoDTO);
    }
    @Override
    public List<BidOpeningCollectBO> collectBidOpening(String[] cities) throws SerException {
        return bidOpeningInfoSer.collectBidOpening(cities);
    }
    @Override
    public List<String> getBidOpeningInfoCities() throws SerException {
        return bidOpeningInfoSer.getBidOpeningInfoCities();
    }
    @Override
    public byte[] exportExcel(BidOpeningInfoDTO dto) throws SerException{
        return bidOpeningInfoSer.exportExcel(dto);
    }

}