package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalMonthCountBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.entity.ArrivalMonth;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 地区收入月业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ArrivalMonthSerImpl extends ServiceImpl<ArrivalMonth, ArrivalMonthDTO> implements ArrivalMonthSer {
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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.busCusPermission("2");
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
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
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
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case DETAIL:
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
    @Transactional(rollbackFor = {SerException.class})
    public ArrivalMonthBO save(ArrivalMonthTO to) throws SerException {
        checkAddIdentity();
        ArrivalMonth arrivalMonth = BeanTransform.copyProperties(to, ArrivalMonth.class, true);
        super.save(arrivalMonth);
        return BeanTransform.copyProperties(arrivalMonth, ArrivalMonthBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ArrivalMonthTO to) throws SerException {
        checkAddIdentity();
        ArrivalMonth arrivalMonth = super.findById(to.getId());
        arrivalMonth = BeanTransform.copyProperties(to, ArrivalMonth.class, true);
        super.update(arrivalMonth);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void deleteAll() throws SerException {
        List<ArrivalMonth> list = super.findAll();
        for (ArrivalMonth a : list) {
            super.remove(a.getId());
        }
    }

    @Override
    public List<ArrivalMonthBO> list(ArrivalMonthDTO dto) throws SerException {
        checkSeeIdentity();
        List<ArrivalMonth> list = super.findByCis(dto, true);
        List<ArrivalMonthBO> boList = new ArrayList<ArrivalMonthBO>();
        for (ArrivalMonth a : list) {
            ArrivalMonthBO bo = BeanTransform.copyProperties(a, ArrivalMonthBO.class);
            bo.setWorkDifferences(a.getTargetWork() - a.getActualWork());
            bo.setIncomeDifferences(a.getTargetIncome() - a.getPlanIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ArrivalMonthBO findByID(String id) throws SerException {
        ArrivalMonth arrivalMonth = super.findById(id);
        ArrivalMonthBO bo = BeanTransform.copyProperties(arrivalMonth, ArrivalMonthBO.class);
        bo.setWorkDifferences(arrivalMonth.getTargetWork() - arrivalMonth.getActualWork());
        bo.setIncomeDifferences(arrivalMonth.getTargetIncome() - arrivalMonth.getPlanIncome());
        return bo;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public List<String> findAllArrivals() throws SerException {
        List<ArrivalMonth> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ArrivalMonth a : list) {
            set.add(a.getArrival());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllYears() throws SerException {
        List<ArrivalMonth> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ArrivalMonth a : list) {
            set.add(a.getYear());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    @Override
    public List<ArrivalMonthCountBO> count() throws SerException {
        checkSeeIdentity();
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        ArrivalMonthDTO dto = new ArrivalMonthDTO();
        List<ArrivalMonth> list = super.findByCis(dto);
        List<ArrivalMonthCountBO> boList = new ArrayList<ArrivalMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        for (String arrival : arrivals) {
            for (Integer year : years) {
                for (ArrivalMonth arrivalMonth : list) {
                    if (arrivalMonth.getArrival().equals(arrival) && arrivalMonth.getYear().equals(year)) {
                        targetIncomeSum += arrivalMonth.getTargetIncome();
                        planIncomeSum += arrivalMonth.getPlanIncome();
                        double incomeDifference = arrivalMonth.getTargetIncome() - arrivalMonth.getPlanIncome();
                        incomeDifferencesSum += incomeDifference;
                        targetWorkSum += arrivalMonth.getTargetWork();
                        actualWorkSum += arrivalMonth.getActualWork();
                        int workDifference = arrivalMonth.getTargetWork() - arrivalMonth.getActualWork();
                        workDifferencesSum += workDifference;
                    }
                }
                if (targetWorkSum != 0) {
                    ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                    bo.setArrival(arrival);
                    bo.setYear(year);
                    bo.setTargetIncomeSum(targetIncomeSum);
                    bo.setPlanIncomeSum(planIncomeSum);
                    bo.setIncomeDifferencesSum(incomeDifferencesSum);
                    bo.setTargetWorkSum(targetWorkSum);
                    bo.setActualWorkSum(actualWorkSum);
                    bo.setWorkDifferencesSum(workDifferencesSum);
                    Double scale = Double.parseDouble(String.format("%.2f", planIncomeSum / targetIncomeSum));
                    bo.setScale(scale);
                    boList.add(bo);
                    targetIncomeSum = 0.00;
                    planIncomeSum = 0.00;
                    incomeDifferencesSum = 0.00;
                    targetWorkSum = 0;
                    actualWorkSum = 0;
                    workDifferencesSum = 0;
                }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalMonthCountBO> conditionsCount(ArrivalMonthDTO dto1) throws SerException {
        checkSeeIdentity();
        List<Integer> years = findAllYears();
        List<ArrivalMonthCountBO> boList = new ArrayList<ArrivalMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        String[] arrivals = dto1.getArrivals();
        if (arrivals != null) {
            for (String arrival : arrivals) {
                ArrivalMonthDTO dto = new ArrivalMonthDTO();
                dto.getConditions().add(Restrict.eq("arrival", arrival));
                List<ArrivalMonth> list = super.findByCis(dto);
                for (Integer year : years) {
                    for (ArrivalMonth arrivalMonth : list) {
                        if (arrivalMonth.getYear().equals(year)) {
                            targetIncomeSum += arrivalMonth.getTargetIncome();
                            planIncomeSum += arrivalMonth.getPlanIncome();
                            double incomeDifference = arrivalMonth.getTargetIncome() - arrivalMonth.getPlanIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += arrivalMonth.getTargetWork();
                            actualWorkSum += arrivalMonth.getActualWork();
                            int workDifference = arrivalMonth.getTargetWork() - arrivalMonth.getActualWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
                        ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                        bo.setArrival(arrival);
                        bo.setYear(year);
                        bo.setTargetIncomeSum(targetIncomeSum);
                        bo.setPlanIncomeSum(planIncomeSum);
                        bo.setIncomeDifferencesSum(incomeDifferencesSum);
                        bo.setTargetWorkSum(targetWorkSum);
                        bo.setActualWorkSum(actualWorkSum);
                        bo.setWorkDifferencesSum(workDifferencesSum);
                        Double scale = Double.parseDouble(String.format("%.2f", planIncomeSum / targetIncomeSum));
                        bo.setScale(scale);
                        boList.add(bo);
                        targetIncomeSum = 0.00;
                        planIncomeSum = 0.00;
                        incomeDifferencesSum = 0.00;
                        targetWorkSum = 0;
                        actualWorkSum = 0;
                        workDifferencesSum = 0;
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalWeekBO> findDetail(String id) throws SerException {
        checkSeeIdentity();
        ArrivalMonth arrivalMonth = super.findById(id);
        if (arrivalMonth == null) {
            throw new SerException("该对象不存在");
        }
        String[] arrivals = new String[]{arrivalMonth.getArrival()};
        Integer[] years = new Integer[]{arrivalMonth.getYear()};
        Integer[] months = new Integer[]{arrivalMonth.getMonth()};
        List<ArrivalWeekBO> list = null;
        for (int i = 0; i < arrivals.length && i < years.length && i < months.length; i++) {
            String sql = "SELECT week,targetWork,actualWork,price,targetIncome,planIncome " +
                    "from budget_arrivalweek where arrival='" + arrivals[i] + "' AND year='" + years[i] + "' AND month='" + months[i] + "'";
            String[] fields = new String[]{"week", "targetWork", "actualWork", "price", "targetIncome", "planIncome"};
            list = super.findBySql(sql, ArrivalWeekBO.class, fields);
        }
        for (ArrivalWeekBO bo : list) {
            bo.setArrival(arrivalMonth.getArrival());
            bo.setYear(arrivalMonth.getYear());
            bo.setMonth(arrivalMonth.getMonth());
            bo.setIncomeDifferences(bo.getTargetIncome() - bo.getPlanIncome());
            bo.setWorkDifferences(arrivalMonth.getTargetWork() - arrivalMonth.getActualWork());
        }
        return list;
    }

    @Override
    public Long countNum(ArrivalMonthDTO dto) throws SerException {
        return super.count(dto);
    }
}