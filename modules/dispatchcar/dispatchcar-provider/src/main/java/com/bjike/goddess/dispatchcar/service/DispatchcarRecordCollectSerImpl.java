package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.bo.AreaCollectBO;
import com.bjike.goddess.dispatchcar.bo.DepartmentCollectBO;
import com.bjike.goddess.dispatchcar.bo.DispatchcarRecordCollectBO;
import com.bjike.goddess.dispatchcar.dto.CheckChangeCarDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchcarRecordCollectDTO;
import com.bjike.goddess.dispatchcar.entity.CheckChangeCar;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;
import com.bjike.goddess.dispatchcar.entity.DispatchcarRecordCollect;
import com.bjike.goddess.dispatchcar.enums.CarSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* 出车记录管理汇总业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="dispatchcarSerCache")
@Service
public class DispatchcarRecordCollectSerImpl extends ServiceImpl<DispatchcarRecordCollect, DispatchcarRecordCollectDTO> implements DispatchcarRecordCollectSer {
    @Autowired
    private DispatchCarInfoSer dispatchCarInfoSer;

    @Autowired
    private CheckChangeCarSer checkChangeCarSer;

    @Override
    public List<AreaCollectBO> dayCollect(String day) throws SerException {
        LocalDate startDay = null;
        if("".equals(day) || day == null){
            startDay = LocalDate.now();
        }else {
            startDay = DateUtil.parseDate(day);
        }
        DispatchCarInfoDTO dispatchCarInfoDTO = new DispatchCarInfoDTO();
        dispatchCarInfoDTO.getConditions().add(Restrict.eq("dispatchDate",startDay));
        List<DispatchCarInfo> dispatchCarInfos = dispatchCarInfoSer.findByCis(dispatchCarInfoDTO);
        Set<String> areaSet = dispatchCarInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for(String area :areaSet){
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.eq("dispatchDate",startDay));
            dto.getConditions().add(Restrict.eq("area",area));
            List<DispatchCarInfo> dispatchCarInfos1 = dispatchCarInfoSer.findByCis(dto);
            Set<String> groupSet = dispatchCarInfos1.stream().map(p -> p.getGroup()).collect(Collectors.toSet());
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            for(String group : groupSet){
                List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                DispatchCarInfoDTO dto1 = new DispatchCarInfoDTO();
                DispatchcarRecordCollectBO dispatchcarRecordCollectBO = new DispatchcarRecordCollectBO();
                dto1.getConditions().add(Restrict.eq("dispatchDate",startDay));
                dto1.getConditions().add(Restrict.eq("area",area));
                dto1.getConditions().add(Restrict.eq("group",group));
                List<DispatchCarInfo> dispatchCarInfos2 = dispatchCarInfoSer.findByCis(dto1);
                //已有出车记录数
                dispatchcarRecordCollectBO.setHasCarRecordNumber(dispatchCarInfos2.size());
                dto1.getConditions().add(Restrict.eq("carSource", CarSource.MANUALENTRY));
                List<DispatchCarInfo> dispatchCarInfos3 = dispatchCarInfoSer.findByCis(dto1);
                //人工录入出车单数
                dispatchcarRecordCollectBO.setPeopleTypeNumber(dispatchCarInfos3.size());
                CheckChangeCarDTO dto2 = new CheckChangeCarDTO();
                dto2.getConditions().add(Restrict.eq("dispatchDate",startDay));
                List<CheckChangeCar> checkChangeCars = checkChangeCarSer.findByCis(dto2);
                //有问题出车记录数
                dispatchcarRecordCollectBO.setHasProblemNumber(checkChangeCars.size());
                dto2.getConditions().add(Restrict.eq("ifSolve",true));
                List<CheckChangeCar> checkChangeCars1 = checkChangeCarSer.findByCis(dto2);
                List<DispatchCarInfo> dispatchCarInfos4 = dispatchCarInfos2.stream().filter(p -> p.getIfPass() != null).collect(Collectors.toList());
                //已解决出车记录问题数
                dispatchcarRecordCollectBO.setAlreadySolveNumber(checkChangeCars1.size());
                //负责人已审核数
                dispatchcarRecordCollectBO.setAudityNumber(dispatchCarInfos4.size());
                //预算模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos5 = dispatchCarInfos2.stream().filter(p -> p.getBudgetModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setBudgetNumber(dispatchCarInfos5.size());
                //账务模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos6 = dispatchCarInfos2.stream().filter(p -> p.getAccountModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setAccountNumber(dispatchCarInfos6.size());
                //资金模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos7 = dispatchCarInfos2.stream().filter(p -> p.getMoneyModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setMoneyAccountNumber(dispatchCarInfos7.size());
                //租车费
                Double rentcarCost = dispatchCarInfos2.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
                dispatchcarRecordCollectBO.setRentcarCost(rentcarCost);
                //停车费
                Double parkCost = dispatchCarInfos2.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
                dispatchcarRecordCollectBO.setParkCost(parkCost);
                //过路费
                Double roadCost = dispatchCarInfos2.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
                dispatchcarRecordCollectBO.setRoadCost(roadCost);
                //餐补费
                Double mealCost = dispatchCarInfos2.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
                dispatchcarRecordCollectBO.setMealCost(mealCost);
                //已经付款出车数
                long payedRentcarNumber = dispatchCarInfos2.stream().filter(p -> p.getIfPayed() !=null && p.getIfPayed() ).count();
                dispatchcarRecordCollectBO.setPayedRentcarNumber((int)payedRentcarNumber);
                //已经付款出车费
                Double payedRentcarCost = rentcarCost + parkCost + mealCost +rentcarCost;
                dispatchcarRecordCollectBO.setPayedRentcarCost(payedRentcarCost);
                departmentCollectBO.setDispatchcarRecordCollect(dispatchcarRecordCollectBO);
                departmentCollectBOS.add(departmentCollectBO);
                areaCollectBO.setDepartmentCollect(departmentCollectBOS);
            }
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }

