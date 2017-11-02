package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
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
import com.bjike.goddess.dispatchcar.enums.GuideAddStatus;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddStatus guideAddrStatus = guidePermissionTO.getGuideAddStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<AreaCollectBO> dayCollect(String day) throws SerException {
        LocalDate[] startDay = null;
        if (StringUtils.isNotBlank(day)){
            LocalDate startDate = DateUtil.parseDate(day);
            LocalDate endDate = DateUtil.parseDate(day);
            startDay = new LocalDate[]{startDate,endDate};
        }else{
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now();
            startDay = new LocalDate[]{startDate,endDate};
        }
        return collect(startDay);
    }

    @Override
    public List<AreaCollectBO> weekCollect(Integer year,Integer month,Integer week) throws SerException {
        LocalDate[] startTime = null;
        if (year != null && month != null && week != null){
             startTime = DateUtil.getWeekTimes(year,month,week);
        }else {
            LocalDate startDate = DateUtil.getStartWeek();
            LocalDate endDate = DateUtil.getEndWeek();
            startTime = new LocalDate[]{startDate,endDate};
        }
        return collect(startTime);

    }

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
        DispatchCarInfoDTO dispatchCarInfoDTO = new DispatchCarInfoDTO();
        dispatchCarInfoDTO.getConditions().add(Restrict.between("dispatchDate",startTime));
        List<DispatchCarInfo> dispatchCarInfos = dispatchCarInfoSer.findByCis(dispatchCarInfoDTO);
        Set<String> areaSet = dispatchCarInfos.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaCollectBO> areaCollectBOS = new ArrayList<>();
        for(String area :areaSet){
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.between("dispatchDate",startTime));
            dto.getConditions().add(Restrict.eq("area",area));
            List<DispatchCarInfo> dispatchCarInfos1 = dispatchCarInfoSer.findByCis(dto);
            Set<String> groupSet = dispatchCarInfos1.stream().map(p -> p.getGroup()).collect(Collectors.toSet());
            AreaCollectBO areaCollectBO = new AreaCollectBO();
            List<DepartmentCollectBO> departmentCollectBOS = new ArrayList<>();
            for(String group : groupSet){
                //todo 项目出车数量
                Integer dispatchcarNumber = 0;
                //已有出车记录数
                Integer hasCarRecordNumber = 0;
                //人工录入出车单数
                Integer peopleTypeNumber = 0;
                //有问题出车数
                Integer hasProblemNumber = 0;
                //已解决出车记录问题数
                Integer alreadySolveNumber = 0;
                //审核
                Integer audityNumber = 0;
                Integer budgetNumber = 0;
                //账务模块核对数
                Integer accountNumber = 0;
                //资金模块核对数
                Integer moneyAccountNumber = 0;
                //租车费
                Double rentcarCost = 0.0;
                //停车费
                Double parkCost = 0.0;
                //过路费
                Double roadCost = 0.0;
                //餐补费
                Double mealCost = 0.0;
                //已付款出车数
                long payedRentcarNumber = 0;
                //已付款出车费
                Double payedRentcarCost = 0.0;

                DepartmentCollectBO departmentCollectBO = new DepartmentCollectBO();
                DispatchCarInfoDTO dto1 = new DispatchCarInfoDTO();
                DispatchcarRecordCollectBO dispatchcarRecordCollectBO = new DispatchcarRecordCollectBO();
                dto1.getConditions().add(Restrict.between("dispatchDate",startTime));
                dto1.getConditions().add(Restrict.eq("area",area));
                dto1.getConditions().add(Restrict.eq("group",group));
                List<DispatchCarInfo> dispatchCarInfos2 = dispatchCarInfoSer.findByCis(dto1);
                if (dispatchCarInfos2 != null && dispatchCarInfos2.size() > 0){
                    hasCarRecordNumber = dispatchCarInfos2.size();
                }
                //项目出车数
                dispatchcarRecordCollectBO.setDispatchcarNumber(dispatchcarNumber);
                //已有出车记录数
                dispatchcarRecordCollectBO.setHasCarRecordNumber(hasCarRecordNumber);

                dto1.getConditions().add(Restrict.eq("carSource", CarSource.MANUALENTRY));
                List<DispatchCarInfo> dispatchCarInfos3 = dispatchCarInfoSer.findByCis(dto1);
                if (dispatchCarInfos3 != null && dispatchCarInfos3.size() > 0){
                    peopleTypeNumber = dispatchCarInfos3.size();
                }
                //人工录入出车单数
                dispatchcarRecordCollectBO.setPeopleTypeNumber(peopleTypeNumber);

                CheckChangeCarDTO dto2 = new CheckChangeCarDTO();
                dto2.getConditions().add(Restrict.between("dispatchDate",startTime));
                List<CheckChangeCar> checkChangeCars = checkChangeCarSer.findByCis(dto2);
                if (checkChangeCars != null && checkChangeCars.size() > 0){
                    hasProblemNumber = checkChangeCars.size();
                }
                //有问题出车记录数
                dispatchcarRecordCollectBO.setHasProblemNumber(hasProblemNumber);

                dto2.getConditions().add(Restrict.eq("ifSolve",true));
                List<CheckChangeCar> checkChangeCars1 = checkChangeCarSer.findByCis(dto2);
                List<DispatchCarInfo> dispatchCarInfos4 = dispatchCarInfos2.stream().filter(p -> p.getIfPass() != null).collect(Collectors.toList());
                if (dispatchCarInfos4 != null && dispatchCarInfos4.size() > 0){
                    alreadySolveNumber = dispatchCarInfos4.size();
                    audityNumber = dispatchCarInfos4.size();
                }
                //已解决出车记录问题数
                dispatchcarRecordCollectBO.setAlreadySolveNumber(alreadySolveNumber);
                //负责人已审核数
                dispatchcarRecordCollectBO.setAudityNumber(audityNumber);
                //预算模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos5 = dispatchCarInfos2.stream().filter(p -> p.getBudgetModuleIdea() != null).collect(Collectors.toList());
                if (dispatchCarInfos5 != null && dispatchCarInfos5.size() > 0){
                    budgetNumber = dispatchCarInfos5.size();
                }
                dispatchcarRecordCollectBO.setBudgetNumber(budgetNumber);
                //账务模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos6 = dispatchCarInfos2.stream().filter(p -> p.getAccountModuleIdea() != null).collect(Collectors.toList());
                if (dispatchCarInfos6 != null && dispatchCarInfos6.size() > 0){
                    accountNumber = dispatchCarInfos6.size();
                }
                dispatchcarRecordCollectBO.setAccountNumber(accountNumber);
                //资金模块负责人核对数
                List<DispatchCarInfo> dispatchCarInfos7 = dispatchCarInfos2.stream().filter(p -> p.getMoneyModuleIdea() != null).collect(Collectors.toList());
                if (dispatchCarInfos7 != null && dispatchCarInfos7.size() > 0){
                    moneyAccountNumber = dispatchCarInfos7.size();
                }
                dispatchcarRecordCollectBO.setMoneyAccountNumber(moneyAccountNumber);
                //租车费
                rentcarCost = dispatchCarInfos2.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
                dispatchcarRecordCollectBO.setRentcarCost(rentcarCost);
                //停车费
                parkCost = dispatchCarInfos2.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
                dispatchcarRecordCollectBO.setParkCost(parkCost);
                //过路费
                roadCost = dispatchCarInfos2.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
                dispatchcarRecordCollectBO.setRoadCost(roadCost);
                //餐补费
                mealCost = dispatchCarInfos2.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
                dispatchcarRecordCollectBO.setMealCost(mealCost);
                //已经付款出车数
                payedRentcarNumber = dispatchCarInfos2.stream().filter(p -> p.getIfPayed() !=null && p.getIfPayed() ).count();
                dispatchcarRecordCollectBO.setPayedRentcarNumber((int)payedRentcarNumber);
                //已经付款出车费
                payedRentcarCost = rentcarCost + parkCost + mealCost +rentcarCost;
                dispatchcarRecordCollectBO.setPayedRentcarCost(payedRentcarCost);
                departmentCollectBO.setDepartment(group);
                departmentCollectBO.setDispatchcarRecordCollect(dispatchcarRecordCollectBO);
                departmentCollectBOS.add(departmentCollectBO);
            }
            areaCollectBO.setDepartmentCollect(departmentCollectBOS);
            areaCollectBO.setArea(area);
            areaCollectBOS.add(areaCollectBO);
        }
        return areaCollectBOS;
    }
}