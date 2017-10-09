package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingCollectBO;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.service.BiddingInfoSer;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.to.SearchTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return biddingInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return biddingInfoSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return biddingInfoSer.countBiddingInfo(biddingInfoDTO);
    }

    @Override
    public BiddingInfoBO getOne(String id) throws SerException {
        return biddingInfoSer.getOne(id);
    }

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
    public List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return biddingInfoSer.findListBiddingInfo(biddingInfoDTO);
    }

    @Override
    public BiddingInfoBO getBidding(String biddingNumber) throws SerException {
        return biddingInfoSer.getBidding(biddingNumber);
    }

    @Override
    public List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        return biddingInfoSer.collectBiddingInfo(cities);
    }
    @Override
    public List<String> getBiddingInfoCities() throws SerException {
        return biddingInfoSer.getBiddingInfoCities();
    }
    @Override
    public List<String> getProjectName() throws SerException {
        return biddingInfoSer.getProjectName();
    }
    @Override
    public List<String> getTenderNumber() throws SerException {
        return biddingInfoSer.getTenderNumber();
    }
    @Override
    public byte[] exportExcel(BiddingInfoDTO dto) throws SerException{
        return biddingInfoSer.exportExcel(dto);
    }
    @Override
    public List<BiddingCollectBO> dayCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.dayCollect(to);
    }

    @Override
    public List<BiddingCollectBO> weekCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.weekCollect(to);
    }

    @Override
    public List<BiddingCollectBO> monthCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.monthCollect(to);
    }

    @Override
    public List<BiddingCollectBO> totalCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.totalCollect(to);
    }

    @Override
    public List<String> info(SearchTO to) throws SerException {
        return biddingInfoSer.info(to);
    }
    @Override
    public List<String> txzbInfo(SearchTO to) throws SerException {
        return biddingInfoSer.txzbInfo(to);
    }

    @Override
    public List<String> zycgInfo(SearchTO to) throws SerException {
        return biddingInfoSer.zycgInfo(to);
    }

    @Override
    public List<String> toobiaoInfo(SearchTO to) throws SerException {
        return biddingInfoSer.toobiaoInfo(to);
    }

    @Override
    public List<String> schoolbidInfo(SearchTO to) throws SerException {
        return biddingInfoSer.schoolbidInfo(to);
    }
}