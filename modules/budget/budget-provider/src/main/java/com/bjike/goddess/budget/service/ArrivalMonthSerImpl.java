package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.*;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<ArrivalMonthCountBO> collect(ArrivalMonthDTO dto) throws SerException {
        checkSeeIdentity();
        List<ArrivalMonthCountBO> boList = new ArrayList<ArrivalMonthCountBO>();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;

        ArrivalMonthDTO dto1 = new ArrivalMonthDTO();
        if (null != dto.getArrivals()) {
            for (String arrival : dto.getArrivals()) {
                dto1 = new ArrivalMonthDTO();
                dto1 = condition(dto);
                dto1.getConditions().add(Restrict.eq("arrival", arrival));
                dto1.getSorts().add("year=desc");
                dto1.getSorts().add("month=asc");
                List<ArrivalMonth> projectMonths = super.findByCis(dto1);
                if (null != projectMonths && projectMonths.size() > 0) {
                    List<String> arrivals = projectMonths.stream().map(ArrivalMonth::getArrival).distinct().collect(Collectors.toList());
                    List<Integer> years = projectMonths.stream().map(ArrivalMonth::getYear).distinct().collect(Collectors.toList());
                    List<Integer> months = projectMonths.stream().map(ArrivalMonth::getMonth).distinct().collect(Collectors.toList());
//                    for (String area : arrivals) {
                    for (Integer year : years) {
//                        for (Integer month : months) {
                        for (ArrivalMonth entity : projectMonths) {
                            if (entity.getYear().equals(year)) {
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
                            ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                            bo.setArrival(arrival);
                            bo.setYear(year);
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
        } else {
            dto1 = new ArrivalMonthDTO();
            dto1 = condition(dto);
            dto1.getSorts().add("year=desc");
            dto1.getSorts().add("month=asc");
            List<ArrivalMonth> projectMonths = super.findByCis(dto1);
            if (null != projectMonths && projectMonths.size() > 0) {
                List<String> arrivals = projectMonths.stream().map(ArrivalMonth::getArrival).distinct().collect(Collectors.toList());
                List<Integer> years = projectMonths.stream().map(ArrivalMonth::getYear).distinct().collect(Collectors.toList());
                List<Integer> months = projectMonths.stream().map(ArrivalMonth::getMonth).distinct().collect(Collectors.toList());
                for (String area : arrivals) {
                    for (Integer year : years) {
//                        for (Integer month : months) {
                        for (ArrivalMonth entity : projectMonths) {
                            if (entity.getArrival().equals(area) && entity.getYear().equals(year)) {
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
                            ArrivalMonthCountBO bo = new ArrivalMonthCountBO();
                            bo.setArrival(area);
                            bo.setYear(year);
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
        return boList;
    }

    @Override
    public OptionBO figureShow() throws SerException {
        List<ArrivalMonth> projectMonths = super.findAll();
        List<ArrivalMonth> list = new ArrayList<>(0);
        if (null != projectMonths && projectMonths.size() > 0) {
            List<String> arrivals = projectMonths.stream().map(ArrivalMonth::getArrival).distinct().collect(Collectors.toList());
            for (String arrival : arrivals) {
                ArrivalMonth entity = findData(arrival);
                if (null != entity) {
                    list.add(entity);
                }
            }
        }

        String text_1 = "地区收入月";

        return getOptionBO(text_1, list);
    }

    private OptionBO getOptionBO(String text_1, List<ArrivalMonth> list) throws SerException {
        TitleBO title = new TitleBO();
        title.setText(text_1);

        TooltipBO tooltip = new TooltipBO();

        List<String> nameList = new ArrayList<>(0);
        nameList.add("目标任务量");
        nameList.add("实际任务量");
        nameList.add("目标收入");
        nameList.add("实际收入");
        String[] data1 = (String[]) nameList.toArray(new String[nameList.size()]);
        LegendBO legend = new LegendBO();
        legend.setData(data1);

        List<String> names = list.stream().map(ArrivalMonth::getArrival).distinct().collect(Collectors.toList());
        XAxisBO xAxis = new XAxisBO();
        String[] data2 = (String[]) names.toArray(new String[names.size()]);
        xAxis.setData(data2);

        YAxisBO yAxis = new YAxisBO();

        List<SeriesBO> seriesBOList = new ArrayList<>(0);

        for (String name : nameList) {
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(name);
            if ("目标任务量".equals(name) || "实际任务量".equals(name)) {
                seriesBO.setType("bar");
            } else {
                seriesBO.setType("line");
            }

            List<Double> numbers = new ArrayList<>(0);
            for (ArrivalMonth entity : list) {
                for (String arrival : names) {
                    if ("目标任务量".equals(name) && entity.getArrival().equals(arrival)) {
                        Double value = 0d;
                        Integer val = entity.getTargetWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.add(value);
                    }
                    if ("实际任务量".equals(name) && entity.getArrival().equals(arrival)) {
                        Double value = 0d;
                        Integer val = entity.getActualWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.add(value);
                    }
                    if ("目标收入".equals(name) && entity.getArrival().equals(arrival)) {
                        Double value = 0d;
                        value = entity.getTargetIncome();
                        numbers.add(value);
                    }
                    if ("实际收入".equals(name) && entity.getArrival().equals(arrival)) {
                        Double value = 0d;
                        value = entity.getPlanIncome();
                        numbers.add(value);
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

    private ArrivalMonth findData(String arrival) throws SerException {
        String[] files = new String[]{"targetWork", "actualWork", "targetIncome", "planIncome", "arrival"};
        Integer year = LocalDate.now().getYear();
        year = 2017;
        StringBuilder sql = new StringBuilder("select ifnull(sum(targetWork),0) as targetWork, ");
        sql.append(" ifnull(sum(actualWork),0) as actualWork, ");
        sql.append(" ifnull(sum(targetIncome),0) as targetIncome, ");
        sql.append(" ifnull(sum(planIncome),0) as planIncome, arrival ");
        sql.append(" from budget_projectmonth ");
        sql.append(" where year = '" + year + "' ");
        sql.append(" and arrival = '" + arrival + "' ");

        List<ArrivalMonth> list = super.findBySql(sql.toString(), ArrivalMonth.class, files);
        return list.get(0).getArrival() == null ? null : list.get(0);
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

    private ArrivalMonthDTO condition(ArrivalMonthDTO dto) throws SerException {
        ArrivalMonthDTO dto1 = new ArrivalMonthDTO();
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto1.getConditions().add(Restrict.eq("arrival", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto1.getConditions().add(Restrict.eq("year", dto.getTime()));
//            dto1.getConditions().add(Restrict.eq("month", dto.getTime().substring(dto.getTime().indexOf("-") + 1, dto.getTime().length())));
        }
        return dto1;
    }
}