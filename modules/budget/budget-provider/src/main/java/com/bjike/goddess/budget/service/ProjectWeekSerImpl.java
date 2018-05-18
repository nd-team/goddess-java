package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.entity.ProjectWeek;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.excel.ProjectWeekImportTemple;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ArrivalWeekSer arrivalWeekSer;
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
    public ProjectWeekBO save(ProjectWeekTO to) throws SerException {
        checkAddIdentity();
        ProjectWeek projectWeek = BeanTransform.copyProperties(to, ProjectWeek.class, true);
//        projectWeek.setTargetIncome(to.getPrice()*to.getTargetWork());
//        projectWeek.setPlanIncome(to.getPrice()*to.getActualWork());
        projectWeek.setCreateTime(LocalDateTime.now());
        projectWeek.setModifyTime(LocalDateTime.now());
        super.save(projectWeek);

        //项目收入月
        projectMonthSer.deleteAll();
        List<ProjectWeekCountBO> list = count();
        for (ProjectWeekCountBO bo : list) {
            ProjectMonthTO projectMonthTO = new ProjectMonthTO();
            projectMonthTO.setArrival(bo.getArrival());
            projectMonthTO.setProject(bo.getProject());
            projectMonthTO.setYear(bo.getYear());
            projectMonthTO.setMonth(bo.getMonth());
//            projectMonthTO.setPrice(bo.getPrice());
            projectMonthTO.setTargetWork(bo.getTargetWorkSum());
            projectMonthTO.setActualWork(bo.getActualWorkSum());
            projectMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            projectMonthTO.setPlanIncome(bo.getPlanIncomeSum());
            projectMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            projectMonthTO.setProjectName(bo.getProjectName());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }

        //地区收入周
        arrivalWeekSer.deleteAll();
        List<ArrivalWeekTO> arrivalWeekTOs = countArea();

        ProjectWeekBO bo = BeanTransform.copyProperties(projectWeek, ProjectWeekBO.class);
        return bo;
    }

    //zhuangkaiqin
    //获取地区收入周
    private List<ArrivalWeekTO> countArea() throws SerException {
        List<ArrivalWeekTO> boList = new ArrayList<>(0);
        List<String> areas = findArea();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Integer> weeks = findAllWeeks();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Double priceSum = 0d;
        Double targetIncomeSum = 0d;
        Double planIncomeSum = 0d;
        Integer workDifferencesSum = 0;
        Double price = 0d;
        int i = 0;

        List<ProjectWeek> projectWeeks = super.findAll();
        if (null != projectWeeks && projectWeeks.size() > 0) {
            for (String area : areas) {
                for (Integer year : years) {
                    for (Integer month : months) {
                        for (Integer week : weeks) {
                            for (ProjectWeek entity : projectWeeks) {
                                if (entity.getArrival().equals(area) && entity.getYear().equals(year) && entity.getMonth().equals(month) && entity.getWeek().equals(week)) {
                                    targetWorkSum += entity.getTargetWork();
                                    actualWorkSum += entity.getActualWork();
                                    workDifferencesSum += (targetWorkSum - actualWorkSum);
                                    priceSum += entity.getPrice();
                                    if (null == entity.getTargetIncome()) {
                                        entity.setTargetIncome(0d);
                                    }
                                    targetIncomeSum += entity.getTargetIncome();
                                    planIncomeSum += entity.getPlanIncome();
                                    i++;
                                    price += entity.getPrice();
                                }
                            }
                            if (targetWorkSum != 0) {
                                ArrivalWeekTO arrivalWeekTO = new ArrivalWeekTO();
                                arrivalWeekTO.setArrival(area);
                                arrivalWeekTO.setYear(year);
                                arrivalWeekTO.setMonth(month);
                                arrivalWeekTO.setWeek(week);
                                arrivalWeekTO.setTargetWork(targetWorkSum);
                                arrivalWeekTO.setActualWork(actualWorkSum);
                                arrivalWeekTO.setPrice(price / i);
                                arrivalWeekTO.setTargetIncome(targetIncomeSum);
                                arrivalWeekTO.setPlanIncome(planIncomeSum);
                                boList.add(arrivalWeekTO);
                                arrivalWeekSer.save(arrivalWeekTO);
                                targetWorkSum = 0;
                                actualWorkSum = 0;
                                workDifferencesSum = 0;
                                targetIncomeSum = 0.00;
                                planIncomeSum = 0.00;
                                workDifferencesSum = 0;
                                i = 0;
                                price = 0d;
                            }
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectWeekTO to) throws SerException {
        checkAddIdentity();
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
            projectMonthTO.setProjectName(bo.getProjectName());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }
        //地区收入周
        arrivalWeekSer.deleteAll();
        List<ArrivalWeekTO> arrivalWeekTOs = countArea();
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        projectMonthSer.deleteAll();
        List<ProjectWeekCountBO> list = count();
        for (ProjectWeekCountBO bo : list) {
            ProjectMonthTO projectMonthTO = new ProjectMonthTO();
            projectMonthTO.setArrival(bo.getArrival());
            projectMonthTO.setProject(bo.getProject());
            projectMonthTO.setYear(bo.getYear());
            projectMonthTO.setMonth(bo.getMonth());
//            projectMonthTO.setPrice(bo.getPrice());
            projectMonthTO.setProjectName(bo.getProjectName());
            projectMonthTO.setTargetWork(bo.getTargetWorkSum());
            projectMonthTO.setActualWork(bo.getActualWorkSum());
            projectMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            projectMonthTO.setPlanIncome(bo.getPlanIncomeSum());
            projectMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            projectMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            projectMonthSer.save(projectMonthTO);
        }
        //地区收入周
        arrivalWeekSer.deleteAll();
        List<ArrivalWeekTO> arrivalWeekTOs = countArea();
    }

    @Override
    public List<ProjectWeekBO> list(ProjectWeekDTO dto) throws SerException {
        checkSeeIdentity();
        List<ProjectWeek> list = super.findByCis(dto, true);
        List<ProjectWeekBO> boList = new ArrayList<ProjectWeekBO>();
        for (ProjectWeek a : list) {
            ProjectWeekBO bo = BeanTransform.copyProperties(a, ProjectWeekBO.class);
            bo.setWorkDifferences(a.getActualWork() - a.getTargetWork());
            bo.setIncomeDifferences(a.getTargetIncome() - a.getPlanIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public List<ProjectWeekListBO> listProject(ProjectWeekDTO dto) throws SerException {
        List<String> areas = findArea();
        List<ProjectWeekListBO> projectWeekListBOS = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {

                List<String> projects = findProjectByArea(area);
                List<ProjectBO> projectBOList = new ArrayList<>();
                if (projects != null && projects.size() > 0) {
                    for (String project : projects) {
                        List<String> projectNames = findNameByArPro(area, project);
                        List<ProjectNameListBO> projectNameListBOList = new ArrayList<>();

                        if (projectNames != null && projectNames.size() > 0) {
                            for (String projectName : projectNames) {

                                List<Integer> years = findYearByArProName(area, project, projectName);
                                List<YearListBO> yearListBOList = new ArrayList<>();
                                if (years != null && years.size() > 0) {
                                    for (Integer year : years) {
                                        List<Integer> months = findMonthByArProNaYe(area, project, projectName, year);
                                        List<MonthListBO> monthListBOList = new ArrayList<>();
                                        if (months != null && months.size() > 0) {
                                            for (Integer month : months) {
                                                List<WeekListBO> weekListBOS = findWeekByArProNaYeMo(area, project, projectName, year, month);
                                                for (WeekListBO weekListBO : weekListBOS) {
                                                    weekListBO.setIncomeDifferences(weekListBO.getTargetIncome() - weekListBO.getPlanIncome());
                                                    weekListBO.setWorkDifferences(weekListBO.getActualWork() - weekListBO.getTargetWork());
                                                }
                                                MonthListBO monthListBO = new MonthListBO();
                                                monthListBO.setMonth(month);
                                                monthListBO.setWeekListBOList(weekListBOS);
                                                monthListBOList.add(monthListBO);
                                            }
                                        }
                                        YearListBO yearListBO = new YearListBO();
                                        yearListBO.setYear(year);
                                        yearListBO.setMonthListBOList(monthListBOList);
                                        yearListBOList.add(yearListBO);
                                    }
                                }
                                ProjectNameListBO projectNameListBO = new ProjectNameListBO();
                                projectNameListBO.setProjectName(projectName);
                                projectNameListBO.setYearListBOList(yearListBOList);
                                projectNameListBOList.add(projectNameListBO);
                            }

                        }
                        ProjectBO projectBO = new ProjectBO();
                        projectBO.setProject(project);
                        projectBO.setProjectNameListBOList(projectNameListBOList);
                        projectBOList.add(projectBO);
                    }
                }
                ProjectWeekListBO projectWeekListBO = new ProjectWeekListBO();
                projectWeekListBO.setArrival(area);
                projectWeekListBO.setProjectBOList(projectBOList);
                projectWeekListBOS.add(projectWeekListBO);
            }
        }
        int limit = dto.getLimit();
        int start = limit * dto.getPage();
        return projectWeekListBOS.stream().skip(start).limit(limit).collect(Collectors.toList());
    }

    /**
     * 获取地区
     *
     * @return
     * @throws SerException
     */
    public List<String> findArea() throws SerException {
        List<ProjectWeek> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getArrival).distinct().collect(Collectors.toList());
    }

    /**
     * 获取周数
     */
    public List<Integer> findAllWeeks() throws SerException {
        List<ProjectWeek> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getWeek).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区获取所属项目组
     *
     * @return
     * @throws SerException
     */
    public List<String> findProjectByArea(String area) throws SerException {
        ProjectWeekDTO projectWeekDTO = new ProjectWeekDTO();
        projectWeekDTO.getConditions().add(Restrict.eq("arrival", area));
        List<ProjectWeek> list = super.findByCis(projectWeekDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getProject).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组获取内部项目名称
     *
     * @return
     * @throws SerException
     */
    public List<String> findNameByArPro(String area, String project) throws SerException {
        ProjectWeekDTO projectWeekDTO = new ProjectWeekDTO();
        projectWeekDTO.getConditions().add(Restrict.eq("arrival", area));
        projectWeekDTO.getConditions().add(Restrict.eq("project", project));
        List<ProjectWeek> list = super.findByCis(projectWeekDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getProjectName).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组,内部项目名称获取年度
     *
     * @return
     * @throws SerException
     */
    public List<Integer> findYearByArProName(String area, String project, String projectName) throws SerException {
        ProjectWeekDTO projectWeekDTO = new ProjectWeekDTO();
        projectWeekDTO.getConditions().add(Restrict.eq("arrival", area));
        projectWeekDTO.getConditions().add(Restrict.eq("project", project));
        projectWeekDTO.getConditions().add(Restrict.eq("projectName", projectName));
        List<ProjectWeek> list = super.findByCis(projectWeekDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getYear).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组,内部项目名称,年度获取月份
     *
     * @return
     * @throws SerException
     */
    public List<Integer> findMonthByArProNaYe(String area, String project, String projectName, Integer year) throws SerException {
        ProjectWeekDTO projectWeekDTO = new ProjectWeekDTO();
        projectWeekDTO.getConditions().add(Restrict.eq("arrival", area));
        projectWeekDTO.getConditions().add(Restrict.eq("project", project));
        projectWeekDTO.getConditions().add(Restrict.eq("projectName", projectName));
        projectWeekDTO.getConditions().add(Restrict.eq("year", year));
        List<ProjectWeek> list = super.findByCis(projectWeekDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectWeek::getMonth).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组,内部项目名称,年度,月份获取周期
     *
     * @return
     * @throws SerException
     */
    public List<WeekListBO> findWeekByArProNaYeMo(String area, String project, String projectName, Integer year, Integer month) throws SerException {
        ProjectWeekDTO projectWeekDTO = new ProjectWeekDTO();
        projectWeekDTO.getConditions().add(Restrict.eq("arrival", area));
        projectWeekDTO.getConditions().add(Restrict.eq("project", project));
        projectWeekDTO.getConditions().add(Restrict.eq("projectName", projectName));
        projectWeekDTO.getConditions().add(Restrict.eq("year", year));
        projectWeekDTO.getConditions().add(Restrict.eq("month", month));
        List<ProjectWeek> list = super.findByCis(projectWeekDTO);
        return BeanTransform.copyProperties(list, WeekListBO.class);
    }

    @Override
    public ProjectWeekBO findByID(String id) throws SerException {
        ProjectWeek projectWeek = super.findById(id);
        ProjectWeekBO bo = BeanTransform.copyProperties(projectWeek, ProjectWeekBO.class);
        bo.setWorkDifferences(projectWeek.getActualWork() - projectWeek.getTargetWork());
        bo.setIncomeDifferences(projectWeek.getTargetIncome() - projectWeek.getPlanIncome());
        return bo;
    }

    @Override
    public List<ProjectWeekCountBO> count() throws SerException {
        checkSeeIdentity();
        List<ProjectWeekCountBO> boList = new ArrayList<ProjectWeekCountBO>();
        List<String> arrivals = findAllArrivals();
        List<String> projects = findAllProjects();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<String> prices = findAllPrices();
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
                        for (String price : prices) {
                            for (ProjectWeek projectWeek : list) {
//                                boolean b = projectWeek.getPrice().compareTo(price) == 0 ? true : false;
                                if (projectWeek.getArrival().equals(arrival) && projectWeek.getProject().equals(project) && projectWeek.getYear().equals(year) && projectWeek.getMonth().equals(month) && price.equals(projectWeek.getProjectName())) {
                                    targetIncomeSum += projectWeek.getTargetIncome();
                                    if (null == projectWeek.getPlanIncome()) {
                                        projectWeek.setPlanIncome(0d);
                                    }
                                    planIncomeSum += projectWeek.getPlanIncome();
                                    double incomeDifference = projectWeek.getPlanIncome() - projectWeek.getTargetIncome();
                                    incomeDifferencesSum += incomeDifference;
                                    targetWorkSum += projectWeek.getTargetWork();
                                    if (null == projectWeek.getActualWork()) {
                                        projectWeek.setActualWork(0);
                                    }
                                    actualWorkSum += projectWeek.getActualWork();
                                    int workDifference = projectWeek.getActualWork() - projectWeek.getTargetWork();
                                    workDifferencesSum += workDifference;
                                }
                            }
                            if (targetWorkSum != 0) {
                                ProjectWeekCountBO projectWeekCountBO = new ProjectWeekCountBO();
                                projectWeekCountBO.setArrival(arrival);
                                projectWeekCountBO.setProject(project);
                                projectWeekCountBO.setYear(year);
                                projectWeekCountBO.setMonth(month);
                                projectWeekCountBO.setProjectName(price);
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
    public List<ProjectWeekCountBO> conditionsCount(ProjectWeekDTO dto1) throws SerException {
        checkSeeIdentity();
        List<ProjectWeekCountBO> boList = new ArrayList<ProjectWeekCountBO>();
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<String> prices = findAllPrices();
        List<ProjectWeek> list = null;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        String[] projects = dto1.getProjects();
        if (projects != null) {
            for (String project : projects) {
                ProjectWeekDTO dto = new ProjectWeekDTO();
                dto.getConditions().add(Restrict.eq("project", project));
                list = super.findByCis(dto);
                for (String arrival : arrivals) {
                    for (Integer year : years) {
                        for (Integer month : months) {
                            for (String price : prices) {
                                for (ProjectWeek a : list) {
//                                boolean b = a.getPrice().compareTo(price) == 0 ? true : false;
                                    if (a.getArrival().equals(arrival) && a.getYear().equals(year) && a.getMonth().equals(month) && price.equals(a.getProjectName())) {
                                        targetIncomeSum += a.getTargetIncome();
                                        planIncomeSum += a.getPlanIncome();
                                        double incomeDifference = a.getPlanIncome() - a.getTargetIncome();
                                        incomeDifferencesSum += incomeDifference;
                                        targetWorkSum += a.getTargetWork();
                                        actualWorkSum += a.getActualWork();
                                        int workDifference = a.getActualWork() - a.getTargetWork();
                                        workDifferencesSum += workDifference;
                                    }
                                }
                                if (targetWorkSum != 0) {
                                    ProjectWeekCountBO bo = new ProjectWeekCountBO();
                                    bo.setArrival(arrival);
                                    bo.setProject(project);
                                    bo.setYear(year);
                                    bo.setMonth(month);
                                    bo.setProjectName(price);
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
    private List<String> findAllPrices() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectWeek projectWeek : projectWeeks) {
            set.add(projectWeek.getProjectName());
        }
        List<String> list = new ArrayList<String>(set);
        return list;
    }

    @Override
    public Long countNum(ProjectWeekDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ProjectWeekImportTemple> projectWeekImportTemples = new ArrayList<>();
        ProjectWeekImportTemple excel = new ProjectWeekImportTemple();
        excel.setArrival("广州");
        excel.setProject("test");
        excel.setYear(2017);
        excel.setMonth(5);
        excel.setWeek(3);
        excel.setPrice(3d);
        excel.setTargetIncome(20000d);
        excel.setPlanIncome(2695d);
        excel.setProjectName("test");
        excel.setTargetWork(25);
        excel.setActualWork(54);
        projectWeekImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(projectWeekImportTemples, exce);
        return bytes;
    }

    @Override
    public List<ProjectWeekCountBO> collect(ProjectWeekDTO dto) throws SerException {
        checkSeeIdentity();
        List<ProjectWeekCountBO> boList = new ArrayList<ProjectWeekCountBO>();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;

        ProjectWeekDTO dto1 = new ProjectWeekDTO();
        if (null != dto.getProjects()) {
            for (String project : dto.getProjects()) {
                dto1 = new ProjectWeekDTO();
                dto1 = condition(dto);
                dto1.getConditions().add(Restrict.eq("project", project));
                dto1.getSorts().add("year=desc");
                dto1.getSorts().add("month=asc");
                List<ProjectWeek> projectWeeks = super.findByCis(dto1);
                if (null != projectWeeks && projectWeeks.size() > 0) {
                    List<String> arrivals = projectWeeks.stream().map(ProjectWeek::getArrival).distinct().collect(Collectors.toList());
                    List<Integer> years = projectWeeks.stream().map(ProjectWeek::getYear).distinct().collect(Collectors.toList());
                    List<Integer> months = projectWeeks.stream().map(ProjectWeek::getMonth).distinct().collect(Collectors.toList());
                    for (String area : arrivals) {
                        for (Integer year : years) {
                            for (Integer month : months) {
                                for (ProjectWeek entity : projectWeeks) {
                                    if (entity.getArrival().equals(area) && entity.getYear().equals(year) && entity.getMonth().equals(month)) {
                                        targetIncomeSum += entity.getTargetIncome();
                                        planIncomeSum += entity.getPlanIncome();
                                        double incomeDifference = entity.getPlanIncome() - entity.getTargetIncome();
                                        incomeDifferencesSum += incomeDifference;
                                        targetWorkSum += entity.getTargetWork();
                                        actualWorkSum += entity.getActualWork();
                                        int workDifference = entity.getActualWork() - entity.getTargetWork();
                                        workDifferencesSum += workDifference;
                                    }
                                }
                                if (targetWorkSum != 0) {
                                    ProjectWeekCountBO bo = new ProjectWeekCountBO();
                                    bo.setArrival(area);
                                    bo.setProject(project);
                                    bo.setYear(year);
                                    bo.setMonth(month);
//                                    bo.setProjectName(price);
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
        } else {
            dto1 = new ProjectWeekDTO();
            dto1 = condition(dto);
            dto1.getSorts().add("year=desc");
            dto1.getSorts().add("month=asc");
            List<ProjectWeek> projectWeeks = super.findByCis(dto1);
            if (null != projectWeeks && projectWeeks.size() > 0) {
                List<String> arrivals = projectWeeks.stream().map(ProjectWeek::getArrival).distinct().collect(Collectors.toList());
                List<Integer> years = projectWeeks.stream().map(ProjectWeek::getYear).distinct().collect(Collectors.toList());
                List<Integer> months = projectWeeks.stream().map(ProjectWeek::getMonth).distinct().collect(Collectors.toList());
                List<String> projects = projectWeeks.stream().map(ProjectWeek::getProject).distinct().collect(Collectors.toList());
                for (String area : arrivals) {
                    for (Integer year : years) {
                        for (Integer month : months) {
                            for (String project : projects) {
                                for (ProjectWeek entity : projectWeeks) {
                                    if (entity.getArrival().equals(area) && entity.getYear().equals(year) && entity.getMonth().equals(month) && entity.getProject().equals(project)) {
                                        targetIncomeSum += entity.getTargetIncome();
                                        planIncomeSum += entity.getPlanIncome();
                                        double incomeDifference = entity.getPlanIncome() - entity.getTargetIncome();
                                        incomeDifferencesSum += incomeDifference;
                                        targetWorkSum += entity.getTargetWork();
                                        actualWorkSum += entity.getActualWork();
                                        int workDifference = entity.getActualWork() - entity.getTargetWork();
                                        workDifferencesSum += workDifference;
                                    }
                                }
                                if (targetWorkSum != 0) {
                                    ProjectWeekCountBO bo = new ProjectWeekCountBO();
                                    bo.setArrival(area);
                                    bo.setProject(project);
                                    bo.setYear(year);
                                    bo.setMonth(month);
//                                    bo.setProjectName(price);
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
        }
        return boList;
    }

    @Override
    public List<String> findAreas() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        if (null != projectWeeks && projectWeeks.size() > 0) {
            List<String> list = projectWeeks.stream().map(ProjectWeek::getArrival).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public OptionBO figureShow() throws SerException {
        List<ProjectWeek> projectWeeks = super.findAll();
        List<ProjectWeek> list = new ArrayList<>(0);
        if (null != projectWeeks && projectWeeks.size() > 0) {
            List<String> projectNames = projectWeeks.stream().map(ProjectWeek::getProjectName).distinct().collect(Collectors.toList());
            for (String projectName : projectNames) {
                for (int i = 1; i < 13; i++) {
                    ProjectWeek entity = findData(projectName, i);
                    if (null != entity) {
                        list.add(entity);
                    }
                }
            }
        }

        String text_1 = "项目收入周";

        return getOptionBO(text_1, list);
    }

    private OptionBO getOptionBO(String text_1, List<ProjectWeek> list) throws SerException {
        TitleBO title = new TitleBO();
        title.setText(text_1);

        TooltipBO tooltip = new TooltipBO();

        List<String> names = list.stream().map(ProjectWeek::getProjectName).distinct().collect(Collectors.toList());
        List<String> nameList = new ArrayList<>(0);
        for (String name : names) {
            nameList.add(name + "目标任务量");
            nameList.add(name + "实际任务量");
            nameList.add(name + "目标收入");
            nameList.add(name + "实际收入");
        }
        String[] data1 = (String[]) nameList.toArray(new String[nameList.size()]);
        LegendBO legend = new LegendBO();
        legend.setData(data1);

        List<String> months = new ArrayList<>(0);
        for (int i = 1; i < 13; i++) {
            months.add(i + "月");
        }
        XAxisBO xAxis = new XAxisBO();
        String[] data2 = (String[]) months.toArray(new String[months.size()]);
        xAxis.setData(data2);

        YAxisBO yAxis = new YAxisBO();

        List<SeriesBO> seriesBOList = new ArrayList<>(0);


        for (String projectName : nameList) {
            int tar = projectName.indexOf("目标");
            String name = "";
            String flag = "";
            if (tar != -1) {
                name = projectName.substring(0, tar);
                flag = projectName.substring(tar, projectName.length());
            } else {
                name = projectName.substring(0, projectName.indexOf("实际"));
                flag = projectName.substring(projectName.indexOf("实际"), projectName.length());
            }
            List<Double> numbers = new ArrayList<>(0);
            Double value = 0d;
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(projectName);
            for (int i = 1; i < 13; i++) {
                value = 0d;
                numbers.add(value);
            }

            for (ProjectWeek entity : list) {
                if (name.equals(entity.getProjectName())) {
                    if ("目标任务量".equals(flag) || "实际任务量".equals(flag)) {
                        seriesBO.setType("bar");
                    }
                    if ("目标收入".equals(flag) || "实际收入".equals(flag)) {
                        seriesBO.setType("line");
                    }

                    if ("目标任务量".equals(flag)) {
                        int val = entity.getTargetWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.set(entity.getMonth() - 1, value);
                    }
                    if ("实际任务量".equals(flag)) {
                        int val = entity.getActualWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.set(entity.getMonth() - 1, value);
                    }
                    if ("目标收入".equals(flag)) {
                        value = entity.getTargetIncome();
                        numbers.set(entity.getMonth() - 1, value);
                    }
                    if ("实际收入".equals(flag)) {
                        value = entity.getPlanIncome();
                        numbers.set(entity.getMonth() - 1, value);
                    }
                }
            }

            Double[] data3 = numbers.toArray(new Double[numbers.size()]);
            seriesBO.setData(data3);
            seriesBOList.add(seriesBO);
        }

        SeriesBO[] seriesBOs = seriesBOList.toArray(new SeriesBO[seriesBOList.size()]);
        OptionBO option = new OptionBO();
        option.setLegend(legend);
        option.setSeries(seriesBOs);
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setxAxis(xAxis);
        option.setyAxis(yAxis);
        return option;
    }

    private ProjectWeek findData(String projectName, Integer month) throws SerException {
        String[] files = new String[]{"targetWork", "actualWork", "targetIncome", "planIncome", "month", "projectName"};
        Integer year = LocalDate.now().getYear();
        StringBuilder sql = new StringBuilder("select ifnull(sum(targetWork),0) as targetWork, ");
        sql.append(" ifnull(sum(actualWork),0) as actualWork, ");
        sql.append(" ifnull(sum(targetIncome),0) as targetIncome, ");
        sql.append(" ifnull(sum(planIncome),0) as planIncome, ");
        sql.append(" ifnull(month, 0) as month, projectName ");
        sql.append(" from budget_projectweek ");
        sql.append(" where year = '" + year + "' ");
        sql.append(" and month = '" + month + "' ");
        sql.append(" and projectName = '" + projectName + "' ");

        List<ProjectWeek> list = super.findBySql(sql.toString(), ProjectWeek.class, files);
        return list.get(0).getMonth() == 0 ? null : list.get(0);
    }

    private ProjectWeekDTO condition(ProjectWeekDTO dto) throws SerException {
        ProjectWeekDTO dto1 = new ProjectWeekDTO();
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto1.getConditions().add(Restrict.eq("arrival", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto1.getConditions().add(Restrict.eq("year", dto.getTime().substring(0, dto.getTime().indexOf("-"))));
            dto1.getConditions().add(Restrict.eq("month", dto.getTime().substring(dto.getTime().indexOf("-") + 1, dto.getTime().length())));
        }
        return dto1;
    }
}