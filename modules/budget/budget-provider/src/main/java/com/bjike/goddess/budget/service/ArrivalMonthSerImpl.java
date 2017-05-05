package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalMonthCountBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.entity.ArrivalMonth;
import com.bjike.goddess.budget.entity.ArrivalWeek;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
    private ArrivalWeekSer arrivalWeekSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ArrivalMonthBO save(ArrivalMonthTO to) throws SerException {
        ArrivalMonth arrivalMonth = BeanTransform.copyProperties(to, ArrivalMonth.class, true);
        super.save(arrivalMonth);
        return BeanTransform.copyProperties(arrivalMonth, ArrivalMonthBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ArrivalMonthTO to) throws SerException {
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
        List<ArrivalMonth> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ArrivalMonthBO.class);
    }

    @Override
    public ArrivalMonthBO findByID(String id) throws SerException {
        ArrivalMonth arrivalMonth = super.findById(id);
        return BeanTransform.copyProperties(arrivalMonth, ArrivalMonthBO.class);
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private List<String> findAllArrivals() throws SerException {
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
                    if (arrivalMonth.getArrival().equals(arrival) && arrivalMonth.getYear() == year) {
                        targetIncomeSum += arrivalMonth.getTargetIncome();
                        planIncomeSum += arrivalMonth.getPlanIncome();
                        incomeDifferencesSum += arrivalMonth.getIncomeDifferences();
                        targetWorkSum += arrivalMonth.getTargetWork();
                        actualWorkSum += arrivalMonth.getActualWork();
                        workDifferencesSum += arrivalMonth.getWorkDifferences();
                    }
                }
                ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                bo.setArrival(arrival);
                bo.setYear(year);
                bo.setTargetIncomeSum(targetIncomeSum);
                bo.setPlanIncomeSum(planIncomeSum);
                bo.setIncomeDifferencesSum(incomeDifferencesSum);
                bo.setTargetWorkSum(targetWorkSum);
                bo.setActualWorkSum(actualWorkSum);
                bo.setWorkDifferencesSum(workDifferencesSum);
                boList.add(bo);
                targetIncomeSum = 0.00;
                planIncomeSum = 0.00;
                incomeDifferencesSum = 0.00;
                targetWorkSum = 0;
                actualWorkSum = 0;
                workDifferencesSum = 0;
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalMonthCountBO> conditionsCount(String[] arrivals) throws SerException {
        List<Integer> years = findAllYears();
        List<ArrivalMonthCountBO> boList = new ArrayList<ArrivalMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        for (String arrival : arrivals) {
            ArrivalMonthDTO dto = new ArrivalMonthDTO();
            dto.getConditions().add(Restrict.eq("arrival", arrival));
            List<ArrivalMonth> list = super.findByCis(dto);
            for (Integer year : years) {
                for (ArrivalMonth arrivalMonth : list) {
                    if (arrivalMonth.getYear() == year) {
                        targetIncomeSum += arrivalMonth.getTargetIncome();
                        planIncomeSum += arrivalMonth.getPlanIncome();
                        incomeDifferencesSum += arrivalMonth.getIncomeDifferences();
                        targetWorkSum += arrivalMonth.getTargetWork();
                        actualWorkSum += arrivalMonth.getActualWork();
                        workDifferencesSum += arrivalMonth.getWorkDifferences();
                    }
                }
                ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                bo.setArrival(arrival);
                bo.setYear(year);
                bo.setTargetIncomeSum(targetIncomeSum);
                bo.setPlanIncomeSum(planIncomeSum);
                bo.setIncomeDifferencesSum(incomeDifferencesSum);
                bo.setTargetWorkSum(targetWorkSum);
                bo.setActualWorkSum(actualWorkSum);
                bo.setWorkDifferencesSum(workDifferencesSum);
                boList.add(bo);
                targetIncomeSum = 0.00;
                planIncomeSum = 0.00;
                incomeDifferencesSum = 0.00;
                targetWorkSum = 0;
                actualWorkSum = 0;
                workDifferencesSum = 0;
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalWeekBO> findDetail(String id) throws SerException {
        ArrivalMonth arrivalMonth = super.findById(id);
        String[] arrivals = new String[]{arrivalMonth.getArrival()};
        Integer[] years = new Integer[]{arrivalMonth.getYear()};
        Integer[] months = new Integer[]{arrivalMonth.getMonth()};
        List<ArrivalWeekBO> list=null;
        for (int i = 0; i < arrivals.length && i < years.length && i < months.length; i++) {
            String sql = "SELECT week,targetWork,actualWork,workDifferences,price,targetIncome,planIncome,incomeDifferences\n" +
                    "from budget_arrivalweek where arrival='"+arrivals[i]+"' AND year='"+years[i]+"' AND month='"+months[i]+"'";
            String[] fields=new String[]{"week","targetWork","actualWork","workDifferences","price","targetIncome","planIncome","incomeDifferences"};
            list=super.findBySql(sql,ArrivalWeekBO.class,fields);
        }
        return list;
    }
}