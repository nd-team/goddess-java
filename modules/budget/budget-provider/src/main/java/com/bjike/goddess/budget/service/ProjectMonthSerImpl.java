package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ProjectMonthBO;
import com.bjike.goddess.budget.bo.ProjectMonthCountBO;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.entity.ProjectMonth;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目收入月业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ProjectMonthSerImpl extends ServiceImpl<ProjectMonth, ProjectMonthDTO> implements ProjectMonthSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProjectMonthBO save(ProjectMonthTO to) throws SerException {
        ProjectMonth projectMonth = BeanTransform.copyProperties(to, ProjectMonth.class, true);
        super.save(projectMonth);
        return BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectMonthTO to) throws SerException {
        ProjectMonth projectMonth = super.findById(to.getId());
        projectMonth = BeanTransform.copyProperties(to, ProjectMonth.class, true);
        super.update(projectMonth);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void deleteAll() throws SerException {
        List<ProjectMonth> list = super.findAll();
        for (ProjectMonth p : list) {
            super.remove(p.getId());
        }
    }

    @Override
    public List<ProjectMonthBO> list(ProjectMonthDTO dto) throws SerException {
        List<ProjectMonth> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ProjectMonthBO.class);
    }

    @Override
    public ProjectMonthBO findByID(String id) throws SerException {
        ProjectMonth projectMonth = super.findById(id);
        return BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private List<String> findAllArrivals() throws SerException {
        List<ProjectMonth> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMonth p : list) {
            set.add(p.getArrival());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    /**
     * 查找所有项目
     *
     * @return class String
     * @throws SerException
     */
    private List<String> findAllProjects() throws SerException {
        List<ProjectMonth> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMonth p : list) {
            set.add(p.getProject());
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
        List<ProjectMonth> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ProjectMonth p : list) {
            set.add(p.getYear());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    @Override
    public List<ProjectMonthCountBO> count() throws SerException {
        List<String> arrivals = findAllArrivals();
        List<String> projects = findAllProjects();
        List<Integer> years = findAllYears();
        ProjectMonthDTO dto = new ProjectMonthDTO();
        List<ProjectMonth> list = super.findByCis(dto);
        List<ProjectMonthCountBO> boList = new ArrayList<ProjectMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        for (String arrival : arrivals) {
            for (String project : projects) {
                for (Integer year : years) {
                    for (ProjectMonth projectMonth : list) {
                        if (projectMonth.getArrival().equals(arrival) && projectMonth.getProject().equals(project) && projectMonth.getYear() == year) {
                            targetIncomeSum += projectMonth.getTargetIncome();
                            planIncomeSum += projectMonth.getPlanIncome();
                            incomeDifferencesSum += projectMonth.getIncomeDifferences();
                            targetWorkSum += projectMonth.getTargetWork();
                            actualWorkSum += projectMonth.getActualWork();
                            workDifferencesSum += projectMonth.getWorkDifferences();
                        }
                    }
                    ProjectMonthCountBO bo = new ProjectMonthCountBO();
                    bo.setArrival(arrival);
                    bo.setProject(project);
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
        }
        return boList;
    }

    @Override
    public List<ProjectMonthCountBO> conditionsCount(String[] projects) throws SerException {
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<ProjectMonthCountBO> boList = new ArrayList<ProjectMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        for (String project : projects) {
            ProjectMonthDTO dto = new ProjectMonthDTO();
            dto.getConditions().add(Restrict.eq("project", project));
            List<ProjectMonth> list = super.findByCis(dto);
            for (String arrival : arrivals) {
                for (Integer year : years) {
                    for (ProjectMonth projectMonth : list) {
                        if (projectMonth.getArrival().equals(arrival) && projectMonth.getYear() == year) {
                            targetIncomeSum += projectMonth.getTargetIncome();
                            planIncomeSum += projectMonth.getPlanIncome();
                            incomeDifferencesSum += projectMonth.getIncomeDifferences();
                            targetWorkSum += projectMonth.getTargetWork();
                            actualWorkSum += projectMonth.getActualWork();
                            workDifferencesSum += projectMonth.getWorkDifferences();
                        }
                    }
                    ProjectMonthCountBO bo = new ProjectMonthCountBO();
                    bo.setArrival(arrival);
                    bo.setProject(project);
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
        }
        return boList;
    }
}