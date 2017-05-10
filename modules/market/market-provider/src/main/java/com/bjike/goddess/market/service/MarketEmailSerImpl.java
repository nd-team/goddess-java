package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.bo.MarketCollectBO;
import com.bjike.goddess.market.bo.MarketEmailBO;
import com.bjike.goddess.market.bo.MarketInfoBO;
import com.bjike.goddess.market.dto.MarketEmailDTO;
import com.bjike.goddess.market.entity.MarketEmail;
import com.bjike.goddess.market.to.MarketEmailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private MarketInfoSer marketInfoSer;

    @Override
    public Long counts(MarketEmailDTO marketEmailDTO) throws SerException {
        Long count = super.count(marketEmailDTO);
        return count;
    }


    @Override
    public MarketEmailBO getOne(String id) throws SerException {
        MarketEmail marketEmail = super.findById(id);
        return BeanTransform.copyProperties(marketEmail, MarketEmailBO.class);
    }


    @Override
    public List<MarketEmailBO> listMarketEmail(MarketEmailDTO marketEmailDTO) throws SerException {
        marketEmailDTO.getSorts().add("createTime=desc");
        List<MarketEmail> marketEmails = super.findByPage(marketEmailDTO);
        List<MarketEmailBO> marketEmailBOS = BeanTransform.copyProperties(marketEmails, MarketEmailBO.class, true);
        return marketEmailBOS;
    }

    @Override
    public MarketEmailBO addMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        MarketEmail marketEmail = BeanTransform.copyProperties(marketEmailTO, MarketEmail.class, true);
        marketEmail.setCreateTime(LocalDateTime.now());
        super.save(marketEmail);
        return BeanTransform.copyProperties(marketEmail, MarketEmailBO.class);
    }

    @Override
    public MarketEmailBO editMarketEmail(MarketEmailTO marketEmailTO) throws SerException {
        MarketEmail temp = super.findById(marketEmailTO.getId());
        MarketEmail marketEmail = BeanTransform.copyProperties(marketEmailTO, MarketEmail.class, true);
        BeanUtils.copyProperties(marketEmail, temp, "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
        return BeanTransform.copyProperties(temp, MarketEmailBO.class);
    }

    @Override
    public void deleteMarketEmail(String id) throws SerException {
        super.remove(id);
    }


    @Override
    public List<MarketCollectBO> marketCollect(String[] areas) throws SerException {
        if (areas == null || areas.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT A.*,B.first,B.second,B.third,C.has,C.notHas,D.fresh,D.old FROM ");
        sb.append(" (SELECT area,MAX(CASE WHEN  workType='1' THEN workTypeCounts  END ) AS mobile, ");
        sb.append(" MAX( CASE WHEN workType='2' THEN  workTypeCounts END ) AS soft, ");
        sb.append(" MAX( CASE WHEN workType='3' THEN workTypeCounts END ) AS system, ");
        sb.append(" MAX(  CASE WHEN workType='4' THEN  workTypeCounts END  ) AS plan ");
        sb.append(" FROM  (  select count(*) as workTypeCounts , workType as workType ,area as area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY workType,area ORDER BY area  )a  GROUP BY area)A, ");
        sb.append("  (  SELECT area,MAX(CASE WHEN  scale='1' THEN scaleCounts  END ) AS first, ");
        sb.append(" MAX( CASE WHEN scale='2' THEN  scaleCounts END ) AS second, ");
        sb.append(" MAX( CASE WHEN scale='3' THEN scaleCounts END ) AS third ");
        sb.append(" FROM  (  select count(*) as scaleCounts , scale as scale,area AS area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY scale,area ORDER BY area  )a GROUP BY area)B, ");
        sb.append(" (  SELECT area,MAX(CASE WHEN  is_effective='0' THEN effectiveCounts  END ) AS has, ");
        sb.append(" MAX( CASE WHEN is_effective='1' THEN  effectiveCounts END ) AS notHas ");
        sb.append(" FROM  (  select count(*) as effectiveCounts , is_effective as is_effective,area AS area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY is_effective,area ORDER BY area  )a GROUP BY area)C, ");
        sb.append(" (  SELECT area, MAX(CASE WHEN  projectNature='1' THEN projectNatureCounts  END ) AS fresh, ");
        sb.append(" MAX( CASE WHEN projectNature='2' THEN  projectNatureCounts END ) AS old ");
        sb.append(" FROM  (   select count(*) as projectNatureCounts , projectNature as projectNature,area AS  area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY projectNature,area ORDER BY area  )a GROUP BY area)D ");
        sb.append(" where A.area=B.area AND A.area=C.area AND A.area=D.area)F ");
        sb.append(" UNION ");
        sb.append("  SELECT '合计' AS area,sum(mobile)as mobile , sum(soft)as soft,sum(system)as system, ");
        sb.append(" sum(plan)as plan,sum(first)as first,  sum(second)as second,sum(third)as third , ");
        sb.append(" sum(has)as has,sum(notHas)as notHas,sum(fresh)as fresh,sum(old)as old  FROM ");
        sb.append(" (SELECT A.*,B.first,B.second,B.third,C.has,C.notHas,D.fresh,D.old FROM ");
        sb.append(" (SELECT area,MAX(CASE WHEN  workType='1' THEN workTypeCounts  END ) AS mobile, ");
        sb.append(" MAX( CASE WHEN workType='2' THEN  workTypeCounts END ) AS soft, ");
        sb.append(" MAX( CASE WHEN workType='3' THEN workTypeCounts END ) AS system, ");
        sb.append(" MAX(  CASE WHEN workType='4' THEN  workTypeCounts END  ) AS plan ");
        sb.append(" FROM  (  select count(*) as workTypeCounts , workType as workType ,area as area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY workType,area ORDER BY area  )a  GROUP BY area)A, ");
        sb.append("  (  SELECT area,MAX(CASE WHEN  scale='1' THEN scaleCounts  END ) AS first, ");
        sb.append(" MAX( CASE WHEN scale='2' THEN  scaleCounts END ) AS second, ");
        sb.append(" MAX( CASE WHEN scale='3' THEN scaleCounts END ) AS third ");
        sb.append(" FROM  (  select count(*) as scaleCounts , scale as scale,area AS area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY scale,area ORDER BY area  )a GROUP BY area)B, ");
        sb.append(" (  SELECT area,MAX(CASE WHEN  is_effective='0' THEN effectiveCounts  END ) AS has, ");
        sb.append(" MAX( CASE WHEN is_effective='1' THEN  effectiveCounts END ) AS notHas ");
        sb.append(" FROM  (  select count(*) as effectiveCounts , is_effective as is_effective,area AS area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY is_effective,area ORDER BY area  )a GROUP BY area)C, ");
        sb.append(" (  SELECT area, MAX(CASE WHEN  projectNature='1' THEN projectNatureCounts  END ) AS fresh, ");
        sb.append(" MAX( CASE WHEN projectNature='2' THEN  projectNatureCounts END ) AS old ");
        sb.append(" FROM  (   select count(*) as projectNatureCounts , projectNature as projectNature,area AS  area ");
        sb.append(" from  market_marketinfo a WHERE area in(%s)  GROUP BY projectNature,area ORDER BY area  )a GROUP BY area)D ");
        sb.append(" where A.area=B.area AND A.area=C.area AND A.area=D.area)F ");

        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr);
        System.out.println(sql);
        String[] fields = new String[]{"area", "mobile", "soft",
                "system", "plan", "first", "second", "third", "has", "notHas", "fresh", "old"};
        List<MarketCollectBO> collects = super.findBySql(sql, MarketCollectBO.class, fields);

        return collects;

    }

    /*@Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<MarketInfoBO> marketInfoBOS = super.findBySql("select distinct area,1 from market_marketinfo group by area order by area asc ", MarketInfoBO.class, fields);

        List<String> collectList = marketInfoBOS.stream().map(MarketInfoBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }
*/

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


//    /**
//     * 数据库查询返回，然后添加map数组
//     */
//    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
//        List<MarketEmailBO> marketEmailBOS = marketInfoSer.findBySql(sql, CusEmailBO.class, fields);
//        if (marketEmailBOS != null && marketEmailBOS.size() > 0) {
//            if (obj.size() == marketEmailBOS.size()) {
//                for (MarketEmailBO cbo : marketEmailBOS) {
//                    Map<String, String> areaMap = new HashMap<>();
//                    areaMap.put("remark", cbo.getRemark());
//                    areaMap.put("count", String.valueOf(cbo.getCounts()));
//                    mapList.add(areaMap);
//                }
//            } else if (marketEmailBOS.size() < obj.size()) {
//                List<String> cbStr = new ArrayList<>();
//                for (MarketEmailBO cb : marketEmailBOS) {
//                    cbStr.add(cb.getRemark());
//                }
//
//                //获取到所有不同的  如：地区
//                List<String> diffrent = new ArrayList<>();
//                for (String o : obj) {
//                    if (!cbStr.contains(o)) {
//                        diffrent.add(o);
//                    }
//                }
//
//                //存map
//                for (String o : obj) {
//                    for (MarketEmailBO cbo : marketEmailBOS) {
//                        Map<String, String> areaMap = new HashMap<>();
//                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
//                            areaMap.put("remark", cbo.getRemark());
//                            areaMap.put("count", String.valueOf(cbo.getCounts()));
//                        } else {
//                            areaMap.put("remark", o);
//                            areaMap.put("count", 0 + "");
//                        }
//                        mapList.add(areaMap);
//                    }
//                }
//
//            }
//        }
//        return mapList;
//    }


    /**
     * 将数据库返回的枚举int值转换，然后添加map数组
     */
   /* public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<MarketEmailBO> marketEmailBOS = marketInfoSer.findBySql(sql, CusEmailBO.class, fields);
        if (marketEmailBOS != null && marketEmailBOS.size() > 0) {
            if (obj.size() == marketEmailBOS.size()) {
                for (MarketEmailBO cbo : marketEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("CustomerStatus")) {
                        areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("CustomerType")) {
                        areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
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
                List<Integer> diffrent = new ArrayList<>();
                for (Integer o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (Integer o : obj) {
                    for (MarketEmailBO cbo : marketEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            if (enumStr.equals("MarketWorkType")) {
                                areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("MarketScaleType")) {
                                areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("MarketProjectNature")) {
                                areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            if (enumStr.equals("MarketWorkType")) {
                                areaMap.put("remark", CustomerStatus.getStrConvert(o));
                            } else if (enumStr.equals("MarketScaleType")) {
                                areaMap.put("remark", CustomerType.getStrConvert(o));
                            } else if (enumStr.equals("MarketProjectNature")) {
                                areaMap.put("remark", CustomerType.getStrConvert(o));
                            }
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }*/
}