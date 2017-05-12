package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private RentPaySer rentPaySer;

    @Cacheable
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
        rentPay = count(rentPay);
        super.save(rentPay);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO editRentPay(RentPayTO rentPayTO) throws SerException {
        RentPay rentPay = super.findById(rentPayTO.getId());
        BeanTransform.copyProperties(rentPayTO, rentPay, true);
        rentPay.setModifyTime(LocalDateTime.now());
        rentPay = count(rentPay);
        super.update(rentPay);
        return BeanTransform.copyProperties(rentPayTO, RentPay.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRentPay(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);
    }

    /**
     * 计算方法
     */
    public RentPay count(RentPay rentPay) throws SerException {
        //水费缴纳金额（水费计价金额（元/吨）*用水量）
        Double waterPayMoney = rentPay.getWaterValuationMoney() * rentPay.getWater();
        rentPay.setWaterPayMoney(waterPayMoney);
        //用水量（水费期末数目-水费初期数目）
        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
        rentPay.setWater(water);
        //电费缴纳金额（电费计价金额（元/吨）*用电量）
        Double energyPayMoney = rentPay.getEnergyPayMoney() * rentPay.getEnergy();
        rentPay.setEnergyPayMoney(energyPayMoney);
        //用电量（电费期末数目-电费初期数目）
        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
        rentPay.setEnergy(energy);
        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + rentPay.getWaterPayMoney() + rentPay.getEnergyPayMoney() + rentPay.getGasRechargeLines();
        rentPay.setPayMoneyCollect(payMoneyCollect);
        return rentPay;
    }

    /**
     * 上传附件
     */
    public void uploadAttachments() throws SerException {
        //todo:未做上传附件
        return;
    }

    /**
     * 汇总
     *
     * @param area area
     * @return class RentPayBO
     * @throws SerException
     */
    public List<RentPayBO> collectArea(String[] area) throws SerException {
        if (area == null || area.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        List<RentPayBO> rentPayBOList = new ArrayList<>();

        //先查有几个地区
        List<String> areaList = rentPaySer.getRentPayArea();
        //项目组
        List<String> projectGroup = rentPaySer.getRentPayProGroup();
        //项目名称
        List<String> projectName = rentPaySer.getRentPayProName();
        //地址
        List<String> address = rentPaySer.getRentPayAddress();
        StringBuffer tempAreas = new StringBuffer("");
        for (String str : areaList) {
            tempAreas.append("'" + str + "',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));

        for (String areaStr : area) {
            //处理地区汇总
            String[] fields = new String[]{"counts", "area", "remark"};
            String sql = "select count(*) as counts ,area as area  from  rentutilitiespay_rentpay " +
                    "where area in (" + areaStr + ") area order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            areaMapList = sqlQueryString(areaList, fields, sql, areaMapList);
            //处理项目组汇总
            sql = "select count(*) as counts ,projectGroup as projectGroup  from  rentutilitiespay_rentpay " +
                    "where projectGroup  order by projectGroup asc  ";
            List<Map<String, String>> proGroupMapList = new ArrayList<>();
            proGroupMapList = sqlQueryString(projectGroup, fields, sql, proGroupMapList);
            //处理项目名称汇总
            sql = "select count(*) as counts ,projectName as projectName  from  rentutilitiespay_rentpay " +
                    "where projectName  order by projectName asc  ";
            List<Map<String, String>> proNameMapList = new ArrayList<>();
            proNameMapList = sqlQueryString(projectName, fields, sql, proNameMapList);
            //处理租房地址汇总
            sql = "select count(*) as counts ,address as address  from  rentutilitiespay_rentpay " +
                    "where address  order by address asc  ";
            List<Map<String, String>> addressMapList = new ArrayList<>();
            addressMapList = sqlQueryString(address, fields, sql, addressMapList);

        }
        RentPayDTO rentPayDTO = new RentPayDTO();
        List<RentPay> list = super.findByCis(rentPayDTO);

        Double rent = list.stream().mapToDouble(RentPay::getRent).sum();//房租
        Double waterPayMoney = list.stream().mapToDouble(RentPay::getWaterPayMoney).sum(); //水费缴纳金额
        Double energyPayMoney = list.stream().mapToDouble(RentPay::getEnergyPayMoney).sum(); //电费缴纳金额
        Double gasRechargeLines = list.stream().mapToDouble(RentPay::getGasRechargeLines).sum(); //燃气费充值额度
        Double fee = list.stream().mapToDouble(RentPay::getFee).sum(); //管理费，卫生费
        RentPayBO rentPayBO = new RentPayBO("合计", null, null, null, rent, waterPayMoney, energyPayMoney, gasRechargeLines, fee);

        rentPayBO.setProjectGroup(String.valueOf(projectGroup));
        rentPayBO.setProjectName(String.valueOf(projectName));
        rentPayBO.setAddress(String.valueOf(address));
        rentPayBOList.add(rentPayBO);
        return rentPayBOList;
    }

    @Override
    public List<String> getRentPayArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentPayBO> rentPayBOS = super.findBySql("select area from rentutilitiespay_rentpay order by area asc ", RentPayBO.class, fields);

        List<String> areaList = rentPayBOS.stream().map(RentPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> getRentPayProGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<RentPayBO> rentPayBOS = super.findBySql("select projectGroup from rentutilitiespay_rentpay order by projectGroup asc ", RentPayBO.class, fields);

        List<String> proGroupList = rentPayBOS.stream().map(RentPayBO::getProjectGroup)
                .filter(projectGroup -> (projectGroup != null || !"".equals(projectGroup.trim()))).distinct().collect(Collectors.toList());


        return proGroupList;
    }

    @Override
    public List<String> getRentPayProName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<RentPayBO> rentPayBOS = super.findBySql("select projectName from rentutilitiespay_rentpay order by projectName asc ", RentPayBO.class, fields);

        List<String> proNameList = rentPayBOS.stream().map(RentPayBO::getProjectName)
                .filter(projectName -> (projectName != null || !"".equals(projectName.trim()))).distinct().collect(Collectors.toList());


        return proNameList;
    }

    @Override
    public List<String> getRentPayAddress() throws SerException {
        String[] fields = new String[]{"address"};
        List<RentPayBO> rentPayBOS = super.findBySql("select address from rentutilitiespay_rentpay order by address asc ", RentPayBO.class, fields);

        List<String> addressList = rentPayBOS.stream().map(RentPayBO::getAddress)
                .filter(address -> (address != null || !"".equals(address.trim()))).distinct().collect(Collectors.toList());


        return addressList;
    }

    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<RentPayBO> rentPayBOS = rentPaySer.findBySql(sql, RentPayBO.class, fields);
        if (rentPayBOS != null && rentPayBOS.size() > 0) {
            if (obj.size() == rentPayBOS.size()) {
                for (RentPayBO cbo : rentPayBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (rentPayBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (RentPayBO cb : rentPayBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的  如：地区
                List<String> diffrent = new ArrayList<>();
                for (String o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (String o : obj) {
                    for (RentPayBO cbo : rentPayBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            areaMap.put("remark", cbo.getRemark());
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            areaMap.put("remark", o);
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }


}