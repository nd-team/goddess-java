package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentutilitiespay.bo.AreaCollectBO;
import com.bjike.goddess.rentutilitiespay.bo.CollectBO;
import com.bjike.goddess.rentutilitiespay.bo.DepartmentCollectBO;
import com.bjike.goddess.rentutilitiespay.dto.CollectDTO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.Collect;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="rentutilitiespaySerCache")
@Service
public class CollectSerImpl extends ServiceImpl<Collect, CollectDTO> implements CollectSer {
    @Autowired
    private RentPaySer rentPaySer;

    @Autowired
    private StayUtilitiesSer stayUtilitiesSer;

    @Override
    public List<AreaCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        LocalDate[] startTime = null;
        if (year != null && month != null){
            LocalDate startDate = DateUtil.getStartDayOfMonth(year,month);
            LocalDate endDate = DateUtil.getEndDaYOfMonth(year,month);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.getStartMonth();
            LocalDate endDate = DateUtil.getEndMonth();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public List<AreaCollectBO> yeareCollect(Integer year) throws SerException {
        LocalDate[] startTime = null;
        if (year != null){
            LocalDate startDate = DateUtil.parseDate(year+"-01-01");
            LocalDate endDate = DateUtil.parseDate(year+"-12-31");
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.getStartYear();
            LocalDate endDate = DateUtil.getEndYear();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    @Override
    public List<AreaCollectBO> allCollect(String day) throws SerException {
        LocalDate[] startTime = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(day);
            startTime = new LocalDate[]{startDate,endDate};
        }else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);
    }

    private List<AreaCollectBO> collect(LocalDate[] startTime) throws SerException{
        List<RentPay> rentPays = rentPaySer.findAll();
        Set<String> areas = rentPays.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for (String area : areas){
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            RentPayDTO rentPayDTO = new RentPayDTO();
            rentPayDTO.getConditions().add(Restrict.eq("area",area));
            List<RentPay> rentPays1 = rentPaySer.findByCis(rentPayDTO);
            Set<String> departments = rentPays1.stream().map(p -> p.getProjectGroup()).collect(Collectors.toSet());
            List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
            for (String department : departments){
                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                RentPayDTO rentPayDTO1 = new RentPayDTO();
                rentPayDTO1.getConditions().add(Restrict.eq("area",area));
                rentPayDTO1.getConditions().add(Restrict.eq("projectGroup",department));
                List<RentPay> rentPays2 = rentPaySer.findByCis(rentPayDTO1);
                Set<String> locations = rentPays2.stream().map(p -> p.getAddress()).collect(Collectors.toSet());
                List<CollectBO> collectBOS = new ArrayList<>();
                for (String address :locations){
                    CollectBO collectBO = new CollectBO();
                    //房租缴付总金额
                    Double rent = 0.0;
                    //水费缴付总金额
                    Double waterPayMoney = 0.0;
                    //电费缴付总金额
                    Double energyPayMoney = 0.0;
                    //燃气费缴付总金额
                    Double gasRechargeLines = 0.0;
                    //管理费、卫生费总金额
                    Double fee = 0.0;
                    //平摊的水费
                    Double waterStaffPay = 0.0;
                    //平摊的电费
                    Double energyStaffPay = 0.0;
                    //平摊的电气费
                    Double gasStaffPay = 0.0;
                    //预缴水费
                    Double waterStaffPrepay = 0.0;
                    //预缴电费
                    Double energyStaffPrepay = 0.0;
                    //预缴燃气费
                    Double gasStaffPrepay = 0.0;
                    //员工应缴金额
                    Double staffPayCollect = 0.0;
                    //员工核实确认数
                    Integer staffVerifySureNumber = 0;
                    //员工核实有误数
                    Integer staffVerifyWrongNumber = 0;
                    RentPayDTO rentPayDTO2 = new RentPayDTO();
                    rentPayDTO2.getConditions().add(Restrict.eq("area",area));
                    rentPayDTO2.getConditions().add(Restrict.eq("projectGroup",department));
                    rentPayDTO2.getConditions().add(Restrict.eq("address",address));
                    rentPayDTO2.getConditions().add(Restrict.between("payDate",startTime));
                    List<RentPay> rentPays3 = rentPaySer.findByCis(rentPayDTO2);
                    if (rentPays3 != null && rentPays3.size() > 0){
                        rent = rentPays3.stream().filter(p -> p.getRent() != null).mapToDouble( p -> p.getRent()).sum();
                        waterPayMoney = rentPays3.stream().filter(p -> p.getWaterPayMoney() != null).mapToDouble(p -> p.getWaterPayMoney()).sum();
                        energyPayMoney = rentPays3.stream().filter(p -> p.getEnergyPayMoney() != null).mapToDouble(p -> p.getEnergyPayMoney()).sum();
                        gasRechargeLines = rentPays3.stream().filter(p -> p.getGasRechargeLines() != null).mapToDouble(p -> p.getGasRechargeLines()).sum();
                        fee = rentPays3.stream().filter(p -> p.getFee() != null).mapToDouble(p -> p.getFee()).sum();
                    }
                    StayUtilitiesDTO stayUtilitiesDTO = new StayUtilitiesDTO();
                    stayUtilitiesDTO.getConditions().add(Restrict.eq("area",area));
                    stayUtilitiesDTO.getConditions().add(Restrict.eq("projectGroup",department));
                    stayUtilitiesDTO.getConditions().add(Restrict.eq("address",address));
                    List<StayUtilities> stayUtilities = stayUtilitiesSer.findByCis(stayUtilitiesDTO);
                    Double waterStaffPay1 = 0.0;
                    Double energyStaffPay1 = 0.0;
                    Double gasStaffPay1 = 0.0;
                    Double waterStaffPrepay1 = 0.0;
                    Double energyStaffPrepay1 = 0.0;
                    Double gasStaffPrepay1 = 0.0;
                    Double staffPayCollect1 = 0.0;
                    Integer staffVerifySureNumber1 = 0;
                    Integer staffVerifyWrongNumber1 = 0;
                    if (stayUtilities !=null && stayUtilities.size() > 0){
                        waterStaffPay1 = stayUtilities.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPay1 = stayUtilities.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPay1 = stayUtilities.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        waterStaffPrepay1 = stayUtilities.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPrepay1 = stayUtilities.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPrepay1 = stayUtilities.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        staffPayCollect1 = stayUtilities.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                        staffVerifySureNumber1 = (int)stayUtilities.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.CONFIRM).count();
                        staffVerifyWrongNumber1 = (int)stayUtilities.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.ERROR).count();
                    }
                    Double waterStaffPay2 = 0.0;
                    Double energyStaffPay2 = 0.0;
                    Double gasStaffPay2 = 0.0;
                    Double waterStaffPrepay2 = 0.0;
                    Double energyStaffPrepay2 = 0.0;
                    Double gasStaffPrepay2 = 0.0;
                    Double staffPayCollect2 = 0.0;
                    Integer staffVerifySureNumber2 = 0;
                    Integer staffVerifyWrongNumber2 = 0;
                    String[] fields = new String[]{"waterStaffPay","energyStaffPay","gasStaffPay","waterStaffPrepay","energyStaffPrepay","gasStaffPrepay","staffPayCollect"};
                    StringBuilder sql = new StringBuilder("SELECT waterStaffPay,energyStaffPay,gasStaffPay,waterStaffPrepay,energyStaffPrepay ,gasStaffPrepay,staffPayCollect FROM rentutilitiespay_stayutilities ");
                    sql.append("WHERE area='"+area+"' ");
                    sql.append("and projectGroup='"+department+"' ");
                    sql.append("and address='"+address+"' ");
                    sql.append("and stayEndTime < '"+startTime[0]+"'");
                    List<StayUtilities> stayUtilities1 = stayUtilitiesSer.findBySql(sql.toString(),StayUtilities.class,fields);
                    if (stayUtilities1 != null && stayUtilities1.size() > 0){
                        waterStaffPay2 = stayUtilities1.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPay2 = stayUtilities1.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPay2 = stayUtilities1.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        waterStaffPrepay2 = stayUtilities1.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPrepay2 = stayUtilities1.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPrepay2 = stayUtilities1.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        staffPayCollect2 = stayUtilities1.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                        staffVerifySureNumber2 = (int)stayUtilities1.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.CONFIRM).count();
                        staffVerifyWrongNumber2 = (int)stayUtilities1.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.ERROR).count();
                    }
                    Double waterStaffPay3 = 0.0;
                    Double energyStaffPay3 = 0.0;
                    Double gasStaffPay3 = 0.0;
                    Double waterStaffPrepay3 = 0.0;
                    Double energyStaffPrepay3 = 0.0;
                    Double gasStaffPrepay3 = 0.0;
                    Double staffPayCollect3 = 0.0;
                    Integer staffVerifySureNumber3 = 0;
                    Integer staffVerifyWrongNumber3 = 0;
                    StringBuilder sql2 = new StringBuilder("SELECT waterStaffPay,energyStaffPay,gasStaffPay,waterStaffPrepay,energyStaffPrepay ,gasStaffPrepay,staffPayCollect FROM rentutilitiespay_stayutilities ");
                    sql2.append("WHERE area='"+area+"' ");
                    sql2.append("and projectGroup='"+department+"' ");
                    sql2.append("and address='"+address+"' ");
                    sql2.append("and stayStartTime > '"+startTime[1]+"'");
                    List<StayUtilities> stayUtilities2 = stayUtilitiesSer.findBySql(sql.toString(),StayUtilities.class,fields);
                    if (stayUtilities1 != null && stayUtilities1.size() > 0){
                        waterStaffPay3 = stayUtilities2.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPay3 = stayUtilities2.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPay3 = stayUtilities2.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        waterStaffPrepay3 = stayUtilities2.stream().filter(p -> p.getWaterStaffPay() != null).mapToDouble(p -> p.getWaterStaffPay()).sum();
                        energyStaffPrepay3 = stayUtilities2.stream().filter(p -> p.getEnergyStaffPay() != null).mapToDouble(p -> p.getEnergyStaffPay()).sum();
                        gasStaffPrepay3 = stayUtilities2.stream().filter(p -> p.getGasStaffPay() != null).mapToDouble(p -> p.getGasStaffPay()).sum();
                        staffPayCollect3 = stayUtilities2.stream().filter(p -> p.getStaffPayCollect() != null).mapToDouble(p -> p.getStaffPayCollect()).sum();
                        staffVerifySureNumber3 = (int)stayUtilities2.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.CONFIRM).count();
                        staffVerifyWrongNumber3 = (int)stayUtilities2.stream().filter(p -> p.getStaffVerify() != null).map(p -> p.getStaffVerify() == StaffVerify.ERROR).count();
                    }
                    waterStaffPay = waterStaffPay1 - waterStaffPay2 -waterStaffPay3;
                    energyStaffPay = energyStaffPay - energyStaffPay2 - energyStaffPay3;
                    gasStaffPay = gasStaffPay1 - gasStaffPay2 - gasStaffPay3;
                    waterStaffPrepay = waterStaffPrepay1 - waterStaffPay2 - waterStaffPay2;
                    energyStaffPrepay = energyStaffPrepay1 - energyStaffPay2 - energyStaffPay3;
                    gasStaffPrepay = gasStaffPrepay1 - gasStaffPay2 - gasStaffPay3;
                    staffPayCollect = staffPayCollect1 - staffPayCollect2 - staffPayCollect3;
                    staffVerifySureNumber = staffVerifySureNumber1 - staffVerifySureNumber2 - staffVerifySureNumber3;
                    staffVerifyWrongNumber = staffVerifyWrongNumber1 - staffVerifySureNumber2 - staffVerifySureNumber3;
                    collectBO.setAddress(address);
                    collectBO.setRent(rent);
                    collectBO.setWaterPayMoney(waterPayMoney);
                    collectBO.setEnergyPayMoney(energyPayMoney);
                    collectBO.setGasRechargeLines(gasRechargeLines);
                    collectBO.setFee(fee);
                    collectBO.setWaterStaffPay(waterStaffPay);
                    collectBO.setEnergyStaffPay(energyStaffPay);
                    collectBO.setGasStaffPay(gasStaffPay);
                    collectBO.setWaterStaffPrepay(waterStaffPrepay);
                    collectBO.setEnergyStaffPrepay(energyStaffPrepay);
                    collectBO.setGasStaffPrepay(gasStaffPrepay);
                    collectBO.setStaffPayCollect(staffPayCollect);
                    collectBO.setStaffVerifySureNumber(staffVerifySureNumber);
                    collectBO.setStaffVerifyWrongNumber(staffVerifyWrongNumber);
                    collectBOS.add(collectBO);
                }
                departmentCollectBO.setDepartment(department);
                departmentCollectBO.setCollectList(collectBOS);
                departmentCollectBOS.add(departmentCollectBO);
            }
            areaCollectBO.setArea(area);
            areaCollectBO.setDepartmentCollectList(departmentCollectBOS);
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }
}