package com.bjike.goddess.bidding.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        BidOpeningInfo bidOpeningInfo = BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfo.class, true);
        try {
            bidOpeningInfo.setModifyTime(LocalDateTime.now());
            super.save(bidOpeningInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(bidOpeningInfo, BidOpeningInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        BidOpeningInfo bidOpeningInfo = BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfo.class, true);
        try {
            bidOpeningInfo.setModifyTime(LocalDateTime.now());
            super.update(bidOpeningInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(bidOpeningInfo, BidOpeningInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBidOpeningInfo(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO biddingWebInfoDTO) throws SerException {
        List<BidOpeningInfo> bidOpeningInfos = super.findByCis(biddingWebInfoDTO,true);
        return BeanTransform.copyProperties(bidOpeningInfos,BidOpeningInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO search(String competitive) throws SerException {
        BidOpeningInfoDTO dto = new BidOpeningInfoDTO();
        dto.getConditions().add(Restrict.eq("competitive", competitive));
        BidOpeningInfoBO bidOpeningInfoBO = BeanTransform.copyProperties(super.findOne(dto), BidOpeningInfoBO.class);
        return bidOpeningInfoBO;
    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel(String projectName) throws SerException {
        //TODO: xiazhili 2017-03-10 未做导出
        return null;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO sendBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        //TODO: xiazhili 2017-03-10 未做发送邮件
        return null;
    }
    /**
     /**
     * 汇总
     *
     * @param cities cities
     * @return class bidOpeningInfoBO
     * @throws SerException
     */
    public BidOpeningInfoBO collectBidOpeningInfo(String cities) throws SerException {
        List<BidOpeningInfoBO> bidOpeningInfoBOList = new ArrayList<>();
        //先查询地市
        List<String> citie = bidOpeningInfoAPI.getBidOpeningInfoCities();

        String [] fields = new String[]{"count","competitive"};
        String sql = "select count(*) as count ,competitive from bidding_bidopeninginfo where cities=''";
        List<Map<String,String>> citiesMapList = new ArrayList<Map<String,String>>();
        citiesMapList = sqlQueryString(citie, fields, sql, citiesMapList);


        BidOpeningInfoBO bidOpeningInfoBO = new BidOpeningInfoBO();
        bidOpeningInfoBO.setAreaMap(citiesMapList);
        bidOpeningInfoBOList.add(bidOpeningInfoBO);

        return null;
    }
    /**
     *
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<BidOpeningInfoBO> bidOpeningInfoBOS = bidOpeningInfoAPI.findBySql(sql, BidOpeningInfoBO.class, fields);
        if (bidOpeningInfoBOS != null && bidOpeningInfoBOS.size() > 0) {
            if (obj.size() == bidOpeningInfoBOS.size()) {
                for (BidOpeningInfoBO cbo : bidOpeningInfoBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (bidOpeningInfoBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (BidOpeningInfoBO cb : bidOpeningInfoBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的  如：地区
                List<String> diffrent = new ArrayList<>() ;
                for (String o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add( o );
                    }
                }

                //存map
                for (String o : obj) {
                    for (BidOpeningInfoBO cbo : bidOpeningInfoBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if( !diffrent.contains( o ) && cbo.getRemark().equals(o)){
                            areaMap.put("remark", cbo.getRemark());
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        }else {
                            areaMap.put("remark", o);
                            areaMap.put("count", 0+"");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }

}
