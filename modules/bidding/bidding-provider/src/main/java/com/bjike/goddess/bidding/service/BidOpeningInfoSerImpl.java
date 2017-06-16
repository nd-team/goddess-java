package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BidOpeningCollectBO;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;
import com.bjike.goddess.bidding.excel.BidOpeningInfoExport;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class BidOpeningInfoSerImpl extends ServiceImpl<BidOpeningInfo, BidOpeningInfoDTO> implements BidOpeningInfoSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        try {
            DateUtil.parseDate(bidOpeningInfoTO.getBidOpeningTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        bidOpeningInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(bidOpeningInfoDTO);
        return count;
    }

    @Override
    public BidOpeningInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BidOpeningInfo bidOpeningInfo = super.findById(id);
        return BeanTransform.copyProperties(bidOpeningInfo, BidOpeningInfoBO.class);
    }

    @Override
    public List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<BidOpeningInfo> bidOpeningInfos = super.findByCis(bidOpeningInfoDTO, true);
        List<BidOpeningInfoBO> bidOpeningInfoBOS = BeanTransform.copyProperties(bidOpeningInfos, BidOpeningInfoBO.class);
        return bidOpeningInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        checkDate(bidOpeningInfoTO);
        BidOpeningInfo bidOpeningInfo = BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfo.class, true);
        bidOpeningInfo.setModifyTime(LocalDateTime.now());
        super.save(bidOpeningInfo);
        return BeanTransform.copyProperties(bidOpeningInfo, BidOpeningInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BidOpeningInfoBO editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        if (StringUtils.isBlank(bidOpeningInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        BidOpeningInfo bidOpeningInfo = super.findById(bidOpeningInfoTO.getId());
        BeanTransform.copyProperties(bidOpeningInfoTO, bidOpeningInfo, true);
        checkDate(bidOpeningInfoTO);
        bidOpeningInfo.setModifyTime(LocalDateTime.now());
        super.update(bidOpeningInfo);
        return BeanTransform.copyProperties(bidOpeningInfoTO, BidOpeningInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBidOpeningInfo(String id) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }

    @Override
    public List<BidOpeningInfoBO> searchBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        /**
         * 竞争公司
         */
        if (StringUtils.isNotBlank(bidOpeningInfoDTO.getCompetitive())) {
            bidOpeningInfoDTO.getConditions().add(Restrict.like("competitive", bidOpeningInfoDTO.getCompetitive()));
        }
        List<BidOpeningInfo> bidOpeningInfos = super.findByCis(bidOpeningInfoDTO, true);
        List<BidOpeningInfoBO> bidOpeningInfoBOS = BeanTransform.copyProperties(bidOpeningInfos, BidOpeningInfoBO.class);
        return bidOpeningInfoBOS;
    }

    @Override
    public List<BidOpeningCollectBO> collectBidOpening(String[] cities) throws SerException {
        if (cities == null || cities.length <= 0) {
            throw new SerException("汇总失败，请选择地市");
        }
        String[] citiesTemp = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            citiesTemp[i] = "'" + cities[i] + "'";
        }
        String areaStr = StringUtils.join(citiesTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT cities,competitive AS competitive FROM bidding_bidopeninginfo a WHERE cities IN (%s) ");
        sb.append(" GROUP BY competitive ,cities ORDER BY cities ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr);
        String[] fields = new String[]{"cities", "competitive"};
        List<BidOpeningCollectBO> bidOpeningCollectBOS = super.findBySql(sql, BidOpeningCollectBO.class, fields);
        return bidOpeningCollectBOS;
    }

    @Override
    public List<String> getBidOpeningInfoCities() throws SerException {
        String[] fields = new String[]{"cities"};
        String sql = "select distinct cities from bidding_bidopeninginfo group by cities order by cities asc ";
        List<BidOpeningInfoBO> bidOpeningInfoBOS =
                super.findBySql(sql, BidOpeningInfoBO.class, fields);

        List<String> collectList = bidOpeningInfoBOS.stream().map(BidOpeningInfoBO::getCities)
                .filter(cities -> (cities != null || !"".equals(cities.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }

    @Override
    public byte[] exportExcel(BidOpeningInfoDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        List<BidOpeningInfo> list = super.findByCis(dto);
        List<BidOpeningInfoExport> exports = new ArrayList<>();
        list.stream().forEach(str->{
            BidOpeningInfoExport export = BeanTransform.copyProperties(str,BidOpeningInfoExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte [] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }

}