    @Override
    public List<AreaCollectBO> weekCollect(String day) throws SerException {
        LocalDate endDay = null;
        if("".equals(day) || day == null){
            endDay = LocalDate.now();
        } else {
            endDay = DateUtil.parseDate(day);
        }
        Integer week = DateUtil.parseDate(day).getDayOfWeek().getValue();
        LocalDate startDay = null;
        switch (week){
            case 1:
                startDay = endDay;
                break;
            case 2:
                //获得前一天的日期
                startDay = endDay.minusDays(1);
                break;
            case 3:
                startDay = endDay.minusDays(2);
                break;
            case 4:
                startDay = endDay.minusDays(3);
                break;
            case 5:
                startDay = endDay.minusDays(4);
                break;
            case 6:
                startDay = endDay.minusDays(5);
                break;
            case 7:
                startDay = endDay.minusDays(6);
                break;
        }
        LocalDate[] localDates = new LocalDate[]{startDay,endDay};
        DispatchCarInfoDTO dispatchCarInfoDTO = new DispatchCarInfoDTO();
        dispatchCarInfoDTO.getConditions().add(Restrict.between("dispatchDate",localDates));
        List<DispatchCarInfo> dispatchCarInfos = dispatchCarInfoSer.findByCis(dispatchCarInfoDTO);
        Set<String> areaSet = dispatchCarInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for(String area :areaSet){
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.between("dispatchDate",localDates));
            dto.getConditions().add(Restrict.eq("area",area));
            List<DispatchCarInfo> dispatchCarInfos1 = dispatchCarInfoSer.findByCis(dto);
            Set<String> groupSet = dispatchCarInfos1.stream().map(p -> p.getGroup()).collect(Collectors.toSet());
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            for(String group : groupSet){
                List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                DispatchCarInfoDTO dto1 = new DispatchCarInfoDTO();
                DispatchcarRecordCollectBO dispatchcarRecordCollectBO = new DispatchcarRecordCollectBO();
                dto1.getConditions().add(Restrict.between("dispatchDate",localDates));
                dto1.getConditions().add(Restrict.eq("area",area));
                dto1.getConditions().add(Restrict.eq("group",group));
                List<DispatchCarInfo> dispatchCarInfos2 = dispatchCarInfoSer.findByCis(dto1);
                //已有出车记录数
                dispatchcarRecordCollectBO.setHasCarRecordNumber(dispatchCarInfos2.size());
                dto1.getConditions().add(Restrict.eq("carSource", CarSource.MANUALENTRY));
                List<DispatchCarInfo> dispatchCarInfos3 = dispatchCarInfoSer.findByCis(dto1);
                //人工录入出车单数
                dispatchcarRecordCollectBO.setPeopleTypeNumber(dispatchCarInfos3.size());
                CheckChangeCarDTO dto2 = new CheckChangeCarDTO();
                dto2.getConditions().add(Restrict.between("dispatchDate",localDates));
                List<CheckChangeCar> checkChangeCars = checkChangeCarSer.findByCis(dto2);
                //有问题出车记录数
                dispatchcarRecordCollectBO.setHasProblemNumber(checkChangeCars.size());
                dto2.getConditions().add(Restrict.eq("ifSolve",true));
                List<CheckChangeCar> checkChangeCars1 = checkChangeCarSer.findByCis(dto2);
                List<DispatchCarInfo> dispatchCarInfos4 = dispatchCarInfos2.stream().filter(p -> p.getIfPass() != null).collect(Collectors.toList());
                //已解决出车记录问题数
                dispatchcarRecordCollectBO.setAlreadySolveNumber(checkChangeCars1.size());
                //负责人已审核数
                dispatchcarRecordCollectBO.setAudityNumber(dispatchCarInfos4.size());
                //预算模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos5 = dispatchCarInfos2.stream().filter(p -> p.getBudgetModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setBudgetNumber(dispatchCarInfos5.size());
                //账务模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos6 = dispatchCarInfos2.stream().filter(p -> p.getAccountModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setAccountNumber(dispatchCarInfos6.size());
                //资金模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos7 = dispatchCarInfos2.stream().filter(p -> p.getMoneyModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setMoneyAccountNumber(dispatchCarInfos7.size());
                //租车费
                Double rentcarCost = dispatchCarInfos2.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
                dispatchcarRecordCollectBO.setRentcarCost(rentcarCost);
                //停车费
                Double parkCost = dispatchCarInfos2.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
                dispatchcarRecordCollectBO.setParkCost(parkCost);
                //过路费
                Double roadCost = dispatchCarInfos2.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
                dispatchcarRecordCollectBO.setRoadCost(roadCost);
                //餐补费
                Double mealCost = dispatchCarInfos2.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
                dispatchcarRecordCollectBO.setMealCost(mealCost);
                //已经付款出车数
                long payedRentcarNumber = dispatchCarInfos2.stream().filter(p -> p.getIfPayed() !=null && p.getIfPayed() ).count();
                dispatchcarRecordCollectBO.setPayedRentcarNumber((int)payedRentcarNumber);
                //已经付款出车费
                Double payedRentcarCost = rentcarCost + parkCost + mealCost +rentcarCost;
                dispatchcarRecordCollectBO.setPayedRentcarCost(payedRentcarCost);
                departmentCollectBO.setDispatchcarRecordCollect(dispatchcarRecordCollectBO);
                departmentCollectBOS.add(departmentCollectBO);
                areaCollectBO.setDepartmentCollect(departmentCollectBOS);
            }
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }

