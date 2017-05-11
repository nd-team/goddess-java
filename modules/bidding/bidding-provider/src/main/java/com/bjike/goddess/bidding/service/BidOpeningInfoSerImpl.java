package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BidOpeningCollectBO;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: [xiazhili]
 * @Date: [17-3-18]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BidOpeningInfoSerImpl extends ServiceImpl<BidOpeningInfo, BidOpeningInfoDTO> implements BidOpeningInfoSer{
    @Autowired
    private BidOpeningInfoSer bidOpeningInfoAPI;
    @Override
    public Long countBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        bidOpeningInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(bidOpeningInfoDTO);
        return count;
    }
    @Override
    public BidOpeningInfoBO getOne(String id) throws SerException {
        BidOpeningInfo bidOpeningInfo = super.findById(id);
        return BeanTransform.copyProperties(bidOpeningInfo,BidOpeningInfoBO.class);
    }
    @Override
    public List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        bidOpeningInfoDTO.getSorts().add("createTime=desc");
        List<BidOpeningInfo> bidOpeningInfos = super.findByCis(bidOpeningInfoDTO,true);
        List<BidOpeningInfoBO> bidOpeningInfoBOS = BeanTransform.copyProperties(bidOpeningInfos,BidOpeningInfoBO.class);
        return bidOpeningInfoBOS;
    }
    @Override
    public BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        BidOpeningInfo bidOpeningInfo = BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfo.class, true);
        bidOpeningInfo.setModifyTime(LocalDateTime.now());
        super.save(bidOpeningInfo);
        return BeanTransform.copyProperties(bidOpeningInfo, BidOpeningInfoBO.class);
    }

    @Override
    public BidOpeningInfoBO editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        BidOpeningInfo bidOpeningInfo = super.findById(bidOpeningInfoTO.getId());
        BeanTransform.copyProperties(bidOpeningInfoTO, bidOpeningInfo, true);
        bidOpeningInfo.setModifyTime(LocalDateTime.now());
        super.update(bidOpeningInfo);
        return BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfoBO.class);
    }

    @Override
    public void removeBidOpeningInfo(String id) throws SerException {
        super.remove(id);

    }

    @Override
    public List<BidOpeningInfoBO> searchBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        /**
         * 竞争公司
         */
        if(StringUtils.isNotBlank(bidOpeningInfoDTO.getCompetitive())){
            bidOpeningInfoDTO.getConditions().add(Restrict.eq("competitive", bidOpeningInfoDTO.getCompetitive()));
        }
        List<BidOpeningInfo> bidOpeningInfos = super.findByCis(bidOpeningInfoDTO,true);
        List<BidOpeningInfoBO> bidOpeningInfoBOS = BeanTransform.copyProperties(bidOpeningInfos,BidOpeningInfoBO.class,true);
        return bidOpeningInfoBOS;
    }


    @Override
    public String exportExcel(String projectName) throws SerException {
        //TODO: xiazhili 2017-03-10 未做导出
        return null;
    }
    @Override
    public BidOpeningInfoBO sendBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        //TODO: xiazhili 2017-03-10 未做发送邮件
        return null;
    }
    @Override
    public List<BidOpeningCollectBO> collectBidOpening(String[] cities) throws SerException {
        if(cities == null || cities.length <= 0){
            throw new SerException("汇总失败，请选择地市");
        }
        String[] citiesTemp = new String[cities.length];
        for(int i = 0;i<cities.length;i++){
            citiesTemp[i] = "'"+cities[i]+"'";
        }
        String areaStr = StringUtils.join(citiesTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT cities,competitive AS competitive FROM bidding_bidopeninginfo a WHERE cities IN (%s) ");
        sb.append(" GROUP BY competitive ,cities ORDER BY cities ");
        String sql = sb.toString();
        sql = String.format(sql,areaStr);
        String [] fields = new String[]{"cities","competitive"};
        List<BidOpeningCollectBO> bidOpeningCollectBOS = super.findBySql(sql,BidOpeningInfoBO.class,fields);
        return bidOpeningCollectBOS;
    }

    @Override
    public List<String> getBidOpeningInfoCities() throws SerException {
        String [] fields = new String[]{"cities"};
        String sql = "select distinct cities from bidding_bidopeninginfo group by cities order by cities asc ";
        List<BidOpeningInfoBO> bidOpeningInfoBOS =
                super.findBySql( sql ,BidOpeningInfoBO.class,fields);

        List<String> collectList = bidOpeningInfoBOS.stream().map(BidOpeningInfoBO::getCities)
                .filter(cities -> (cities != null || !"".equals(cities.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }

}
