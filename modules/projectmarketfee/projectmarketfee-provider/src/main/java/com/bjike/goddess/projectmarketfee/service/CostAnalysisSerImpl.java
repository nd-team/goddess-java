package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.api.CostAnalysisCountAPI;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisCountDTO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysis;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysisCount;
import com.bjike.goddess.projectmarketfee.entity.Warn;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisCountTO;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;
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
 * 费用效益分析业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class CostAnalysisSerImpl extends ServiceImpl<CostAnalysis, CostAnalysisDTO> implements CostAnalysisSer {
    @Autowired
    private WarnSer warnSer;
    @Autowired
    private ProjectMarketFeeSer projectMarketFeeSer;
    @Autowired
    private CostAnalysisCountAPI costAnalysisCountAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CostAnalysisBO save(CostAnalysisTO to) throws SerException {
        CostAnalysis costAnalysis = BeanTransform.copyProperties(to, CostAnalysis.class, true);
        super.save(costAnalysis);
        return BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(CostAnalysisTO to) throws SerException {
        CostAnalysis costAnalysis = super.findById(to.getId());
        LocalDateTime a = costAnalysis.getCreateTime();
        LocalDateTime b = costAnalysis.getModifyTime();
        costAnalysis = BeanTransform.copyProperties(to, CostAnalysis.class, true);
        costAnalysis.setCreateTime(a);
        costAnalysis.setModifyTime(b);
        super.update(costAnalysis);
    }

    @Override
    public List<CostAnalysisBO> list(CostAnalysisDTO dto) throws SerException {
        List<CostAnalysis> list = super.findByCis(dto, true);
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysisBO> boList = new ArrayList<CostAnalysisBO>();
        for (CostAnalysis costAnalysis : list) {
            CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
            costAnalysisBO = BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
            Double actualMarketCost = projectMarketFeeSer.count(costAnalysis.getProject(), costAnalysis.getArrival(), costAnalysis.getYear(), costAnalysis.getMonth(), costAnalysis.getProjectName()).getBorrowMoney();
            costAnalysisBO.setActualMarketCost(actualMarketCost);
            costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
            costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
            costAnalysisBO.setActualScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
            if (warns.size() != 0) {
                Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                if (a > warns.get(0).getWarnValue()) {
                    costAnalysisBO.setWarn("超出");
                }
            }
            boList.add(costAnalysisBO);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CostAnalysisBO findByID(String id) throws SerException {
        CostAnalysis costAnalysis = super.findById(id);
        return BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
    }

    @Override
    public List<CostAnalysisCountBO> arrivalCount(Integer year, Integer month) throws SerException {
        Set<String> arrivals = allArrivals();
        List<CostAnalysisBO> list = all();
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysisCountBO> boList = new ArrayList<CostAnalysisCountBO>();
        Double expectedIncomeSum = 0.00;
        Double expectedMarketCostSum = 0.00;
        Double actualMarketCostSum = 0.00;
        Double differences = 0.00;
        for (String arrival : arrivals) {
            for (CostAnalysisBO bo : list) {
                if (bo.getArrival().equals(arrival) && bo.getYear().equals(year) && bo.getMonth().equals(month)) {
                    expectedIncomeSum += bo.getExpectedIncome();
                    expectedMarketCostSum += bo.getExpectedMarketCost();
                    actualMarketCostSum += bo.getActualMarketCost();
                    differences += bo.getDifferences();
                }
            }
            if (expectedIncomeSum != 0) {
                CostAnalysisCountTO bo = new CostAnalysisCountTO();
                bo.setArrival(arrival);
                bo.setYear(year);
                bo.setMonth(month);
                bo.setExpectedIncomeSum(expectedIncomeSum);
                bo.setExpectedMarketCostSum(expectedMarketCostSum);
                bo.setActualMarketCostSum(actualMarketCostSum);
                bo.setDifferences(differences);
                bo.setExpectedScale(expectedMarketCostSum / expectedIncomeSum);
                bo.setActualScale(actualMarketCostSum / expectedIncomeSum);
                Double a = bo.getActualScale() - bo.getExpectedScale();
                if (warns.size() != 0) {
                    if (a > warns.get(0).getWarnValue()) {
                        bo.setWarn("超出");
                    }
                }
                boList.add(costAnalysisCountAPI.save(bo));
                expectedIncomeSum = 0.00;
                expectedMarketCostSum = 0.00;
                actualMarketCostSum = 0.00;
                differences = 0.00;    //置为0
            }
        }
        return boList;
    }

    @Override
    public List<CostAnalysisCountBO> projectGroupCount(Integer year, Integer month) throws SerException {
        Set<String> arrivals = allArrivals();
        Set<String> projectGroups = allProjects();
        List<CostAnalysisBO> list = all();
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysisCountBO> boList = new ArrayList<CostAnalysisCountBO>();
        Double expectedIncomeSum = 0.00;
        Double expectedMarketCostSum = 0.00;
        Double actualMarketCostSum = 0.00;
        Double differences = 0.00;
        for (String arrival : arrivals) {
            for (String projectGroup : projectGroups) {
                for (CostAnalysisBO bo : list) {
                    if (bo.getArrival().equals(arrival) && bo.getProject().equals(projectGroup) && bo.getYear().equals(year) && bo.getMonth().equals(month)) {
                        expectedIncomeSum += bo.getExpectedIncome();
                        expectedMarketCostSum += bo.getExpectedMarketCost();
                        actualMarketCostSum += bo.getActualMarketCost();
                        differences += bo.getDifferences();
                    }
                }
                if (expectedIncomeSum != 0) {
                    CostAnalysisCountTO bo = new CostAnalysisCountTO();
                    bo.setArrival(arrival);
                    bo.setProject(projectGroup);
                    bo.setYear(year);
                    bo.setMonth(month);
                    bo.setExpectedIncomeSum(expectedIncomeSum);
                    bo.setExpectedMarketCostSum(expectedMarketCostSum);
                    bo.setActualMarketCostSum(actualMarketCostSum);
                    bo.setDifferences(differences);
                    bo.setExpectedScale(expectedMarketCostSum / expectedIncomeSum);
                    bo.setActualScale(actualMarketCostSum / expectedIncomeSum);
                    Double a = bo.getActualScale() - bo.getExpectedScale();
                    if (warns.size() != 0) {
                        if (a > warns.get(0).getWarnValue()) {
                            bo.setWarn("超出");
                        }
                    }
                    boList.add(costAnalysisCountAPI.save(bo));
                    expectedIncomeSum = 0.00;
                    expectedMarketCostSum = 0.00;
                    actualMarketCostSum = 0.00;
                    differences = 0.00;    //置为0
                }
            }
        }
        return boList;
    }

    @Override
    public List<CostAnalysisCountBO> projectNameCount(Integer year, Integer month) throws SerException {
        Set<String> arrivals = allArrivals();
        Set<String> projectGroups = allProjects();
        Set<String> projectNames = allProjectNames();
        List<CostAnalysisBO> list = all();
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysisCountBO> boList = new ArrayList<CostAnalysisCountBO>();
        Double expectedIncomeSum = 0.00;
        Double expectedMarketCostSum = 0.00;
        Double actualMarketCostSum = 0.00;
        Double differences = 0.00;
        for (String arrival : arrivals) {
            for (String projectGroup : projectGroups) {
                for (String projectName : projectNames) {
                    for (CostAnalysisBO bo : list) {
                        if (bo.getArrival().equals(arrival) && bo.getProject().equals(projectGroup) && bo.getProjectName().equals(projectName) && bo.getYear().equals(year) && bo.getMonth().equals(month)) {
                            expectedIncomeSum += bo.getExpectedIncome();
                            expectedMarketCostSum += bo.getExpectedMarketCost();
                            actualMarketCostSum += bo.getActualMarketCost();
                            differences += bo.getDifferences();
                        }
                    }
                    if (expectedIncomeSum != 0) {
                        CostAnalysisCountTO bo = new CostAnalysisCountTO();
                        bo.setArrival(arrival);
                        bo.setProject(projectGroup);
                        bo.setProjectName(projectName);
                        bo.setYear(year);
                        bo.setMonth(month);
                        bo.setExpectedIncomeSum(expectedIncomeSum);
                        bo.setExpectedMarketCostSum(expectedMarketCostSum);
                        bo.setActualMarketCostSum(actualMarketCostSum);
                        bo.setDifferences(differences);
                        bo.setExpectedScale(expectedMarketCostSum / expectedIncomeSum);
                        bo.setActualScale(actualMarketCostSum / expectedIncomeSum);
                        Double a = bo.getActualScale() - bo.getExpectedScale();
                        if (warns.size() != 0) {
                            if (a > warns.get(0).getWarnValue()) {
                                bo.setWarn("超出");
                            }
                        }
                        CostAnalysisCountBO aa=costAnalysisCountAPI.save(bo);
                        boList.add(aa);
                        expectedIncomeSum = 0.00;
                        expectedMarketCostSum = 0.00;
                        actualMarketCostSum = 0.00;
                        differences = 0.00;    //置为0
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<CostAnalysisBO> findDetail(String id) throws SerException {
        CostAnalysisCountBO to=costAnalysisCountAPI.findByID(id);
        String[] arrivals = new String[]{to.getArrival()};
        String[] projectGroups = new String[]{to.getProject()};
        String[] projectNames = new String[]{to.getProjectName()};
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysis> list = null;
        List<CostAnalysisBO> boList = new ArrayList<CostAnalysisBO>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT project,arrival,year,month,projectName,expectedIncome,expectedMarketCost,grade\n" +
                "from projectmarketfee_costanalysis");
        String[] fields = new String[]{"project", "arrival", "year", "month", "projectName", "expectedIncome", "expectedMarketCost", "grade"};
        if ((to.getArrival() != null) && (to.getProject() != null) && (to.getProjectName() != null)) {
            for (int i = 0; i < arrivals.length; i++) {
                sb.append(" WHERE project='" + projectGroups[i] + "' AND arrival='" + arrivals[i] + "' AND projectName='" + projectNames[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, CostAnalysis.class, fields);
            }
            for (CostAnalysis costAnalysis : list) {
                CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
                costAnalysisBO = BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
                Double actualMarketCost = projectMarketFeeSer.count(costAnalysis.getProject(), costAnalysis.getArrival(), costAnalysis.getYear(), costAnalysis.getMonth(), costAnalysis.getProjectName()).getBorrowMoney();
                costAnalysisBO.setActualMarketCost(actualMarketCost);
                costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
                costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
                costAnalysisBO.setActualScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
                if (warns.size() != 0) {
                    Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                    if (a > warns.get(0).getWarnValue()) {
                        costAnalysisBO.setWarn("超出");
                    }
                }
                boList.add(costAnalysisBO);
            }
            return boList;
        } else if ((to.getArrival() != null) && (to.getProject() != null)) {
            for (int i = 0; i < arrivals.length; i++) {
                sb.append(" WHERE project='" + projectGroups[i] + "' AND arrival='" + arrivals[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, CostAnalysis.class, fields);
            }
            for (CostAnalysis costAnalysis : list) {
                CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
                costAnalysisBO = BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
                Double actualMarketCost = projectMarketFeeSer.count(costAnalysis.getProject(), costAnalysis.getArrival(), costAnalysis.getYear(), costAnalysis.getMonth(), costAnalysis.getProjectName()).getBorrowMoney();
                costAnalysisBO.setActualMarketCost(actualMarketCost);
                costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
                costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
                costAnalysisBO.setActualScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
                if (warns.size() != 0) {
                    Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                    if (a > warns.get(0).getWarnValue()) {
                        costAnalysisBO.setWarn("超出");
                    }
                }
                boList.add(costAnalysisBO);
            }
            return boList;
        } else if ((to.getArrival() != null)) {
            for (int i = 0; i < arrivals.length; i++) {
                sb.append(" WHERE arrival='" + arrivals[i] + "'");
                String sql = sb.toString();
                list = super.findBySql(sql, CostAnalysis.class, fields);
            }
            for (CostAnalysis costAnalysis : list) {
                CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
                costAnalysisBO = BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
                Double actualMarketCost = projectMarketFeeSer.count(costAnalysis.getProject(), costAnalysis.getArrival(), costAnalysis.getYear(), costAnalysis.getMonth(), costAnalysis.getProjectName()).getBorrowMoney();
                costAnalysisBO.setActualMarketCost(actualMarketCost);
                costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
                costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
                costAnalysisBO.setActualScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
                if (warns.size() != 0) {
                    Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                    if (a > warns.get(0).getWarnValue()) {
                        costAnalysisBO.setWarn("超出");
                    }
                }
                boList.add(costAnalysisBO);
            }
            return boList;
        }
        return null;
    }

    /**
     * 获取所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allArrivals() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getArrival());
        }
        return set;
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjects() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getProject());
        }
        return set;
    }

    /**
     * 获取所有项目名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjectNames() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getProjectName());
        }
        return set;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    @Override
    public Set<Integer> allYears() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getYear());
        }
        return set;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    @Override
    public Set<Integer> allMonths() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getMonth());
        }
        return set;
    }

    private List<CostAnalysisBO> all() throws SerException {
        List<CostAnalysis> list = super.findAll();
        List<Warn> warns = warnSer.findAll();
        List<CostAnalysisBO> boList = new ArrayList<CostAnalysisBO>();
        for (CostAnalysis costAnalysis : list) {
            CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
//            costAnalysisBO.setProject(costAnalysis.getProject());
//            costAnalysisBO.setArrival(costAnalysis.getArrival());
//            costAnalysisBO.setYear(costAnalysis.getYear());
//            costAnalysisBO.setMonth(costAnalysis.getMonth());
//            costAnalysisBO.setProjectName(costAnalysis.getProjectName());
//            costAnalysisBO.setExpectedIncome(costAnalysis.getExpectedIncome());
//            costAnalysisBO.setExpectedMarketCost(costAnalysis.getExpectedMarketCost());
//            costAnalysisBO.setGrade(costAnalysis.getGrade());
            costAnalysisBO = BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
            Double actualMarketCost = projectMarketFeeSer.count(costAnalysis.getProject(), costAnalysis.getArrival(), costAnalysis.getYear(), costAnalysis.getMonth(), costAnalysis.getProjectName()).getBorrowMoney();
            costAnalysisBO.setActualMarketCost(actualMarketCost);
            costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
            costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
            costAnalysisBO.setActualScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
            if (warns.size() != 0) {
                Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                if (a > warns.get(0).getWarnValue()) {
                    costAnalysisBO.setWarn("超出");
                }
            }
            boList.add(costAnalysisBO);
        }
        return boList;
    }

    @Override
    public CostAnalysisBO countNum(CostAnalysisDTO dto) throws SerException {
        CostAnalysisBO bo = new CostAnalysisBO();
        bo.setNum(super.count(dto));
        return bo;
    }
}