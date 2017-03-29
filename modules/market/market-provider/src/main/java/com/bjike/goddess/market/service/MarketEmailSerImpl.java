package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.entity.MarketEmail;
import com.bjike.goddess.market.enums.MarketProjectNature;
import com.bjike.goddess.market.enums.MarketScaleType;
import com.bjike.goddess.market.enums.MarketWorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 市场邮件发送定制业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.890 ]
 * @Description: [ 市场邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketEmailSerCache")
@Service
public class MarketEmailSerImpl extends ServiceImpl<MarketEmail, MarketEmailDTO> implements MarketEmailSer {

    @Autowired
    private MarketInfoSer marketInfoApi;
    @Cacheable
    @Override
    public List<MarketEmailBO> collectMarketEmail(String[] areas) throws SerException {
        List<MarketEmailBO> marketEmailBOS = new ArrayList<>();

        //获取所有的地区
        List<String> area = Arrays.asList(areas);
        /*//先查有几个地区
        List<String> areas = marketInfoApi.getMarketInfoArea();
*/
        //行业类别
        List<Integer> workType = Arrays.asList(MarketWorkType.MOBILECOMMUNICATION.getCode(), MarketWorkType.SOFTWAREDEVELOPMENT.getCode()
                , MarketWorkType.INTELLIGENTSYSTEMINTEGRATION.getCode(), MarketWorkType.PLANNINGMARKETINGSOLUTIONS.getCode());
        //项目级别
        List<Integer> scaleType = Arrays.asList(MarketScaleType.PROJECTLEVELA.getCode(),
                MarketScaleType.PROJECTLEVELB.getCode(), MarketScaleType.PROJECTLEVELC.getCode());

        //项目性质
        List<Integer> natureType = Arrays.asList(MarketProjectNature.NEWPROJECT.getCode(),
                MarketProjectNature.OLDPROJECT.getCode());

        for (String areaStr : areas) {
            //处理地区汇总
            String[] fields = new String[]{"count", "area", "remark"};
            String sql = "select count(*) as count  ,area as area  from  market_marketinfo " +
                    "where area in (" + areaStr + ")  area order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            areaMapList = sqlQueryString(area, fields, sql, areaMapList);

            //处理行业类别汇总
            sql = "select count(*) as count , area as area ,workType as enumConvert  from  market_marketinfo " +
                    "where workType in (" + workType + ") and area = " + areaStr + " group by area , workType order by workType asc  ";
            List<Map<String, String>> workTypeMapList = new ArrayList<>();
            workTypeMapList = sqlQueryInt("MarketWorkType",workType, fields, sql, workTypeMapList);

            //处理项目性质汇总
            sql = "select count(*) as count , area as area ,natureType as enumConvert  from  market_marketinfo " +
                    "where natureType in (" + natureType + ") and area = " + areaStr + " group by area , natureType order by natureType asc  ";
            List<Map<String, String>> natureTypeMapList = new ArrayList<>();
            natureTypeMapList = sqlQueryInt("MarketProjectNature",natureType, fields, sql, natureTypeMapList);

            //处理项目级别汇总
            sql = "select count(*) as count , area as area ,natureType as enumConvert  from  market_marketinfo " +
                    "where scaleType in (" + scaleType + ") and area = " + areaStr + " group by area , scaleType order by scaleType asc  ";
            List<Map<String, String>> scaleTypeMapList = new ArrayList<>();
            scaleTypeMapList = sqlQueryInt("MarketScaleType",scaleType, fields, sql, scaleTypeMapList);

            //处理是否为有效信息汇总
            sql = "select count(*) as count ,effective from market_marketinfo where area=''";
            List<Map<String,String>> effectiveMapList = new ArrayList<Map<String,String>>();
            effectiveMapList = sqlQueryString(area, fields, sql, effectiveMapList);


            MarketEmailBO marketEmailBO = new MarketEmailBO();
            marketEmailBO.setAreaMap(areaMapList);
            marketEmailBO.setWorkMap(workTypeMapList);
            marketEmailBO.setNatureMap(natureTypeMapList);
            marketEmailBO.setScaleMap(scaleTypeMapList);
            marketEmailBOS.add(marketEmailBO);
        }

        return marketEmailBOS;
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    /**
     *
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<MarketEmailBO> marketEmailBOS = marketInfoApi.findBySql(sql, CusEmailBO.class, fields);
        if (marketEmailBOS != null && marketEmailBOS.size() > 0) {
            if (obj.size() == marketEmailBOS.size()) {
                for (MarketEmailBO cbo : marketEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (marketEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (MarketEmailBO cb : marketEmailBOS) {
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
                    for (MarketEmailBO cbo : marketEmailBOS) {
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


    /**
     *
     * 将数据库返回的枚举int值转换，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryInt (String enumStr ,List<Integer> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<MarketEmailBO> marketEmailBOS = marketInfoApi.findBySql(sql, CusEmailBO.class, fields);
        if (marketEmailBOS != null && marketEmailBOS.size() > 0) {
            if (obj.size() == marketEmailBOS.size()) {
                for (MarketEmailBO cbo : marketEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if( enumStr.equals("CustomerStatus")){
                        areaMap.put("remark", CustomerStatus.getStrConvert( cbo.getEnumConvert()));
                    }else if(enumStr.equals("CustomerType")){
                        areaMap.put("remark", CustomerType.getStrConvert( cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (marketEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (MarketEmailBO cb : marketEmailBOS) {
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
                    for (MarketEmailBO cbo : marketEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if( !diffrent.contains( o ) && cbo.getRemark().equals(o)){
                            if( enumStr.equals("MarketWorkType")){
                                areaMap.put("remark", CustomerStatus.getStrConvert( cbo.getEnumConvert()));
                            }else if(enumStr.equals("MarketScaleType")){
                                areaMap.put("remark", CustomerType.getStrConvert( cbo.getEnumConvert()));
                            }else if(enumStr.equals("MarketProjectNature")) {
                                areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                            }
                                areaMap.put("count", String.valueOf(cbo.getCounts()));
                        }else {
                            if( enumStr.equals("MarketWorkType")){
                                areaMap.put("remark", CustomerStatus.getStrConvert( o ));
                            }else if(enumStr.equals("MarketScaleType")){
                                areaMap.put("remark", CustomerType.getStrConvert( o ));
                            }else if(enumStr.equals("MarketProjectNature")) {
                                areaMap.put("remark", CustomerType.getStrConvert(o));
                            }
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