    @Override
    public List<AreaCollectBO> monthCollect(String year, String month) throws SerException {
        LocalDate startDay = null;
        LocalDate endDay = null;
        if(!"".equals(year) && year != null && !"".equals(month) && month != null){
            if(month.length()==1) {
                String startDate = year + " 0"+month + "-" + "01";
                startDay = DateUtil.parseDate(startDate);
                Integer nextMonth = Integer.valueOf(month)+1;
                if(nextMonth <10) {
                    String nextDate = year + " 0" + nextMonth + "-" + "01";
                    LocalDate nextDay = DateUtil.parseDate(nextDate);
                    endDay = nextDay.minusDays(1);
                }else{
                    String nextDate = year + " " + nextMonth + "-" + "01";
                    LocalDate nextDay = DateUtil.parseDate(nextDate);
                    endDay = nextDay.minusDays(1);
                }
            }else{
                String startDate = year + " "+month + "-" + "1";
                Integer nextMonth = Integer.valueOf(month)+1;
                startDay = DateUtil.parseDate(startDate);
                if(nextMonth >12) {
                    String nextDate = year + " 01"+ "-" + "01";
                    LocalDate nextDay = DateUtil.parseDate(nextDate);
                    endDay = nextDay.minusDays(1);
                }else{
                    String nextDate = year + " " + nextMonth + "-" + "01";
                    LocalDate nextDay = DateUtil.parseDate(nextDate);
                    endDay = nextDay.minusDays(1);
                }
            }
        }
        LocalDate[] localDate = new LocalDate[]{startDay,endDay};
        DispatchCarInfoDTO dispatchCarInfoDTO = new DispatchCarInfoDTO();
        dispatchCarInfoDTO.getConditions().add(Restrict.between("dispatchDate",localDate));
        List<DispatchCarInfo> dispatchCarInfos = dispatchCarInfoSer.findByCis(dispatchCarInfoDTO);
        Set<String> areaSet = dispatchCarInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for(String area :areaSet){
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.between("dispatchDate",localDate));
            dto.getConditions().add(Restrict.eq("area",area));
            List<DispatchCarInfo> dispatchCarInfos1 = dispatchCarInfoSer.findByCis(dto);
            Set<String> groupSet = dispatchCarInfos1.stream().map(p -> p.getGroup()).collect(Collectors.toSet());
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            for(String group : groupSet){
                List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                DispatchCarInfoDTO dto1 = new DispatchCarInfoDTO();
                DispatchcarRecordCollectBO dispatchcarRecordCollectBO = new DispatchcarRecordCollectBO();
                dto1.getConditions().add(Restrict.between("dispatchDate",localDate));
                dto1.getConditions().add(Restrict.eq("area",area));
                dto1.getConditions().add(Restrict.eq("group",group));
                List<DispatchCarInfo> dispatchCarInfos2 = dispatchCarInfoSer.findByCis(dto1);
                //已有出车记录数
                dispatchcarRecordCollectBO.setHasCarRecordNumber(dispatchCarInfos2.size());
                dto1.getConditions().add(Restrict.eq("carSource", CarSource.MANUALENTRY));
                List<DispatchCarInfo> dispatchCarInfos3 = dispatchCarInfoSer.findByCis(dto1);
                //人工录入出车单数
                dispatchcarRecordCollectBO.setPeopleTypeNumber(dispatchCarInfos3.size());
                CheckChangeCarDTO dto2 = new CheckChangeCarDTO();
                dto2.getConditions().add(Restrict.between("dispatchDate",localDate));
                List<CheckChangeCar> checkChangeCars = checkChangeCarSer.findByCis(dto2);
                //有问题出车记录数
                dispatchcarRecordCollectBO.setHasProblemNumber(checkChangeCars.size());
                dto2.getConditions().add(Restrict.eq("ifSolve",true));
                List<CheckChangeCar> checkChangeCars1 = checkChangeCarSer.findByCis(dto2);
                List<DispatchCarInfo> dispatchCarInfos4 = dispatchCarInfos2.stream().filter(p -> p.getIfPass() != null).collect(Collectors.toList());
                //已解决出车记录问题数
                dispatchcarRecordCollectBO.setAlreadySolveNumber(checkChangeCars1.size());
                //负责人已审核数
                dispatchcarRecordCollectBO.setAudityNumber(dispatchCarInfos4.size());
                //预算模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos5 = dispatchCarInfos2.stream().filter(p -> p.getBudgetModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setBudgetNumber(dispatchCarInfos5.size());
                //账务模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos6 = dispatchCarInfos2.stream().filter(p -> p.getAccountModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setAccountNumber(dispatchCarInfos6.size());
                //资金模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos7 = dispatchCarInfos2.stream().filter(p -> p.getMoneyModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setMoneyAccountNumber(dispatchCarInfos7.size());
                //租车费
                Double rentcarCost = dispatchCarInfos2.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
                dispatchcarRecordCollectBO.setRentcarCost(rentcarCost);
                //停车费
                Double parkCost = dispatchCarInfos2.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
                dispatchcarRecordCollectBO.setParkCost(parkCost);
                //过路费
                Double roadCost = dispatchCarInfos2.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
                dispatchcarRecordCollectBO.setRoadCost(roadCost);
                //餐补费
                Double mealCost = dispatchCarInfos2.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
                dispatchcarRecordCollectBO.setMealCost(mealCost);
                //已经付款出车数
                long payedRentcarNumber = dispatchCarInfos2.stream().filter(p -> p.getIfPayed() !=null && p.getIfPayed() ).count();
                dispatchcarRecordCollectBO.setPayedRentcarNumber((int)payedRentcarNumber);
                //已经付款出车费
                Double payedRentcarCost = rentcarCost + parkCost + mealCost +rentcarCost;
                dispatchcarRecordCollectBO.setPayedRentcarCost(payedRentcarCost);
                departmentCollectBO.setDispatchcarRecordCollect(dispatchcarRecordCollectBO);
                departmentCollectBOS.add(departmentCollectBO);
                areaCollectBO.setDepartmentCollect(departmentCollectBOS);
            }
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }

