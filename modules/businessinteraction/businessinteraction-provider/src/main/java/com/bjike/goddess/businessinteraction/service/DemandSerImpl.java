package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.*;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.entity.Demand;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.excel.DemandImport;
import com.bjike.goddess.businessinteraction.excel.DemandImportTemple;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 需求信息业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class DemandSerImpl extends ServiceImpl<Demand, DemandDTO> implements DemandSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
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
        if (flagSee) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
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
    public Long countInter(DemandDTO demandDTO) throws SerException {
        Long count = super.count(demandDTO);
        return count;
    }

    @Override
    public DemandBO getOneById(String id) throws SerException {
        Demand demand = super.findById(id);
        return BeanTransform.copyProperties(demand, DemandBO.class);
    }

    @Override
    public List<DemandBO> listIntera(DemandDTO demandDTO) throws SerException {
        checkSeeIdentity();
        List<Demand> demands = super.findByCis(demandDTO, true);
        return BeanTransform.copyProperties(demands, DemandBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DemandBO addIntera(DemandTO demandTO) throws SerException {
        checkSeeIdentity();
        Demand demand = BeanTransform.copyProperties(demandTO, Demand.class, true);
        demand.setCreateTime(LocalDateTime.now());
        demand.setDemandDate(LocalDate.now());
        super.save(demand);
        return BeanTransform.copyProperties(demand, DemandBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DemandBO editIntera(DemandTO demandTO) throws SerException {
        checkSeeIdentity();
        Demand demand = super.findById(demandTO.getId());
        LocalDateTime dateTime = demand.getCreateTime();
        LocalDate date = demand.getDemandDate();
        demand = BeanTransform.copyProperties(demandTO, Demand.class, true);
        demand.setDemandDate(date);
        demand.setCreateTime(dateTime);
        demand.setModifyTime(LocalDateTime.now());
        super.update(demand);
        return BeanTransform.copyProperties(demand, DemandBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deleteIntera(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<Demand> list = super.findAll();
        List<DemandImport> demandImports = new ArrayList<>();
        for (Demand demand : list) {
            DemandImport excel = BeanTransform.copyProperties(demand, DemandImport.class, "feedbackDemand", "initialAnalysis", "projectEstimates",
                    "projectEstimatesThrought", "discuss", "reachedCoop", "introduceContra");
            excel.setFeedbackDemand(boolToString(demand.getFeedbackDemand()));
            excel.setInitialAnalysis(boolToString(demand.getInitialAnalysis()));
            excel.setProjectEstimates(boolToString(demand.getProjectEstimates()));
            excel.setProjectEstimatesThrought(boolToString(demand.getProjectEstimatesThrought()));
            excel.setDiscuss(boolToString(demand.getDiscuss()));
            excel.setReachedCoop(boolToString(demand.getReachedCoop()));
            excel.setIntroduceContra(boolToString(demand.getIntroduceContra()));
            demandImports.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(demandImports, excel);
        return bytes;
    }

    private String boolToString(Boolean type) throws SerException {
        String name = "";
        if (type != null) {
            if (type) {
                name = "是";
            } else {
                name = "否";
            }
        }
        return name;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<DemandImportTemple> demandImportTemples = new ArrayList<>();
        DemandImportTemple excel = new DemandImportTemple();
        excel.setOrigin("员工");
        excel.setName("北京艾佳天城");
        excel.setWork("test");
        excel.setCompanyName("test");
        excel.setCompanyType("test");
        excel.setContactWay("test");
        excel.setArea("test");
        excel.setBusinessTarget("test");
        excel.setSize("test");
        excel.setArtificial("test");
        excel.setDevice("test");
        excel.setServiceFee(10d);
        excel.setIntermediaryFee(10d);
        excel.setProfession("test");
        excel.setFeedbackDate("2017-12-12");
        excel.setMarkerNum("5215313");
        excel.setInitialAnalysisDate("2017-12-12");
        excel.setInitialAnalysis("是");
        excel.setProjectEstimatesDate("2017-12-12");
        excel.setProjectEstimates("是");
        excel.setProjectEstimatesThrought("是");
        excel.setDiscussDate("2017-12-12");
        excel.setDiscuss("是");
        excel.setReachedCoop("是");
        excel.setIntroduceContra("是");
        demandImportTemples.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(demandImportTemples, exce);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<DemandTO> demandTOS) throws SerException {
        List<Demand> demandList = BeanTransform.copyProperties(demandTOS, Demand.class, true);
        demandList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
            str.setDemandDate(LocalDate.now());
        });
        super.save(demandList);
    }

    public List<String> findBussType() throws SerException {
        List<Demand> demandList = super.findAll();
        if (CollectionUtils.isEmpty(demandList)) {
            return Collections.emptyList();
        }
        return demandList.stream().map(Demand::getBusinessTarget).distinct().collect(Collectors.toList());
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;

        return totalMethod(startDate, endDate);
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];

        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));


        return totalMethod(startDate, endDate);
    }


    public String[] quarterChange(Integer year, Integer quarter) throws SerException {
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().toString();
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = LocalDate.now().toString();
                endDate = LocalDate.now().toString();
                break;
        }
        return new String[]{startDate, endDate};
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);

        return totalMethod(date[0], date[1]);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));

        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) AS demandInforNum FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "')a, ");
                sql.append(" (SELECT count(*) AS haveFeedbackNum FROM businessinteraction_demand WHERE feedbackDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_feedbackDemand = 1)b, ");
                sql.append(" (SELECT count(*) AS noFeedbackNum FROM businessinteraction_demand WHERE feedbackDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_feedbackDemand = 0)c, ");
                sql.append(" (SELECT count(*) AS preliminaryAnalysisNum FROM businessinteraction_demand WHERE initialAnalysisDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_initialAnalysis = 1)d, ");
                sql.append(" (SELECT count(*) AS noPreliminaryAnalysisNum FROM businessinteraction_demand WHERE initialAnalysisDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_initialAnalysis = 0)e, ");
                sql.append(" (SELECT count(*) AS calculatedNum FROM businessinteraction_demand WHERE projectEstimatesDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimates = 1)f, ");
                sql.append(" (SELECT count(*) AS noCalculatedNum FROM businessinteraction_demand WHERE projectEstimatesDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimates = 0)g, ");
                sql.append(" (SELECT count(*) AS calculatedThroughNum FROM businessinteraction_demand WHERE projectEstimatesDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimatesThrought = 1)h, ");
                sql.append(" (SELECT count(*) AS talksMadeNum FROM businessinteraction_demand WHERE discussDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_discuss = 1)i, ");
                sql.append(" (SELECT count(*) AS noTalksMadeNum FROM businessinteraction_demand WHERE discussDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_discuss = 0)j, ");
                sql.append(" (SELECT count(*) AS cooperationNum FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_reachedCoop = 1)k, ");
                sql.append(" (SELECT count(*) AS discardedNum FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_reachedCoop = 0)l, ");
                sql.append(" (SELECT count(*) AS IntroduceOtherNum FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_introduceContra = 1)m, ");
                sql.append(" (SELECT ifnull(0,sum(serviceFee)) AS serviceFee FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "')n, ");
                sql.append(" (SELECT ifnull(0,sum(intermediaryFee)) AS intermediaryFee FROM businessinteraction_demand WHERE demandDate < '" + endDate + "' AND businessTarget = '" + bussType + "')o ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer demandInforNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer haveFeedbackNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer noFeedbackNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer preliminaryAnalysisNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer noPreliminaryAnalysisNum = Integer.parseInt(String.valueOf(objects[4]));
                Integer calculatedNum = Integer.parseInt(String.valueOf(objects[5]));
                Integer noCalculatedNum = Integer.parseInt(String.valueOf(objects[6]));
                Integer calculatedThroughNum = Integer.parseInt(String.valueOf(objects[7]));
                Integer talksMadeNum = Integer.parseInt(String.valueOf(objects[8]));
                Integer noTalksMadeNum = Integer.parseInt(String.valueOf(objects[9]));
                Integer cooperationNum = Integer.parseInt(String.valueOf(objects[10]));
                Integer discardedNum = Integer.parseInt(String.valueOf(objects[11]));
                Integer IntroduceOtherNum = Integer.parseInt(String.valueOf(objects[12]));
                Double serviceFee = Double.parseDouble(String.valueOf(objects[13]));
                Double intermediaryFee = Double.parseDouble(String.valueOf(objects[14]));


                SummationBO summationBO = new SummationBO();
                summationBO.setBussType(bussType);
                summationBO.setDemandInforNum(demandInforNum);
                summationBO.setHaveFeedbackNum(haveFeedbackNum);
                summationBO.setNoFeedbackNum(noFeedbackNum);
                summationBO.setPreliminaryAnalysisNum(preliminaryAnalysisNum);
                summationBO.setNoPreliminaryAnalysisNum(noPreliminaryAnalysisNum);
                summationBO.setCalculatedNum(calculatedNum);
                summationBO.setNoCalculatedNum(noCalculatedNum);
                summationBO.setCalculatedThroughNum(calculatedThroughNum);
                summationBO.setTalksMadeNum(talksMadeNum);
                summationBO.setNoTalksMadeNum(noTalksMadeNum);
                summationBO.setCooperationNum(cooperationNum);
                summationBO.setDiscardedNum(discardedNum);
                summationBO.setIntroduceOtherNum(IntroduceOtherNum);
                summationBO.setServiceFee(serviceFee);
                summationBO.setIntermediaryFee(intermediaryFee);
                summationBOList.add(summationBO);
            }
        }
        return summationBOList;
    }

    /**
     * 汇总总方法
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws SerException
     */
    private List<SummationBO> totalMethod(String startDate, String endDate) throws SerException {
        List<SummationBO> summationBOList = new ArrayList<>();
        List<String> bussTypes = findBussType();
        if (bussTypes != null && bussTypes.size() > 0) {
            for (String bussType : bussTypes) {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM ( ");
                sql.append(" (SELECT count(*) AS demandInforNum FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "')a, ");
                sql.append(" (SELECT count(*) AS haveFeedbackNum FROM businessinteraction_demand WHERE feedbackDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_feedbackDemand = 1)b, ");
                sql.append(" (SELECT count(*) AS noFeedbackNum FROM businessinteraction_demand WHERE feedbackDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_feedbackDemand = 0)c, ");
                sql.append(" (SELECT count(*) AS preliminaryAnalysisNum FROM businessinteraction_demand WHERE initialAnalysisDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_initialAnalysis = 1)d, ");
                sql.append(" (SELECT count(*) AS noPreliminaryAnalysisNum FROM businessinteraction_demand WHERE initialAnalysisDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_initialAnalysis = 0)e, ");
                sql.append(" (SELECT count(*) AS calculatedNum FROM businessinteraction_demand WHERE projectEstimatesDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimates = 1)f, ");
                sql.append(" (SELECT count(*) AS noCalculatedNum FROM businessinteraction_demand WHERE projectEstimatesDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimates = 0)g, ");
                sql.append(" (SELECT count(*) AS calculatedThroughNum FROM businessinteraction_demand WHERE projectEstimatesDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_projectEstimatesThrought = 1)h, ");
                sql.append(" (SELECT count(*) AS talksMadeNum FROM businessinteraction_demand WHERE discussDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_discuss = 1)i, ");
                sql.append(" (SELECT count(*) AS noTalksMadeNum FROM businessinteraction_demand WHERE discussDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_discuss = 0)j, ");
                sql.append(" (SELECT count(*) AS cooperationNum FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_reachedCoop = 1)k, ");
                sql.append(" (SELECT count(*) AS discardedNum FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_reachedCoop = 0)l, ");
                sql.append(" (SELECT count(*) AS IntroduceOtherNum FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "' AND is_introduceContra = 1)m, ");
                sql.append(" (SELECT ifnull(0,sum(serviceFee)) AS serviceFee FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "')n, ");
                sql.append(" (SELECT ifnull(0,sum(intermediaryFee)) AS intermediaryFee FROM businessinteraction_demand WHERE demandDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND businessTarget = '" + bussType + "')o ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] objects = (Object[]) objectList.get(0);
                Integer demandInforNum = Integer.parseInt(String.valueOf(objects[0]));
                Integer haveFeedbackNum = Integer.parseInt(String.valueOf(objects[1]));
                Integer noFeedbackNum = Integer.parseInt(String.valueOf(objects[2]));
                Integer preliminaryAnalysisNum = Integer.parseInt(String.valueOf(objects[3]));
                Integer noPreliminaryAnalysisNum = Integer.parseInt(String.valueOf(objects[4]));
                Integer calculatedNum = Integer.parseInt(String.valueOf(objects[5]));
                Integer noCalculatedNum = Integer.parseInt(String.valueOf(objects[6]));
                Integer calculatedThroughNum = Integer.parseInt(String.valueOf(objects[7]));
                Integer talksMadeNum = Integer.parseInt(String.valueOf(objects[8]));
                Integer noTalksMadeNum = Integer.parseInt(String.valueOf(objects[9]));
                Integer cooperationNum = Integer.parseInt(String.valueOf(objects[10]));
                Integer discardedNum = Integer.parseInt(String.valueOf(objects[11]));
                Integer IntroduceOtherNum = Integer.parseInt(String.valueOf(objects[12]));
                Double serviceFee = Double.parseDouble(String.valueOf(objects[13]));
                Double intermediaryFee = Double.parseDouble(String.valueOf(objects[14]));


                SummationBO summationBO = new SummationBO();
                summationBO.setBussType(bussType);
                summationBO.setDemandInforNum(demandInforNum);
                summationBO.setHaveFeedbackNum(haveFeedbackNum);
                summationBO.setNoFeedbackNum(noFeedbackNum);
                summationBO.setPreliminaryAnalysisNum(preliminaryAnalysisNum);
                summationBO.setNoPreliminaryAnalysisNum(noPreliminaryAnalysisNum);
                summationBO.setCalculatedNum(calculatedNum);
                summationBO.setNoCalculatedNum(noCalculatedNum);
                summationBO.setCalculatedThroughNum(calculatedThroughNum);
                summationBO.setTalksMadeNum(talksMadeNum);
                summationBO.setNoTalksMadeNum(noTalksMadeNum);
                summationBO.setCooperationNum(cooperationNum);
                summationBO.setDiscardedNum(discardedNum);
                summationBO.setIntroduceOtherNum(IntroduceOtherNum);
                summationBO.setServiceFee(serviceFee);
                summationBO.setIntermediaryFee(intermediaryFee);
                summationBOList.add(summationBO);
            }
        }
        return summationBOList;
    }


    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        List<SummationBO> summationBOList = summaDay(summDate);
        String text_1 = "商业能力互动信息管理日统计";
        return figureShow(summationBOList, text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        List<SummationBO> summationBOList = summaWeek(year, month, week);
        String text_1 = "商业能力互动信息管理周统计";
        return figureShow(summationBOList, text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        List<SummationBO> summationBOList = summaMonth(year, month);
        String text_1 = "商业能力互动信息管理月统计";
        return figureShow(summationBOList, text_1);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        List<SummationBO> summationBOList = summaQuarter(year, quarter);
        String text_1 = "商业能力互动信息管理季度统计";
        return figureShow(summationBOList, text_1);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
        List<SummationBO> summationBOList = summaYear(year);
        String text_1 = "商业能力互动信息管理年统计";
        return figureShow(summationBOList, text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        List<SummationBO> summationBOList = summaTotal(endDate);
        String text_1 = "商业能力互动信息管理累计统计";
        return figureShow(summationBOList, text_1);
    }

    /**
     * 图形展示总方法
     *
     * @param summationBOS 汇总数据对象
     * @param text_1
     * @return
     * @throws SerException
     */
    public OptionBO figureShow(List<SummationBO> summationBOS, String text_1) throws SerException {
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list_2 = new ArrayList<>();//1
//        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_list_3 = new String[]{"需求信息数量", "已反馈数量", "未反馈数", "已初步分析数", "未进行初步分析数", "已项目测算数",
                "未项目测算数", "项目测算通过数", "已进行洽谈数量", "未进行洽谈数量", "已转换商机数量", "已丢弃数量", "介绍给别的承包商数", "达成合作数"};
        xAxisBO.setData(text_list_3);

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        //        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (summationBOS != null && summationBOS.size() > 0) {
            for (SummationBO summationBO : summationBOS) {
                text_list_2.add(summationBO.getBussType());

                //柱状图数据
                Integer[] nums = new Integer[]{summationBO.getDemandInforNum(),
                        summationBO.getHaveFeedbackNum(), summationBO.getNoFeedbackNum(), summationBO.getPreliminaryAnalysisNum(),
                        summationBO.getNoPreliminaryAnalysisNum(), summationBO.getCalculatedNum(), summationBO.getNoCalculatedNum(),
                        summationBO.getCalculatedThroughNum(), summationBO.getTalksMadeNum(), summationBO.getNoTalksMadeNum(),
                        summationBO.getCooperationNum(), summationBO.getDiscardedNum(), summationBO.getIntroduceOtherNum()};
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(summationBO.getBussType());
                seriesBO.setType("bar");
                seriesBO.setData(nums);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list_2.size()];
        text_2 = text_list_2.toArray(text_2);
        legendBO.setData(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }
}