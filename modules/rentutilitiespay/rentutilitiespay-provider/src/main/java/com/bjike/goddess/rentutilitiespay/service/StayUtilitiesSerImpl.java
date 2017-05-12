package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
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
 * 员工住宿水电费业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentutilitiespaySerCache")
@Service
public class StayUtilitiesSerImpl extends ServiceImpl<StayUtilities, StayUtilitiesDTO> implements StayUtilitiesSer {

    @Autowired
    private StayUtilitiesSer stayUtilitiesSer;

    @Cacheable
    @Override
    public List<StayUtilitiesBO> findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        List<StayUtilities> stayUtilities = super.findByCis(stayUtilitiesDTO, true);
        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO insertStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        StayUtilities stayUtilities = BeanTransform.copyProperties(stayUtilitiesTO, StayUtilities.class, true);
        //是否需要修改
        if (stayUtilities.getComprehensiveVerifySituation()) {
            stayUtilities.setStaffVerify(StaffVerify.ERROR);
        } else {
            stayUtilities.setComprehensiveVerifySituation(true);
            stayUtilities.setStaffVerify(StaffVerify.CONFIRM);
            stayUtilities.setCreateTime(LocalDateTime.now());
            stayUtilities = count(stayUtilities);
            super.save(stayUtilities);
        }

        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO editStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        StayUtilities stayUtilities = super.findById(stayUtilitiesTO.getId());
        BeanTransform.copyProperties(stayUtilitiesTO, stayUtilities, true);
        stayUtilities.setModifyTime(LocalDateTime.now());
        stayUtilities = count(stayUtilities);
        super.update(stayUtilities);
        return BeanTransform.copyProperties(stayUtilitiesTO, StayUtilitiesBO.class);
    }

    /**
     * 计算方法
     */
    public StayUtilities count(StayUtilities stayUtilities) throws SerException {
        //同一住宿地址员工住宿天数总和
        String[] fields = new String[]{"sumDays", "address"};
        String sql = "select sum(stayDay) as sumDays ,address from rentutilitiespay_stayutilities group by address ";
        List<StayUtilities> sum = super.findBySql(sql, StayUtilities.class, fields);
        Integer sumDays = sum.get(0).getSumDays();

        //个人员工住宿天数
        fields = new String[]{"personalDays"};
        sql = "select sum(stayDay) as personalDays from rentutilitiespay_stayutilities where name = '' ";
        List<StayUtilities> num = super.findBySql(sql, StayUtilities.class, fields);
        Integer personalDays = num.get(0).getPersonalDays();
        //水费员工缴纳（(当月应缴水费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double waterStaffPay = (stayUtilities.getWaterAmount() / sumDays) * personalDays;
        stayUtilities.setWaterStaffPay(waterStaffPay);
        //电费员工缴纳（(当月应缴电费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double energyStaffPay = (stayUtilities.getEnergyAmount() / sumDays) * personalDays;
        stayUtilities.setEnergyStaffPay(energyStaffPay);
        //燃气费员工缴纳（(管道燃气费充值额度/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double gasStaffPay = (stayUtilities.getGasRechargeLines() / sumDays) * personalDays;
        stayUtilities.setGasStaffPay(gasStaffPay);

        //员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）
        Double staffPayCollect = waterStaffPay + energyStaffPay + gasStaffPay;
        stayUtilities.setStaffPayCollect(staffPayCollect);
        return stayUtilities;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayUtilities(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<StayUtilitiesBO> collectName(String[] name) throws SerException {
        if (name == null || name.length <= 0) {
            throw new SerException("汇总失败，请选择姓名");
        }
        List<StayUtilitiesBO> stayUtilitiesBOList = new ArrayList<>();

        //先查姓名
        List<String> nameList = stayUtilitiesSer.getStayUtilitiesName();
        //员工编号
        List<String> num = stayUtilitiesSer.getStayUtilitiesNum();
        //地区
        List<String> area = stayUtilitiesSer.getStayUtilitiesArea();
        //项目组
        List<String> projectGroup = stayUtilitiesSer.getStayUtilitiesProGroup();
        //项目名称
        List<String> projectName = stayUtilitiesSer.getStayUtilitiesProName();
        //地址
        List<String> address = stayUtilitiesSer.getStayUtilitiesAddress();
        StringBuffer tempNames = new StringBuffer("");
        for (String str : nameList) {
            tempNames.append("'" + str + "',");
        }
        String names = String.valueOf(tempNames).substring(0, String.valueOf(tempNames).lastIndexOf(","));

        for (String nameStr : name) {
            //处理姓名汇总
            String[] fields = new String[]{"counts", "name", "remark"};
            String sql = "select count(*) as counts ,name as name  from  rentutilitiespay_stayutilities " +
                    "where name in (" + nameStr + ") name order by name asc  ";
            List<Map<String, String>> nameMapList = new ArrayList<>();
            nameMapList = sqlQueryString(nameList, fields, sql, nameMapList);
            //处理员工编号汇总
            sql = "select count(*) as counts ,num as num  from  rentutilitiespay_stayutilities " +
                    "where num  order by num asc  ";
            List<Map<String, String>> numMapList = new ArrayList<>();
            numMapList = sqlQueryString(num, fields, sql, numMapList);
            //处理地区汇总
            sql = "select count(*) as counts ,area as area  from  rentutilitiespay_stayutilities " +
                    "where area  order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            areaMapList = sqlQueryString(area, fields, sql, areaMapList);
            //处理项目组汇总
            sql = "select count(*) as counts ,projectGroup as projectGroup  from  rentutilitiespay_stayutilities " +
                    "where projectGroup  order by projectGroup asc  ";
            List<Map<String, String>> proGroupMapList = new ArrayList<>();
            proGroupMapList = sqlQueryString(projectGroup, fields, sql, proGroupMapList);
            //处理项目名称汇总
            sql = "select count(*) as counts ,projectName as projectName  from  rentutilitiespay_stayutilities " +
                    "where projectName  order by projectName asc  ";
            List<Map<String, String>> proNameMapList = new ArrayList<>();
            proNameMapList = sqlQueryString(projectName, fields, sql, proNameMapList);
            //处理租房地址汇总
            sql = "select count(*) as counts ,address as address  from  rentutilitiespay_stayutilities " +
                    "where address  order by address asc  ";
            List<Map<String, String>> addressMapList = new ArrayList<>();
            addressMapList = sqlQueryString(address, fields, sql, addressMapList);
        }
        StayUtilitiesDTO stayUtilitiesDTO = new StayUtilitiesDTO();
        List<StayUtilities> list = super.findByCis(stayUtilitiesDTO);

        Double waterStaffPay = list.stream().mapToDouble(StayUtilities::getWaterStaffPay).sum();//水费员工缴纳
        Double energyStaffPay = list.stream().mapToDouble(StayUtilities::getEnergyStaffPay).sum();//电费员工缴纳
        Double gasStaffPay = list.stream().mapToDouble(StayUtilities::getGasStaffPay).sum();//燃气费员工缴纳
        StayUtilitiesBO stayUtilitiesBO = new StayUtilitiesBO("合计", null, null, null, null, null, waterStaffPay, energyStaffPay, gasStaffPay);

        stayUtilitiesBO.setNum(String.valueOf(num));
        stayUtilitiesBO.setArea(String.valueOf(area));
        stayUtilitiesBO.setProjectGroup(String.valueOf(projectGroup));
        stayUtilitiesBO.setProjectName(String.valueOf(projectName));
        stayUtilitiesBO.setAddress(String.valueOf(address));
        stayUtilitiesBOList.add(stayUtilitiesBO);

        return stayUtilitiesBOList;
    }

    @Override
    public List<String> getStayUtilitiesName() throws SerException {
        String[] fields = new String[]{"name"};
        List<StayUtilitiesBO> stayUtilitiesBOS = super.findBySql("select name from rentutilitiespay_stayutilities order by name asc ", StayUtilitiesBO.class, fields);

        List<String> nameList = stayUtilitiesBOS.stream().map(StayUtilitiesBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }

    @Override
    public List<String> getStayUtilitiesNum() throws SerException {
        String[] fields = new String[]{"num"};
        List<StayUtilitiesBO> stayUtilitiesBOS = super.findBySql("select num from rentutilitiespay_stayutilities order by num asc ", StayUtilitiesBO.class, fields);

        List<String> nameList = stayUtilitiesBOS.stream().map(StayUtilitiesBO::getNum)
                .filter(num -> (num != null || !"".equals(num.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }

    @Override
    public List<String> getStayUtilitiesArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentPayBO> rentPayBOS = super.findBySql("select area from rentutilitiespay_stayutilities order by area asc ", RentPayBO.class, fields);

        List<String> areaList = rentPayBOS.stream().map(RentPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Override
    public List<String> getStayUtilitiesProGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<RentPayBO> rentPayBOS = super.findBySql("select projectGroup from rentutilitiespay_stayutilities order by projectGroup asc ", RentPayBO.class, fields);

        List<String> proGroupList = rentPayBOS.stream().map(RentPayBO::getProjectGroup)
                .filter(projectGroup -> (projectGroup != null || !"".equals(projectGroup.trim()))).distinct().collect(Collectors.toList());


        return proGroupList;
    }

    @Override
    public List<String> getStayUtilitiesProName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<RentPayBO> rentPayBOS = super.findBySql("select projectName from rentutilitiespay_stayutilities order by projectName asc ", RentPayBO.class, fields);

        List<String> proNameList = rentPayBOS.stream().map(RentPayBO::getProjectName)
                .filter(projectName -> (projectName != null || !"".equals(projectName.trim()))).distinct().collect(Collectors.toList());


        return proNameList;
    }

    @Override
    public List<String> getStayUtilitiesAddress() throws SerException {
        String[] fields = new String[]{"address"};
        List<RentPayBO> rentPayBOS = super.findBySql("select address from rentutilitiespay_stayutilities order by address asc ", RentPayBO.class, fields);

        List<String> addressList = rentPayBOS.stream().map(RentPayBO::getAddress)
                .filter(address -> (address != null || !"".equals(address.trim()))).distinct().collect(Collectors.toList());


        return addressList;
    }

    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<StayUtilitiesBO> stayUtilitiesBOS = stayUtilitiesSer.findBySql(sql, RentPayBO.class, fields);
        if (stayUtilitiesBOS != null && stayUtilitiesBOS.size() > 0) {
            if (obj.size() == stayUtilitiesBOS.size()) {
                for (StayUtilitiesBO cbo : stayUtilitiesBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (stayUtilitiesBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (StayUtilitiesBO cb : stayUtilitiesBOS) {
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
                    for (StayUtilitiesBO cbo : stayUtilitiesBOS) {
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