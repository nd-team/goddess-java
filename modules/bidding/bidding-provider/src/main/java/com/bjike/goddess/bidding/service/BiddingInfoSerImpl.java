package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * 招标信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.995 ]
 * @Description: [ 招标信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class BiddingInfoSerImpl extends ServiceImpl<BiddingInfo, BiddingInfoDTO> implements BiddingInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Override
    public Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        biddingInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(biddingInfoDTO);
        return count;
    }
    @Override
    public BiddingInfoBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        BiddingInfo biddingInfo = super.findById(id);
        return BeanTransform.copyProperties(biddingInfo,BiddingInfoBO.class);
    }
    @Override
    public List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<BiddingInfo> biddingInfo = super.findByCis(biddingInfoDTO,true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfo,BiddingInfoBO.class);
        return biddingInfoBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        BiddingInfo biddingInfo = BeanTransform.copyProperties(biddingInfoTO, BiddingInfo.class, true);
        biddingInfo.setId(biddingInfoTO.getId());
        super.save(biddingInfo);
        return BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        if(StringUtils.isBlank(biddingInfoTO.getId())){
            throw new SerException("id不能为空");
        }
        BiddingInfo biddingInfo = super.findById(biddingInfoTO.getId());
        BeanTransform.copyProperties(biddingInfoTO, biddingInfo, true);
        biddingInfo.setModifyTime(LocalDateTime.now());
        super.update(biddingInfo);
        return BeanTransform.copyProperties(biddingInfoTO, BiddingInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBiddingInfo(String id) throws SerException {
        Boolean permission = cusPermissionSer.getCusPermission("1");
        if ( !permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public String exportExcel(String projectName) throws SerException {
        //TODO: xiazhili 2017-03-10 未做导出明细
        return null;
    }

    @Override
    public List<BiddingInfoBO> searchBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        /**
         * 网站名称
         */
        if(StringUtils.isNotBlank(biddingInfoDTO.getWebName())){
            biddingInfoDTO.getConditions().add(Restrict.like("webName",biddingInfoDTO.getWebName()));
        }
        /**
         * 网址
         */
        if(StringUtils.isNotBlank(biddingInfoDTO.getUrl())){
            biddingInfoDTO.getConditions().add(Restrict.like("url", biddingInfoDTO.getUrl()));
        }
        /**
         * 省份
         */

        if(StringUtils.isNotBlank(biddingInfoDTO.getProvinces())){
            biddingInfoDTO.getConditions().add(Restrict.like("provinces", biddingInfoDTO.getProvinces()));

        }
        /**
         * 地市
         */
        if(StringUtils.isNotBlank(biddingInfoDTO.getCities())){
            biddingInfoDTO.getConditions().add(Restrict.like("cities", biddingInfoDTO.getCities()));

        }
        List<BiddingInfo> biddingInfos = super.findByCis(biddingInfoDTO,true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfos, BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-17 未做上传
        return;

    }
    @Override
    public BiddingInfoBO sendBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        //TODO: xiazhili 2017-03-17 未做发送邮件
        return null;
    }

    @Override
    public List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        if(cities == null || cities.length <= 0){
            throw new SerException("汇总失败，请选择地市");
        }
        String[] citiesTemp = new String[cities.length];
        for(int i = 0;i<cities.length;i++){
            citiesTemp[i] = "'"+cities[i]+"'";
        }
        String citiesStr = StringUtils.join(citiesTemp,',');
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType=0 THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType=1 THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType=4 THEN businessTypeCounts END )AS plan FROM ");
        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)B ");
        sb.append(" WHERE A.cities=B.cities)C ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as area ,sum(invite) AS invite,sum(openly) AS openly,sum(mobile) AS mobile, ");
        sb.append(" sum(soft) AS soft,sum(system) AS system,sum(plan) AS plan from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType=0 THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType=1 THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType=0 THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType=1 THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType=2 THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType=3 THEN businessTypeCounts END )AS plan FROM ");
        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)B ");
        sb.append(" WHERE A.cities=B.cities)C ");
        String sql = sb.toString();
        sql = String.format(sql,citiesStr,citiesStr,citiesStr,citiesStr);
        String [] fields = new String[]{"cities","invite","openly","mobile","soft","system","plan"};
        List<BiddingInfoCollectBO> biddingInfoCollectBOS = super.findBySql(sql,BiddingInfoCollectBO.class,fields);
        return biddingInfoCollectBOS;
    }

    @Override
    public List<String> getBiddingInfoCities() throws SerException {
        String [] fields = new String[]{"cities"};
        List<BiddingInfoBO> biddingInfoBOS = super.findBySql("select distinct cities from bidding_biddinginfo group by cities order by cities asc ",BiddingInfoBO.class,fields);

        List<String> citiesList = biddingInfoBOS.stream().map(BiddingInfoBO::getCities)
                .filter(cities -> (cities != null || !"".equals(cities.trim()))).distinct().collect(Collectors.toList());


        return citiesList;
    }


}