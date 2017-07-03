package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.ProjectMonthBO;
import com.bjike.goddess.budget.bo.ProjectMonthCountBO;
import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.entity.ProjectMonth;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectMonthTO;
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
    @Transactional(rollbackFor = {SerException.class})
    public ProjectMonthBO save(ProjectMonthTO to) throws SerException {
        checkAddIdentity();
        ProjectMonth projectMonth = BeanTransform.copyProperties(to, ProjectMonth.class, true);
        super.save(projectMonth);
        return BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectMonthTO to) throws SerException {
        checkAddIdentity();
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
        checkSeeIdentity();
        List<ProjectMonth> list = super.findByCis(dto, true);
        List<ProjectMonthBO> boList = new ArrayList<ProjectMonthBO>();
        for (ProjectMonth a : list) {
            ProjectMonthBO bo = BeanTransform.copyProperties(a, ProjectMonthBO.class);
            bo.setWorkDifferences(a.getActualWork() - a.getTargetWork());
            bo.setIncomeDifferences(a.getPlanIncome() - a.getTargetIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ProjectMonthBO findByID(String id) throws SerException {
        ProjectMonth projectMonth = super.findById(id);
        ProjectMonthBO bo = BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
        bo.setWorkDifferences(projectMonth.getActualWork() - projectMonth.getTargetWork());
        bo.setIncomeDifferences(projectMonth.getPlanIncome() - projectMonth.getTargetIncome());
        return bo;
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
    @Override
    public List<String> findAllProjects() throws SerException {
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
        checkSeeIdentity();
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
                        if (projectMonth.getArrival().equals(arrival) && projectMonth.getProject().equals(project) && projectMonth.getYear().equals(year)) {
                            targetIncomeSum += projectMonth.getTargetIncome();
                            planIncomeSum += projectMonth.getPlanIncome();
                            double incomeDifference = projectMonth.getPlanIncome() - projectMonth.getTargetIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += projectMonth.getTargetWork();
                            actualWorkSum += projectMonth.getActualWork();
                            int workDifference = projectMonth.getActualWork() - projectMonth.getTargetWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
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
        }
        return boList;
    }

    @Override
    public List<ProjectMonthCountBO> conditionsCount(String[] projects) throws SerException {
        checkSeeIdentity();
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
                        if (projectMonth.getArrival().equals(arrival) && projectMonth.getYear().equals(year)) {
                            targetIncomeSum += projectMonth.getTargetIncome();
                            planIncomeSum += projectMonth.getPlanIncome();
                            double incomeDifference = projectMonth.getPlanIncome() - projectMonth.getTargetIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += projectMonth.getTargetWork();
                            actualWorkSum += projectMonth.getActualWork();
                            int workDifference = projectMonth.getActualWork() - projectMonth.getTargetWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
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
        }
        return boList;
    }

    @Override
    public List<ProjectWeekBO> findDetail(String id) throws SerException {
        checkSeeIdentity();
        ProjectMonth projectMonth = super.findById(id);
        if (projectMonth == null) {
            throw new SerException("该对象不存在");
        }
        String[] arrivals = new String[]{projectMonth.getArrival()};
        String[] projects = new String[]{projectMonth.getProject()};
        Integer[] years = new Integer[]{projectMonth.getYear()};
        Integer[] months = new Integer[]{projectMonth.getMonth()};
        List<ProjectWeekBO> list = null;
        for (int i = 0; i < arrivals.length && i < projects.length && i < years.length && i < months.length; i++) {
            String sql = "SELECT week,targetWork,actualWork,price,targetIncome,planIncome\n" +
                    "from budget_projectweek\n" +
                    "where arrival='" + arrivals[i] + "' AND project='" + projects[i] + "' AND year='" + years[i] + "' AND month='" + months[i] + "'";
            String[] fields = new String[]{"week", "targetWork", "actualWork", "price", "targetIncome", "planIncome"};
            list = super.findBySql(sql, ProjectWeekBO.class, fields);
        }
        for (ProjectWeekBO bo : list) {
            bo.setArrival(projectMonth.getArrival());
            bo.setProject(projectMonth.getProject());
            bo.setYear(projectMonth.getYear());
            bo.setMonth(projectMonth.getMonth());
            bo.setIncomeDifferences(bo.getPlanIncome() - bo.getTargetIncome());
            bo.setWorkDifferences(bo.getActualWork() - bo.getTargetWork());
        }
        return list;
    }

    @Override
    public Long countNum(ProjectMonthDTO dto) throws SerException {
        return super.count(dto);
    }
}