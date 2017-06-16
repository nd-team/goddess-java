package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.CollectAreaBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 房租缴费业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentutilitiespaySerCache")
@Service
public class RentPaySerImpl extends ServiceImpl<RentPay, RentPayDTO> implements RentPaySer {
    @Override
    public Long countRentPay(RentPayDTO rentPayDTO) throws SerException {
        Long count = super.count(rentPayDTO);
        return count;
    }

    @Override
    public RentPayBO getOne(String id) throws SerException {
        RentPay rentPay = super.findById(id);
        return BeanTransform.copyProperties(rentPay,RentPayBO.class);
    }
    @Override
    public List<RentPayBO> findListRentPay(RentPayDTO rentPayDTO) throws SerException {
        List<RentPay> rentPays = super.findByCis(rentPayDTO, true);
        return BeanTransform.copyProperties(rentPays, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO insertRentPay(RentPayTO rentPayTO) throws SerException {
        RentPay rentPay = BeanTransform.copyProperties(rentPayTO, RentPay.class, true);
        rentPay.setCreateTime(LocalDateTime.now());
        //用水量（水费期末数目-水费初期数目）
        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
        rentPay.setWater(water);
        //水费缴纳金额（水费计价金额（元/吨）*用水量）
        Double waterPayMoney = rentPay.getWaterValuationMoney() * water;
        rentPay.setWaterPayMoney(waterPayMoney);
        //用电量（电费期末数目-电费初期数目）
        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
        rentPay.setEnergy(energy);
        //电费缴纳金额（电费计价金额（元/吨）*用电量）
        Double energyPayMoney = rentPay.getEnergyValuationMoney() * energy;
        rentPay.setEnergyPayMoney(energyPayMoney);
        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + waterPayMoney + energyPayMoney + rentPay.getGasRechargeLines();
        rentPay.setPayMoneyCollect(payMoneyCollect);
        super.save(rentPay);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO editRentPay(RentPayTO rentPayTO) throws SerException {
        RentPay rentPay = super.findById(rentPayTO.getId());
        BeanTransform.copyProperties(rentPayTO, rentPay, true);
        rentPay.setModifyTime(LocalDateTime.now());
        //用水量（水费期末数目-水费初期数目）
        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
        rentPay.setWater(water);
        //水费缴纳金额（水费计价金额（元/吨）*用水量）
        Double waterPayMoney = rentPay.getWaterValuationMoney() * water;
        rentPay.setWaterPayMoney(waterPayMoney);
        //用电量（电费期末数目-电费初期数目）
        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
        rentPay.setEnergy(energy);
        //电费缴纳金额（电费计价金额（元/吨）*用电量）
        Double energyPayMoney = rentPay.getEnergyValuationMoney() * energy;
        rentPay.setEnergyPayMoney(energyPayMoney);
        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + waterPayMoney + energyPayMoney + rentPay.getGasRechargeLines();
        rentPay.setPayMoneyCollect(payMoneyCollect);
        super.update(rentPay);
        return BeanTransform.copyProperties(rentPayTO, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRentPay(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);
    }

//    /**
//     * 计算方法
//     */
//    public RentPay count(RentPay rentPay) throws SerException {
//        //水费缴纳金额（水费计价金额（元/吨）*用水量）
//        Double waterPayMoney = rentPay.getWaterValuationMoney() * rentPay.getWater();
//        rentPay.setWaterPayMoney(waterPayMoney);
//        //用水量（水费期末数目-水费初期数目）
//        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
//        rentPay.setWater(water);
//        //电费缴纳金额（电费计价金额（元/吨）*用电量）
//        Double energyPayMoney = rentPay.getEnergyPayMoney() * rentPay.getEnergy();
//        rentPay.setEnergyPayMoney(energyPayMoney);
//        //用电量（电费期末数目-电费初期数目）
//        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
//        rentPay.setEnergy(energy);
//        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
//        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + rentPay.getWaterPayMoney() + rentPay.getEnergyPayMoney() + rentPay.getGasRechargeLines();
//        rentPay.setPayMoneyCollect(payMoneyCollect);
//        return rentPay;
//    }

    /**
     * 上传附件
     */
    public void uploadAttachments() throws SerException {
        //todo:未做上传附件
        return;
    }
    @Override
    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        String[] areasTemp = new String[areas.length];
        for(int i = 0;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney, sum(fee)AS fee, ");
        sb.append(" ( sum(rent)+sum(waterPayMoney)+sum(energyPayMoney)+sum(fee)) AS remark ");
        sb.append(" FROM rentutilitiespay_rentpay WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address, ");
        sb.append(" area ORDER BY area)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,NULL as projectGroup,NULL as projectName,NULL as address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney, sum(fee)AS fee , ");
        sb.append(" ( sum(remark)) as remark FROM ");
        sb.append(" (SELECT area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney, sum(fee)AS fee, ");
        sb.append(" ( sum(rent)+sum(waterPayMoney)+sum(energyPayMoney)+sum(fee)) AS remark ");
        sb.append(" FROM rentutilitiespay_rentpay WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address, ");
        sb.append(" area ORDER BY area)A ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr,areaStr);
        String [] fields = new String[]{"area","projectGroup","projectName","address",
            "rent","waterPayMoney","energyPayMoney","fee","remark"};
        List<CollectAreaBO> collectAreaBOS = super.findBySql(sql,CollectAreaBO.class,fields);
        return collectAreaBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentPayBO> rentPayBOS = super.findBySql("select distinct area from rentutilitiespay_rentpay group by area order by area asc ", RentPayBO.class, fields);

        List<String> areaList = rentPayBOS.stream().map(RentPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;

    }


    /**
     * 数据库查询返回，然后添加map数组
     */
//    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
//        List<RentPayBO> rentPayBOS = rentPaySer.findBySql(sql, RentPayBO.class, fields);
//        if (rentPayBOS != null && rentPayBOS.size() > 0) {
//            if (obj.size() == rentPayBOS.size()) {
//                for (RentPayBO cbo : rentPayBOS) {
//                    Map<String, String> areaMap = new HashMap<>();
//                    areaMap.put("remark", cbo.getRemark());
//                    areaMap.put("count", String.valueOf(cbo.getCounts()));
//                    mapList.add(areaMap);
//                }
//            } else if (rentPayBOS.size() < obj.size()) {
//                List<String> cbStr = new ArrayList<>();
//                for (RentPayBO cb : rentPayBOS) {
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
//                    for (RentPayBO cbo : rentPayBOS) {
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


}