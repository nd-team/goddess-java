package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.entity.ArrivalWeek;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.excel.ArrivalMonthImportTemple;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 地区收入周业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ArrivalWeekSerImpl extends ServiceImpl<ArrivalWeek, ArrivalWeekDTO> implements ArrivalWeekSer {
    @Autowired
    private ArrivalMonthSer arrivalMonthSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ProjectMonthSer projectMonthSer;
    @Autowired
    private ProjectWeekSer projectWeekSer;
    @Autowired
    private WarnSer warnSer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("arrivalweek");
        obj.setDescribesion("地区收入周");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = arrivalMonthSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("arrivalmonth");
        obj.setDescribesion("地区收入月");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = projectMonthSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectmonth");
        obj.setDescribesion("项目收入月");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = projectWeekSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectweek");
        obj.setDescribesion("项目收入周");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = warnSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("warn");
        obj.setDescribesion("预警");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    public void deleteAll() throws SerException {
        List<ArrivalWeek> arrivalWeeks = super.findAll();
        if (null != arrivalWeeks && arrivalWeeks.size() > 0) {
            super.remove(arrivalWeeks);
        }
    }

    @Override
    public List<ArrivalWeekCountBO> collect(ArrivalWeekDTO dto) throws SerException {
        checkSeeIdentity();
        List<ArrivalWeekCountBO> boList = new ArrayList<ArrivalWeekCountBO>();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;

        ArrivalWeekDTO dto1 = new ArrivalWeekDTO();
        if (null != dto.getArrivals()) {
            for (String arrival : dto.getArrivals()) {
                dto1 = new ArrivalWeekDTO();
                dto1 = condition(dto);
                dto1.getConditions().add(Restrict.eq("arrival", arrival));
                dto1.getSorts().add("year=desc");
                dto1.getSorts().add("month=asc");
                List<ArrivalWeek> projectWeeks = super.findByCis(dto1);
                if (null != projectWeeks && projectWeeks.size() > 0) {
                    List<String> arrivals = projectWeeks.stream().map(ArrivalWeek::getArrival).distinct().collect(Collectors.toList());
                    List<Integer> years = projectWeeks.stream().map(ArrivalWeek::getYear).distinct().collect(Collectors.toList());
                    List<Integer> months = projectWeeks.stream().map(ArrivalWeek::getMonth).distinct().collect(Collectors.toList());
//                    for (String area : arrivals) {
                    for (Integer year : years) {
                        for (Integer month : months) {
                            for (ArrivalWeek entity : projectWeeks) {
                                if (entity.getYear().equals(year) && entity.getMonth().equals(month)) {
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
                                ArrivalWeekCountBO bo = new ArrivalWeekCountBO();
                                bo.setArrival(arrival);
                                bo.setYear(year);
                                bo.setMonth(month);
//                                    bo.setProjectName(price);
                                bo.setTargetWorkSum(targetWorkSum);
                                bo.setActualWorkSum(actualWorkSum);
                                bo.setWorkDifferencesSum(workDifferencesSum);
                                bo.setTargetIncomeSum(targetIncomeSum);
                                bo.setPlanIncome(planIncomeSum);
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
        } else {
            dto1 = new ArrivalWeekDTO();
            dto1 = condition(dto);
            dto1.getSorts().add("year=desc");
            dto1.getSorts().add("month=asc");
            List<ArrivalWeek> projectWeeks = super.findByCis(dto1);
            if (null != projectWeeks && projectWeeks.size() > 0) {
                List<String> arrivals = projectWeeks.stream().map(ArrivalWeek::getArrival).distinct().collect(Collectors.toList());
                List<Integer> years = projectWeeks.stream().map(ArrivalWeek::getYear).distinct().collect(Collectors.toList());
                List<Integer> months = projectWeeks.stream().map(ArrivalWeek::getMonth).distinct().collect(Collectors.toList());
                for (String area : arrivals) {
                    for (Integer year : years) {
                        for (Integer month : months) {
                            for (ArrivalWeek entity : projectWeeks) {
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
                                ArrivalWeekCountBO bo = new ArrivalWeekCountBO();
                                bo.setArrival(area);
                                bo.setYear(year);
                                bo.setMonth(month);
//                                    bo.setProjectName(price);
                                bo.setTargetWorkSum(targetWorkSum);
                                bo.setActualWorkSum(actualWorkSum);
                                bo.setWorkDifferencesSum(workDifferencesSum);
                                bo.setTargetIncomeSum(targetIncomeSum);
                                bo.setPlanIncome(planIncomeSum);
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

    @Override
    public OptionBO figureShow() throws SerException {
        List<ArrivalWeek> projectWeeks = super.findAll();
        List<ArrivalWeek> list = new ArrayList<>(0);
        if (null != projectWeeks && projectWeeks.size() > 0) {
            List<String> arrivals = projectWeeks.stream().map(ArrivalWeek::getArrival).distinct().collect(Collectors.toList());
            for (String arrival : arrivals) {
                for (int i = 1; i < 13; i++) {
                    ArrivalWeek entity = findData(arrival, i);
                    if (null != entity) {
                        list.add(entity);
                    }
                }
            }
        }

        String text_1 = "地区收入周";

        return getOptionBO(text_1, list);
    }

    private OptionBO getOptionBO(String text_1, List<ArrivalWeek> list) throws SerException {
        TitleBO title = new TitleBO();
        title.setText(text_1);

        TooltipBO tooltip = new TooltipBO();

        List<String> names = list.stream().map(ArrivalWeek::getArrival).distinct().collect(Collectors.toList());
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


        for (String arrival : nameList) {
            int tar = arrival.indexOf("目标");
            String name = "";
            String flag = "";
            if (tar != -1) {
                name = arrival.substring(0, tar);
                flag = arrival.substring(tar, arrival.length());
            } else {
                name = arrival.substring(0, arrival.indexOf("实际"));
                flag = arrival.substring(arrival.indexOf("实际"), arrival.length());
            }
            List<Double> numbers = new ArrayList<>(0);
            Double value = 0d;
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(arrival);
            for (int i = 1; i < 13; i++) {
                value = 0d;
                numbers.add(value);
            }

            for (ArrivalWeek entity : list) {
                if (name.equals(entity.getArrival())) {
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

    private ArrivalWeek findData(String arrival, Integer month) throws SerException {
        String[] files = new String[]{"targetWork", "actualWork", "targetIncome", "planIncome", "month", "arrival"};
        Integer year = LocalDate.now().getYear();
        StringBuilder sql = new StringBuilder("select ifnull(sum(targetWork),0) as targetWork, ");
        sql.append(" ifnull(sum(actualWork),0) as actualWork, ");
        sql.append(" ifnull(sum(targetIncome),0) as targetIncome, ");
        sql.append(" ifnull(sum(planIncome),0) as planIncome, ");
        sql.append(" ifnull(month, 0) as month, arrival ");
        sql.append(" from budget_arrivalweek ");
        sql.append(" where year = '" + year + "' ");
        sql.append(" and month = '" + month + "' ");
        sql.append(" and arrival = '" + arrival + "' ");

        List<ArrivalWeek> list = super.findBySql(sql.toString(), ArrivalWeek.class, files);
        return list.get(0).getMonth() == 0 ? null : list.get(0);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ArrivalWeekBO save(ArrivalWeekTO to) throws SerException {
        checkAddIdentity();
        ArrivalWeek arrivalWeek = BeanTransform.copyProperties(to, ArrivalWeek.class, true);
//        arrivalWeek.setTargetIncome(to.getPrice()*to.getTargetWork());
//        arrivalWeek.setPlanIncome(to.getPrice()*to.getActualWork());
        super.save(arrivalWeek);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = 0d;
            if (targetIncome != 0d) {
                scale = Double.parseDouble(String.format("%.2f", planIncome / targetIncome));
            }
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
        return BeanTransform.copyProperties(arrivalWeek, ArrivalWeekBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ArrivalWeekTO to) throws SerException {
        checkAddIdentity();
        ArrivalWeek arrivalWeek = super.findById(to.getId());
        LocalDateTime a = arrivalWeek.getCreateTime();
        LocalDateTime b = arrivalWeek.getModifyTime();
        arrivalWeek = BeanTransform.copyProperties(to, ArrivalWeek.class, true);
        arrivalWeek.setCreateTime(a);
        arrivalWeek.setModifyTime(b);
        super.update(arrivalWeek);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = Double.parseDouble(String.format("%.2f", planIncome / targetIncome));
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
        arrivalMonthSer.deleteAll();
        List<ArrivalWeekCountBO> list = count();
        for (ArrivalWeekCountBO bo : list) {
            ArrivalMonthTO arrivalMonthTO = new ArrivalMonthTO();
            arrivalMonthTO.setArrival(bo.getArrival());
            arrivalMonthTO.setYear(bo.getYear());
            arrivalMonthTO.setMonth(bo.getMonth());
            arrivalMonthTO.setTargetWork(bo.getTargetWorkSum());
            arrivalMonthTO.setActualWork(bo.getActualWorkSum());
            arrivalMonthTO.setWorkDifferences(bo.getWorkDifferencesSum());
            arrivalMonthTO.setTargetIncome(bo.getTargetIncomeSum());
            arrivalMonthTO.setPlanIncome(bo.getPlanIncome());
            arrivalMonthTO.setIncomeDifferences(bo.getIncomeDifferencesSum());
            Double planIncome = bo.getPlanIncome();
            Double targetIncome = bo.getTargetIncomeSum();
            Double scale = Double.parseDouble(String.format("%.2f", planIncome / targetIncome));
            arrivalMonthTO.setScale(scale);
            arrivalMonthSer.save(arrivalMonthTO);
        }
    }

    @Override
    public List<ArrivalWeekBO> list(ArrivalWeekDTO dto) throws SerException {
        checkSeeIdentity();
        List<ArrivalWeek> list = super.findByCis(dto, true);
        List<ArrivalWeekBO> boList = new ArrayList<ArrivalWeekBO>();
        for (ArrivalWeek a : list) {
            ArrivalWeekBO bo = BeanTransform.copyProperties(a, ArrivalWeekBO.class);
            bo.setWorkDifferences(a.getTargetWork() - a.getActualWork());
            bo.setIncomeDifferences(a.getTargetIncome() - a.getPlanIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ArrivalWeekBO findByID(String id) throws SerException {
        ArrivalWeek arrivalWeek = super.findById(id);
        ArrivalWeekBO bo = BeanTransform.copyProperties(arrivalWeek, ArrivalWeekBO.class);
        bo.setWorkDifferences(arrivalWeek.getTargetWork() - arrivalWeek.getActualWork());
        bo.setIncomeDifferences(arrivalWeek.getTargetIncome() - arrivalWeek.getPlanIncome());
        return bo;
    }

    @Override
    public List<ArrivalWeekCountBO> count() throws SerException {
        checkSeeIdentity();
        List<ArrivalWeekCountBO> boList = new ArrayList<ArrivalWeekCountBO>();
        ArrivalWeekDTO dto = new ArrivalWeekDTO();
        List<ArrivalWeek> list = super.findByCis(dto);
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        for (String arrival : arrivals) {
            for (Integer year : years) {
                for (Integer month : months) {
                    //     for (Double price : prices) {
                    for (ArrivalWeek a : list) {
                        boolean b1 = a.getArrival().equals(arrival);
                        int year1 = a.getYear();
                        boolean b2 = year1 == year;
                        boolean b3 = a.getMonth() == month;
                        //           Double price1 = a.getPrice();
//                        int aa = price1.compareTo(price);
//                        boolean b4 = aa == 0 ? true : false;
                        if (b1 && b2 && b3/* && b4*/) {
                            targetIncomeSum += a.getTargetIncome();
                            planIncomeSum += a.getPlanIncome();
                            double incomeDifference = a.getPlanIncome() - a.getTargetIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += a.getTargetWork();
                            actualWorkSum += a.getActualWork();
                            int workDifference = a.getTargetWork() - a.getActualWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
                        ArrivalWeekCountBO arrivalWeekCountBO = new ArrivalWeekCountBO();
                        arrivalWeekCountBO.setArrival(arrival);
                        arrivalWeekCountBO.setYear(year);
                        arrivalWeekCountBO.setMonth(month);
                        //       arrivalWeekCountBO.setPrice(price);
                        arrivalWeekCountBO.setTargetWorkSum(targetWorkSum);
                        arrivalWeekCountBO.setActualWorkSum(actualWorkSum);
                        arrivalWeekCountBO.setWorkDifferencesSum(workDifferencesSum);
                        arrivalWeekCountBO.setTargetIncomeSum(targetIncomeSum);
                        arrivalWeekCountBO.setPlanIncome(planIncomeSum);
                        arrivalWeekCountBO.setIncomeDifferencesSum(incomeDifferencesSum);
                        boList.add(arrivalWeekCountBO);
                        targetWorkSum = 0;
                        actualWorkSum = 0;
                        workDifferencesSum = 0;
                        targetIncomeSum = 0.00;
                        planIncomeSum = 0.00;
                        incomeDifferencesSum = 0.00;     //置为0
                    }
                }
                //   }
            }
        }
        return boList;
    }

    @Override
    public List<ArrivalWeekCountBO> conditionsCount(ArrivalWeekDTO dto1) throws SerException {
        checkSeeIdentity();
        List<ArrivalWeekCountBO> boList = new ArrayList<ArrivalWeekCountBO>();
        List<Integer> years = findAllYears();
        List<Integer> months = findAllMonths();
        List<Double> prices = findAllPrices();
        List<ArrivalWeek> list = null;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        String[] arrivals = dto1.getArrivals();
        if (arrivals != null) {
            for (String arrival : arrivals) {
                ArrivalWeekDTO dto = new ArrivalWeekDTO();
                dto.getConditions().add(Restrict.eq("arrival", arrival));
                list = super.findByCis(dto);
                //    for (Double price : prices) {
                for (Integer year : years) {
                    for (Integer month : months) {
                        for (ArrivalWeek a : list) {
//                            Double price1 = a.getPrice();
//                            int aa = price1.compareTo(price);
//                            boolean b = aa == 0 ? true : false;
                            if (/*b &&*/ a.getYear().equals(year) && a.getMonth().equals(month)) {
                                targetIncomeSum += a.getTargetIncome();
                                planIncomeSum += a.getPlanIncome();
                                double incomeDifference = a.getPlanIncome() - a.getTargetIncome();
                                incomeDifferencesSum += incomeDifference;
                                targetWorkSum += a.getTargetWork();
                                actualWorkSum += a.getActualWork();
                                int workDifference = a.getTargetWork() - a.getActualWork();
                                workDifferencesSum += workDifference;
                            }
                        }
                        if (targetWorkSum != 0) {
                            ArrivalWeekCountBO arrivalWeekCountBO = new ArrivalWeekCountBO();
                            arrivalWeekCountBO.setArrival(arrival);
                            //        arrivalWeekCountBO.setPrice(price);
                            arrivalWeekCountBO.setYear(year);
                            arrivalWeekCountBO.setMonth(month);
                            arrivalWeekCountBO.setTargetWorkSum(targetWorkSum);
                            arrivalWeekCountBO.setActualWorkSum(actualWorkSum);
                            arrivalWeekCountBO.setWorkDifferencesSum(workDifferencesSum);
                            arrivalWeekCountBO.setTargetIncomeSum(targetIncomeSum);
                            arrivalWeekCountBO.setPlanIncome(planIncomeSum);
                            arrivalWeekCountBO.setIncomeDifferencesSum(incomeDifferencesSum);
                            boList.add(arrivalWeekCountBO);
                            targetWorkSum = 0;
                            actualWorkSum = 0;
                            workDifferencesSum = 0;
                            targetIncomeSum = 0.00;
                            planIncomeSum = 0.00;
                            incomeDifferencesSum = 0.00;     //置为0
                        }
                    }
                }
                //}
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
    @Override
    public List<String> findAllArrivals() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ArrivalWeek a : list) {
            set.add(a.getArrival());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ArrivalMonthImportTemple> arrivalMonthImportTemples = new ArrayList<>();
        ArrivalMonthImportTemple excel = new ArrivalMonthImportTemple();
        excel.setArrival("广州");
        excel.setYear(2017);
        excel.setMonth(5);
        excel.setWeek(1);
        excel.setTargetWork(16);
        excel.setActualWork(33);
        excel.setPrice(20000d);
        excel.setTargetIncome(20000d);
        excel.setPlanIncome(2695d);
        arrivalMonthImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(arrivalMonthImportTemples, exce);
        return bytes;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllYears() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ArrivalWeek a : list) {
            set.add(a.getYear());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllMonths() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ArrivalWeek a : list) {
            set.add(a.getMonth());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    /**
     * 查找所有的单价
     *
     * @return class Double
     * @throws SerException
     */
    private List<Double> findAllPrices() throws SerException {
        List<ArrivalWeek> list = super.findAll();
        Set<Double> set = new HashSet<Double>();
        for (ArrivalWeek a : list) {
            set.add(a.getPrice());
        }
        List<Double> l = new ArrayList<Double>(set);
        return l;
    }

    @Override
    public Long countNum(ArrivalWeekDTO dto) throws SerException {
        return super.count(dto);
    }

    private ArrivalWeekDTO condition(ArrivalWeekDTO dto) throws SerException {
        ArrivalWeekDTO dto1 = new ArrivalWeekDTO();
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto1.getConditions().add(Restrict.eq("arrival", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto1.getConditions().add(Restrict.eq("year", dto.getTime().substring(0, dto.getTime().indexOf("-"))));
            dto1.getConditions().add(Restrict.eq("month", Integer.parseInt(dto.getTime().substring(dto.getTime().indexOf("-") + 1, dto.getTime().length()))));
        }
        return dto1;
    }
}