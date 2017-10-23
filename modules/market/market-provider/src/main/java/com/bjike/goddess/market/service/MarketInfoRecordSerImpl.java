package com.bjike.goddess.market.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.competitormanage.api.CompetitorAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerNameNumBO;
import com.bjike.goddess.market.bo.*;
import com.bjike.goddess.market.dto.MarketInfoPreAnalysisDTO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.entity.MarketInfoPreAnalysis;
import com.bjike.goddess.market.entity.MarketInfoRecord;
import com.bjike.goddess.market.enums.GuideAddrStatus;
import com.bjike.goddess.market.enums.SourceInfo;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoRecordTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
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
import java.util.*;

/**
 * 市场信息记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 市场信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketSerCache")
@Service
public class MarketInfoRecordSerImpl extends ServiceImpl<MarketInfoRecord, MarketInfoRecordDTO> implements MarketInfoRecordSer {
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;
    @Autowired
    private CompetitorAPI competitorAPI;
    @Autowired
    private MarketInfoPreAnalysisSer marketInfoPreAnalysisSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看添加修改删除权限（部门级别）
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
                throw new SerException("您不是相应部门的人员，不可以进行此操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对预算模块分析权限（模块级别）
     */
    private void checkButgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是预算模块人员，不可以进行分析");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }
    /**
     * 核对规划模块分析权限（模块级别）
     */
    private void checkPlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是规划模块的人员，不可以进行分析");
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
     * 导航栏核对预算模块分析权限（模块级别)
     */
    private Boolean guideButgetIdentity() throws SerException {
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
    /**
     * 导航栏核对规划模块分析权限（模块级别)
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagButget = guideButgetIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPlan = guidePlanIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("marketInforecord");
        obj.setDescribesion("市场信息记录");
        if (flagSee || flagButget || flagPlan) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagAnaly = marketInfoPreAnalysisSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("marketinfopreanalysis");
        obj.setDescribesion("市场信息初步分析");
        if (flagAnaly) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case BUDGETAUDIT:
                flag = guideButgetIdentity();
                break;
            case PLANAUDIT:
                flag = guidePlanIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        Long count = super.count(marketInfoRecordDTO);
        return null;
    }

    @Override
    public MarketInfoRecordBO getOne(String id) throws SerException {
        MarketInfoRecord marketInfoRecord = super.findById(id);
        return BeanTransform.copyProperties(marketInfoRecord, MarketInfoRecordBO.class);
    }

    @Override
    public List<MarketInfoRecordBO> findListRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        checkSeeIdentity();
        List<MarketInfoRecord> marketInfoRecords = super.findByCis(marketInfoRecordDTO, true);
        return BeanTransform.copyProperties(marketInfoRecords, MarketInfoRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoRecordBO insertRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
        checkSeeIdentity();
        MarketInfoRecord marketInfoRecord = BeanTransform.copyProperties(marketInfoRecordTO, MarketInfoRecord.class, true, "majors", "infoFillPerson");
        marketInfoRecord.setCreateTime(LocalDateTime.now());
        marketInfoRecord.setMajors(StringUtils.join(marketInfoRecordTO.getMajors(), ","));
        marketInfoRecord.setInfoFillPerson(StringUtils.join(marketInfoRecordTO.getInfoFillPerson(), ","));
        if (marketInfoRecord.getInitiateDate() != null) {
            String name = positionDetailUserAPI.customRepPerson();//获取客户模块负责人
            if (StringUtils.isNotBlank(name)) {
                String email = internalContactsAPI.getEmail(name);
                //如果该乘车人邮箱存在发送邮件,否则就不发
                if (StringUtils.isNotBlank(email)) {
                    StringBuffer content = new StringBuffer();
                    content.append("客户模块负责人:您好!市场信息编号为:" + marketInfoRecord.getMarketInfoNum() + "的信息已在" + marketInfoRecord.getInfoCollectDate() + "收集成功,请在" + marketInfoRecord.getAgingStartTime() + "进行初步分析工作.谢谢!");
                    MessageTO messageTO = new MessageTO();
                    messageTO.setContent(content.toString());
                    messageTO.setTitle("市场信息初步分析");
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);

                    messageTO.setReceivers(new String[]{email});
                    messageAPI.send(messageTO);
                }
            }
        }
        super.save(marketInfoRecord);
        MarketInfoPreAnalysis marketInfoPreAnalysis = BeanTransform.copyProperties(marketInfoRecordTO, MarketInfoPreAnalysis.class, true, "majors", "infoFillPerson");
        marketInfoPreAnalysis.setCreateTime(LocalDateTime.now());
        marketInfoPreAnalysis.setMajors(StringUtils.join(marketInfoRecordTO.getMajors(), ","));
        marketInfoPreAnalysis.setInfoFillPerson(StringUtils.join(marketInfoRecordTO.getInfoFillPerson(), ","));
        marketInfoPreAnalysisSer.save(marketInfoPreAnalysis);
        return BeanTransform.copyProperties(marketInfoRecord, MarketInfoRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MarketInfoRecordBO editRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
       checkSeeIdentity();
        MarketInfoRecord marketInfoRecord = super.findById(marketInfoRecordTO.getId());
        LocalDate initiateDate = marketInfoRecord.getInitiateDate();
        Boolean effctiveInfo = marketInfoRecord.getEffctiveInfo();//是否为有效信息
        Boolean preliminaryAnaly = marketInfoRecord.getPreliminaryAnaly();//是否进行初步分析
        Boolean conversionBuissOpp = marketInfoRecord.getConversionBuissOpp();//是否转换商机
        Boolean conversionMarketFor = marketInfoRecord.getConversionMarketFor();//是否转换为市场招待
        Boolean conversionBussNegotia = marketInfoRecord.getConversionBussNegotia();//是否转换为商务洽谈
        if (StringUtils.isNotBlank(marketInfoRecordTO.getInitiateDate()) && initiateDate == null) {
            String name = positionDetailUserAPI.customRepPerson();//获取客户模块负责人
            if (StringUtils.isNotBlank(name)) {
                String email = internalContactsAPI.getEmail(name);
                //如果该乘车人邮箱存在发送邮件,否则就不发
                if (StringUtils.isNotBlank(email)) {
                    StringBuffer content = new StringBuffer();
                    content.append("客户模块负责人:您好!市场信息编号为:" + marketInfoRecord.getMarketInfoNum() + "的信息已在" + marketInfoRecord.getInfoCollectDate() + "收集成功,请在" + marketInfoRecord.getAgingStartTime() + "进行初步分析工作.谢谢!");
                    MessageTO messageTO = new MessageTO();
                    messageTO.setContent(content.toString());
                    messageTO.setTitle("市场信息初步分析");
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);

                    messageTO.setReceivers(new String[]{email});
                    messageAPI.send(messageTO);
                }
            }
        }
        marketInfoRecord = BeanTransform.copyProperties(marketInfoRecordTO, MarketInfoRecord.class, true);
        marketInfoRecord.setEffctiveInfo(effctiveInfo);
        marketInfoRecord.setPreliminaryAnaly(preliminaryAnaly);
        marketInfoRecord.setConversionBuissOpp(conversionBuissOpp);
        marketInfoRecord.setConversionMarketFor(conversionMarketFor);
        marketInfoRecord.setConversionBussNegotia(conversionBussNegotia);
        marketInfoRecord.setMajors(StringUtils.join(marketInfoRecordTO.getMajors(), ","));
        marketInfoRecord.setInfoFillPerson(StringUtils.join(marketInfoRecordTO.getInfoFillPerson(), ","));
        super.update(marketInfoRecord);
        MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO = new MarketInfoPreAnalysisDTO();
        marketInfoPreAnalysisDTO.getConditions().add(Restrict.eq("marketInfoNum", marketInfoRecordTO.getMarketInfoNum()));
        MarketInfoPreAnalysis marketInfoPreAnalysis = marketInfoPreAnalysisSer.findOne(marketInfoPreAnalysisDTO);
        if (marketInfoPreAnalysis != null) {
            Boolean effctiveInfo1 = marketInfoPreAnalysis.getEffctiveInfo();
            LocalDate preliminaryAnalyDate1 = marketInfoPreAnalysis.getPreliminaryAnalyDate();
            Boolean fundOperation = marketInfoPreAnalysis.getFundOperation();
            Boolean ableProvide = marketInfoPreAnalysis.getAbleProvide();
            Boolean partnerRisk = marketInfoPreAnalysis.getPartnerRisk();
            String riskDescribe = marketInfoPreAnalysis.getRiskDescribe();
            Boolean preliminaryAnaly1 = marketInfoPreAnalysis.getPreliminaryAnaly();
            Boolean conversionBuissOpp1 = marketInfoPreAnalysis.getConversionBuissOpp();
            Boolean conversionMarketFor1 = marketInfoPreAnalysis.getConversionMarketFor();
            Boolean conversionBussNegotia1 = marketInfoPreAnalysis.getConversionBussNegotia();
            String remark = marketInfoPreAnalysis.getRemark();
            LocalDateTime date = marketInfoPreAnalysis.getCreateTime();
            marketInfoPreAnalysis = BeanTransform.copyProperties(marketInfoRecordTO, MarketInfoPreAnalysis.class, true, "majors", "infoFillPerson");
            marketInfoPreAnalysis.setCreateTime(date);
            marketInfoPreAnalysis.setModifyTime(LocalDateTime.now());
            marketInfoPreAnalysis.setMajors(StringUtils.join(marketInfoRecordTO.getMajors(), ","));
            marketInfoPreAnalysis.setInfoFillPerson(StringUtils.join(marketInfoRecordTO.getInfoFillPerson(), ","));
            marketInfoPreAnalysis.setEffctiveInfo(effctiveInfo1);
            marketInfoPreAnalysis.setPreliminaryAnalyDate(preliminaryAnalyDate1);
            marketInfoPreAnalysis.setFundOperation(fundOperation);
            marketInfoPreAnalysis.setAbleProvide(ableProvide);
            marketInfoPreAnalysis.setPartnerRisk(partnerRisk);
            marketInfoPreAnalysis.setRiskDescribe(riskDescribe);
            marketInfoPreAnalysis.setPreliminaryAnaly(preliminaryAnaly1);
            marketInfoPreAnalysis.setConversionBuissOpp(conversionBuissOpp1);
            marketInfoPreAnalysis.setConversionMarketFor(conversionMarketFor1);
            marketInfoPreAnalysis.setConversionBussNegotia(conversionBussNegotia1);
            marketInfoPreAnalysis.setRemark(remark);
            marketInfoPreAnalysisSer.update(marketInfoPreAnalysis);
        }
        return BeanTransform.copyProperties(marketInfoRecord, MarketInfoRecordBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRecord(String id) throws SerException {
       checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public List<String> findallUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS) {
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<CustomerNameNumBO> getNameNum() throws SerException {
        List<CustomerNameNumBO> customerNameNumBOS = new ArrayList<>(0);
        if (moduleAPI.isCheck("customer")) {  //判断关联模块该模块是否被勾选
            customerNameNumBOS = customerBaseInfoAPI.findNameNum();
        }
        return customerNameNumBOS;
    }

    @Override
    public List<String> getCompetName() throws SerException {
        List<String> competName = new ArrayList<>(0);
        if (moduleAPI.isCheck("competitormanage")) {  //判断关联模块该模块是否被勾选
            competName = competitorAPI.findCompeName();
        }
        return competName;
    }

    @Override
    public List<String> findBussType() throws SerException {
        List<MarketInfoRecord> marketInfoRecordList = super.findAll();
        if (CollectionUtils.isEmpty(marketInfoRecordList)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MarketInfoRecord marketInfoRecord : marketInfoRecordList) {
            String bussType = marketInfoRecord.getBusinessType();
            if (StringUtils.isNotBlank(marketInfoRecord.getBusinessType())) {
                set.add(bussType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAreaByBussType(String bussType) throws SerException {
        MarketInfoRecordDTO marketInfoRecordDTO = new MarketInfoRecordDTO();
        marketInfoRecordDTO.getConditions().add(Restrict.eq("businessType", bussType));
        List<MarketInfoRecord> marketInfoRecordList = super.findByCis(marketInfoRecordDTO);
        if (CollectionUtils.isEmpty(marketInfoRecordList)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MarketInfoRecord marketInfoRecord : marketInfoRecordList) {
            String area = marketInfoRecord.getArea();
            if (StringUtils.isNotBlank(marketInfoRecord.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
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
    public List<SummationBO> summaDay(String summDate) throws SerException {
        checkSeeIdentity();
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationAreaBO> summaDayByArea(String summDate) throws SerException {
       checkSeeIdentity();
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        return totalMethodArea(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
       checkSeeIdentity();
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
    public List<SummationAreaBO> summaWeekByArea(Integer year, Integer month, Integer week) throws SerException {
       checkSeeIdentity();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        return totalMethodArea(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        checkSeeIdentity();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationAreaBO> summaMonthByArea(Integer year, Integer month) throws SerException {
        checkSeeIdentity();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return totalMethodArea(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        checkSeeIdentity();
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        return totalMethod(date[0], date[1]);
    }

    @Override
    public List<SummationAreaBO> summaQuarterByArea(Integer year, Integer quarter) throws SerException {
        checkSeeIdentity();
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        return totalMethodArea(date[0], date[1]);
    }

    @Override
    public List<SummationBO> summaYear(Integer year) throws SerException {
        checkSeeIdentity();
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<SummationAreaBO> summaYearByArea(Integer year) throws SerException {
       checkSeeIdentity();
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return totalMethodArea(startDate, endDate);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
       checkSeeIdentity();
        if (StringUtils.isBlank(endDate)) {
            endDate = LocalDate.now().toString();
        }
        List<SummationBO> summationBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate  < '" + endDate + "') a, ");
                sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                sb.append("(SELECT sum(estimateTransferAmount) AS estTransferAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "') i, ");
                sb.append("(SELECT sum(estimateMarketLoss) AS estMarketLossAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "') j, ");
                sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "') s ");
                sb.append(" ) ");
                List<Object> objects = super.findBySql(sb.toString());
                Object[] obj = (Object[]) objects.get(0);
                Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                Double estTransferAmount = Double.parseDouble(String.valueOf(obj[9] == null ? 0 : obj[9]));//预估转正金额
                Double estMarketLossAmount = Double.parseDouble(String.valueOf(obj[10] == null ? 0 : obj[10]));//预估市场亏损金额
                Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[11]));//已初步分析数
                Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[12]));//转换为市场招待数
                Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[13]));//转换为商务洽谈数
                int lengths = 0;
                if (null != obj[14]) {
                    String competitorsName = String.valueOf(obj[14]);//竞争对象
                    String[] competitorsName_str = competitorsName.split(",");
                    lengths = competitorsName_str.length;

                }

                SummationBO summationBO = new SummationBO();
                summationBO.setBusinessType(bussType);//业务类型
                summationBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                summationBO.setBiddingNum(biddingNum);//招投标数量
                summationBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                summationBO.setIntroduceNum(introduceNum);//介绍数量
                summationBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                summationBO.setVilidityNum(vilidityNum);//有效信息
                summationBO.setInvilidNum(invilidNum);//无效信息
                summationBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                summationBO.setCompetingNum(lengths);//竞争对象数量
                summationBO.setBussOppNum(bussOppNum);//已转换商机
                summationBO.setEstTransferAmount(estTransferAmount);//预估转正金额
                summationBO.setEstMarketLossAmount(estMarketLossAmount);//预估市场亏损金额
                summationBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                summationBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                summationBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                summationBOS.add(summationBO);
            }
        }
        return summationBOS;
    }

    @Override
    public List<SummationAreaBO> summaTotalByArea(String endDate) throws SerException {
       checkSeeIdentity();
        if (StringUtils.isBlank(endDate)) {
            endDate = LocalDate.now().toString();
        }
        List<SummationAreaBO> summationAreaBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                List<String> areas = findAreaByBussType(bussType);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                        sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "') a, ");
                        sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                        sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                        sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                        sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                        sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                        sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                        sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                        sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                        sb.append("(SELECT sum(estimateTransferAmount) AS estTransferAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "') i, ");
                        sb.append("(SELECT sum(estimateMarketLoss) AS estMarketLossAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "') j, ");
                        sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                        sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                        sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate < '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                        sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate < '" + endDate + "') s ");
                        sb.append(" ) ");
                        List<Object> objects = super.findBySql(sb.toString());
                        Object[] obj = (Object[]) objects.get(0);
                        Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                        Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                        Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                        Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                        Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                        Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                        Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                        Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                        Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                        Double estTransferAmount = Double.parseDouble(String.valueOf(obj[9] == null ? 0 : obj[9]));//预估转正金额
                        Double estMarketLossAmount = Double.parseDouble(String.valueOf(obj[10] == null ? 0 : obj[10]));//预估市场亏损金额
                        Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[11]));//已初步分析数
                        Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[12]));//转换为市场招待数
                        Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[13]));//转换为商务洽谈数
                        int lengths = 0;
                        if (null != obj[14]) {
                            String competitorsName = String.valueOf(obj[14]);//竞争对象
                            String[] competitorsName_str = competitorsName.split(",");
                            lengths = competitorsName_str.length;

                        }

                        SummationAreaBO summationAreaBO = new SummationAreaBO();
                        summationAreaBO.setBusinessType(bussType);//业务类型
                        summationAreaBO.setArea(area);//地区
                        summationAreaBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                        summationAreaBO.setBiddingNum(biddingNum);//招投标数量
                        summationAreaBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                        summationAreaBO.setIntroduceNum(introduceNum);//介绍数量
                        summationAreaBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                        summationAreaBO.setVilidityNum(vilidityNum);//有效信息
                        summationAreaBO.setInvilidNum(invilidNum);//无效信息
                        summationAreaBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                        summationAreaBO.setCompetingNum(lengths);//竞争对象数量
                        summationAreaBO.setBussOppNum(bussOppNum);//已转换商机
                        summationAreaBO.setEstTransferAmount(estTransferAmount);//预估转正金额
                        summationAreaBO.setEstMarketLossAmount(estMarketLossAmount);//预估市场亏损金额
                        summationAreaBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                        summationAreaBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                        summationAreaBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                        summationAreaBOS.add(summationAreaBO);
                    }
                }
            }
        }
        return summationAreaBOS;
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

    //汇总总方法
    public List<SummationBO> totalMethod(String startDate, String endDate) throws SerException {
        List<SummationBO> summationBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') a, ");
                sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                sb.append("(SELECT sum(estimateTransferAmount) AS estTransferAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') i, ");
                sb.append("(SELECT sum(estimateMarketLoss) AS estMarketLossAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') j, ");
                sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') s ");
                sb.append(" ) ");
                List<Object> objects = super.findBySql(sb.toString());
                Object[] obj = (Object[]) objects.get(0);
                Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                Double estTransferAmount = Double.parseDouble(String.valueOf(obj[9] == null ? 0 : obj[9]));//预估转正金额
                Double estMarketLossAmount = Double.parseDouble(String.valueOf(obj[10] == null ? 0 : obj[10]));//预估市场亏损金额
                Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[11]));//已初步分析数
                Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[12]));//转换为市场招待数
                Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[13]));//转换为商务洽谈数
                int lengths = 0;
                if (null != obj[14]) {
                    String competitorsName = String.valueOf(obj[14]);//竞争对象
                    String[] competitorsName_str = competitorsName.split(",");
                    lengths = competitorsName_str.length;

                }
                SummationBO summationBO = new SummationBO();
                summationBO.setBusinessType(bussType);//业务类型
                summationBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                summationBO.setBiddingNum(biddingNum);//招投标数量
                summationBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                summationBO.setIntroduceNum(introduceNum);//介绍数量
                summationBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                summationBO.setVilidityNum(vilidityNum);//有效信息
                summationBO.setInvilidNum(invilidNum);//无效信息
                summationBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                summationBO.setCompetingNum(lengths);//竞争对象数量
                summationBO.setBussOppNum(bussOppNum);//已转换商机
                summationBO.setEstTransferAmount(estTransferAmount);//预估转正金额
                summationBO.setEstMarketLossAmount(estMarketLossAmount);//预估市场亏损金额
                summationBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                summationBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                summationBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                summationBOS.add(summationBO);
            }
        }
        return summationBOS;
    }

    //汇总总方法加个地区
    public List<SummationAreaBO> totalMethodArea(String startDate, String endDate) throws SerException {
        List<SummationAreaBO> summationAreaBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                List<String> areas = findAreaByBussType(bussType);
                if (areas != null && areas.size() > 0) {
                    for (String area : areas) {
                        StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                        sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') a, ");
                        sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                        sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                        sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                        sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                        sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                        sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                        sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                        sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                        sb.append("(SELECT sum(estimateTransferAmount) AS estTransferAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') i, ");
                        sb.append("(SELECT sum(estimateMarketLoss) AS estMarketLossAmount FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') j, ");
                        sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                        sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                        sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                        sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND area = '" + area + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') s ");
                        sb.append(" ) ");
                        List<Object> objects = super.findBySql(sb.toString());
                        Object[] obj = (Object[]) objects.get(0);
                        Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                        Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                        Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                        Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                        Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                        Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                        Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                        Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                        Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                        Double estTransferAmount = Double.parseDouble(String.valueOf(obj[9] == null ? 0 : obj[9]));//预估转正金额
                        Double estMarketLossAmount = Double.parseDouble(String.valueOf(obj[10] == null ? 0 : obj[10]));//预估市场亏损金额
                        Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[11]));//已初步分析数
                        Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[12]));//转换为市场招待数
                        Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[13]));//转换为商务洽谈数
                        int lengths = 0;
                        if (null != obj[14]) {
                            String competitorsName = String.valueOf(obj[14]);//竞争对象
                            String[] competitorsName_str = competitorsName.split(",");
                            lengths = competitorsName_str.length;

                        }

                        SummationAreaBO summationAreaBO = new SummationAreaBO();
                        summationAreaBO.setBusinessType(bussType);//业务类型
                        summationAreaBO.setArea(area);//地区
                        summationAreaBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                        summationAreaBO.setBiddingNum(biddingNum);//招投标数量
                        summationAreaBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                        summationAreaBO.setIntroduceNum(introduceNum);//介绍数量
                        summationAreaBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                        summationAreaBO.setVilidityNum(vilidityNum);//有效信息
                        summationAreaBO.setInvilidNum(invilidNum);//无效信息
                        summationAreaBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                        summationAreaBO.setCompetingNum(lengths);//竞争对象数量
                        summationAreaBO.setBussOppNum(bussOppNum);//已转换商机
                        summationAreaBO.setEstTransferAmount(estTransferAmount);//预估转正金额
                        summationAreaBO.setEstMarketLossAmount(estMarketLossAmount);//预估市场亏损金额
                        summationAreaBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                        summationAreaBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                        summationAreaBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                        summationAreaBOS.add(summationAreaBO);
                    }
                }
            }
        }
        return summationAreaBOS;
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
       checkSeeIdentity();
        if (StringUtils.isBlank(summDate)) {
            summDate = LocalDate.now().toString();
        }
        String startDate = summDate;
        String endDate = summDate;
        String text_1 = "市场信息日统计(" + startDate + ")";
        return figureShowTotalMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
       checkSeeIdentity();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "市场信息周统计(" + startDate + "--" + endDate + ")";
        return figureShowTotalMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
       checkSeeIdentity();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "市场信息月统计(" + startDate + "--" + endDate + ")";
        return figureShowTotalMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
       checkSeeIdentity();
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        String text_1 = "市场信息季度统计(" + date[0] + "--" + date[1] + ")";
        return figureShowTotalMethod(date[0], date[1], text_1);
    }

    @Override
    public OptionBO figureShowYear(Integer year) throws SerException {
       checkSeeIdentity();
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "市场信息年度统计(" + startDate + "--" + endDate + ")";
        return figureShowTotalMethod(startDate, endDate, text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
      checkSeeIdentity();
        List<FigureShowDataBO> figureShowDataBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "') a, ");
                sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate < '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate < '" + endDate + "') s ");
                sb.append(" ) ");
                List<Object> objects = super.findBySql(sb.toString());
                Object[] obj = (Object[]) objects.get(0);
                Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[9]));//已初步分析数
                Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[10]));//转换为市场招待数
                Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[11]));//转换为商务洽谈数
                int lengths = 0;
                if (null != obj[12]) {
                    String competitorsName = String.valueOf(obj[12]);//竞争对象
                    String[] competitorsName_str = competitorsName.split(",");
                    lengths = competitorsName_str.length;

                }

                FigureShowDataBO figureShowDataBO = new FigureShowDataBO();
                figureShowDataBO.setBusinessType(bussType);//业务类型
                figureShowDataBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                figureShowDataBO.setBiddingNum(biddingNum);//招投标数量
                figureShowDataBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                figureShowDataBO.setIntroduceNum(introduceNum);//介绍数量
                figureShowDataBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                figureShowDataBO.setVilidityNum(vilidityNum);//有效信息
                figureShowDataBO.setInvilidNum(invilidNum);//无效信息
                figureShowDataBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                figureShowDataBO.setCompetingNum(lengths);//竞争对象数量
                figureShowDataBO.setBussOppNum(bussOppNum);//已转换商机
                figureShowDataBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                figureShowDataBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                figureShowDataBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                figureShowDataBOS.add(figureShowDataBO);
            }
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText("市场信息累计统计(累计)");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"现有市场信息", "招投标数量",
                "市场招待获取的信息数量", "介绍数量", "商务洽谈获取的市场信息数量",
                "有效信息", "无效信息", "与商机关联的客户数量", "竞争对象数量", "已转换商机", "已初步分析数",
                "转换为市场招待数", "转换为商务洽谈数"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
//        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureShowDataBOS != null && figureShowDataBOS.size() > 0) {
            List<Integer> exisMarketInfoNums = new ArrayList<>();
            List<Integer> biddingNums = new ArrayList<>();
            List<Integer> marketForNums = new ArrayList<>();
            List<Integer> introduceNums = new ArrayList<>();
            List<Integer> bussNegotiaNums = new ArrayList<>();
            List<Integer> vilidityNums = new ArrayList<>();
            List<Integer> invilidNums = new ArrayList<>();
            List<Integer> bussOppCustomNums = new ArrayList<>();
            List<Integer> competingNums = new ArrayList<>();
            List<Integer> bussOppNums = new ArrayList<>();
            List<Integer> prelimAnalyNums = new ArrayList<>();
            List<Integer> convertMarketNums = new ArrayList<>();
            List<Integer> convertBussNegotiaNums = new ArrayList<>();
            for (FigureShowDataBO figureShowDataBO : figureShowDataBOS) {
                text_list_3.add(figureShowDataBO.getBusinessType());

                //柱状图数据
                exisMarketInfoNums.add(figureShowDataBO.getExisMarketInfoNum());
                biddingNums.add(figureShowDataBO.getBiddingNum());
                marketForNums.add(figureShowDataBO.getMarketForNum());
                introduceNums.add(figureShowDataBO.getIntroduceNum());
                bussNegotiaNums.add(figureShowDataBO.getBussNegotiaNum());
                vilidityNums.add(figureShowDataBO.getVilidityNum());
                invilidNums.add(figureShowDataBO.getInvilidNum());
                bussOppCustomNums.add(figureShowDataBO.getBussOppCustomNum());
                competingNums.add(figureShowDataBO.getCompetingNum());
                bussOppNums.add(figureShowDataBO.getBussOppNum());
                prelimAnalyNums.add(figureShowDataBO.getPrelimAnalyNum());
                convertMarketNums.add(figureShowDataBO.getConvertMarketNum());
                convertBussNegotiaNums.add(figureShowDataBO.getConvertBussNegotiaNum());
            }
            String[] dataNames = new String[]{"现有市场信息", "招投标数量",
                    "市场招待获取的信息数量", "介绍数量", "商务洽谈获取的市场信息数量",
                    "有效信息", "无效信息", "与商机关联的客户数量", "竞争对象数量", "已转换商机", "已初步分析数",
                    "转换为市场招待数", "转换为商务洽谈数"};
            List<List<Integer>> datas = new ArrayList<>();
            datas.add(exisMarketInfoNums);
            datas.add(biddingNums);
            datas.add(marketForNums);
            datas.add(introduceNums);
            datas.add(bussNegotiaNums);
            datas.add(vilidityNums);
            datas.add(invilidNums);
            datas.add(bussOppCustomNums);
            datas.add(competingNums);
            datas.add(bussOppNums);
            datas.add(prelimAnalyNums);
            datas.add(convertMarketNums);
            datas.add(convertBussNegotiaNums);
            for (int i = 0; i < datas.size(); i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(dataNames[i]);
                seriesBO.setType("bar");
                Integer[] nums = new Integer[]{datas.get(i).size()};
                nums = datas.get(i).toArray(nums);
                seriesBO.setData(nums);
                seriesBOList.add(seriesBO);
            }

        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

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

    public OptionBO figureShowTotalMethod(String startDate, String endDate, String text_1) throws SerException {
        List<FigureShowDataBO> figureShowDataBOS = new ArrayList<>();
        List<String> bussTypeList = findBussType();
        if (bussTypeList != null && bussTypeList.size() > 0) {
            for (String bussType : bussTypeList) {
                StringBuilder sb = new StringBuilder("SELECT * FROM ( ");
                sb.append("(SELECT count(*) AS exisMarketInfoNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') a, ");
                sb.append("(SELECT count(*) AS biddingNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.TENDER.getCode() + ") b, ");
                sb.append("(SELECT count(*) AS marketForNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.MARKETFOR.getCode() + ") c, ");
                sb.append("(SELECT count(*) AS introduceNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.INTRODUCE.getCode() + ") d, ");
                sb.append("(SELECT count(*) AS bussNegotiaNum FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND sourceInfo = " + SourceInfo.BUSSNEGOTIATION.getCode() + ") e, ");
                sb.append("(SELECT count(*) AS vilidityNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 1) f, ");
                sb.append("(SELECT count(*) AS invilidNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_effctiveInfo = 0) g, ");
                sb.append("(SELECT count(*) AS bussOppCustomNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1) t, ");
                sb.append("(SELECT count(*) AS bussOppNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionBuissOpp = 1 AND internalProjectName IS NOT NULL) h, ");
                sb.append("(SELECT count(*) AS prelimAnalyNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_preliminaryAnaly = 1) l, ");
                sb.append("(SELECT count(*) AS convertMarketNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND is_conversionMarketFor = 1) p, ");
                sb.append("(SELECT count(*) AS convertBussNegotiaNum FROM market_marketinfopreanalysis WHERE businessType = '" + bussType + "' AND preliminaryAnalyDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND  is_conversionBussNegotia = 1) m,");
                sb.append("(SELECT GROUP_CONCAT(competitorsName) AS competitorsName FROM market_marketinforecord WHERE businessType = '" + bussType + "' AND infoCollectDate BETWEEN '" + startDate + "' AND '" + endDate + "') s ");
                sb.append(" ) ");
                List<Object> objects = super.findBySql(sb.toString());
                Object[] obj = (Object[]) objects.get(0);
                Integer exisMarketInfoNum = Integer.parseInt(String.valueOf(obj[0]));//现有市场信息
                Integer biddingNum = Integer.parseInt(String.valueOf(obj[1]));//招投标数量
                Integer marketForNum = Integer.parseInt(String.valueOf(obj[2]));//市场招待获取的信息数量
                Integer introduceNum = Integer.parseInt(String.valueOf(obj[3]));//介绍数量
                Integer bussNegotiaNum = Integer.parseInt(String.valueOf(obj[4]));//商务洽谈获取的市场信息数量
                Integer vilidityNum = Integer.parseInt(String.valueOf(obj[5]));//有效信息
                Integer invilidNum = Integer.parseInt(String.valueOf(obj[6]));//无效信息
                Integer bussOppCustomNum = Integer.parseInt(String.valueOf(obj[7]));//与商机关联的客户数量
                Integer bussOppNum = Integer.parseInt(String.valueOf(obj[8]));//已转换商机
                Integer prelimAnalyNum = Integer.parseInt(String.valueOf(obj[9]));//已初步分析数
                Integer convertMarketNum = Integer.parseInt(String.valueOf(obj[10]));//转换为市场招待数
                Integer convertBussNegotiaNum = Integer.parseInt(String.valueOf(obj[11]));//转换为商务洽谈数
                int lengths = 0;
                if (null != obj[12]) {
                    String competitorsName = String.valueOf(obj[12]);//竞争对象
                    String[] competitorsName_str = competitorsName.split(",");
                    lengths = competitorsName_str.length;

                }

                FigureShowDataBO figureShowDataBO = new FigureShowDataBO();
                figureShowDataBO.setBusinessType(bussType);//业务类型
                figureShowDataBO.setExisMarketInfoNum(exisMarketInfoNum);//现有市场信息
                figureShowDataBO.setBiddingNum(biddingNum);//招投标数量
                figureShowDataBO.setMarketForNum(marketForNum);//市场招待获取的信息数量
                figureShowDataBO.setIntroduceNum(introduceNum);//介绍数量
                figureShowDataBO.setBussNegotiaNum(bussNegotiaNum);//商务洽谈获取的市场信息数量
                figureShowDataBO.setVilidityNum(vilidityNum);//有效信息
                figureShowDataBO.setInvilidNum(invilidNum);//无效信息
                figureShowDataBO.setBussOppCustomNum(bussOppCustomNum);//与商机关联的客户数量
                figureShowDataBO.setCompetingNum(lengths);//竞争对象数量
                figureShowDataBO.setBussOppNum(bussOppNum);//已转换商机
                figureShowDataBO.setPrelimAnalyNum(prelimAnalyNum);//已初步分析数
                figureShowDataBO.setConvertMarketNum(convertMarketNum);//转换为市场招待数
                figureShowDataBO.setConvertBussNegotiaNum(convertBussNegotiaNum);//转换为商务洽谈数
                figureShowDataBOS.add(figureShowDataBO);
            }
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"现有市场信息", "招投标数量",
                "市场招待获取的信息数量", "介绍数量", "商务洽谈获取的市场信息数量",
                "有效信息", "无效信息", "与商机关联的客户数量", "竞争对象数量", "已转换商机", "已初步分析数",
                "转换为市场招待数", "转换为商务洽谈数"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
//        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureShowDataBOS != null && figureShowDataBOS.size() > 0) {
            List<Integer> exisMarketInfoNums = new ArrayList<>();
            List<Integer> biddingNums = new ArrayList<>();
            List<Integer> marketForNums = new ArrayList<>();
            List<Integer> introduceNums = new ArrayList<>();
            List<Integer> bussNegotiaNums = new ArrayList<>();
            List<Integer> vilidityNums = new ArrayList<>();
            List<Integer> invilidNums = new ArrayList<>();
            List<Integer> bussOppCustomNums = new ArrayList<>();
            List<Integer> competingNums = new ArrayList<>();
            List<Integer> bussOppNums = new ArrayList<>();
            List<Integer> prelimAnalyNums = new ArrayList<>();
            List<Integer> convertMarketNums = new ArrayList<>();
            List<Integer> convertBussNegotiaNums = new ArrayList<>();
            for (FigureShowDataBO figureShowDataBO : figureShowDataBOS) {
                text_list_3.add(figureShowDataBO.getBusinessType());

                //柱状图数据
                exisMarketInfoNums.add(figureShowDataBO.getExisMarketInfoNum());
                biddingNums.add(figureShowDataBO.getBiddingNum());
                marketForNums.add(figureShowDataBO.getMarketForNum());
                introduceNums.add(figureShowDataBO.getIntroduceNum());
                bussNegotiaNums.add(figureShowDataBO.getBussNegotiaNum());
                vilidityNums.add(figureShowDataBO.getVilidityNum());
                invilidNums.add(figureShowDataBO.getInvilidNum());
                bussOppCustomNums.add(figureShowDataBO.getBussOppCustomNum());
                competingNums.add(figureShowDataBO.getCompetingNum());
                bussOppNums.add(figureShowDataBO.getBussOppNum());
                prelimAnalyNums.add(figureShowDataBO.getPrelimAnalyNum());
                convertMarketNums.add(figureShowDataBO.getConvertMarketNum());
                convertBussNegotiaNums.add(figureShowDataBO.getConvertBussNegotiaNum());
            }
            String[] dataNames = new String[]{"现有市场信息", "招投标数量",
                    "市场招待获取的信息数量", "介绍数量", "商务洽谈获取的市场信息数量",
                    "有效信息", "无效信息", "与商机关联的客户数量", "竞争对象数量", "已转换商机", "已初步分析数",
                    "转换为市场招待数", "转换为商务洽谈数"};
            List<List<Integer>> datas = new ArrayList<>();
            datas.add(exisMarketInfoNums);
            datas.add(biddingNums);
            datas.add(marketForNums);
            datas.add(introduceNums);
            datas.add(bussNegotiaNums);
            datas.add(vilidityNums);
            datas.add(invilidNums);
            datas.add(bussOppCustomNums);
            datas.add(competingNums);
            datas.add(bussOppNums);
            datas.add(prelimAnalyNums);
            datas.add(convertMarketNums);
            datas.add(convertBussNegotiaNums);
            for (int i = 0; i < datas.size(); i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(dataNames[i]);
                seriesBO.setType("bar");
                Integer[] nums = new Integer[]{datas.get(i).size()};
                nums = datas.get(i).toArray(nums);
                seriesBO.setData(nums);
                seriesBOList.add(seriesBO);
            }

        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

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