package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
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
    private BiddingInfoSer biddingInfoAPI;
    @Override
    public Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        biddingInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(biddingInfoDTO);
        return count;
    }
    @Override
    public BiddingInfoBO getOne(String id) throws SerException {
        BiddingInfo biddingInfo = super.findById(id);
        return BeanTransform.copyProperties(biddingInfo,BiddingInfoBO.class);
    }
    @Override
    public List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        biddingInfoDTO.getSorts().add("createTime=desc");
        List<BiddingInfo> biddingInfo = super.findByCis(biddingInfoDTO,true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfo,BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Override
    public BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        BiddingInfo biddingInfo = BeanTransform.copyProperties(biddingInfoTO, BiddingInfo.class, true);
        biddingInfo.setId(biddingInfoTO.getId());
        super.save(biddingInfo);
        return BeanTransform.copyProperties(biddingInfo, BiddingInfoBO.class);
    }

    @Override
    public BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        BiddingInfo biddingInfo = super.findById(biddingInfoTO.getId());
        BeanTransform.copyProperties(biddingInfoTO, biddingInfo, true);
        biddingInfo.setModifyTime(LocalDateTime.now());
        super.update(biddingInfo);
        return BeanTransform.copyProperties(biddingInfoTO, BiddingInfoBO.class);
    }

    @Override
    public void removeBiddingInfo(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
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
            biddingInfoDTO.getConditions().add(Restrict.eq("webName",biddingInfoDTO.getWebName()));
        }
        /**
         * 网址
         */
        if(StringUtils.isNotBlank(biddingInfoDTO.getUrl())){
            biddingInfoDTO.getConditions().add(Restrict.eq("url", biddingInfoDTO.getUrl()));
        }
        /**
         * 省份
         */

        if(StringUtils.isNotBlank(biddingInfoDTO.getProvinces())){
            biddingInfoDTO.getConditions().add(Restrict.eq("provinces", biddingInfoDTO.getProvinces()));

        }
        /**
         * 地市
         */
        if(StringUtils.isNotBlank(biddingInfoDTO.getCities())){
            biddingInfoDTO.getConditions().add(Restrict.eq("cities", biddingInfoDTO.getCities()));

        }
        List<BiddingInfo> biddingInfos = super.findByCis(biddingInfoDTO,true);
        List<BiddingInfoBO> biddingInfoBOS = BeanTransform.copyProperties(biddingInfos, BiddingInfoBO.class);
        return biddingInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void upload() throws SerException {
        //TODO: xiazhili 2017-03-17 未做上传
        return;

    }
    @Transactional(rollbackFor = SerException.class)
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
        for(int i = 0;i <cities.length;i++){
            citiesTemp[i] = "'"+cities[i]+"'";
        }
        String citiesStr = StringUtils.join(citiesTemp,',');
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType='1' THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType='2' THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType='1' THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType='2' THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType='3' THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType='4' THEN businessTypeCounts END )AS plan FROM ");
        sb.append(" (SELECT count(*) AS businessTypeCounts,businessType as businessType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY businessType,cities ORDER BY cities)a GROUP BY cities)B ");
        sb.append(" WHERE A.cities=B.cities)C ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as area ,sum(invite) AS invite,sum(openly) AS openly,sum(mobile) AS mobile, ");
        sb.append(" sum(soft) AS soft,sum(system) AS system,sum(plan) AS plan from ");
        sb.append(" (SELECT A.*,B.mobile,B.soft,B.system,B.plan FROM ");
        sb.append(" (SELECT cities,max(CASE WHEN biddingType='1' THEN biddingTypeCounts END ) AS invite, ");
        sb.append(" max(CASE WHEN biddingType='2' THEN biddingTypeCounts END ) AS openly FROM ");
        sb.append(" (select count(*) AS biddingTypeCounts,biddingType AS biddingType,cities as cities ");
        sb.append(" FROM bidding_biddinginfo a WHERE cities IN (%s) GROUP BY biddingType,cities ORDER BY cities )a GROUP BY cities)A, ");
        sb.append(" (SELECT cities,max(CASE WHEN businessType='1' THEN businessTypeCounts END )AS mobile, ");
        sb.append(" max(CASE WHEN businessType='2' THEN businessTypeCounts END )AS soft, ");
        sb.append(" max(CASE WHEN businessType='3' THEN businessTypeCounts END )AS system, ");
        sb.append(" max(CASE WHEN businessType='4' THEN businessTypeCounts END )AS plan FROM ");
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
        List<BiddingInfoBO> biddingInfoBOS = super.findBySql("select distinct cities,1 from bidding_biddinginfo group by cities order by cities asc ",BiddingInfoBO.class,fields);

        List<String> citiesList = biddingInfoBOS.stream().map(BiddingInfoBO::getCities)
                .filter(cities -> (cities != null || !"".equals(cities.trim()))).distinct().collect(Collectors.toList());


        return citiesList;
    }


    /**
     * 汇总
     *
     * @param cities cities
     * @return class biddingInfoBO
     * @throws SerException
     *//*
    public List<BiddingInfoBO> collectBiddingInfo(String[] cities) throws SerException {
        List<BiddingInfoBO> biddingInfoList = new ArrayList<>();
        //先查询地市
        List<String> citie = biddingInfoAPI.getBiddingInfoCities();
        //招投标类型
        List<Integer> bidType = Arrays.asList(BiddingType.INVITEDTENDERING.getCode(), BiddingType.OPENTENDERING.getCode());
        //业务类型
        List<Integer> busType = Arrays.asList(BusinessType.MOBILECOMMUNICATION.getCode(), BusinessType.SOFTWAREDEVELOPMENT.getCode(),
                BusinessType.INTELLIGENTSYSTEMINTEGRATION.getCode(), BusinessType.PLANNINGMARKETINGSOLUTIONS.getCode());

        for (String c : cities) {
            String[] fields = {"citie", "bidType" ,"busType"};
            String sql = "select count(*) bidType as bidType and busType as busType from bidding_biddinginfo where cities as citie";
            List<Map<String, String>> citieMapList = new ArrayList<>();
            citieMapList = sqlQueryString(citie, fields, sql, citieMapList);

            //处理招投标类型汇总
            sql = "select count(*) as count , biddingType as biddingType from bidding_biddinginfo where cities as citie";
            List<Map<String,String >> bidTypeMapList = new ArrayList<>();
            bidTypeMapList = sqlQueryInt("BiddingType" ,bidType,fields,sql,bidTypeMapList);

            //处理业务类型汇总
            sql = "select count(*) as count , businessType as businessType from bidding_biddinginfo where cities as citie";
            List<Map<String,String >> busTypeMapList = new ArrayList<>();
            busTypeMapList = sqlQueryInt("BusinessType" ,busType,fields,sql,busTypeMapList);

            BiddingInfoBO biddingInfoBO = new BiddingInfoBO();
            *//*biddingInfoBO.setAreaMap(citieMapList);
            biddingInfoBO.setBiddingType(bidTypeMapList);
            biddingInfoBO.setBusinessType(busTypeMapList);*//*
            biddingInfoList.add(biddingInfoBO);
        }
        return biddingInfoList;
    }
    *//**
     *
     * 数据库查询返回，然后添加map数组
     *//*
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<BiddingInfoBO> biddingInfoBOS = biddingInfoAPI.findBySql(sql, BiddingInfoBO.class, fields);
        if (biddingInfoBOS != null && biddingInfoBOS.size() > 0) {
            if (obj.size() == biddingInfoBOS.size()) {
                for (BiddingInfoBO cbo : biddingInfoBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    //areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (biddingInfoBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (BiddingInfoBO cb : biddingInfoBOS) {
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
                    for (BiddingInfoBO cbo : biddingInfoBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if( !diffrent.contains( o ) && cbo.getRemark().equals(o)){
                            areaMap.put("remark", cbo.getRemark());
                      //      areaMap.put("count", String.valueOf(cbo.getCounts()));
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


    *//**
     *
     * 将数据库返回的枚举int值转换，然后添加map数组
     *//*
    public List<Map<String, String>> sqlQueryInt (String enumStr ,List<Integer> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<BiddingInfoBO> biddingInfoBOS = biddingInfoAPI.findBySql(sql, BiddingInfoBO.class, fields);
        if (biddingInfoBOS != null && biddingInfoBOS.size() > 0) {
            if (obj.size() == biddingInfoBOS.size()) {
                for (BiddingInfoBO cbo : biddingInfoBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if( enumStr.equals("BiddingType")){
                   //     areaMap.put("remark", BiddingType.getStrConvert( cbo.getEnumConvert()));
                    }else if(enumStr.equals("BusinessType")){
                   //     areaMap.put("remark", BusinessType.getStrConvert( cbo.getEnumConvert()));
                    }
                   // areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (biddingInfoBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (BiddingInfoBO cb : biddingInfoBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的int值  如：枚举
                List<Integer> diffrent = new ArrayList<>() ;
                for (Integer o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add( o );
                    }
                }

                //存map
                for (Integer o : obj) {
                    for (BiddingInfoBO cbo : biddingInfoBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if( !diffrent.contains( o ) && cbo.getRemark().equals(o)){
                            if( enumStr.equals("BiddingType")){
                          //      areaMap.put("remark", BiddingType.getStrConvert( cbo.getEnumConvert()));
                            }else if(enumStr.equals("BusinessType")){
                          //      areaMap.put("remark", BusinessType.getStrConvert( cbo.getEnumConvert()));
                            }
                            //areaMap.put("count", String.valueOf(cbo.getCounts()));
                        }else {
                            if( enumStr.equals("BiddingType")){
                                areaMap.put("remark", BiddingType.getStrConvert( o ));
                            }else if(enumStr.equals("BusinessType")){
                                areaMap.put("remark", BusinessType.getStrConvert( o ));
                            }
                            areaMap.put("count", 0+"");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }*/



}