    @Override
    public List<AreaCollectBO> allCollect(String day) throws SerException {
        String startDate = "1970 01-01";
        LocalDate startDay = DateUtil.parseDate(startDate);
        LocalDate endDay = null;
        if(!"".equals(day) && day != null){
            endDay = DateUtil.parseDate(day);
        }else{
            endDay = LocalDate.now();
        }
        LocalDate[] localDate = new LocalDate[]{startDay,endDay};
        DispatchCarInfoDTO dispatchCarInfoDTO = new DispatchCarInfoDTO();
        dispatchCarInfoDTO.getConditions().add(Restrict.between("dispatchDate",localDate));
        List<DispatchCarInfo> dispatchCarInfos = dispatchCarInfoSer.findByCis(dispatchCarInfoDTO);
        Set<String> areaSet = dispatchCarInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for(String area :areaSet){
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.between("dispatchDate",localDate));
            dto.getConditions().add(Restrict.eq("area",area));
            List<DispatchCarInfo> dispatchCarInfos1 = dispatchCarInfoSer.findByCis(dto);
            Set<String> groupSet = dispatchCarInfos1.stream().map(p -> p.getGroup()).collect(Collectors.toSet());
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            for(String group : groupSet){
                List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                DispatchCarInfoDTO dto1 = new DispatchCarInfoDTO();
                DispatchcarRecordCollectBO dispatchcarRecordCollectBO = new DispatchcarRecordCollectBO();
                dto1.getConditions().add(Restrict.between("dispatchDate",localDate));
                dto1.getConditions().add(Restrict.eq("area",area));
                dto1.getConditions().add(Restrict.eq("group",group));
                List<DispatchCarInfo> dispatchCarInfos2 = dispatchCarInfoSer.findByCis(dto1);
                //已有出车记录数
                dispatchcarRecordCollectBO.setHasCarRecordNumber(dispatchCarInfos2.size());
                dto1.getConditions().add(Restrict.eq("carSource", CarSource.MANUALENTRY));
                List<DispatchCarInfo> dispatchCarInfos3 = dispatchCarInfoSer.findByCis(dto1);
                //人工录入出车单数
                dispatchcarRecordCollectBO.setPeopleTypeNumber(dispatchCarInfos3.size());
                CheckChangeCarDTO dto2 = new CheckChangeCarDTO();
                dto2.getConditions().add(Restrict.between("dispatchDate",localDate));
                List<CheckChangeCar> checkChangeCars = checkChangeCarSer.findByCis(dto2);
                //有问题出车记录数
                dispatchcarRecordCollectBO.setHasProblemNumber(checkChangeCars.size());
                dto2.getConditions().add(Restrict.eq("ifSolve",true));
                List<CheckChangeCar> checkChangeCars1 = checkChangeCarSer.findByCis(dto2);
                List<DispatchCarInfo> dispatchCarInfos4 = dispatchCarInfos2.stream().filter(p -> p.getIfPass() != null).collect(Collectors.toList());
                //已解决出车记录问题数
                dispatchcarRecordCollectBO.setAlreadySolveNumber(checkChangeCars1.size());
                //负责人已审核数
                dispatchcarRecordCollectBO.setAudityNumber(dispatchCarInfos4.size());
                //预算模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos5 = dispatchCarInfos2.stream().filter(p -> p.getBudgetModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setBudgetNumber(dispatchCarInfos5.size());
                //账务模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos6 = dispatchCarInfos2.stream().filter(p -> p.getAccountModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setAccountNumber(dispatchCarInfos6.size());
                //资金模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos7 = dispatchCarInfos2.stream().filter(p -> p.getMoneyModuleIdea() != null).collect(Collectors.toList());
                dispatchcarRecordCollectBO.setMoneyAccountNumber(dispatchCarInfos7.size());
                //租车费
                Double rentcarCost = dispatchCarInfos2.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
                dispatchcarRecordCollectBO.setRentcarCost(rentcarCost);
                //停车费
                Double parkCost = dispatchCarInfos2.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
                dispatchcarRecordCollectBO.setParkCost(parkCost);
                //过路费
                Double roadCost = dispatchCarInfos2.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
                dispatchcarRecordCollectBO.setRoadCost(roadCost);
                //餐补费
                Double mealCost = dispatchCarInfos2.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
                dispatchcarRecordCollectBO.setMealCost(mealCost);
                //已经付款出车数
                long payedRentcarNumber = dispatchCarInfos2.stream().filter(p -> p.getIfPayed() !=null && p.getIfPayed() ).count();
                dispatchcarRecordCollectBO.setPayedRentcarNumber((int)payedRentcarNumber);
                //已经付款出车费
                Double payedRentcarCost = rentcarCost + parkCost + mealCost +rentcarCost;
                dispatchcarRecordCollectBO.setPayedRentcarCost(payedRentcarCost);
                departmentCollectBO.setDispatchcarRecordCollect(dispatchcarRecordCollectBO);
                departmentCollectBOS.add(departmentCollectBO);
                areaCollectBO.setDepartmentCollect(departmentCollectBOS);
            }
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }
}