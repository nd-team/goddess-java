package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.*;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.dto.SearchDTO;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.service.BiddingInfoSer;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
        return biddingInfoSer.guidePermission(guidePermissionTO);
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
    public byte[] exportExcel(BiddingInfoDTO dto) throws SerException {
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
    public OptionBO dayFigureCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.dayFigureCollect(to);
    }

    @Override
    public OptionBO weekFigureCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.weekFigureCollect(to);
    }

    @Override
    public OptionBO monthFigureCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.monthFigureCollect(to);
    }

    @Override
    public OptionBO totalFigureCollect(BiddingCollectTO to) throws SerException {
        return biddingInfoSer.totalFigureCollect(to);
    }

    @Override
    public Long infoTotal() throws SerException {
        return biddingInfoSer.infoTotal();
    }

    @Override
    public List<InfoBO> info(SearchDTO dto) throws SerException {
        return biddingInfoSer.info(dto);
    }

    @Override
    public Long txzbTotal() throws SerException {
        return biddingInfoSer.txzbTotal();
    }

    @Override
    public List<InfoBO> txzbInfo(SearchDTO dto) throws SerException {
        return biddingInfoSer.txzbInfo(dto);
    }

    @Override
    public List<InfoBO> zycgInfo(SearchDTO dto) throws SerException {
        return biddingInfoSer.zycgInfo(dto);
    }

    @Override
    public Long zycyTotal() throws SerException {
        return biddingInfoSer.zycyTotal();
    }

    @Override
    public List<InfoBO> caigouInfo(SearchDTO dto) throws SerException {
        return biddingInfoSer.caigouInfo(dto);
    }

    @Override
    public Long caigouTotal() throws SerException {
        return biddingInfoSer.caigouTotal();
    }

    @Override
    public List<InfoBO> toobiaoInfo(SearchDTO dto) throws SerException {
        return biddingInfoSer.toobiaoInfo(dto);
    }
    @Override
    public Long toobiaoTotal() throws SerException{
        return biddingInfoSer.toobiaoTotal();
    }

//    @Override
//    public List<InfoBO> schoolbidInfo(SearchDTO dto) throws SerException {
//        return biddingInfoSer.schoolbidInfo(dto);
//    }
}