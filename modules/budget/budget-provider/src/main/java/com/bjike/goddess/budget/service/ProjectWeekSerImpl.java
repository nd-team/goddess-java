package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.bo.ProjectWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.entity.ProjectWeek;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目收入周业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ProjectWeekSerImpl extends ServiceImpl<ProjectWeek, ProjectWeekDTO> implements ProjectWeekSer {
    @Autowired
    private ProjectMonthSer projectMonthSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProjectWeekBO save(ProjectWeekTO to) throws SerException {
        ProjectWeek projectWeek = BeanTransform.copyProperties(to, ProjectWeek.class, true);
        super.save(projectWeek);
        projectMonthSer.deleteAll();
        List<ProjectWeekCountBO> list = count();
        for (ProjectWeekCountBO bo : list) {
            ProjectMonthTO projectMonthTO = new ProjectMonthTO();
            projectMonthTO.setArrival(bo.getArrival());
            projectMonthTO.setProject(bo.getProject());
            projectMonthTO.setYear(bo.getYear());
            projectMonthTO.setMonth(bo.getMonth());
            projectMonthTO.setPrice(bo.getPrice());
            projectMonthTO.setTargetWork(bo.getTargetWorkSum());
            projectMonthTO.setActualWork(bo.getActualWorkSum());
            projectMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            projectMonthTO.setPlanIncome(bo.getPlanIncomeSum());
            projectMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }
        return BeanTransform.copyProperties(projectWeek, ProjectWeekBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectWeekTO to) throws SerException {
        ProjectWeek projectWeek = super.findById(to.getId());
        LocalDateTime a = projectWeek.getCreateTime();
        LocalDateTime b = projectWeek.getModifyTime();
        projectWeek = BeanTransform.copyProperties(to, ProjectWeek.class, true);
        projectWeek.setCreateTime(a);
        projectWeek.setModifyTime(b);
        super.update(projectWeek);
        projectMonthSer.deleteAll();
        List<ProjectWeekCountBO> list = count();
        for (ProjectWeekCountBO bo : list) {
            ProjectMonthTO projectMonthTO = new ProjectMonthTO();
            projectMonthTO.setArrival(bo.getArrival());
            projectMonthTO.setProject(bo.getProject());
            projectMonthTO.setYear(bo.getYear());
            projectMonthTO.setMonth(bo.getMonth());
            projectMonthTO.setPrice(bo.getPrice());
            projectMonthTO.setTargetWork(bo.getTargetWorkSum());
            projectMonthTO.setActualWork(bo.getActualWorkSum());
            projectMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            projectMonthTO.setPlanIncome(bo.getPlanIncomeSum());
            projectMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
        projectMonthSer.deleteAll();
        List<ProjectWeekCountBO> list = count();
        for (ProjectWeekCountBO bo : list) {
            ProjectMonthTO projectMonthTO = new ProjectMonthTO();
            projectMonthTO.setArrival(bo.getArrival());
            projectMonthTO.setProject(bo.getProject());
            projectMonthTO.setYear(bo.getYear());
            projectMonthTO.setMonth(bo.getMonth());
            projectMonthTO.setPrice(bo.getPrice());
            projectMonthTO.setTargetWork(bo.getTargetWorkSum());
            projectMonthTO.setActualWork(bo.getActualWorkSum());
            projectMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            projectMonthTO.setPlanIncome(bo.getPlanIncomeSum());
            projectMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }
    }

    @Override
    public List<ProjectWeekBO> list(ProjectWeekDTO dto) throws SerException {
        List<ProjectWeek> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ProjectWeekBO.class);
    }

    @Override
    public ProjectWeekBO findByID(String id) throws SerException {
        ProjectWeek projectWeek = super.findById(id);
        return BeanTransform.copyProperties(projectWeek, ProjectWeekBO.class);
    }

    @Override
    public List<ProjectWeekCountBO> count() throws SerException {
        List<ProjectWeekCountBO> boList = new ArrayList<ProjectWeekCountBO>();
        List<String> arrivals = findAllArrivals();
        List<String> projects = findAllProjects();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        ProjectWeekDTO dto = new ProjectWeekDTO();
        List<ProjectWeek> list = super.findByCis(dto);
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        for (String arrival : arrivals) {
            for (String project : projects) {
                for (Integer year : years) {
                    for (Integer month : months) {
                        for (Double price : prices) {
                            for (ProjectWeek projectWeek : list) {
                                boolean b = projectWeek.getPrice().compareTo(price) == 0 ? true : false;
                                if (projectWeek.getArrival().equals(arrival) && projectWeek.getProject().equals(project) && projectWeek.getYear().equals(year) && projectWeek.getMonth().equals(month) && b) {
                                    targetWorkSum += projectWeek.getTargetWork();
                                    actualWorkSum += projectWeek.getActualWork();
                                    workDifferencesSum += projectWeek.getWorkDifferences();
                                    targetIncomeSum += projectWeek.getTargetIncome();
                                    planIncomeSum += projectWeek.getPlanIncome();
                                    incomeDifferencesSum += projectWeek.getIncomeDifferences();
                                }
                            }
                            if (targetWorkSum != 0) {
                                ProjectWeekCountBO projectWeekCountBO = new ProjectWeekCountBO();
                                projectWeekCountBO.setArrival(arrival);
                                projectWeekCountBO.setProject(project);
                                projectWeekCountBO.setYear(year);
                                projectWeekCountBO.setMonth(month);
                                projectWeekCountBO.setPrice(price);
                                projectWeekCountBO.setTargetWorkSum(targetWorkSum);
                                projectWeekCountBO.setActualWorkSum(actualWorkSum);
                                projectWeekCountBO.setWorkDifferencesSum(workDifferencesSum);
                                projectWeekCountBO.setTargetIncomeSum(targetIncomeSum);
                                projectWeekCountBO.setPlanIncomeSum(planIncomeSum);
                                projectWeekCountBO.setIncomeDifferencesSum(incomeDifferencesSum);
                                boList.add(projectWeekCountBO);
                                targetWorkSum = 0;
                                actualWorkSum = 0;
                                workDifferencesSum = 0;
                                targetIncomeSum = 0.00;
                                planIncomeSum = 0.00;
                                incomeDifferencesSum = 0.00;   //置为0
                            }
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ProjectWeekCountBO> conditionsCount(String[] projects) throws SerException {
        List<ProjectWeekCountBO> boList = new ArrayList<ProjectWeekCountBO>();
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        List<ProjectWeek> list = null;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        for (String project : projects) {
            ProjectWeekDTO dto = new ProjectWeekDTO();
            dto.getConditions().add(Restrict.eq("project", project));
            list = super.findByCis(dto);
            for (String arrival : arrivals) {
                for (Integer year : years) {
                    for (Integer month : months) {
                        for (Double price : prices) {
                            for (ProjectWeek a : list) {
                                boolean b = a.getPrice().compareTo(price) == 0 ? true : false;
                                if (a.getArrival().equals(arrival) && a.getYear().equals(year) && a.getMonth().equals(month) && b) {
                                    targetWorkSum += a.getTargetWork();
                                    actualWorkSum += a.getActualWork();
                                    workDifferencesSum += a.getWorkDifferences();
                                    targetIncomeSum += a.getTargetIncome();
                                    planIncomeSum += a.getPlanIncome();
                                    incomeDifferencesSum += a.getIncomeDifferences();
                                }
                            }
                            if (targetWorkSum != 0) {
                                ProjectWeekCountBO bo = new ProjectWeekCountBO();
                                bo.setArrival(arrival);
                                bo.setProject(project);
                                bo.setYear(year);
                                bo.setMonth(month);
                                bo.setPrice(price);
                                bo.setTargetWorkSum(targetWorkSum);
                                bo.setActualWorkSum(actualWorkSum);
                                bo.setWorkDifferencesSum(workDifferencesSum);
                                bo.setTargetIncomeSum(targetIncomeSum);
                                bo.setPlanIncomeSum(planIncomeSum);
                                bo.setIncomeDifferencesSum(incomeDifferencesSum);
                                boList.add(bo);
                                targetWorkSum = 0;
                                actualWorkSum = 0;
                                workDifferencesSum = 0;
                                targetIncomeSum = 0.00;
                                planIncomeSum = 0.00;
                                incomeDifferencesSum = 0.00;     //置为0
                            }
                        }
                    }
                }
            }
        }
        return boList;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private List<String> findAllArrivals() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getArrival());
        }
        List<String> list = new ArrayList<String>(set);
        return list;
    }

    /**
     * 查找所有项目
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public List<String> findAllProjects() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getProject());
        }
        List<String> list = new ArrayList<String>(set);
        return list;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllYears() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getYear());
        }
        List<Integer> list = new ArrayList<Integer>(set);
        return list;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllMonths() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getMonth());
        }
        List<Integer> list = new ArrayList<Integer>(set);
        return list;
    }

    /**
     * 查找所有单价
     *
     * @return class Double
     * @throws SerException
     */
    private List<Double> findAllPrices() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getPrice());
        }
        List<Double> list = new ArrayList<Double>(set);
        return list;
    }

    @Override
    public Long countNum(ProjectWeekDTO dto) throws SerException{
        return super.count(dto);
    }
}