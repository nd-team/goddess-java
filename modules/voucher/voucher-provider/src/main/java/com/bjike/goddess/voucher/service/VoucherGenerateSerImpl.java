package com.bjike.goddess.voucher.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.api.BaseParameterAPI;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.api.InitDateEntryAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.financeinit.bo.InitDateEntryBO;
import com.bjike.goddess.financeinit.bo.SecondSubjectDataBO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.*;
import com.bjike.goddess.voucher.dto.*;
import com.bjike.goddess.voucher.entity.SubjectCollect;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.entity.VoucherTotal;
import com.bjike.goddess.voucher.enums.*;
import com.bjike.goddess.voucher.excel.*;
import com.bjike.goddess.voucher.to.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 记账凭证生成业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "voucherSerCache")
@Service
public class VoucherGenerateSerImpl extends ServiceImpl<VoucherGenerate, VoucherGenerateDTO> implements VoucherGenerateSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private VoucherTotalSer voucherTotalSer;
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;
    @Autowired
    private CategoryAPI categoryAPI;
    @Autowired
    private VoucherPermissionSer voucherPermissionSer;
    @Autowired
    private BaseParameterAPI baseParameterAPI;
    @Autowired
    private InitDateEntryAPI initDateEntryAPI;


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
            flag = voucherPermissionSer.getCusPermission("1");
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
            flag = voucherPermissionSer.busCusPermission("2");
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
        obj.setName("voucherGenerate");
        obj.setDescribesion("记账凭证生成设置");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        obj = new SonPermissionObject();
        obj.setName("voucherAudit");
        obj.setDescribesion("记账凭证审核");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("voucherHasAudit");
        obj.setDescribesion("已审核凭证");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("voucherPassAccount");
        obj.setDescribesion("已过帐");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("voucherPayAccount");
        obj.setDescribesion("结帐记录");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("voucherRecord");
        obj.setDescribesion("记账凭证记录");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("subjectCollect");
        obj.setDescribesion("科目汇总表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public List<SonPermissionObject> sonPermissionAccount() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("account");
        obj.setDescribesion("明细账");
        if (flagSeeSign || flagAddSign) {
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
            case SPLIT:
                flag = guideAddIdentity();
                break;
            case PASSACCOUNT:
                flag = guideAddIdentity();
                break;
            case ANTIAUDIT:
                flag = guideAddIdentity();
                break;
            case ANTIPASSACCOUNT:
                flag = guideAddIdentity();
                break;
            case PAYACCOUNT:
                flag = guideAddIdentity();
                break;
            case ANTIPAYACCOUNT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public VoucherGenerateBO getById(String id) throws SerException {
        /*VoucherGenerate vg = super.findById(id);
        VoucherGenerateBO bo = BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
        bo.setFirstSubjects(Arrays.asList(bo.getFirstSubject()));
        bo.setSecondSubjects(Arrays.asList(bo.getSecondSubject()));
        bo.setThirdSubjects(Arrays.asList(bo.getThirdSubject()));
        bo.setLoanMoneys(Arrays.asList(bo.getLoanMoney()));
        bo.setBorrowMoneys(Arrays.asList(bo.getBorrowMoney()));

        VoucherTotal vt = voucherTotalSer.findById(vg.getTotalId());
        bo.setMoneyTotal(vt.getMoney());*/

        String colums = "id, voucherWord, voucherNum, voucherDate, firstSubject, secondSubject, thirdSubject" +
                ", ifnull(borrowMoney, 0), ifnull(loanMoney, 0), sumary, source, area, projectName, projectGroup, ticketer, ticketNum, extraFile, " +
                "auditor, auditStatus, transferStatus, checkStatus, totalId, uId";
        StringBuffer sql = new StringBuffer();
        sql.append("select " + colums + " from voucher_vouchergenerate where uId = '" + id + "'");
        String[] fields = {"id", "voucherWord", "voucherNum", "voucherDate", "firstSubject", "secondSubject", "thirdSubject"
                , "borrowMoney", "loanMoney", "sumary", "source", "area", "projectName", "projectGroup", "ticketer", "ticketNum", "extraFile", "auditor",
                "auditStatus", "transferStatus", "checkStatus", "totalId", "uId"};
        List<VoucherGenerate> list = super.findBySql(sql.toString(), VoucherGenerate.class, fields);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
        for (VoucherGenerateBO str : listBO) {
            VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
            if (vt != null) {
                str.setMoneyTotal(vt.getMoney());
            }
        }
        List<VoucherGenerateBO> listBOs = convertVoucher(listBO, null);

        return listBOs == null ? null : listBOs.get(0);

    }

    @Override
    public VoucherGenerateBO getByIdCJh(String id) throws SerException {
        VoucherGenerate vg = super.findById(id);
        if (vg == null) {
            return null;
        }
        VoucherGenerateBO bo = BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
        bo.setFirstSubjects(Arrays.asList(bo.getFirstSubject()));
        bo.setSecondSubjects(Arrays.asList(bo.getSecondSubject()));
        bo.setThirdSubjects(Arrays.asList(bo.getThirdSubject()));
        bo.setLoanMoneys(Arrays.asList(bo.getLoanMoney()));
        bo.setBorrowMoneys(Arrays.asList(bo.getBorrowMoney()));

        VoucherTotal vt = voucherTotalSer.findById(vg.getTotalId());
        bo.setMoneyTotal(vt.getMoney());

        return bo;

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void antiCheckAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
       /* List<VoucherGenerate> list = new ArrayList<>(0);
        for (String id : voucherGenerateTO.getIds()) {
            VoucherGenerate vg = super.findById(id);
            vg.setCheckStatus(CheckStatus.NONE);
            vg.setModifyTime(LocalDateTime.now());
            list.add(vg);
        }
        super.update(list);*/

        //@see 修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());
        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set checkStatus = 0, modifyTime = NOW() where " +
                " uId in(" + uIds + ")");
        super.executeSql(sql.toString());
    }

    @Override
    public List<VoucherGenerateBO> findCkRecordByTime(String month, Integer quart, String year) throws SerException {
        String startTime = getStartTime(month, quart, year, true);
        String endTime = getStartTime(month, quart, year, false);
        String aa[] = new String[2];
        aa[0] = startTime;
        aa[1] = endTime;
        String fields[] = new String[]{"id"};
        StringBuilder sql = new StringBuilder(" select id from voucher_vouchergenerate ");
        sql.append(" where voucherDate between '" + startTime + "' ");
        sql.append(" and '" + endTime + "' ");
        List<VoucherGenerate> ids = super.findBySql(sql.toString(), VoucherGenerate.class, fields);
        List<VoucherGenerate> list = new ArrayList<>(0);
        for (VoucherGenerate voucherGenerate : ids) {
            VoucherGenerate entity = super.findById(voucherGenerate.getId());
            list.add(entity);
        }
//        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
//        voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", aa));
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class, false);
    }

    private String getStartTime(String month, Integer quart, String year, Boolean tar) throws SerException {
        if (null != quart && quart > 4 || null != quart && quart <= 0) {
            throw new SerException("季度错误");
        }
        String startTime = "";
        String endTime = "";
        if (StringUtils.isBlank(year)) {
            year = String.valueOf(LocalDate.now().getYear());
        }
        if (StringUtils.isBlank(month) && quart == null) {
            month = String.valueOf(LocalDate.now().getMonth().getValue());
//            startTime = year + "-" + month + "-01";
//            endTime = findEndDayOfMonth(startTime);
            startTime = year + "-01-01";
            endTime = year + "-12-31";
        } else if (StringUtils.isBlank(month) && quart != null) {
            String strMonth1 = "";
            String strMonth2 = "";
            int month1 = (quart - 1) * 3 + 1;
            int month2 = (quart - 1) * 3 + 3;
            if (month1 < 10) {
                strMonth1 = "0" + month1;
            } else {
                strMonth1 = month1 + "";
            }
            if (month2 < 10) {
                strMonth2 = "0" + month2;
            } else {
                strMonth2 = month2 + "";
            }
            startTime = year + "-" + strMonth1 + "-01";
            endTime = year + "-" + strMonth2 + "-01";
            endTime = findEndDayOfMonth(endTime);
        } else if (StringUtils.isNotBlank(month)) {
            startTime = year + "-" + month + "-01";
            endTime = findEndDayOfMonth(startTime);
        }
        if (tar) {
            return startTime;
        } else {
            return endTime;
        }
    }

    @Override
    public List<AnalysisBO> analysis(AnalysisTO to) throws SerException {
        if (StringUtils.isBlank(to.getBrow())) {
            if ("偏差分析".equals(to.getAnalysis())) {
                to.setBrow("1000");
            }
            if ("百分比分析".equals(to.getAnalysis())) {
                to.setBrow("30");
            }
            if ("增长率分析".equals(to.getAnalysis())) {
                to.setBrow("30");
            }
        } else {
            if ("百分比分析".equals(to.getAnalysis())) {
                int index = to.getBrow().indexOf("%");
                if (index != 1) {
                    throw new SerException("百分比分析的发生预警额应包含%号,如30%");
                }
                int value = Integer.valueOf(to.getBrow().substring(0, to.getBrow().indexOf("%")));
                if (value > 100 || value < 0) {
                    throw new SerException("百分比分析的发生预警额不正确");
                }
                to.setBrow(to.getBrow().substring(0, to.getBrow().indexOf("%")));
            }
            if ("增长率分析".equals(to.getAnalysis())) {
                int index = to.getBrow().indexOf("%");
                if (index != 1) {
                    throw new SerException("增长率分析的发生预警额应包含%号,如30%");
                }
                int value = Integer.valueOf(to.getBrow().substring(0, to.getBrow().indexOf("%")));
                if (value > 100 || value < 0) {
                    throw new SerException("增长率分析的发生预警额不正确");
                }
                to.setBrow(to.getBrow().substring(0, to.getBrow().indexOf("%")));
            }
        }
        String month = to.getYear() + "-" + (to.getMonth() > 9 ? to.getMonth() : "0" + to.getMonth()) + "-01";
//        String month = to.getMonth() > 9 ? to.getMonth() + "" : "0" + to.getMonth();
        //得到偏差分析
        if ("偏差分析".equals(to.getAnalysis())) {
            return getVarianceAnalysisBOs(to, month);
        }
        //得到百分比分析
        if ("百分比分析".equals(to.getAnalysis())) {
            return getPercentageAnalysisBOs(to, month);
        }
        //得到增长率分析
        if ("增长率分析".equals(to.getAnalysis())) {
            return getVarianceRatioAnalysisBOs(to, month);
        }
        return null;
    }

    @Override
    public OptionBO ctReSubHistogram(VoucherChartDTO dto) throws SerException {
        String[] fields = new String[]{"borrowMoney", "loanMoney", "voucherDate"};

        StringBuilder sql = new StringBuilder("select borrowMoney as borrowMoney, loanMoney as loanMoney, voucherDate from voucher_vouchergenerate ");
        sql.append(" WHERE 1 = 1 ");
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            sql.append(" and voucherDate BETWEEN '" + dto.getStartTime() + "' and '" + dto.getEndTime() + "' ");
        }
        if (StringUtils.isNotBlank(dto.getFirstSubject())) {
            sql.append(" and firstSubject = '" + dto.getFirstSubject() + "'");
        }
        if (StringUtils.isNotBlank(dto.getSecondSubject())) {
            sql.append(" and secondSubject = '" + dto.getSecondSubject() + "'");
        }
        if (StringUtils.isNotBlank(dto.getThirdSubject())) {
            sql.append(" and thirdSubject = '" + dto.getThirdSubject() + "'");
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            sql.append(" and area = '" + dto.getArea() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            sql.append(" and projectName = '" + dto.getProjectName() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            sql.append(" and projectGroup = '" + dto.getProjectGroup() + "'");
        }
        sql.append(" order by voucherDate desc");
        List<HistogramBO> bos = super.findBySql(sql.toString(), HistogramBO.class, fields);

        int len = bos.size();
        for (int i = 0; i < len; i++) {
            int year = bos.get(i).getVoucherDate().getYear();
            int month = bos.get(i).getVoucherDate().getMonthValue();
            for (int j = 1; j < len; j++) {
                int year1 = bos.get(j).getVoucherDate().getYear();
                int month1 = bos.get(j).getVoucherDate().getMonthValue();
                if (year == year1 && month == month1) {
                    bos.get(i).setBorrowMoney(bos.get(i).getBorrowMoney() + bos.get(j).getBorrowMoney());
                    bos.get(i).setLoanMoney(bos.get(i).getLoanMoney() + bos.get(j).getLoanMoney());
                    if (i != j) {
                        bos.remove(j);
                        len = len - 1;
                        j--;
                    }
                }
            }
        }

        for (HistogramBO bo : bos) {
            bo.setMonth(bo.getVoucherDate().getYear() +
                    (bo.getVoucherDate().getMonthValue() > 9 ? "-" + bo.getVoucherDate().getMonthValue() : "-0" + bo.getVoucherDate().getMonthValue()));
        }

        String text_1 = "借方金额和贷方金额汇总";
        return getOptionBO(text_1, bos);

    }

    @Override
    public SubjectCollectBO getSum(SubjectCollectDTO subjectCollectDTO, String sTime, String time, Boolean tar) throws SerException {
        String firstSubject = subjectCollectDTO.getFirstSubject();
        //保留两位小数
        DecimalFormat df = new DecimalFormat("######0.00");

        VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
        dto1.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        BeanTransform.copyProperties(subjectCollectDTO, dto1,"serialVersionUID");
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        BeanTransform.copyProperties(subjectCollectDTO, dto,"serialVersionUID");
//        dto.getConditions().add(Restrict.between("voucherDate",new String[]{"2017-01-01","2017-02-02"}));

        Double begin = 0d;//年初数
        Double end = 0d;//年末数
        Double startSum = 0d;//财务初始化期初余额
        Double issueDebitAmount = 0d;//本期借方总额
        Double issueCreditAmount = 0d;//本期贷方总额
        Double issueTotalAmount = 0d;//本期合计余额
        Double endDebitAmount = 0d;//期末借方总额
        Double endCreditAmount = 0d;//期末贷方总额
        Double endTotalAmount = 0d;//本年累计额

        //获取财务初始化开始时间
        String firstTime = baseParameterAPI.findDoudap();
        if (StringUtils.isBlank(firstTime)) {
            firstTime = "1970-01-01";
        }
        InitDateEntryBO initDateEntryBO = initDateEntryAPI.findBySubject(subjectCollectDTO.getFirstSubject());
        if (null != initDateEntryBO) {
//            if (BalanceDirection.BORROW.equals(initDateEntryBO.getBalanceDirection())) {
//                tar = true;
//            } else {
//                tar = false;
//            }
            startSum = initDateEntryBO.getBegingBalance();
        }

        int year = Integer.valueOf(sTime.substring(0, 4)) - 1;
//        String startYear = String.valueOf(Integer.valueOf(time.substring(0, 4))) + "-01-01";
//        String startTime = year + "-01-01";
        String startTime = firstTime;
        String endTime = year + "-12-31";
        String[] times = new String[]{startTime, endTime};

        dto.getConditions().add(Restrict.between("voucherDate", times));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list && list.size() < 1) {
            VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
            dto2.getConditions().add(Restrict.between("voucherDate", times));
            dto2.getConditions().add(Restrict.between("secondSubject", firstSubject));
            list = super.findByCis(dto2);
            if (null == list && list.size() < 1) {
                VoucherGenerateDTO dto3 = new VoucherGenerateDTO();
                dto3.getConditions().add(Restrict.between("voucherDate", times));
                dto3.getConditions().add(Restrict.between("thirdSubject", firstSubject));
                list = super.findByCis(dto3);
            }
        }
        //获取年初数
        if (null != list && list.size() > 0) {
            if (tar) {
                begin = startSum + list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                begin = startSum + list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        times = new String[]{startTime, time};

        dto1.getConditions().add(Restrict.between("voucherDate", times));
        List<VoucherGenerate> list1 = super.findByCis(dto1);
        if (null == list1 && list1.size() < 1) {
            VoucherGenerateDTO dto4 = new VoucherGenerateDTO();
            dto4.getConditions().add(Restrict.between("voucherDate", times));
            dto4.getConditions().add(Restrict.between("secondSubject", firstSubject));
            list1 = super.findByCis(dto4);
            if (null == list1 && list1.size() < 1) {
                VoucherGenerateDTO dto5 = new VoucherGenerateDTO();
                dto5.getConditions().add(Restrict.between("voucherDate", times));
                dto5.getConditions().add(Restrict.between("thirdSubject", firstSubject));
                list1 = super.findByCis(dto5);
            }
        }
        //获取年末数
        if (null != list1 && list1.size() > 0) {
            if (tar) {
                end = startSum + list1.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list1.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                end = startSum + list1.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list1.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        //获取查看金额明细中的期初余额
        Double beginningCreditAmount = begin;
        VoucherGenerateDTO dto12 = new VoucherGenerateDTO();
        if (DateUtil.parseDate(sTime).getMonthValue() != 1) {
            String beginStartTime = DateUtil.parseDate(sTime).getYear() + "-01-01";
            String beginEndTime = DateUtil.parseDate(sTime).with(TemporalAdjusters.lastDayOfMonth()).toString();
//            DateUtil.dateToString(LocalDate.of(DateUtil.parseDate(sTime).getYear(), DateUtil.parseDate(sTime).getMonthValue(),
//                    DateUtil.getDayByDate(year, DateUtil.parseDate(sTime).getMonthValue())));
            String[] times4 = new String[]{beginStartTime, beginEndTime};
            dto12.getConditions().add(Restrict.eq("firstSubject", firstSubject));
            dto12.getConditions().add(Restrict.between("voucherDate", times4));
            List<VoucherGenerate> list4 = super.findByCis(dto12);
            if (null != list4 && list4.size() > 0) {
                if (tar) {
                    beginningCreditAmount += list4.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list4.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
                } else {
                    beginningCreditAmount += list4.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list4.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
                }
            }
        }
        //获取查看金额明细中的本期数据
        issueTotalAmount = beginningCreditAmount;
        VoucherGenerateDTO dto11 = new VoucherGenerateDTO();
        String[] times1 = new String[]{sTime, time};
        dto11.getConditions().add(Restrict.eq("firstSubject", firstSubject));
        dto11.getConditions().add(Restrict.between("voucherDate", times1));
        List<VoucherGenerate> list2 = super.findByCis(dto11);
        if (null != list2 && list2.size() > 0) {
            issueDebitAmount = list2.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            issueCreditAmount = list2.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            if (tar) {
                issueTotalAmount += list2.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list2.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                issueTotalAmount += list2.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list2.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        //获取查看金额明细中的本年累计数据
        endTotalAmount = begin;
        VoucherGenerateDTO dto22 = new VoucherGenerateDTO();
        String eStartTime = DateUtil.parseDate(sTime).getYear() + "-01-01";
        String[] times2 = new String[]{eStartTime, time};
        dto22.getConditions().add(Restrict.eq("firstSubject", firstSubject));
        dto22.getConditions().add(Restrict.between("voucherDate", times2));
        List<VoucherGenerate> list3 = super.findByCis(dto22);
        if (null != list3 && list3.size() > 0) {
            endDebitAmount = list3.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            endCreditAmount = list3.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            if (tar) {
                endTotalAmount += list3.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list3.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                endTotalAmount += list3.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list3.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }


        SubjectCollectBO bo = new SubjectCollectBO();
        bo.setBeginAmount(Double.parseDouble(df.format(begin)));
        bo.setEndAmount(Double.parseDouble(df.format(end)));
        bo.setBeginningCreditAmount(Double.parseDouble(df.format(beginningCreditAmount)));
        bo.setIssueDebitAmount(Double.parseDouble(df.format(issueDebitAmount)));
        bo.setIssueCreditAmount(Double.parseDouble(df.format(issueCreditAmount)));
        bo.setIssueTotalAmount(Double.parseDouble(df.format(issueTotalAmount)));
        bo.setEndDebitAmount(Double.parseDouble(df.format(endDebitAmount)));
        bo.setEndCreditAmount(Double.parseDouble(df.format(endCreditAmount)));
        bo.setEndTotalAmount(Double.parseDouble(df.format(endTotalAmount)));
        return bo;
    }

    @Override
    public Double getCurrent(SubjectCollectDTO subjectCollectDTO, String startTime, String endTime, Boolean tar) throws SerException {
        Double current = 0d;
        String[] times = new String[]{startTime, endTime};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        BeanUtils.copyProperties(subjectCollectDTO, dto);
        if (StringUtils.isNotBlank(subjectCollectDTO.getFirstSubject())) {
            dto.getConditions().add(Restrict.eq("firstSubject", subjectCollectDTO.getFirstSubject()));
        }
        dto.getConditions().add(Restrict.between("voucherDate", times));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null != list && list.size() > 0) {
            if (tar) {
                current = list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            } else {
                current = list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            }
        }
        return current;
    }

    @Override
    public List<String> findProjectName() throws SerException {
        List<VoucherGenerate> voucherGenerateList = super.findAll();
        if (null != voucherGenerateList && voucherGenerateList.size() > 0) {
            List<String> list = voucherGenerateList.stream().map(VoucherGenerate::getProjectName).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public SubjectCollectBO findCurrentAndYear(String firstSubject, String startTime, String endTime) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        SubjectCollectBO bo = new SubjectCollectBO();
        Double currentAmount = 0d;
        Double yearAmount = 0d;
        SubjectCollectBO subjectCollectBO = findCurrent(2, firstSubject, startTime, endTime);
        SubjectCollectBO subjectCollectBO1 = findCurrent(1, firstSubject, startTime.substring(0, 4) + "-01-01", endTime);
        if (null != subjectCollectBO) {
            currentAmount = subjectCollectBO.getCurrentAmount();
            currentAmount = currentAmount == null ? 0d : currentAmount;
        }
        if (null != subjectCollectBO1) {
            yearAmount = subjectCollectBO1.getCurrentAmount();
            yearAmount = yearAmount == null ? 0d : yearAmount;
        }

        bo.setCurrentAmount(currentAmount);
        bo.setYearAmount(yearAmount);
        return bo;
    }

    @Override
    public SubjectCollectBO findCurrentAndYear(String firstSubject, SubjectCollectDTO subjectCollectDTO) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        SubjectCollectBO bo = new SubjectCollectBO();
        bo.setCurrentAmount(findCurrent(2, firstSubject, subjectCollectDTO));
        bo.setYearAmount(findCurrent(1, firstSubject, subjectCollectDTO));
        return bo;
    }

    @Override
    public SubjectCollectBO findCurrent(int i, String firstSubject, String startTime, String endTime) throws SerException {

        SubjectCollectBO subjectCollectBO = new SubjectCollectBO();
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        Double debitAmount = 0d;
        Double creditAmount = 0d;
        Double current = 0d;
        Double year = 0d;
        String[] times = new String[]{startTime, endTime};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
//        List<VoucherGenerate> list = super.findByCis(dto);
        Long count = super.count(dto);
        if (null != count && count > 0) {
            if ("营业收入".equals(firstSubject)) {
                SubjectCollectBO subjectCollectBO1 = getCurrent(i, "主营业务收入", startTime, endTime, false);
                SubjectCollectBO subjectCollectBO2 = getCurrent(i, "其他业务收入", startTime, endTime, false);
                debitAmount = subjectCollectBO1.getIssueDebitAmount() + subjectCollectBO2.getIssueDebitAmount();
                creditAmount = subjectCollectBO1.getIssueCreditAmount() + subjectCollectBO2.getIssueCreditAmount();
                current = subjectCollectBO1.getCurrentAmount() + subjectCollectBO2.getCurrentAmount();

                subjectCollectBO.setIssueDebitAmount(debitAmount);
                subjectCollectBO.setIssueCreditAmount(creditAmount);
                subjectCollectBO.setCurrentAmount(current);

            } else if ("营业成本".equals(firstSubject)) {
                SubjectCollectBO subjectCollectBO1 = getCurrent(i, "主营业务成本", startTime, endTime, true);
                SubjectCollectBO subjectCollectBO2 = getCurrent(i, "其他业务成本", startTime, endTime, true);

                debitAmount = subjectCollectBO1.getIssueDebitAmount() + subjectCollectBO2.getIssueDebitAmount();
                creditAmount = subjectCollectBO1.getIssueCreditAmount() + subjectCollectBO2.getIssueCreditAmount();
                current = subjectCollectBO1.getCurrentAmount() + subjectCollectBO2.getCurrentAmount();

                subjectCollectBO.setIssueDebitAmount(debitAmount);
                subjectCollectBO.setIssueCreditAmount(creditAmount);
                subjectCollectBO.setCurrentAmount(current);
            } else if ("营业税金及附加".equals(firstSubject)) {

                SubjectCollectBO subjectCollectBO1 = getCurrent(i, "城建税", startTime, endTime, true);
                SubjectCollectBO subjectCollectBO2 = getCurrent(i, "教育附加", startTime, endTime, true);
                SubjectCollectBO subjectCollectBO3 = getCurrent(i, "地方教育附加", startTime, endTime, true);
                debitAmount = subjectCollectBO1.getIssueDebitAmount() + subjectCollectBO2.getIssueDebitAmount() + subjectCollectBO3.getIssueDebitAmount();
                creditAmount = subjectCollectBO1.getIssueCreditAmount() + subjectCollectBO2.getIssueCreditAmount() + subjectCollectBO3.getIssueCreditAmount();
                current = subjectCollectBO1.getCurrentAmount() + subjectCollectBO2.getCurrentAmount() + subjectCollectBO3.getCurrentAmount();

                subjectCollectBO.setIssueDebitAmount(debitAmount);
                subjectCollectBO.setIssueCreditAmount(creditAmount);
                subjectCollectBO.setCurrentAmount(current);

            } else if ("销售费用".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "销售费用", startTime, endTime, true);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("管理费用".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "管理费用", startTime, endTime, true);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("财务费用".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "财务费用", startTime, endTime, true);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("营业外收入".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "营业外收入", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("营业外支出".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "营业外支出", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("营业利润".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount();
            } else if ("利润总额".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount() /*-
                        getCurrent(i, "销售费用", startTime, endTime, true).getCurrentAmount()*/ +
                        getCurrent(i, "补贴收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "营业外收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "营业外支出", startTime, endTime, true).getCurrentAmount();
            } else if ("净利润".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount() /*-
                        getCurrent(i, "销售费用", startTime, endTime, true).getCurrentAmount()*/ +
                        getCurrent(i, "补贴收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "营业外收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "营业外支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "所得税", startTime, endTime, true).getCurrentAmount();
            } else if ("年初未分配利润".equals(firstSubject)) {
                subjectCollectBO = specialCurr(i, "未分配利润", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("其他转入".equals(firstSubject)) {
                subjectCollectBO = getCurrentBySumary(1, "其他转入", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("可供分配的利润".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount() /*-
                        getCurrent(i, "销售费用", startTime, endTime, true).getCurrentAmount()*/ +
                        getCurrent(i, "补贴收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "营业外收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "营业外支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "所得税", startTime, endTime, true).getCurrentAmount() +
                        specialCurr(i, "未分配利润", startTime, endTime, false).getCurrentAmount() +
                        getCurrentBySumary(1, "其他转入", startTime, endTime, false).getCurrentAmount();
            } else if ("提取法定盈余公积".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "法定盈余公积", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("提取法定公益金".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "法定公益金", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("提取职工奖励及福利基金".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "职工奖励及福利基金", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("提取储备基金".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "储备基金", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("提取企业发展基金".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "企业发展基金", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("利润归还投资".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "归还投资", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("可供投资者分配的利润".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount() /*-
                        getCurrent(i, "销售费用", startTime, endTime, true).getCurrentAmount()*/ +
                        getCurrent(i, "补贴收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "营业外收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "营业外支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "所得税", startTime, endTime, true).getCurrentAmount() +
                        specialCurr(i, "未分配利润", startTime, endTime, false).getCurrentAmount() +
                        getCurrentBySumary(1, "其他转入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "法定盈余公积", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "法定公益金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "职工奖励及福利基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "储备基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "企业发展基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "归还投资", startTime, endTime, false).getCurrentAmount();
            } else if ("应付优先股股利".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "应付优先股股利", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("提取任意盈余公积".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "任意盈余公积", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("应付普通股股利".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "应付普通股股利", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("转作资本（或股本）的普通股股利".equals(firstSubject)) {
                subjectCollectBO = getCurrentBySumary(2, "转作资本（或股本）的普通股股利", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("以前年度损益调整".equals(firstSubject)) {
                subjectCollectBO = getCurrent(i, "以前年度损益", startTime, endTime, false);
                current = subjectCollectBO.getCurrentAmount();
            } else if ("未分配利润".equals(firstSubject)) {
                Double currentAmount1 = getCurrent(i, "主营业务收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount();
                current = currentAmount1 -
                        getCurrent(i, "主营业务成本", startTime, endTime, true).getCurrentAmount() -
                        (getCurrent(i, "城建税", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "教育附加", startTime, endTime, true).getCurrentAmount() +
                                getCurrent(i, "地方教育附加", startTime, endTime, true).getCurrentAmount()) +
                        getCurrent(i, "其他业务收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "其他业务支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "营业费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "管理费用", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "财务费用", startTime, endTime, true).getCurrentAmount() /*-
                        getCurrent(i, "销售费用", startTime, endTime, true).getCurrentAmount()*/ +
                        getCurrent(i, "补贴收入", startTime, endTime, false).getCurrentAmount() +
                        getCurrent(i, "营业外收入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "营业外支出", startTime, endTime, true).getCurrentAmount() -
                        getCurrent(i, "所得税", startTime, endTime, true).getCurrentAmount() +
                        specialCurr(i, "未分配利润", startTime, endTime, false).getCurrentAmount() +
                        getCurrentBySumary(1, "其他转入", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "法定盈余公积", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "法定公益金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "职工奖励及福利基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "储备基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "企业发展基金", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "归还投资", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "应付优先股股利", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "任意盈余公积", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "应付普通股股利", startTime, endTime, false).getCurrentAmount() -
                        getCurrentBySumary(2, "转作资本（或股本）的普通股股利", startTime, endTime, false).getCurrentAmount() -
                        getCurrent(i, "以前年度损益", startTime, endTime, false).getCurrentAmount();
                ;
            } else {
                current = getCurrent(i, firstSubject, startTime, endTime, true).getCurrentAmount();
            }
            subjectCollectBO.setCurrentAmount(current);
            return subjectCollectBO;
        }
        return subjectCollectBO;
    }

    @Override
    public Double findCurrent(int i, String firstSubject, SubjectCollectDTO subjectCollectDTO) throws SerException {
        if (StringUtils.isBlank(firstSubject)) {
            return null;
        }
        Double current = 0d;
        Double year = 0d;
        String[] times = new String[]{subjectCollectDTO.getStartTime(), subjectCollectDTO.getEndTime()};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null != list && list.size() > 0) {
            if ("营业收入".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true);
            } else if ("营业成本".equals(firstSubject)) {
                current = getCurrent(i, "主营业务成本", subjectCollectDTO, true);
            } else if ("营业税金及附加".equals(firstSubject)) {
                current = getCurrent(i, "城建税", subjectCollectDTO, true) +
                        getCurrent(i, "教育附加", subjectCollectDTO, true) +
                        getCurrent(i, "地方教育附加", subjectCollectDTO, true);
            } else if ("销售费用".equals(firstSubject)) {
                current = getCurrent(i, "销售费用", subjectCollectDTO, true);
            } else if ("管理费用".equals(firstSubject)) {
                current = getCurrent(i, "管理费用", subjectCollectDTO, true);
            } else if ("财务费用".equals(firstSubject)) {
                current = getCurrent(i, "财务费用", subjectCollectDTO, true);
            } else if ("营业外收入".equals(firstSubject)) {
                current = getCurrent(i, "营业外收入", subjectCollectDTO, true);
            } else if ("营业外支出".equals(firstSubject)) {
                current = getCurrent(i, "营业外支出", subjectCollectDTO, false);
            } else if ("营业利润".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true)/* -
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/;
            } else if ("利润总额".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true) /*-
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/ +
                        getCurrent(i, "补贴收入", subjectCollectDTO, true) +
                        getCurrent(i, "营业外收入", subjectCollectDTO, true) -
                        getCurrent(i, "营业外支出", subjectCollectDTO, true);
            } else if ("净利润".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true) /*-
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/ +
                        getCurrent(i, "补贴收入", subjectCollectDTO, true) +
                        getCurrent(i, "营业外收入", subjectCollectDTO, true) -
                        getCurrent(i, "营业外支出", subjectCollectDTO, true) -
                        getCurrent(i, "所得税", subjectCollectDTO, true);
            } else if ("年初未分配利润".equals(firstSubject)) {
                current = specialCurr(i, "未分配利润", subjectCollectDTO, true);
            } else if ("其他转入".equals(firstSubject)) {
                current = getCurrentBySumary(1, "其他转入", subjectCollectDTO, true);
            } else if ("可供分配的利润".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true) /*-
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/  +
                        getCurrent(i, "补贴收入", subjectCollectDTO, true) +
                        getCurrent(i, "营业外收入", subjectCollectDTO, true) -
                        getCurrent(i, "营业外支出", subjectCollectDTO, true) -
                        getCurrent(i, "所得税", subjectCollectDTO, true) +
                        specialCurr(i, "未分配利润", subjectCollectDTO, true) +
                        getCurrentBySumary(1, "其他转入", subjectCollectDTO, true);
            } else if ("提取法定盈余公积".equals(firstSubject)) {
                current = getCurrent(i, "法定盈余公积", subjectCollectDTO, true);
            } else if ("提取法定公益金".equals(firstSubject)) {
                current = getCurrent(i, "法定公益金", subjectCollectDTO, true);
            } else if ("提取职工奖励及福利基金".equals(firstSubject)) {
                current = getCurrent(i, "职工奖励及福利基金", subjectCollectDTO, true);
            } else if ("提取储备基金".equals(firstSubject)) {
                current = getCurrent(i, "储备基金", subjectCollectDTO, true);
            } else if ("提取企业发展基金".equals(firstSubject)) {
                current = getCurrent(i, "企业发展基金", subjectCollectDTO, true);
            } else if ("利润归还投资".equals(firstSubject)) {
                current = getCurrent(i, "归还投资", subjectCollectDTO, true);
            } else if ("可供投资者分配的利润".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true) /*-
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/ +
                        getCurrent(i, "补贴收入", subjectCollectDTO, true) +
                        getCurrent(i, "营业外收入", subjectCollectDTO, true) -
                        getCurrent(i, "营业外支出", subjectCollectDTO, true) -
                        getCurrent(i, "所得税", subjectCollectDTO, true) +
                        specialCurr(i, "未分配利润", subjectCollectDTO, true) +
                        getCurrentBySumary(1, "其他转入", subjectCollectDTO, true) -
                        getCurrent(i, "法定盈余公积", subjectCollectDTO, true) -
                        getCurrent(i, "法定公益金", subjectCollectDTO, true) -
                        getCurrent(i, "职工奖励及福利基金", subjectCollectDTO, true) -
                        getCurrent(i, "储备基金", subjectCollectDTO, true) -
                        getCurrent(i, "企业发展基金", subjectCollectDTO, true) -
                        getCurrent(i, "归还投资", subjectCollectDTO, true);
            } else if ("应付优先股股利".equals(firstSubject)) {
                current = getCurrent(i, "应付优先股股利", subjectCollectDTO, true);
            } else if ("提取任意盈余公积".equals(firstSubject)) {
                current = getCurrent(i, "任意盈余公积", subjectCollectDTO, true);
            } else if ("应付普通股股利".equals(firstSubject)) {
                current = getCurrent(i, "应付普通股股利", subjectCollectDTO, true);
            } else if ("转作资本（或股本）的普通股股利".equals(firstSubject)) {
                current = getCurrentBySumary(2, "转作资本（或股本）的普通股股利", subjectCollectDTO, true);
            } else if ("以前年度损益调整".equals(firstSubject)) {
                current = getCurrent(i, "以前年度损益", subjectCollectDTO, true);
            } else if ("未分配利润".equals(firstSubject)) {
                current = getCurrent(i, "主营业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "主营业务成本", subjectCollectDTO, true) -
                        (getCurrent(i, "城建税", subjectCollectDTO, true) +
                                getCurrent(i, "教育附加", subjectCollectDTO, true) +
                                getCurrent(i, "地方教育附加", subjectCollectDTO, true)) +
                        getCurrent(i, "其他业务收入", subjectCollectDTO, true) -
                        getCurrent(i, "其他业务支出", subjectCollectDTO, true) -
                        getCurrent(i, "营业费用", subjectCollectDTO, true) -
                        getCurrent(i, "管理费用", subjectCollectDTO, true) -
                        getCurrent(i, "财务费用", subjectCollectDTO, true) /*-
                        getCurrent(i, "销售费用", subjectCollectDTO, true)*/ +
                        getCurrent(i, "补贴收入", subjectCollectDTO, true) +
                        getCurrent(i, "营业外收入", subjectCollectDTO, true) -
                        getCurrent(i, "营业外支出", subjectCollectDTO, true) -
                        getCurrent(i, "所得税", subjectCollectDTO, true) +
                        specialCurr(i, "未分配利润", subjectCollectDTO, true) +
                        getCurrentBySumary(1, "其他转入", subjectCollectDTO, true) -
                        getCurrent(i, "法定盈余公积", subjectCollectDTO, true) -
                        getCurrent(i, "法定公益金", subjectCollectDTO, true) -
                        getCurrent(i, "职工奖励及福利基金", subjectCollectDTO, true) -
                        getCurrent(i, "储备基金", subjectCollectDTO, true) -
                        getCurrent(i, "企业发展基金", subjectCollectDTO, true) -
                        getCurrent(i, "归还投资", subjectCollectDTO, true) -
                        getCurrent(i, "应付优先股股利", subjectCollectDTO, true) -
                        getCurrent(i, "任意盈余公积", subjectCollectDTO, true) -
                        getCurrent(i, "应付普通股股利", subjectCollectDTO, true) -
                        getCurrentBySumary(2, "转作资本（或股本）的普通股股利", subjectCollectDTO, true) -
                        getCurrent(i, "以前年度损益", subjectCollectDTO, true);
                ;
            } else {
                current = getCurrent(i, firstSubject, subjectCollectDTO, true);
            }
            return current;
        }
        return 0d;
    }


    //tar:true,获取借方,false,获取贷方
    @Override
    public SubjectCollectBO getCurrent(int i, String firstSubject, String startTime, String endTime, Boolean tar) throws SerException {
        Double current = 0d;
        Double borrowMoney = 0d;
        Double loanMoney = 0d;
        String[] times = new String[]{startTime, endTime};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        /*if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            dto1.getConditions().add(Restrict.eq("secondSubject", firstSubject));
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                dto2.getConditions().add(Restrict.eq("thirdSubject", firstSubject));
                list = super.findByCis(dto2);
            }
        }*/

        if (null != list && list.size() > 0) {
            List<VoucherGenerate> voucherGenerateList = new ArrayList<>();
            for (VoucherGenerate voucherGenerate : list) {
                if (voucherGenerate.getSumary().length() > 2) {
                    if (!"结转".equals(voucherGenerate.getSumary().substring(0, 2))) {
                        voucherGenerateList.add(voucherGenerate);
                    }
                } else {
                    voucherGenerateList.add(voucherGenerate);
                }
            }
            borrowMoney = voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            loanMoney = voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            if (tar) {
                current = voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }

        //如果传入的时间年份与财务初始化中的启用年份相等(就需要加上初始化表中的累计损益数)只针对于本年累计数
        String firstTime = baseParameterAPI.findDoudap();
        if (i == 1) {
            if (StringUtils.isNotBlank(firstTime)) {
                if (DateUtil.parseDate(firstTime).getYear() == DateUtil.parseDate(startTime).getYear()) {
                    current += initDateEntryAPI.findYearProfitLossNumByName(firstSubject);
                }
            }
        }
        SubjectCollectBO subjectCollectBO = new SubjectCollectBO();
        subjectCollectBO.setIssueDebitAmount(borrowMoney);
        subjectCollectBO.setIssueCreditAmount(loanMoney);
        subjectCollectBO.setCurrentAmount(current);
        return subjectCollectBO;
    }

    @Override
    public Double getCurrent(int i, String firstSubject, SubjectCollectDTO subjectCollectDTO, Boolean tar) throws SerException {
        Double current = 0d;
        String[] times = new String[]{subjectCollectDTO.getStartTime(), subjectCollectDTO.getEndTime()};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));

        if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
            dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
            dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
        }
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
            }
            dto1.getConditions().add(Restrict.eq("secondSubject", firstSubject));
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                    dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                    dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                    dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
                }
                dto2.getConditions().add(Restrict.eq("thirdSubject", firstSubject));
                list = super.findByCis(dto2);
            }
        }

        if (null != list && list.size() > 0) {
            List<VoucherGenerate> voucherGenerateList = new ArrayList<>();
            for (VoucherGenerate voucherGenerate : list) {
                if (voucherGenerate.getSumary().length() > 2) {
                    if (!"结转".equals(voucherGenerate.getSumary().substring(0, 2))) {
                        voucherGenerateList.add(voucherGenerate);
                    }
                } else {
                    voucherGenerateList.add(voucherGenerate);
                }
            }
            if (tar) {
                current = voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }

        //如果传入的时间年份与财务初始化中的启用年份相等(就需要加上初始化表中的累计损益数)只针对于本年累计数
        String firstTime = baseParameterAPI.findDoudap();
        if (i == 1) {
            if (StringUtils.isNotBlank(firstTime)) {
                if (DateUtil.parseDate(firstTime).getYear() == DateUtil.parseDate(subjectCollectDTO.getStartTime()).getYear()) {
                    current += initDateEntryAPI.findYearProfitLossNumByName(firstSubject);
                }
            }
        }

        return current;
    }


    //获取年初未分配利润科目(1月)的数据
    public SubjectCollectBO specialCurr(int i, String firstSubject, String startTime, String endTime, Boolean tar) throws SerException {
        Double current = 0d;
        Double borrowMoney = 0d;
        Double loanMoney = 0d;
        String[] times = new String[]{startTime, endTime};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            dto1.getConditions().add(Restrict.eq("secondSubject", firstSubject));
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                dto2.getConditions().add(Restrict.eq("thirdSubject", firstSubject));
                list = super.findByCis(dto2);
            }
        }

        if (null != list && list.size() > 0) {
            List<VoucherGenerate> voucherGenerateList = new ArrayList<>();
            for (VoucherGenerate voucherGenerate : list) {
                if (voucherGenerate.getSumary().length() > 2) {
                    if (!"结转".equals(voucherGenerate.getSumary().substring(0, 2))) {
                        voucherGenerateList.add(voucherGenerate);
                    }
                } else {
                    voucherGenerateList.add(voucherGenerate);
                }
            }
            borrowMoney = voucherGenerateList.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            loanMoney = voucherGenerateList.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            if (tar) {
                current = voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getBorrowMoney()).sum() - voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getLoanMoney()).sum() - voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        //如果传入的时间年份与财务初始化中的启用年份相等(就需要加上初始化表中的累计损益数)只针对于本年累计数
        String firstTime = baseParameterAPI.findDoudap();
        if (i == 1) {
            if (StringUtils.isNotBlank(firstTime)) {
                if (DateUtil.parseDate(firstTime).getYear() == DateUtil.parseDate(startTime).getYear()) {
                    current += initDateEntryAPI.findYearProfitLossNumByName(firstSubject);
                }
            }
        }
        SubjectCollectBO subjectCollectBO = new SubjectCollectBO();
        subjectCollectBO.setIssueDebitAmount(borrowMoney);
        subjectCollectBO.setIssueCreditAmount(loanMoney);
        subjectCollectBO.setCurrentAmount(current);
        return subjectCollectBO;
    }

    public Double specialCurr(int i, String firstSubject, SubjectCollectDTO subjectCollectDTO, Boolean tar) throws SerException {
        Double current = 0d;
        String[] times = new String[]{subjectCollectDTO.getStartTime(), subjectCollectDTO.getEndTime()};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
            dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
            dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
        }
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
            }
            dto1.getConditions().add(Restrict.eq("secondSubject", firstSubject));
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                    dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                    dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                    dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
                }
                dto2.getConditions().add(Restrict.eq("thirdSubject", firstSubject));
                list = super.findByCis(dto2);
            }
        }

        if (null != list && list.size() > 0) {
            List<VoucherGenerate> voucherGenerateList = new ArrayList<>();
            for (VoucherGenerate voucherGenerate : list) {
                if (voucherGenerate.getSumary().length() > 2) {
                    if (!"结转".equals(voucherGenerate.getSumary().substring(0, 2))) {
                        voucherGenerateList.add(voucherGenerate);
                    }
                } else {
                    voucherGenerateList.add(voucherGenerate);
                }
            }
            if (tar) {
                current = voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getBorrowMoney()).sum() - voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getLoanMoney()).sum() - voucherGenerateList.stream().filter(obj -> 1 == obj.getVoucherDate().getMonthValue()).mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        //如果传入的时间年份与财务初始化中的启用年份相等(就需要加上初始化表中的累计损益数)只针对于本年累计数
        String firstTime = baseParameterAPI.findDoudap();
        if (i == 1) {
            if (StringUtils.isNotBlank(firstTime)) {
                if (DateUtil.parseDate(firstTime).getYear() == DateUtil.parseDate(subjectCollectDTO.getStartTime()).getYear()) {
                    current += initDateEntryAPI.findYearProfitLossNumByName(firstSubject);
                }
            }
        }
        return current;
    }

    //获取摘要为传入的值的贷方借方余额(有一个特殊的传了一个i=1)
    public SubjectCollectBO getCurrentBySumary(int i, String sumary, String startTime, String endTime, Boolean tar) throws SerException {
        Double current = 0d;
        Double borrowMoney = 0d;
        Double loanMoney = 0d;
        String[] times = new String[]{startTime, endTime};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        dto.getConditions().add(Restrict.eq("sumary", sumary));
        if (i == 1) {
            dto.getConditions().add(Restrict.eq("firstSubject", "未分配利润"));
        }
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            dto1.getConditions().add(Restrict.eq("sumary", sumary));
            if (i == 1) {
                dto1.getConditions().add(Restrict.eq("secondSubject", "未分配利润"));
            }
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                dto2.getConditions().add(Restrict.eq("sumary", sumary));
                if (i == 1) {
                    dto2.getConditions().add(Restrict.eq("thirdSubject", "未分配利润"));
                }
                list = super.findByCis(dto2);
            }
        }

        if (null != list && list.size() > 0) {
            borrowMoney = list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            loanMoney = list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            if (tar) {
                current = list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        SubjectCollectBO subjectCollectBO = new SubjectCollectBO();
        subjectCollectBO.setIssueDebitAmount(borrowMoney);
        subjectCollectBO.setIssueCreditAmount(loanMoney);
        subjectCollectBO.setCurrentAmount(current);
        return subjectCollectBO;
    }

    //获取摘要为传入的值的贷方借方余额(有一个特殊的传了一个i=1)
    public Double getCurrentBySumary(int i, String sumary, SubjectCollectDTO subjectCollectDTO, Boolean tar) throws SerException {
        Double current = 0d;
        String[] times = new String[]{subjectCollectDTO.getStartTime(), subjectCollectDTO.getEndTime()};
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.between("voucherDate", times));
        if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
            dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
            dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
        }
        dto.getConditions().add(Restrict.eq("sumary", sumary));
        if (i == 1) {
            dto.getConditions().add(Restrict.eq("firstSubject", "未分配利润"));
        }
//        dto.getConditions().add(Restrict.like("sumary", "结转"));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (null == list || list.size() < 1) {
            VoucherGenerateDTO dto1 = new VoucherGenerateDTO();
            dto1.getConditions().add(Restrict.between("voucherDate", times));
            if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
            }
            if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
            }
            dto1.getConditions().add(Restrict.eq("sumary", sumary));
            if (i == 1) {
                dto1.getConditions().add(Restrict.eq("secondSubject", "未分配利润"));
            }
            list = super.findByCis(dto1);
            if (null == list || list.size() < 1) {
                VoucherGenerateDTO dto2 = new VoucherGenerateDTO();
                dto2.getConditions().add(Restrict.between("voucherDate", times));
                if (StringUtils.isNotBlank(subjectCollectDTO.getArea()[0])) {
                    dto.getConditions().add(Restrict.eq("area", subjectCollectDTO.getArea()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectGroup())) {
                    dto.getConditions().add(Restrict.eq("projectGroup", subjectCollectDTO.getProjectGroup()));
                }
                if (StringUtils.isNotBlank(subjectCollectDTO.getProjectName())) {
                    dto.getConditions().add(Restrict.eq("projectName", subjectCollectDTO.getProjectName()));
                }
                dto2.getConditions().add(Restrict.eq("sumary", sumary));
                if (i == 1) {
                    dto2.getConditions().add(Restrict.eq("thirdSubject", "未分配利润"));
                }
                list = super.findByCis(dto2);
            }
        }

        if (null != list && list.size() > 0) {
            if (tar) {
                current = list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum() - list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum();
            } else {
                current = list.stream().mapToDouble(obj -> obj.getLoanMoney()).sum() - list.stream().mapToDouble(obj -> obj.getBorrowMoney()).sum();
            }
        }
        return current;
    }


    //柱状图数据
    private OptionBO getOptionBO(String text_1, List<HistogramBO> histogramBOList) throws SerException {
        List<String> monthList = histogramBOList.stream().map(HistogramBO::getMonth).collect(Collectors.toList());
        String[] text_3 = monthList.toArray(new String[monthList.size()]);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_2 = new String[]{"借方金额", "贷方金额"};
        text_list2 = Arrays.stream(text_2).collect(Collectors.toList());
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (histogramBOList != null && histogramBOList.size() > 0) {
            for (String str : text_list2) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(str);
                seriesBO.setType("bar");
                List<Double> number = new ArrayList<>(0);
                for (HistogramBO bo : histogramBOList) {
                    Double i = 0d;
                    if (str.equals("借方金额")) {
                        Double j = bo.getBorrowMoney();
                        if (j != null) {
                            i = j;
                        }
                    }
                    if (str.equals("贷方金额")) {
                        Double j = bo.getLoanMoney();
                        if (j != null) {
                            i = j;
                        }
                    }
                    number.add(i);
                }
                //柱状图数据
                Double[] numbers = number.toArray(new Double[number.size()]);
                seriesBO.setData(numbers);
                seriesBOList.add(seriesBO);
            }
        }
        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        TooltipBO tooltipBO = new TooltipBO();
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }


    //得到偏差分析
    private List<AnalysisBO> getVarianceAnalysisBOs(AnalysisTO to, String month) throws SerException {
        List<AnalysisBO> list1 = getVarianceAnalysis(to, month);
        //得到本月偏差分析的地区/项目组/时间段

        //得到上个月的偏差分析
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-")) + "-01";
        }
        List<AnalysisBO> list2 = getVarianceAnalysis(to, getLastMonth(month));

        List<AnalysisBO> analysisBOs = new ArrayList<>(0);
        for (AnalysisBO bo1 : list1) {
            for (AnalysisBO bo2 : list2) {
                if (bo1.getCondition().equals(bo2.getCondition())) {
                    AnalysisBO analysisBO = new AnalysisBO();
                    analysisBO.setCondition(bo1.getCondition());
                    analysisBO.setRatio(String.valueOf(Double.valueOf(bo1.getRatio()) - Double.valueOf(bo2.getRatio())));
                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                        analysisBO.setWarning(true);
                    } else {
                        analysisBO.setWarning(false);
                    }
                    analysisBOs.add(analysisBO);
                } else if (!list1.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo2.getCondition())) {
                    AnalysisBO analysisBO = new AnalysisBO();
                    analysisBO.setCondition(bo2.getCondition());
                    analysisBO.setRatio(bo2.getRatio());
                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                        analysisBO.setWarning(true);
                    } else {
                        analysisBO.setWarning(false);
                    }
                    analysisBOs.add(analysisBO);
                }
            }
            if (!list2.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo1.getCondition())) {
                AnalysisBO analysisBO = new AnalysisBO();
                analysisBO.setCondition(bo1.getCondition());
                analysisBO.setRatio(bo1.getRatio());
                if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                    analysisBO.setWarning(true);
                } else {
                    analysisBO.setWarning(false);
                }
                analysisBOs.add(analysisBO);
            }
        }
        return analysisBOs;
    }


    //查询本月偏差分析发生额
    private List<AnalysisBO> getVarianceAnalysis(AnalysisTO to, String month) throws SerException {
        String value = "";
        if ("地区".equals(to.getAnalysisType().substring(0, 2))) {
            value = "area";
        }
        if ("项目".equals(to.getAnalysisType().substring(0, 2))) {
            value = "projectGroup";
        }
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        String startTime = month;
        String endTime = this.findEndDayOfMonth(startTime);

        String fields[] = null;
        String tempSql = null;
        String tempSql1 = null;
        if ("area".equals(value)) {
            fields = new String[]{"area", "firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select area, firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by area, firstSubject;";
        } else if ("projectGroup".equals(value)) {
            fields = new String[]{"area", "firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select projectGroup as area, firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by projectGroup, firstSubject;";
        } else {
            fields = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by firstSubject;";
        }

        StringBuilder sql = new StringBuilder(tempSql);
        sql.append(" where voucherDate between '" + startTime + "'");
        sql.append(" and '" + endTime + "'");
        sql.append(tempSql1);
        List<VoucherGenerate> list = super.findBySql(sql.toString(), VoucherGenerate.class, fields);
        List<String> strList = new ArrayList<>(0);
        if (value.equals("area") || value.equals("projectGroup")) {
            strList = list.stream().map(VoucherGenerate::getArea).distinct().collect(Collectors.toList());
        }
        for (VoucherGenerate voucherGenerate : list) {
            //发生额
            Double temp = 0d;
            //判断是否是资产类还是负债类
            if (categoryAPI.isAssets(voucherGenerate.getFirstSubject())) {
                temp = voucherGenerate.getBorrowMoney() - voucherGenerate.getLoanMoney();
            } else {
                temp = voucherGenerate.getLoanMoney() - voucherGenerate.getBorrowMoney();
            }
            voucherGenerate.setBorrowMoney(temp);
        }
        List<AnalysisBO> analysisBOs = new ArrayList<>(0);
        for (String string : strList) {
            //发生额
            Double temp = 0d;
            for (VoucherGenerate voucherGenerate : list) {
                if (string.equals(voucherGenerate.getArea())) {
                    temp += voucherGenerate.getBorrowMoney();
                }
            }
            AnalysisBO analysisBO = new AnalysisBO();
            analysisBO.setCondition(string);
            analysisBO.setRatio(String.valueOf(temp));
            if (temp > 1000) {
                analysisBO.setWarning(true);
            } else {
                analysisBO.setWarning(false);
            }
            analysisBOs.add(analysisBO);
        }
        if (!value.equals("area") && !value.equals("projectGroup")) {
            //发生额
            Double temp = 0d;
            for (VoucherGenerate voucherGenerate : list) {
                temp += voucherGenerate.getBorrowMoney();
            }
            AnalysisBO analysisBO = new AnalysisBO();
            analysisBO.setCondition("时间段");
            analysisBO.setRatio(String.valueOf(temp));
            if (temp > 1000) {
                analysisBO.setWarning(true);
            } else {
                analysisBO.setWarning(false);
            }
            analysisBOs.add(analysisBO);
        }
        return analysisBOs;
    }

    //得到百分比分析
    private List<AnalysisBO> getPercentageAnalysisBOs(AnalysisTO to, String month) throws SerException {
        List<AnalysisBO> list1 = getPercentageAnalysis(to, month);
        //得到本月百分比分析的地区/项目组/时间段

        //得到上个月的百分比分析
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-")) + "-01";
        }
        List<AnalysisBO> list2 = getPercentageAnalysis(to, getLastMonth(month));

        List<AnalysisBO> analysisBOs = new ArrayList<>(0);
        for (AnalysisBO bo1 : list1) {
            for (AnalysisBO bo2 : list2) {
                if (bo1.getCondition().equals(bo2.getCondition())) {
                    AnalysisBO analysisBO = new AnalysisBO();
                    analysisBO.setCondition(bo1.getCondition());
                    analysisBO.setRatio(String.valueOf(convert(Double.valueOf(bo1.getRatio()) / Double.valueOf(bo2.getRatio()) * 100)));
                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                        analysisBO.setWarning(true);
                    } else {
                        analysisBO.setWarning(false);
                    }
                    analysisBO.setRatio(String.valueOf(convert(Double.valueOf(bo1.getRatio()) / Double.valueOf(bo2.getRatio()) * 100)) + "%");
                    analysisBOs.add(analysisBO);
                }
//                else if (!list1.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo2.getCondition())) {
//                    AnalysisBO analysisBO = new AnalysisBO();
//                    analysisBO.setCondition(bo2.getCondition());
//                    analysisBO.setRatio(bo2.getRatio());
//                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
//                        analysisBO.setWarning(true);
//                    } else {
//                        analysisBO.setWarning(false);
//                    }
//                    analysisBOs.add(analysisBO);
//                }
//            }
//            if (!list2.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo1.getCondition())) {
//                AnalysisBO analysisBO = new AnalysisBO();
//                analysisBO.setCondition(bo1.getCondition());
//                analysisBO.setRatio(bo1.getRatio());
//                if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
//                    analysisBO.setWarning(true);
//                } else {
//                    analysisBO.setWarning(false);
//                }
//                analysisBOs.add(analysisBO);
//            }
            }
        }
        return analysisBOs;
    }

    //查询该月百分比分析发生额
    private List<AnalysisBO> getPercentageAnalysis(AnalysisTO to, String month) throws SerException {
        String value = "";
        if ("地区".equals(to.getAnalysisType().substring(0, 2))) {
            value = "area";
        }
        if ("项目".equals(to.getAnalysisType().substring(0, 2))) {
            value = "projectGroup";
        }
        String startTime = "";
        String endTime = "";
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
            startTime = month + "-01";
            endTime = this.findEndDayOfMonth(startTime);
        }

        String fields[] = null;
        String tempSql = null;
        String tempSql1 = null;
        if ("area".equals(value)) {
            fields = new String[]{"area", "firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select area, firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by area, firstSubject;";
        } else if ("projectGroup".equals(value)) {
            fields = new String[]{"area", "firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select projectGroup as area, firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by projectGroup, firstSubject;";
        } else {
            fields = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
            tempSql = "select firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ";
            tempSql1 = " group by firstSubject;";
        }

        StringBuilder sql = new StringBuilder(tempSql);
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where voucherDate between '" + startTime + "'");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(tempSql1);
        List<VoucherGenerate> list = super.findBySql(sql.toString(), VoucherGenerate.class, fields);
        List<String> strList = new ArrayList<>(0);
        if (value.equals("area") || value.equals("projectGroup")) {
            strList = list.stream().map(VoucherGenerate::getArea).distinct().collect(Collectors.toList());
        }
        for (VoucherGenerate voucherGenerate : list) {
            //发生额
            Double temp = 0d;
            //判断是否是资产类还是负债类
            if (categoryAPI.isAssets(voucherGenerate.getFirstSubject())) {
                temp = voucherGenerate.getBorrowMoney() - voucherGenerate.getLoanMoney();
            } else {
                temp = voucherGenerate.getLoanMoney() - voucherGenerate.getBorrowMoney();
            }
            voucherGenerate.setBorrowMoney(temp);
        }
        List<AnalysisBO> analysisBOs = new ArrayList<>(0);
        for (String string : strList) {
            //发生额
            Double temp = 0d;
            for (VoucherGenerate voucherGenerate : list) {
                if (string.equals(voucherGenerate.getArea())) {
                    temp += voucherGenerate.getBorrowMoney();
                }
            }
            AnalysisBO analysisBO = new AnalysisBO();
            analysisBO.setCondition(string);
            analysisBO.setRatio(String.valueOf(temp));
            if (temp > 1000) {
                analysisBO.setWarning(true);
            } else {
                analysisBO.setWarning(false);
            }
            analysisBOs.add(analysisBO);
        }
        if (!value.equals("area") && !value.equals("projectGroup")) {
            //发生额
            Double temp = 0d;
            for (VoucherGenerate voucherGenerate : list) {
                temp += voucherGenerate.getBorrowMoney();
            }
            AnalysisBO analysisBO = new AnalysisBO();
            analysisBO.setCondition("时间段");
            analysisBO.setRatio(String.valueOf(temp));
            if (temp > 1000) {
                analysisBO.setWarning(true);
            } else {
                analysisBO.setWarning(false);
            }
            analysisBOs.add(analysisBO);
        }
        return analysisBOs;
    }

    //得到增长率分析
    private List<AnalysisBO> getVarianceRatioAnalysisBOs(AnalysisTO to, String month) throws SerException {
        List<AnalysisBO> list1 = getVarianceAnalysis(to, month);
        //得到本月偏差分析的地区/项目组/时间段

        //得到上个月的偏差分析
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-")) + "-01";
        }
        List<AnalysisBO> list2 = getVarianceAnalysis(to, getLastMonth(month));

        List<AnalysisBO> analysisBOs = new ArrayList<>(0);
        for (AnalysisBO bo1 : list1) {
            for (AnalysisBO bo2 : list2) {
                if (bo1.getCondition().equals(bo2.getCondition())) {
                    AnalysisBO analysisBO = new AnalysisBO();
                    analysisBO.setCondition(bo1.getCondition());
                    if (0d != Double.valueOf(bo2.getRatio())) {
                        analysisBO.setRatio(String.valueOf(convert((Double.valueOf(bo1.getRatio()) - Double.valueOf(bo2.getRatio())) / Double.valueOf(bo2.getRatio()) * 100)));
                    } else {
                        analysisBO.setRatio("0");
                    }
                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                        analysisBO.setWarning(true);
                    } else {
                        analysisBO.setWarning(false);
                    }
                    if (0d != Double.valueOf(bo2.getRatio())) {
                        analysisBO.setRatio(String.valueOf(convert((Double.valueOf(bo1.getRatio()) - Double.valueOf(bo2.getRatio())) / Double.valueOf(bo2.getRatio()) * 100) + "%"));
                    } else {
                        analysisBO.setRatio("0%");
                    }
                    analysisBOs.add(analysisBO);
                } else if (!list1.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo2.getCondition())) {
                    AnalysisBO analysisBO = new AnalysisBO();
                    analysisBO.setCondition(bo2.getCondition());
                    analysisBO.setRatio("0");
                    if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                        analysisBO.setWarning(true);
                    } else {
                        analysisBO.setWarning(false);
                    }
                    analysisBO.setRatio("0%");
                    analysisBOs.add(analysisBO);
                }
            }
            if (!list2.stream().map(AnalysisBO::getCondition).distinct().collect(Collectors.toList()).contains(bo1.getCondition())) {
                AnalysisBO analysisBO = new AnalysisBO();
                analysisBO.setCondition(bo1.getCondition());
                analysisBO.setRatio("100");
                if (Double.valueOf(analysisBO.getRatio()) > Double.valueOf(to.getBrow())) {
                    analysisBO.setWarning(true);
                } else {
                    analysisBO.setWarning(false);
                }
                analysisBO.setRatio("100%");
                analysisBOs.add(analysisBO);
            }
        }
        return analysisBOs;
    }


    @Override
    public Long countVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
//        Long count = super.count(voucherGenerateDTO);
        //@update 修改：查询分组之后的总数

        return executeVoucherCountSql(voucherGenerateDTO, "1");
    }

    @Override
    public List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getSorts().add("voucherDate=asc");
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerate> list = executeVoucherSql(dto, "1");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
        for (VoucherGenerateBO str : listBO) {
            VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
            if (vt != null) {
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return convertVoucher(listBO, null);
    }

    /**
     * 记账凭证条数查询
     *
     * @param dto
     * @param type
     * @return
     * @throws SerException
     */
    Long executeVoucherCountSql(VoucherGenerateDTO dto, String type) throws SerException {
        StringBuffer sql = new StringBuffer();
        sql.append("select ifnull(count(*), 0) from (");
        sql.append("select count(1) as n from voucher_vouchergenerate where 1 = 1 ");
        switch (type) {
            case "1":
                sql.append("and auditStatus = 0 ");
                break;
            case "2":
                sql.append("and auditStatus = 1 and transferStatus = 0 and checkStatus = 0 ");
                break;
            case "3":
                sql.append("and auditStatus = 1 and transferStatus = 1 and checkStatus = 0 ");
                break;
            case "4":
                sql.append("and auditStatus = 1 and transferStatus = 1 and checkStatus = 1 ");
                break;
            case "5":
                sql.append("");
                break;
            default:
                break;
        }
        sql.append("group by uId) m");
        long amount = Long.parseLong(String.valueOf(super.findBySql(sql.toString()).get(0)));
        return amount;
    }

    /**
     * 记账凭证列表查询
     *
     * @param dto
     * @param type
     * @return
     * @throws SerException
     */
    List<VoucherGenerate> executeVoucherSql(VoucherGenerateDTO dto, String type) throws SerException {
        int page = dto.getPage() == 0 ? 1 : dto.getPage();
        int startRow = (page - 1) * dto.getLimit();
        int endRow = page * dto.getLimit();

        StringBuffer sql = new StringBuffer();
        String colums = "id, voucherWord, voucherNum, voucherDate, firstSubject, secondSubject, thirdSubject" +
                ", ifnull(borrowMoney, 0), ifnull(loanMoney, 0), sumary, source, area, projectName, projectGroup, ticketer, ticketNum, extraFile, " +
                "auditor, auditStatus, transferStatus, checkStatus, totalId, uId, firstSubjectCode, secondSubjectCode, thirdSubjectCode";
        sql.append("select " + colums + " from voucher_vouchergenerate a  where a.uId in ");
        sql.append("(select * from (select uId from voucher_vouchergenerate where 1 = 1 ");
        switch (type) {
            case "1":
                sql.append("and auditStatus = 0 ");
                break;
            case "2":
                sql.append("and auditStatus = 1 and transferStatus = 0 and checkStatus = 0 ");
                break;
            case "3":
                sql.append("and auditStatus = 1 and transferStatus = 1 and checkStatus = 0 ");
                break;
            case "4":
                sql.append("and auditStatus = 1 and transferStatus = 1 and checkStatus = 1 ");
                break;
            case "5":
                sql.append("");
                break;
            default:
                break;
        }
        if (StringUtils.isNotBlank(dto.getFirstSubject())) {
            sql.append("and firstSubject like '%" + dto.getFirstSubject() + "%' ");
        }
        if (StringUtils.isNotBlank(dto.getSecondSubject())) {
            sql.append("and secondSubject like '%" + dto.getSecondSubject() + "%' ");
        }
        if (StringUtils.isNotBlank(dto.getThirdSubject())) {
            sql.append("and thirdSubject like '%" + dto.getThirdSubject() + "%' ");
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            sql.append("and voucherDate between '" + dto.getStartTime() + "' and '" + dto.getEndTime() + "' ");
        }
        sql.append(" group by uId limit " + startRow + ", " + endRow + ")m");
        if ("降序".equals(dto.getAscOrDesc())) {
            sql.append(" order by voucherDate, voucherNum, borrowMoney desc");
        } else {
            sql.append(" order by voucherDate, voucherNum asc, borrowMoney desc");
        }
        sql.append(") ");

        String[] fields = {"id", "voucherWord", "voucherNum", "voucherDate", "firstSubject", "secondSubject", "thirdSubject"
                , "borrowMoney", "loanMoney", "sumary", "source", "area", "projectName", "projectGroup", "ticketer", "ticketNum", "extraFile", "auditor",
                "auditStatus", "transferStatus", "checkStatus", "totalId", "uId", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
        List<VoucherGenerate> list = super.findBySql(sql.toString(), VoucherGenerate.class, fields);
        if (list == null) {
            return null;
        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        return list;
    }

    /**
     * 整合记账凭证: uId相同的的数据合并为一条
     *
     * @param bos
     * @param type
     * @return
     * @throws SerException
     */
    List<VoucherGenerateBO> convertVoucher(List<VoucherGenerateBO> bos, String type) throws SerException {
        List<VoucherGenerateBO> list = new ArrayList<>();
        int len = bos.size();
        for (int i = 0; i < len; i++) {
            if (null == bos.get(i).getuId()) {
                continue;
            }
            List<VoucherGenerateChildBO> details = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if (bos.get(i).getuId().equals(bos.get(j).getuId())) {
                    VoucherGenerateChildBO bo = new VoucherGenerateChildBO();
                    bo.setFirstSubject(bos.get(j).getFirstSubject());
                    bo.setSecondSubject(bos.get(j).getSecondSubject());
                    bo.setThirdSubject(bos.get(j).getThirdSubject());
                    bo.setBorrowMoney(bos.get(j).getBorrowMoney());
                    bo.setLoanMoney(bos.get(j).getLoanMoney());
                    bo.setId(bos.get(j).getId());
                    details.add(bo);
//                    bos.get(i).setDetails(details);
                    bos.get(i).setFirstSubject(null);
                    bos.get(i).setSecondSubject(null);
                    bos.get(i).setThirdSubject(null);
                    bos.get(i).setBorrowMoney(null);
                    bos.get(i).setLoanMoney(null);
                    bos.get(i).setId(null);
                    if (i != j) {
                        bos.remove(j);
                        len = len - 1;
                        j--;
//                        continue;
                    }
                }
            }
            bos.get(i).setDetails(details);
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                LocalDate voucherDate = LocalDate.parse(bos.get(j).getVoucherDate());
                LocalDate voucherDate1 = LocalDate.parse(bos.get(j + 1).getVoucherDate());
                Double voucherNum = bos.get(j).getVoucherNum();
                Double voucherNum1 = bos.get(j + 1).getVoucherNum();
                if (voucherDate1.getYear() > voucherDate.getYear()) {
                    VoucherGenerateBO temp = bos.get(j);
                    bos.set(j, bos.get(j + 1));
                    bos.set(j + 1, temp);
                }
                if (voucherDate1.getYear() == voucherDate.getYear() && voucherDate1.getMonthValue() > voucherDate.getMonthValue()) {
                    VoucherGenerateBO temp = bos.get(j);
                    bos.set(j, bos.get(j + 1));
                    bos.set(j + 1, temp);
                }
                if (voucherDate1.getYear() == voucherDate.getYear() && voucherDate1.getMonthValue() == voucherDate.getMonthValue()
                        && voucherDate1.getDayOfMonth() < voucherDate.getDayOfMonth()) {
                    VoucherGenerateBO temp = bos.get(j);
                    bos.set(j, bos.get(j + 1));
                    bos.set(j + 1, temp);
                }
                if (voucherDate1.getYear() == voucherDate.getYear() && voucherDate1.getMonthValue() == voucherDate.getMonthValue()
                        && voucherDate1.getDayOfMonth() == voucherDate.getDayOfMonth()
                        && voucherNum > voucherNum1) {
                    VoucherGenerateBO temp = bos.get(j);
                    bos.set(j, bos.get(j + 1));
                    bos.set(j + 1, temp);
                }
            }
        }

        return bos;
    }

    private void searchCondition(VoucherGenerateDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getFirstSubject())) {
            dto.getConditions().add(Restrict.like("firstSubject", dto.getFirstSubject()));
        }
        if (StringUtils.isNotBlank(dto.getSecondSubject())) {
            dto.getConditions().add(Restrict.like("secondSubject", dto.getSecondSubject()));
        }
        if (StringUtils.isNotBlank(dto.getThirdSubject())) {
            dto.getConditions().add(Restrict.like("thirdSubject", dto.getThirdSubject()));
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            dto.getConditions().add(Restrict.between("voucherDate", new String[]{dto.getStartTime(), dto.getEndTime()}));
        }
        if ("降序".equals(dto.getAscOrDesc())) {
            dto.getSorts().add("voucherNum=desc");
        } else {
            dto.getSorts().add("voucherNum=asc");
        }
    }

    @Override
    public List<VoucherGenerateBO> listNoPage(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO);

        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    //获取凭证字号

    public Double generateVoucherNum(VoucherGenerate voucherGenerate) throws SerException {
        Double num = 1d;
        //凭证日期
        LocalDate voucherDate = voucherGenerate.getVoucherDate();
        //凭证字
        String voucherWord = voucherGenerate.getVoucherWord();

        //这个日期这个月的这个凭证字的第几号
        LocalDate start = voucherDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = voucherDate.with(TemporalAdjusters.lastDayOfMonth());

        String[] field = new String[]{"voucherNum"};
        String sql = "select max(voucherNum) as voucherNum  from voucher_vouchergenerate " +
                " where voucherWord = '" + voucherWord + "' and voucherDate between '" + start + "' and '" + end + "' ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, field);
        if (list != null && list.size() > 0) {
            if (list.get(0).getVoucherNum() != null) {
                num = list.get(0).getVoucherNum() + 1;
            } else {
                num = 1d;
            }
        }
        return num;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<VoucherGenerateBO> addVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }
        if (voucherGenerateTO.getFirstSubjects().size() == 1) {
            throw new SerException("一级科目条数必须为两条或以上");
        }
        //处理多个一级科目
//        UserBO userBO = userAPI.currentUser();
        List<String> first = voucherGenerateTO.getFirstSubjects();
        List<String> second = voucherGenerateTO.getSecondSubjects();
        List<String> third = voucherGenerateTO.getThirdSubjects();
        List<Double> borrow = voucherGenerateTO.getBorrowMoneys();
        List<Double> loan = voucherGenerateTO.getLoanMoneys();
        List<VoucherGenerate> list = new ArrayList<>();
        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);
        //生成字号
        Double num = generateVoucherNum(voucherGenerate);

        //过滤调为空的数据
//        List<Double> borrs = borrow.stream().filter(b->b!=null).collect(Collectors.toList());
//        List<Double> los = loan.stream().filter(b->b!=null).collect(Collectors.toList());

        //精度丢失问题
//        Double borrowSum = borrow.stream().filter(b -> b != null).mapToDouble(b -> b).sum();
//        Double loanSum = loan.stream().filter(l -> l != null).mapToDouble(l -> l).sum();
        BigDecimal borrowSum = new BigDecimal(0);
        BigDecimal loanSum = new BigDecimal(0);
        for (Double b : borrow) {
            borrowSum.add(new BigDecimal(String.valueOf(b)));
        }
        for (Double l : loan) {
            loanSum.add(new BigDecimal(String.valueOf(l)));
        }

        if (!borrowSum.equals(loanSum)) {
            throw new SerException("借方和贷方金额不相等，不能添加");
        }
        Double totalMoney = borrowSum.doubleValue();
        VoucherTotal vt = new VoucherTotal();
        vt.setMoney(totalMoney);
        vt.setCreateTime(LocalDateTime.now());
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.save(vt);

        String userName = voucherGenerateTO.getTicketer();
//        String userName = userBO.getUsername();

        StringBuffer uuId = new StringBuffer();
        uuId.append(UUID.randomUUID());
        for (int i = 0; i < voucherGenerateTO.getFirstSubjects().size(); i++) {

            VoucherGenerate temp = new VoucherGenerate();
            BeanUtils.copyProperties(voucherGenerate, temp);
            temp.setCreateTime(LocalDateTime.now());
            temp.setFirstSubject(first.get(i));
            temp.setSecondSubject(second.get(i));
            temp.setThirdSubject(third == null ? "" : third.get(i));
            temp.setBorrowMoney(borrow.get(i));
            temp.setLoanMoney(loan.get(i));
            temp.setTicketer(userName);
            temp.setCheckStatus(CheckStatus.NONE);
            temp.setTransferStatus(TransferStatus.NONE);
            temp.setAuditStatus(AuditStatus.NONE);
            temp.setTotalId(vt.getId());
            temp.setVoucherNum(num);
            //设置唯一标识id
            temp.setuId(uuId.toString());

            // 加上一级、二级、三级科目的编码
            String code1 = accountanCourseAPI.findByCourseName(temp.getFirstSubject());
            String arr1[] = code1.split(":");
            String code2 = accountanCourseAPI.findByCourseName(temp.getSecondSubject());
            String arr2[] = code2.split(":");
            if (StringUtils.isNotBlank(temp.getThirdSubject())) {
                String code3 = accountanCourseAPI.findByCourseName(temp.getThirdSubject());
                String arr3[] = code3.split(":");
                if (arr3.length > 0) {
                    temp.setThirdSubjectCode(arr3[0]);
                }
            }
            if (arr1.length > 0) {
                temp.setFirstSubjectCode(arr1[0]);
            }
            if (arr2.length > 0) {
                temp.setSecondSubjectCode(arr2[0]);
            }
            list.add(temp);
        }
        super.save(list);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        //测试　todo
//        List<VoucherGenerateChildTO> details = new ArrayList<>();
//        details.add(new VoucherGenerateChildTO("本年利润", "广州",null, 10000.0, 0.0, "248bfd4a-01ac-4ff5-a42c-c3ab499ac5db"));
//        details.add(new VoucherGenerateChildTO("管理费用", "员工工资",null, 0.0, 10000.0, "fa0b9fdf-8261-40aa-88f2-33e5490d8e56"));
//        voucherGenerateTO.setDetails(details);


        for (VoucherGenerateChildTO childTO : voucherGenerateTO.getDetails()) {
            if (StringUtils.isBlank(childTO.getId())) {
                throw new SerException("二级列表id不能为空");
            }
            if (StringUtils.isBlank(childTO.getFirstSubject())) {
                throw new SerException("一级科目不能为空");
            }


        }
        //精度丢失问题
//        Double borrowSum = borrow.stream().filter(b -> b != null).mapToDouble(b -> b).sum();
//        Double loanSum = loan.stream().filter(l -> l != null).mapToDouble(l -> l).sum();
        BigDecimal borrowSum = new BigDecimal(0);
        BigDecimal loanSum = new BigDecimal(0);
        for (Double b : voucherGenerateTO.getBorrowMoneys()) {
            borrowSum.add(new BigDecimal(String.valueOf(b)));
        }
        for (Double l : voucherGenerateTO.getLoanMoneys()) {
            loanSum.add(new BigDecimal(String.valueOf(l)));
        }

        if (!borrowSum.equals(loanSum)) {
            throw new SerException("借方和贷方金额不相等，不能添加");
        }
        Double totalMoney = borrowSum.doubleValue();

        List<VoucherGenerate> entities = new ArrayList<>();

        for (VoucherGenerateChildTO childTO : voucherGenerateTO.getDetails()) {
            VoucherGenerate entity = super.findById(childTO.getId());
            if (entity == null) {
                throw new SerException("更新实体不存在");
            }
            entity.setId(childTO.getId());
            entity.setBorrowMoney(childTO.getBorrowMoney());
            entity.setLoanMoney(childTO.getLoanMoney());
            entity.setFirstSubject(childTO.getFirstSubject());
            entity.setSecondSubject(childTO.getSecondSubject());
            entity.setThirdSubject(childTO.getThirdSubject());
            entity.setModifyTime(LocalDateTime.now());

            // 加上一级、二级、三级科目的编码
            String code1 = accountanCourseAPI.findByCourseName(entity.getFirstSubject());
            String arr1[] = code1.split(":");
            String code2 = accountanCourseAPI.findByCourseName(entity.getSecondSubject());
            String arr2[] = code2.split(":");
//            String code3 = accountanCourseAPI.findByCourseName(entity.getThirdSubject());
//            String arr3[] = code3.split(":");
            if (StringUtils.isNotBlank(entity.getThirdSubject())) {
                String code3 = accountanCourseAPI.findByCourseName(entity.getThirdSubject());
                String arr3[] = code3.split(":");
                if (arr3.length > 0) {
                    entity.setThirdSubjectCode(arr3[0]);
                }
            }
            if (arr1.length > 0) {
                entity.setFirstSubjectCode(arr1[0]);
            }
            if (arr2.length > 0) {
                entity.setSecondSubjectCode(arr2[0]);
            }
            entities.add(entity);
        }
        super.update(entities);

        VoucherTotal vt = voucherTotalSer.findById(voucherGenerateTO.getTotalId());
        vt.setMoney(borrowSum.doubleValue());
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.update(vt);
        return null;



       /* if (StringUtils.isBlank(voucherGenerateTO.getId())) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate temp = super.findById(voucherGenerateTO.getId());
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("totalId", temp.getTotalId()));
        dto.getConditions().add(Restrict.ne("id", temp.getId()));
        List<VoucherGenerate> otherList = super.findByCis(dto);

        Double borrowSum = otherList.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        if (voucherGenerateTO.getBorrowMoneys() != null) {
            borrowSum += voucherGenerateTO.getBorrowMoneys().stream().mapToDouble(Double::shortValue).sum();
        }
        Double loanSum = otherList.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();
        if (voucherGenerateTO.getLoanMoneys() != null) {
            loanSum += voucherGenerateTO.getLoanMoneys().stream().mapToDouble(Double::shortValue).sum();
        }
        if (!borrowSum.equals(loanSum)) {
            throw new SerException("借方和贷方金额不相等，不能编辑");
        }

        Double totalMoney = borrowSum;

        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);

        //修改同一合计金额的其他数据
        if (otherList != null && otherList.size() > 0) {
            for (VoucherGenerate other : otherList) {
                other.setVoucherWord(voucherGenerate.getVoucherWord());
                other.setVoucherDate(voucherGenerate.getVoucherDate());
                other.setSumary(voucherGenerate.getSumary());
                other.setArea(voucherGenerate.getArea());
                other.setProjectName(voucherGenerate.getProjectName());
                other.setProjectGroup(voucherGenerate.getProjectGroup());
                other.setTicketNum(voucherGenerate.getTicketNum());
                other.setTicketer(voucherGenerate.getTicketer());
                other.setExtraFile(voucherGenerate.getExtraFile());
            }

            super.update(otherList);
        }

        //修改合计金额
        VoucherTotal vt = voucherTotalSer.findById(temp.getTotalId());
        vt.setMoney(totalMoney);
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.update(vt);


        BeanTransform.copyProperties(voucherGenerate, temp, "id", "createTime", "modifyTime", "voucherDate");
        temp.setVoucherDate(voucherGenerate.getVoucherDate());
        temp.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(0));
        temp.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(0));
        temp.setThirdSubject(null == voucherGenerateTO.getThirdSubjects() ? "" : voucherGenerateTO.getThirdSubjects().get(0));

        temp.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(0));
        temp.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(0));
        temp.setModifyTime(LocalDateTime.now());
        temp.setTicketer(voucherGenerateTO.getTicketer());
        temp.setCheckStatus(CheckStatus.NONE);
        temp.setTransferStatus(TransferStatus.NONE);
        temp.setAuditStatus(AuditStatus.NONE);

        super.update(temp);
        VoucherGenerateBO bo = BeanTransform.copyProperties(temp, VoucherGenerateBO.class);
        return bo;*/
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        //修改为根据uId属性删除
        StringBuffer sql = new StringBuffer();
        sql.append("delete from voucher_vouchergenerate where uId = '" + id + "'");
        super.executeSql(sql.toString());

        /*VoucherGenerate voucherGenerate = super.findById(id);
        if (voucherGenerate != null) {

            Double borrow = voucherGenerate.getBorrowMoney();
            Double loan = voucherGenerate.getLoanMoney();

            String totalId = voucherGenerate.getTotalId();
            VoucherTotal voucherTotal = voucherTotalSer.findById(totalId);
            voucherTotal.setMoney(voucherTotal.getMoney() - borrow - loan);
            voucherTotal.setCreateTime(LocalDateTime.now());
            voucherTotalSer.update(voucherTotal);
            super.remove(id);

            //删掉合计
            VoucherGenerateDTO vgDTO = new VoucherGenerateDTO();
            vgDTO.getConditions().add(Restrict.eq("totalId", totalId));
            List<VoucherGenerate> list = super.findByCis(vgDTO);
            if (list == null || list.size() <= 0) {
                voucherTotalSer.remove(totalId);
            }
        }*/
    }


    @Override
    public Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
//        Long count = super.count(voucherGenerateDTO);
        return executeVoucherCountSql(voucherGenerateDTO, "1");
    }

    @Override
    public List<VoucherGenerateBO> listAudit(VoucherGenerateDTO dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getSorts().add("createTime=desc");
//        voucherGenerateDTO.getSorts().add("totalId=desc");
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
//
        List<VoucherGenerate> list = executeVoucherSql(dto, "1");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }


//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
//        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        for (VoucherGenerateBO str : listBO) {
            VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
            str.setMoneyTotal(vt.getMoney());
        }

        return convertVoucher(listBO, null);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO split(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }
        if (voucherGenerateTO.getFirstSubjects().size() == 1) {
            throw new SerException("还未写拆分数据");
        }
        Double borrowSum = voucherGenerateTO.getBorrowMoneys().stream().mapToDouble(Double::shortValue).sum();
        Double loanSum = voucherGenerateTO.getLoanMoneys().stream().mapToDouble(Double::shortValue).sum();

        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);
        VoucherGenerate temp = super.findById(voucherGenerateTO.getId());


        //拿到相同合计id的那条数据
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("totalId", temp.getTotalId()));
        dto.getConditions().add(Restrict.ne("id", temp.getId()));
        List<VoucherGenerate> lists = super.findByCis(dto);
        borrowSum = lists.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum() + borrowSum;
        loanSum = lists.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum() + loanSum;

        if (!borrowSum.equals(loanSum)) {
            throw new SerException("借贷方金额不相等，不能添加");
        }

        List<VoucherGenerate> addlist = new ArrayList<>();
        UserBO userBO = userAPI.currentUser();
        for (int i = 0; i < voucherGenerateTO.getFirstSubjects().size(); i++) {

            if (i == 0) {
//                BeanUtils.copyProperties(voucherGenerate, temp);
                temp.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(i));
                temp.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(i));
                temp.setThirdSubject(null == voucherGenerateTO.getThirdSubjects() ? "" : voucherGenerateTO.getThirdSubjects().get(i));
                temp.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(i));
                temp.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(i));
                temp.setModifyTime(LocalDateTime.now());
                temp.setTicketer(userBO.getUsername());
                temp.setCheckStatus(CheckStatus.NONE);
                temp.setTransferStatus(TransferStatus.NONE);
                temp.setAuditStatus(AuditStatus.NONE);
                super.update(temp);
            } else {
                VoucherGenerate temp1 = new VoucherGenerate();
                BeanUtils.copyProperties(temp, temp1, "id");
                temp1.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(i));
                temp1.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(i));
                temp1.setThirdSubject(null == voucherGenerateTO.getThirdSubjects() ? "" : voucherGenerateTO.getThirdSubjects().get(i));
                temp1.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(i));
                temp1.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(i));
                temp1.setCreateTime(LocalDateTime.now());
                temp1.setModifyTime(LocalDateTime.now());
                temp1.setTicketer(userBO.getUsername());
                temp1.setCheckStatus(CheckStatus.NONE);
                temp1.setTransferStatus(TransferStatus.NONE);
                temp1.setAuditStatus(AuditStatus.NONE);
                addlist.add(temp1);
            }
        }

        if (addlist != null && addlist.size() > 0) {
            super.save(addlist);
        }
        return BeanTransform.copyProperties(temp, VoucherGenerateBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(VoucherGenerateTO voucherGenerateTO) throws SerException {
//        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
//        voucherGenerateDTO.getConditions().add(Restrict.in("id", voucherGenerateTO.getIds()));
//        List<VoucherGenerate> vgs = super.findByCis(voucherGenerateDTO);
//        vgs.stream().forEach(str -> {
//            str.setAuditStatus(AuditStatus.CHECK);
//            str.setModifyTime(LocalDateTime.now());
//        });
//        super.update(vgs);

        //修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());
        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set auditStatus = 1, modifyTime = NOW() where" +
                " uId in (" + uIds + ")");
        super.executeSql(sql.toString());
    }

    @Override
    public Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.NONE));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
//        Long count = super.count(voucherGenerateDTO);
        return executeVoucherCountSql(voucherGenerateDTO, "2");
    }

    @Override
    public List<VoucherGenerateBO> listAudited(VoucherGenerateDTO dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.NONE));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
//        voucherGenerateDTO.getSorts().add("createTime=desc");
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);

        List<VoucherGenerate> list = executeVoucherSql(dto, "2");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
//        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        for (VoucherGenerateBO str : listBO) {
            VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
            str.setMoneyTotal(vt.getMoney());
        }

        return convertVoucher(listBO, null);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public Long posting(VoucherGenerateTO voucherGenerateTO) throws SerException {

//        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
//            throw new SerException("id数组不能为空,至少要有一个");
//        }
//        VoucherGenerateDTO dto = new VoucherGenerateDTO();
//        dto.getConditions().add(Restrict.in("id", voucherGenerateTO.getIds()));
//        List<VoucherGenerate> vgList = super.findByCis(dto);
//        vgList.stream().forEach(obj -> {
//            obj.setTransferStatus(TransferStatus.CHECK);
//            obj.setModifyTime(LocalDateTime.now());
//        });
//        super.update(vgList);
//        //提示还有几张记账凭证未审核
//        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
//        return countAudit(voucherGenerateDTO);
//        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);

        //@see 修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());
        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set transferStatus = 1, modifyTime = NOW() where " +
                " uId in (" + uIds + ")");
        super.executeSql(sql.toString());
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void antiAudit(VoucherGenerateTO voucherGenerateTO) throws SerException {
//        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
//            throw new SerException("id数组不能为空,至少要有一个");
//        }
//        VoucherGenerateDTO dto = new VoucherGenerateDTO();
//        dto.getConditions().add(Restrict.in("id", voucherGenerateTO.getIds()));
//        List<VoucherGenerate> vgList = super.findByCis(dto);
//        vgList.stream().forEach(obj -> {
//            obj.setAuditStatus(AuditStatus.NONE);
//            obj.setTransferStatus(TransferStatus.NONE);
//            obj.setModifyTime(LocalDateTime.now());
//        });
//        super.update(vgList);
        //@see 修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());

        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set auditStatus = 0, modifyTime = NOW() where " +
                " uId in(" + uIds + ")");
        super.executeSql(sql.toString());
    }


    @Override
    public List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney", "firstSubjectCode"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            sql.append(" select firstSubject , ifnull(sum(borrowMoney), 0) as borrowMoney , ifnull(sum(loanMoney), 0) as loanMoney, firstSubjectCode ")
                    .append(" from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , ifnull(borrowMoney, 0) ,  ifnull(loanMoney, 0) ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and auditStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , ifnull(borrowMoney, 0) ,  ifnull(loanMoney, 0) ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "' and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  ifnull(borrowMoney, 0) ,  ifnull(loanMoney, 0) ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();
        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setFirstSubject("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> collectArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area)) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by area ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setArea("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }
        return null;
    }

    @Override
    public List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , ifnull(sum(borrowMoney), 0) as borrowMoney , ifnull(sum(loanMoney), 0) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectGroup ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject, secondSubject, thirdSubject ,  ifnull(borrowMoney, 0) ,  ifnull(loanMoney, 0) ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectGroup("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }
        return voucherGenerateBOs;
    }

    @Override
    public List<VoucherGenerateBO> collectPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(projectName)) {
            sql.append(" select projectName , ifnull(sum(borrowMoney), 0) as borrowMoney , ifnull(sum(loanMoney), 0) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectName ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  ifnull(borrowMoney, 0) ,  ifnull(loanMoney, 0) ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and auditStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectName("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }
        return null;
    }

    @Override
    public Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
//        Long count = super.count(voucherGenerateDTO);
        return executeVoucherCountSql(voucherGenerateDTO, "3");
    }

    @Override
    public List<VoucherGenerateBO> listChecked(VoucherGenerateDTO dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
//        voucherGenerateDTO.getSorts().add("createTime=desc");
//        voucherGenerateDTO.getSorts().add("totalId=desc");
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);

        List<VoucherGenerate> list = executeVoucherSql(dto, "3");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
//        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return convertVoucher(listBO, null);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO antiPosting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        /*if (null == to.getIds()) {
            throw new SerException("id不能为空");
        }
        for (String id : to.getIds()) {
            VoucherGenerate vg = super.findById(id);
            if (null == vg) {
                throw new SerException("目标数据对象不能为空");
            }
            vg.setTransferStatus(TransferStatus.NONE);
            vg.setModifyTime(LocalDateTime.now());
            super.update(vg);
//            return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
        }*/
        //@see 修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());
        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set transferStatus = 0, modifyTime = NOW() where " +
                " uId in (" + uIds + ")");
        super.executeSql(sql.toString());
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        /*if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
            throw new SerException("id数组不能为空,至少要有一个");
        }

        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.in("id", voucherGenerateTO.getIds()));
        List<VoucherGenerate> vgList = super.findByCis(dto);
        List<VoucherGenerate> list = new ArrayList<>();
        vgList.stream().forEach(obj -> {
            obj.setCheckStatus(CheckStatus.CHECK);
            obj.setModifyTime(LocalDateTime.now());
            list.add(obj);
        });
        super.update(list);
        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);*/

        //@see 修改
        if (null == voucherGenerateTO.getuIds() || voucherGenerateTO.getuIds().length == 0) {
            throw new SerException("uId不能为空");
        }

        String uIds = "";
        for (String id : voucherGenerateTO.getuIds()) {
            uIds += ",'" + id + "'";
        }
        uIds = uIds.substring(1, uIds.length());
        StringBuffer sql = new StringBuffer();
        sql.append("update voucher_vouchergenerate set checkStatus = 1, modifyTime = NOW() where " +
                " uId in(" + uIds + ")");
        super.executeSql(sql.toString());
        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney", "firstSubjectCode"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney, firstSubjectCode")
                    .append(" from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and transferStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "' and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setFirstSubject("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctTransArea(VoucherGenerateDTO voucherGenerateDTO) throws
            SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(area)) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by area ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and transferStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setArea("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }


    @Override
    public List<VoucherGenerateBO> ctTransGroup(VoucherGenerateDTO voucherGenerateDTO) throws
            SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectGroup ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney , loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and transferStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectGroup("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctTransPname(VoucherGenerateDTO voucherGenerateDTO) throws
            SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(projectName)) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectName ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and transferStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectName("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws
            SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
//        Long count = super.count(voucherGenerateDTO);
        return executeVoucherCountSql(voucherGenerateDTO, "4");
    }

    @Override
    public List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO
                                                        dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
//        voucherGenerateDTO.getSorts().add("voucherDate=desc");
        List<VoucherGenerate> list = executeVoucherSql(dto, "4");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
//        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }


        return convertVoucher(listBO, null);
    }

    @Override
    public List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney", "firstSubjectCode"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney, firstSubjectCode ")
                    .append(" from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and checkStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "' and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setFirstSubject("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctCkArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area)) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by area ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and checkStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setArea("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctCkGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectGroup ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and checkStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectGroup("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctCkPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(projectName)) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectName ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and checkStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectName("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
//        Long count = super.count(voucherGenerateDTO);
        return executeVoucherCountSql(voucherGenerateDTO, "5");
    }

    @Override
    public List<VoucherGenerateBO> listRecord(VoucherGenerateDTO dto) throws SerException {
//        searchCondition(voucherGenerateDTO);
//        voucherGenerateDTO.getSorts().add("createTime=desc");
//        voucherGenerateDTO.getSorts().add("totalId=desc");
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
//        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);

        List<VoucherGenerate> list = executeVoucherSql(dto, "5");
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO == null || listBO.size() == 0) {
            return null;
        }
        for (VoucherGenerateBO str : listBO) {
            VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
            str.setMoneyTotal(vt.getMoney());
        }

        return convertVoucher(listBO, null);
    }

    @Override
    public List<VoucherGenerateBO> ctReSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney", "firstSubjectCode"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject, sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney, firstSubjectCode ")
                    .append(" from voucher_vouchergenerate where 1=1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'   ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'   ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'")
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "'   ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setFirstSubject("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctReArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {

        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area)) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1  ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by area ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "'  ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setArea("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctReGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectGroup ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "'  ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectGroup("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<VoucherGenerateBO> ctRePname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)

        if (StringUtils.isBlank(projectName)) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1  ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            sql.append(" group by projectName ");

            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName", "firstSubjectCode", "secondSubjectCode", "thirdSubjectCode"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName, firstSubjectCode, secondSubjectCode, thirdSubjectCode ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "'  ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
//        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        Double borrowMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanMoneyTotal = list.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();

        //设置科目代码
//        for (VoucherGenerate voucherGenerate : list) {
//            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
//                voucherGenerate.setFirstSubject( voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
//                voucherGenerate.setSecondSubject( voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getSecondSubject());
//            }
//            if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
//                voucherGenerate.setThirdSubject( voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getThirdSubject());
//            }
//        }
        //一级科目编码展示
        for (VoucherGenerate voucherGenerate : list) {
            if (StringUtils.isNotBlank(voucherGenerate.getFirstSubjectCode())) {
                if (StringUtils.isNotBlank(voucherGenerate.getThirdSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getThirdSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else if (StringUtils.isNotBlank(voucherGenerate.getSecondSubjectCode())) {
                    voucherGenerate.setFirstSubject(voucherGenerate.getSecondSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                } else {
                    voucherGenerate.setFirstSubject(voucherGenerate.getFirstSubjectCode() + ":" + voucherGenerate.getFirstSubject());
                }
            }

        }
        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (null != voucherGenerateBOs && voucherGenerateBOs.size() > 0) {
            VoucherGenerateBO total = new VoucherGenerateBO();
            total.setBorrowMoney(borrowMoneyTotal);
            total.setLoanMoney(loanMoneyTotal);
            total.setProjectName("合计");
            voucherGenerateBOs.add(total);
            return voucherGenerateBOs;
        }

        return null;
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        List<String> stringList = new ArrayList<>(0);
        //获取带有科目编号的一级科目
        List<AccountAddDateBO> list = accountanCourseAPI.findFirstNameCode();
        if (null != list && list.size() > 0) {
            list.stream().forEach(obj -> {
//                stringList.add(obj.getCode() + ":" + obj.getAccountanName());
                stringList.add(obj.getAccountanName());
            });
        }
        return stringList;
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
//        firstSub = firstSub.substring(0, firstSub.indexOf(":"));
        List<String> seconds = new ArrayList<>();
        String code = accountanCourseAPI.findByCourseName(firstSub);
        if (StringUtils.isBlank(code)) {
            return null;
        }
        String arr[] = code.split(":");
        code = arr[0];
        List<SecondSubjectDataBO> list = accountanCourseAPI.findSecondSubject(code);
        if (null == list) {
            return null;
        }
        for (SecondSubjectDataBO bo : list) {
            seconds.add(bo.getSecondSubject());
        }
        return seconds;
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
//        firstSub = firstSub.substring(0, firstSub.indexOf(":"));
        List<String> list = accountanCourseAPI.findByFirstName(firstSub);
        return list;
    }

    @Override
    public List<String> listArea() throws SerException {
        String[] field = new String[]{"area"};
        String sql = " select area  from voucher_vouchergenerate group by area ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, field);
        List<String> area = list.stream().map(VoucherGenerate::getArea).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<String> listProject() throws SerException {
        String[] field = new String[]{"projectName"};
        String sql = " select projectName  from voucher_vouchergenerate group by projectName ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, field);
        List<String> area = list.stream().map(VoucherGenerate::getProjectName).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<String> listGroup() throws SerException {
        String[] field = new String[]{"projectGroup"};
        String sql = " select projectGroup  from voucher_vouchergenerate group by projectGroup ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, field);
        List<String> area = list.stream().map(VoucherGenerate::getProjectGroup).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<AccountInfoBO> accountCollect(VoucherGenerateDTO dto) throws SerException {
        List<AccountInfoBO> boList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT voucherDate AS voucherDate,voucherWord AS voucherWord,voucherNum AS voucherNum, ");
        sb.append(" area AS area,projectName AS projectName,projectGroup AS projectGroup,sumary AS sumary, ");
        sb.append(" firstSubject AS firstSubject,secondSubject AS secondSubject,thirdSubject AS thirdSubject, ");
        sb.append(" sum(borrowMoney) AS borrowMoney,sum(loanMoney) AS loanMoney ");
        sb.append(" FROM voucher_vouchergenerate WHERE transferStatus=1 ");
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            sb.append(" and ( cast( voucherDate as date ) between '" + dto.getStartTime() + "' and '" + dto.getEndTime() + "')");
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            sb.append(" and area = '" + dto.getArea() + "' ");
        }
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            sb.append(" and projectName = '" + dto.getProjectName() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            sb.append(" and projectGroup = '" + dto.getProjectGroup() + "'");
        }
        if (StringUtils.isNotBlank(dto.getFirstSubject()) && StringUtils.isNotBlank(dto.getSecondSubject()) && StringUtils.isNotBlank(dto.getThirdSubject())) {
            sb.append(" and firstSubject = '" + dto.getFirstSubject() + "'");
            sb.append(" and secondSubject = '" + dto.getSecondSubject() + "'");
            sb.append(" and thirdSubject = '" + dto.getThirdSubject() + "'");
        }
        sb.append(" GROUP BY voucherDate,voucherWord,voucherNum,area,projectName,projectGroup,sumary, ");
        sb.append(" firstSubject,secondSubject,thirdSubject ORDER BY voucherDate DESC");
        String[] fields = new String[]{"voucherDate", "voucherWord", "voucherNum", "area", "projectName", "projectGroup", "sumary",
                "firstSubject", "secondSubject", "thirdSubject", "borrowMoney", "loanMoney"};
        List<AccountInfoBO> accountInfoBOS = super.findBySql(sb.toString(), AccountInfoBO.class, fields);
        for (AccountInfoBO accountInfoBO : accountInfoBOS) {
//            财务初始化中根据会计科目名称获取方向
            InitDateEntryBO initDateEntryBO = initDateEntryAPI.findBySubject(accountInfoBO.getFirstSubject());
//            String sql = " SELECT balanceDirection AS direction,begingBalance as balance FROM financeinit_initdateentry WHERE accountanName='" + accountInfoBO.getFirstSubject() + "' ";
//            String[] field = new String[]{"direction", "balance"};
//            boList1 = super.findBySql(sql, AccountInfoBO.class, field);
//            if (boList1 != null && !boList1.isEmpty()) {
//                accountInfoBO.setDirection(boList1.get(0).getDirection());
//                accountInfoBO.setBalance(boList1.get(0).getBalance());
//            }
            if (initDateEntryBO != null) {
                if (initDateEntryBO.getBalanceDirection() != null) {
                    if (initDateEntryBO.getBalanceDirection().equals(BalanceDirection.CREDIT)) {
                        accountInfoBO.setDirection("贷");
                    } else if (initDateEntryBO.getBalanceDirection().equals(BalanceDirection.BORROW)) {
                        accountInfoBO.setDirection("借");
                    }
                }
//                accountInfoBO.setDirection(String.valueOf(initDateEntryBO.getBalanceDirection()));
                accountInfoBO.setBalance(initDateEntryBO.getBegingBalance());
            }
            boList.add(accountInfoBO);
        }
        Double borrowMoney = boList.stream().filter(p -> p.getBorrowMoney() != null).mapToDouble(p -> p.getBorrowMoney()).sum();
        Double loanMoney = boList.stream().filter(p -> p.getLoanMoney() != null).mapToDouble(p -> p.getLoanMoney()).sum();
        AccountInfoBO bo = new AccountInfoBO();
        bo.setArea("合计：");
        bo.setBorrowMoney(borrowMoney);
        bo.setLoanMoney(loanMoney);
        boList.add(bo);
        return boList;
    }

    @Override
    public byte[] exportExcelAccount(VoucherGenerateDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getFirstSubject()) && StringUtils.isNotBlank(dto.getSecondSubject()) && StringUtils.isNotBlank(dto.getThirdSubject())) {
            dto.getConditions().add(Restrict.eq("firstSubject", dto.getFirstSubject()));
            dto.getConditions().add(Restrict.eq("secondSubject", dto.getSecondSubject()));
            dto.getConditions().add(Restrict.eq("thirdSubject", dto.getThirdSubject()));
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            dto.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            dto.getConditions().add(Restrict.eq("projectGroup", dto.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            String[] voucherDate = new String[]{dto.getStartTime(), dto.getEndTime()};
            dto.getConditions().add(Restrict.between("voucherDate", voucherDate));
        }
        dto.getSorts().add("voucherDate=asc");
        dto.getSorts().add("voucherNum=asc");
        List<AccountInfoBO> boList = new ArrayList<>();
        List<VoucherGenerate> list = super.findByCis(dto);
        List<AccountInfoBO> accountInfoBOS = BeanTransform.copyProperties(list, AccountInfoBO.class);
        for (AccountInfoBO accountInfoBO : accountInfoBOS) {
            //财务初始化中根据会计科目名称获取方向
            InitDateEntryBO initDateEntryBO = initDateEntryAPI.findBySubject(accountInfoBO.getFirstSubject());
//            String sql = " SELECT balanceDirection AS direction,begingBalance as balance FROM financeinit_initdateentry WHERE accountanName='" + accountInfoBO.getFirstSubject() + "' ";
//            String[] field = new String[]{"direction", "balance"};
//            boList1 = super.findBySql(sql, AccountInfoBO.class, field);
//            if (boList1 != null && !boList1.isEmpty()) {
//                accountInfoBO.setDirection(boList1.get(0).getDirection());
//                accountInfoBO.setBalance(boList1.get(0).getBalance());
//            }
            if (initDateEntryBO != null) {
                if (initDateEntryBO.getBalanceDirection() != null) {
                    if (initDateEntryBO.getBalanceDirection().equals(BalanceDirection.CREDIT)) {
                        accountInfoBO.setDirection("贷");
                    } else if (initDateEntryBO.getBalanceDirection().equals(BalanceDirection.BORROW)) {
                        accountInfoBO.setDirection("借");
                    }
                }
//                accountInfoBO.setDirection(String.valueOf(initDateEntryBO.getBalanceDirection()));
                accountInfoBO.setBalance(initDateEntryBO.getBegingBalance());
            }
            boList.add(accountInfoBO);
        }
        List<AccountInfoExport> accountInfoExports = new ArrayList<>();
        boList.stream().forEach(str -> {
            AccountInfoExport export = BeanTransform.copyProperties(str, AccountInfoExport.class);
            accountInfoExports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(accountInfoExports, excel);
        return bytes;
    }

    @Override
    public List<String> accountArea() throws SerException {
        String[] fields = new String[]{"area"};
        String sql = "SELECT area AS area FROM voucher_vouchergenerate WHERE transferStatus=1 GROUP BY area ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, fields);
        List<String> areaList = list.stream().map(VoucherGenerate::getArea).collect(Collectors.toList());
        return areaList;
    }

    @Override
    public List<String> accountProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        String sql = "SELECT projectName AS projectName FROM voucher_vouchergenerate WHERE transferStatus=1 GROUP BY projectName ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, fields);
        List<String> projectNameList = list.stream().map(VoucherGenerate::getProjectName).collect(Collectors.toList());
        return projectNameList;
    }

    @Override
    public List<String> accountProjectGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        String sql = "SELECT projectGroup AS projectGroup FROM voucher_vouchergenerate WHERE transferStatus=1 GROUP BY projectGroup ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, fields);
        List<String> projectGroupList = list.stream().map(VoucherGenerate::getProjectGroup).collect(Collectors.toList());
        return projectGroupList;
    }

    @Override
    public List<String> accountSubject() throws SerException {
//        String[] fields = new String[]{"firstSubject"};
//        String sql = "SELECT firstSubject AS firstSubject FROM voucher_vouchergenerate WHERE transferStatus=1 GROUP BY firstSubject ";
//        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, fields);
//        List<String> firstSubjectList = list.stream().map(VoucherGenerate::getFirstSubject).collect(Collectors.toList());
//        return firstSubjectList;
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (VoucherGenerate model : list) {
            String firstSubject = model.getFirstSubject();
            if (StringUtils.isNotBlank(model.getFirstSubject())) {
                set.add(firstSubject);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> subSubject(String firstSubject) throws SerException {
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (VoucherGenerate model : list) {
            String secondSubject = model.getSecondSubject();
            if (StringUtils.isNotBlank(model.getSecondSubject())) {
                set.add(secondSubject);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> thirdSubject(String firstSubject, String subSubject) throws SerException {
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        dto.getConditions().add(Restrict.eq("firstSubject", firstSubject));
        dto.getConditions().add(Restrict.eq("secondSubject", subSubject));
        List<VoucherGenerate> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (VoucherGenerate model : list) {
            String thirdSubject = model.getThirdSubject();
            if (StringUtils.isNotBlank(model.getThirdSubject())) {
                set.add(thirdSubject);
            }
        }
        return new ArrayList<>(set);
    }

    //Jason
    @Override
    public List<VoucherGenerateBO> findFundRecord(VoucherGenerateDTO dto) throws SerException {
        //银行存款，库存现金 为必须条件 二者之一即可，由于OR封装尚未完善，需要手动遍历dto条件进行凭借
        String[] field = new String[]{"voucherDate", "createTime", "area", "projectName", "projectGroup", "sumary", "borrowMoney", "loanMoney"};
        StringBuilder sql = new StringBuilder(" select voucherDate , createTime , area , projectName , projectGroup , sumary , borrowMoney , loanMoney ,1 " +
                "from voucher_vouchergenerate where (firstSubject='银行存款' or firstSubject='库存现金') ");
        if (StringUtils.isNotEmpty(dto.getStartTime()) && StringUtils.isNotEmpty(dto.getEndTime())) {
            sql.append(" and ( cast( voucherDate as char ) between '");
            sql.append(dto.getStartTime());
            sql.append("' and '");
            sql.append(dto.getEndTime());
            sql.append("')");
        }
        if (StringUtils.isNotEmpty(dto.getArea())) {
            sql.append("and area = '");
            sql.append(dto.getArea());
            sql.append("'");
        }
        if (StringUtils.isNotEmpty(dto.getProjectGroup())) {
            sql.append("and projectGroup = '");
            sql.append(dto.getProjectGroup());
            sql.append("'");
        }
        if (StringUtils.isNotEmpty(dto.getProjectName())) {
            sql.append(" and projectName = '");
            sql.append(dto.getProjectName());
            sql.append("'");
        }
        List<Condition> conditions = dto.getConditions();
        if (conditions != null && !conditions.isEmpty()) {
            for (Condition condition : dto.getConditions()) {
                if (condition.getField().equals("voucherDate")) {
                    sql.append(" and ( cast( voucherDate as char ) < '");
                    sql.append(condition.getValue());
                    sql.append("' )");
                }
            }
        }

        List<VoucherGenerate> list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> listStatistic(VoucherGenerateDTO voucherGenerateDTO, String condition) throws SerException {
        if (StringUtils.isNotBlank(voucherGenerateDTO.getArea())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("area", voucherGenerateDTO.getArea()));
        }
        if (StringUtils.isNotBlank(voucherGenerateDTO.getProjectName())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("projectName", voucherGenerateDTO.getProjectName()));
        }
        if (StringUtils.isNotBlank(voucherGenerateDTO.getProjectGroup())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("projectGroup", voucherGenerateDTO.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime()) && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
            voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", new String[]{voucherGenerateDTO.getStartTime(), voucherGenerateDTO.getEndTime()}));
        }
        if ("manageFee".equals(condition)) {
            voucherGenerateDTO.getConditions().add(Restrict.in("firstSubject", new String[]{"管理费", "管理费用"}));
//            voucherGenerateDTO.getConditions().add(Restrict.or("firstSubject", "管理费用"));
        } else if ("outFee".equals(condition)) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("firstSubject", "管理费"));
            voucherGenerateDTO.getConditions().add(Restrict.or("firstSubject", "管理费用"));
            voucherGenerateDTO.getConditions().add(Restrict.eq("secondSubject", "外包管理费"));
            voucherGenerateDTO.getConditions().add(Restrict.or("secondSubject", "管理费"));
        }

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        return listBO;
    }

    //chenjunhao
    @Override
    public List<VoucherGenerateBO> allSales() throws SerException {
        List<VoucherGenerate> all = super.findAll();
        List<VoucherGenerate> list = new ArrayList<VoucherGenerate>();
        for (VoucherGenerate v : all) {
            if ("销售费用".equals(v.getFirstSubject())) {
                list.add(v);
            }
        }
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

//    @Override
//    public List<PartBO> findByCondition(String[] conditions) throws SerException {
//        String[] fields = new String[]{"money"};
//        String sql = " ";
//        for (int i = 0; i < conditions.length; i++) {
//            sql += " select sum(borrowMoney+loanMoney) as money from voucher_vouchergenerate where " +
//                    " secondSubject ='" + conditions[i] + "' or thirdSubject ='" + conditions[i] + "'";
//            if (i < conditions.length - 1) {
//                sql += " UNION ";
//            }
//        }
//        System.out.println(sql);
//        List<PartBO> list = super.findBySql(sql, PartBO.class, fields);
//        if (list != null && list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                list.get(i).setName(conditions[i]);
//            }
//        }
//        return list;
//    }

    private Set<Integer> allYear() throws SerException {
        List<VoucherGenerate> list = super.findAll();
        Set<Integer> set = new HashSet<>();
        for (VoucherGenerate v : list) {
            set.add(v.getVoucherDate().getYear());
        }
        return set;
    }

    private Set<Integer> allMonth() throws SerException {
        List<VoucherGenerate> list = super.findAll();
        Set<Integer> set = new HashSet<>();
        for (VoucherGenerate v : list) {
            set.add(v.getVoucherDate().getMonthValue());
        }
        return set;
    }

    @Override
    public List<PartBO> findByMoney(VoucherGenerateDTO dto) throws SerException {
        String area = dto.getArea();
        String projectGroup = dto.getProjectGroup();
//        VoucherGenerateDTO DTO = new VoucherGenerateDTO();
        if (null != area) {
            dto.getConditions().add(Restrict.eq("area", area));
        }
        if (null != projectGroup) {
            dto.getConditions().add(Restrict.eq("projectGroup", projectGroup));
        }
//        Integer year = StringUtils.substringBefore(dto.getYear(), "-");
//        Integer month = StringUtils.substringAfterLast(dto.getMonth(), "-");
        Integer year = dto.getYear();
        Integer month = dto.getMonth();
        List<VoucherGenerate> voucherGenerates = super.findByCis(dto);
        for (VoucherGenerate voucherGenerate : voucherGenerates) {
            if (null != year && null != month) {
                if (year.equals(voucherGenerate.getVoucherDate().getYear()) && month.equals(voucherGenerate.getVoucherDate().getMonthValue())) {
                    List<PartBO> partBOS = new ArrayList<>();

                    double oilmoney = 0;//油卡充值
                    double rent = 0;//房租
                    double socialSecurity = 0;//社保
                    double staffWage = 0;//员工工资
                    double office = 0;//办公费
                    double marketCost = 0;//市场费
                    double tax = 0;//税金
                    if (voucherGenerate.getSecondSubject().equals("油卡充值") || voucherGenerate.getThirdSubject().equals("油卡充值")) {
                        if (null != voucherGenerate.getBorrowMoney()) {
                            oilmoney = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            oilmoney = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("房租") || voucherGenerate.getThirdSubject().equals("房租")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            rent = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            rent = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("社保") || voucherGenerate.getThirdSubject().equals("社保")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            socialSecurity = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            socialSecurity = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("员工工资") || voucherGenerate.getThirdSubject().equals("员工工资")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            staffWage = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            staffWage = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("办公费") || voucherGenerate.getThirdSubject().equals("办公费")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            office = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            office = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("市场费") || voucherGenerate.getThirdSubject().equals("市场费")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            marketCost = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            marketCost = voucherGenerate.getLoanMoney();
                        }
                    }
                    if (voucherGenerate.getSecondSubject().equals("税金") || voucherGenerate.getThirdSubject().equals("税金")) {

                        if (null != voucherGenerate.getBorrowMoney()) {
                            tax = voucherGenerate.getBorrowMoney();
                        } else if (null != voucherGenerate.getLoanMoney()) {
                            tax = voucherGenerate.getLoanMoney();
                        }
                    }

                    PartBO partBO = new PartBO();
//                incomeCostAnalysisBO.setArea(area);
//                incomeCostAnalysisBO.setDepartment(projectGroup);
//                incomeCostAnalysisBO.setYear(year);
//                incomeCostAnalysisBO.setMonth(month);
                    partBO.setOilRecharge(oilmoney);
                    partBO.setRent(rent);
                    partBO.setSocialSecurity(socialSecurity);
                    partBO.setStaffWage(staffWage);
                    partBO.setOffice(office);
                    partBO.setMarketCost(marketCost);
                    partBO.setTax(tax);
                    partBOS.add(partBO);

                    return partBOS;
                }
            }
        }
        return null;
    }

    @Override
    public PartOptionBO findMoneyByCondition(String first, String second, String third) throws SerException {
        String[] fields = new String[]{"loanMoney", "borrowMoney"};
        String sql = " select sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney from voucher_vouchergenerate where 1=1 ";
        if (StringUtils.isNotBlank(first)) {
            sql = sql + " and firstSubject = '" + first + "' ";
        }
        if (StringUtils.isNotBlank(second)) {
            sql = sql + " and secondSubject = '" + second + "' ";
        }
        if (StringUtils.isNotBlank(third)) {
            sql = sql + " and thirdSubject = '" + third + "' ";
        }
        List<PartOptionBO> list = super.findBySql(sql, PartOptionBO.class, fields);
        if (list != null && list.size() > 0) {
            list.get(0).setFirstName(first);
            list.get(0).setSecondName(second);
            list.get(0).setThirdName(third);
        } else {
            list = new ArrayList<>();
            PartOptionBO partBO = new PartOptionBO();
            partBO.setFirstName(first);
            partBO.setSecondName(second);
            partBO.setThirdName(third);
            partBO.setLoanMoney(0d);
            partBO.setBorrowMoney(0d);
            list.add(partBO);
        }
        return list.get(0);
    }

    //Jason
    @Override
    public List<VoucherGenerateBO> areaAnalyze(Integer year, Integer month, String area) throws SerException {
        //查询记账凭证数据sql
        StringBuilder sql = new StringBuilder();
        String voucherSql = "select id, area,borrowmoney,loanmoney from voucher_vouchergenerate where " +
                " (firstSubject='银行存款' or firstSubject='库存现金') and year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;
        sql.append(voucherSql);
        if (StringUtils.isNotEmpty(area)) {
            sql.append(" and area = '" + area + "'");
        }
        String[] fileds = new String[]{"id", "area", "borrowMoney", "loanMoney"};
        List<VoucherGenerateBO> boList = super.findBySql(sql.toString(), VoucherGenerateBO.class, fileds);
        return boList;
    }

    //Jason
    @Override
    public List<VoucherGenerateBO> groupAnalyze(Integer year, Integer month, String projectGroup) throws SerException {
        //查询记账凭证数据sql
        StringBuilder sql = new StringBuilder();
        String voucherSql = "select projectGroup,borrowmoney,loanmoney from voucher_vouchergenerate where " +
                " (firstSubject='银行存款' or firstSubject='库存现金') and year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;
        sql.append(voucherSql);
        if (StringUtils.isNotEmpty(projectGroup)) {
            sql.append(" and projectGroup = '" + projectGroup + "'");
        }
        String[] fileds = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        List<VoucherGenerateBO> boList = super.findBySql(sql.toString(), VoucherGenerateBO.class, fileds);
        return boList;
    }

    //Jason
    @Override
    public List<VoucherGenerateBO> projectAnalyze(Integer year, Integer month, String projectName) throws SerException {
        //查询记账凭证数据sql
        StringBuilder sql = new StringBuilder();
        String voucherSql = "select projectName,borrowmoney,loanmoney from voucher_vouchergenerate where " +
                " (firstSubject='银行存款' or firstSubject='库存现金') and year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;
        sql.append(voucherSql);
        if (StringUtils.isNotEmpty(projectName)) {
            sql.append(" and projectName = '" + projectName + "'");
        }
        String[] fileds = new String[]{"projectName", "borrowMoney", "loanMoney"};
        List<VoucherGenerateBO> boList = super.findBySql(sql.toString(), VoucherGenerateBO.class, fileds);
        return boList;
    }

    public static final String[] EXCELHEAD = {"凭证字", "凭证字号", "凭证日期", "一级科目",
            "二级科目", "三级科目", "借方金额", "贷方金额", "摘要", "地区", "项目名称", "项目组",
            "制单人", "票据数量", "附件", "审核人", "审核状态", "过帐状态", "结帐状态", "借贷金额合计"};

    @Override
    public byte[] exportExcel(VoucherGenerateExportDTO dto) throws SerException {
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        /*if (StringUtils.isNotBlank(dto.getFirstSubject())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("firstSubject", dto.getFirstSubject()));
        }
        if (StringUtils.isNotBlank(dto.getSecondSubject())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("secondSubject", dto.getSecondSubject()));
        }
        if (StringUtils.isNotBlank(dto.getThirdSubject())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("thirdSubject", dto.getThirdSubject()));
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("area", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            voucherGenerateDTO.getConditions().add(Restrict.eq("projectName", dto.getProjectName()));
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            String[] voucherDate = new String[]{dto.getStartTime(), dto.getEndTime()};
            voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", voucherDate));
        }
        ExportStatus exportStatus = dto.getExportStatus();
        switch (exportStatus) {
            case NONE:
                voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
                break;
            case AUDIT:
                voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
                break;
            case TRANS:
                voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
                break;
            case CHECK:
                voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
                break;
            case RECORD:
                break;
            default:
                throw new SerException("请输入正确的数据状态");
        }
        voucherGenerateDTO.getSorts().add("voucherDate=desc");
        voucherGenerateDTO.getSorts().add("voucherNum=desc");
        voucherGenerateDTO.getSorts().add("borrowMoney=desc");*/
//        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO);
//        System.out.println(JSON.toJSON(list));
        StringBuffer paramsSql = new StringBuffer();
        if (StringUtils.isNotBlank(dto.getFirstSubject())) {
            paramsSql.append(" and firstSubject = '"+ dto.getFirstSubject() +"' ");
        }
        if (StringUtils.isNotBlank(dto.getSecondSubject())) {
            paramsSql.append(" and secondSubject = '"+ dto.getSecondSubject() +"' ");
        }
        if (StringUtils.isNotBlank(dto.getThirdSubject())) {
            paramsSql.append(" and thirdSubject = '"+ dto.getThirdSubject() +"' ");
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            paramsSql.append(" and area = '"+ dto.getArea() +"' ");
        }
        if (StringUtils.isNotBlank(dto.getProjectName())) {
            paramsSql.append(" and projectName = '"+ dto.getProjectName() +"' ");
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            paramsSql.append(" and (voucherDate between '"+ dto.getStartTime() +"' and '"+ dto.getEndTime() +"') ");
        }
        ExportStatus exportStatus = dto.getExportStatus();
        switch (exportStatus) {
            case NONE:
                paramsSql.append(" and auditStatus = 0 ");
                break;
            case AUDIT:
                paramsSql.append(" and auditStatus = 1 ");
                break;
            case TRANS:
                paramsSql.append(" and transferStatus = 1 ");
                break;
            case CHECK:
                paramsSql.append(" and checkStatus = 1 ");
                break;
            case RECORD:
                break;
            default:
                throw new SerException("请输入正确的数据状态");
        }

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT vg.voucherWord, vg.voucherNum, vg.voucherDate, vg.firstSubject, vg.secondSubject, vg.thirdSubject, vg.borrowMoney, vg.loanMoney, " +
                "vg.sumary, vg.source, vg.area, vg.projectName, vg.projectGroup, vg.ticketer, vg.ticketNum, vg.extraFile, vg.auditor, vg.auditStatus, vg" +
                ".transferStatus, vg.checkStatus, vg.totalId as totalId, vt.money as moneyTotal " +
                "FROM voucher_vouchergenerate vg LEFT JOIN  voucher_vouchertotal vt ON vg.totalId = vt.id where 1 = 1 ");
        sql.append(paramsSql);
        sql.append(" order by voucherDate desc, voucherNum desc, borrowMoney desc");

        String[] fields = new String[]{"voucherWord", "voucherNum", "voucherDate", "firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                "loanMoney", "sumary", "source", "area", "projectName", "projectGroup", "ticketer",
                "ticketNum", "extraFile", "auditor", "auditStatus", "transferStatus", "checkStatus", "totalId", "moneyTotal"};
        List<VoucherExportExcel> firstSubjectExports = super.findBySql(sql.toString(), VoucherExportExcel.class, fields);
//        List<Object> list = super.findBySql(sql);
//
//        List<VoucherExportExcel> firstSubjectExports = new ArrayList<>();
//        for(Object object : list) {
//            firstSubjectExports.add((VoucherExportExcel) object);
//        }

//         = BeanTransform.copyProperties(list, VoucherExportExcel.class);
        SXSSFWorkbook wb = null;//创建一个Excel文件
        /*if (firstSubjectExports != null && firstSubjectExports.size() > 0) {
            for (VoucherExportExcel str : firstSubjectExports) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());

            }
        }*/
        Excel excel = new Excel(0, 2);
        String tempString = excel.getSheetName();
        wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
        sheet.setDefaultRowHeight((short) 300);//设置默认行高
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD;//Excel表头
        CellStyle contentStyle = getStyle(wb, excel.getContentBGColor()); // 设置标题样式
        SXSSFRow row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 400);//设置第一行单元格的高度

        for (int i = 0, length = excelHeader.length; i < length; i++) {
            SXSSFCell cell = row.createCell(i);
            if (i == 0 || i == 4 || i == 8) {
                sheet.setColumnWidth(i, 2000); //设置单元格的宽
            } else if (i == 18) {
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            cell.setCellValue(excelHeader[i]);//设置单元格的值
            cell.setCellStyle(contentStyle);   //设置样式
        }

        if (firstSubjectExports != null && firstSubjectExports.size() > 0) {
            createRowDetail(firstSubjectExports, row, sheet);//填充数据
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return os.toByteArray();

//        else{
//            return null;
//        }


    }

    /**
     * @param list  记账凭证信息集合
     * @param row
     * @param sheet Excel表单
     * @description 创建数据行
     */
    public void createRowDetail(List<VoucherExportExcel> list, SXSSFRow row, SXSSFSheet sheet) {

        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        String totalId = list.get(0).getTotalId();
        if (list != null && list.size() > 0) {
            firstRow = 1;
            lastRow = 1;
            firstCol = 0;
            lastCol = 0;
        }
        int index = 0;
        int showFlag = 0;
        System.out.println("start:" + new Date().toString());
        for (VoucherExportExcel exportEntity : list) {


            lastRow = index;

//            String ss = exportEntity.getTotalId();
//            String ss1 = totalId;
//            int f = firstRow;
//            int l = lastRow;
            if (!exportEntity.getTotalId().equals(totalId) && firstRow == lastRow) {
                //不合并
                firstRow++;
                showFlag = 1;
            } else if (exportEntity.getTotalId().equals(totalId) && firstRow == lastRow) {
                //合并
                if (index != 0 && index < list.size() - 1 && !totalId.equals(list.get(index + 1).getTotalId())) {
//                    sheet = assiableMergeData(sheet, firstRow - showFlag, lastRow);
                    sheet = assiableMergeData(sheet, lastRow + 1 - showFlag, lastRow + 1);

                   /* AssiableMergeDataDTO assiableMergeDataDTO = new AssiableMergeDataDTO(false);
                    Thread thread = new Thread(new assiableMergeDataThread(sheet, row, lastRow + 1 - showFlag, lastRow + 1, index, exportEntity, assiableMergeDataDTO));
                    thread.start();
                    while (!assiableMergeDataDTO.isSuccess()) {
                        sleep(thread,1);
                    }*/
                }
                if (index == list.size() - 1) {
//                    sheet = assiableMergeData(sheet, lastRow + 1 - showFlag, lastRow + 1);
                    new Thread(new assiableMergeDataThread(sheet, row, lastRow + 1 - showFlag, lastRow + 1,index, exportEntity, null)).start();
                }
                firstRow++;
                totalId = exportEntity.getTotalId();
            }

            if (exportEntity.getTotalId().equals(totalId)) {
                showFlag++;
            } else if (!exportEntity.getTotalId().equals(totalId)) {
                totalId = exportEntity.getTotalId();
            }

            ++index;

            row = sheet.createRow(index);    // 每循环一次创建一行
            int callIndex = 0;
//            row.createCell(callIndex++).setCellValue(index);//设置行的索引
            row.createCell(callIndex++).setCellValue(exportEntity.getVoucherWord());
            row.createCell(callIndex++).setCellValue(exportEntity.getVoucherNum());
            row.createCell(callIndex++).setCellValue(exportEntity.getVoucherDate());
            row.createCell(callIndex++).setCellValue(exportEntity.getFirstSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getSecondSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getThirdSubject() == null ? "" : exportEntity.getThirdSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getBorrowMoney());
            row.createCell(callIndex++).setCellValue(exportEntity.getLoanMoney());
            row.createCell(callIndex++).setCellValue(exportEntity.getSumary());
            row.createCell(callIndex++).setCellValue(exportEntity.getArea());
            row.createCell(callIndex++).setCellValue(exportEntity.getProjectName());
            row.createCell(callIndex++).setCellValue(exportEntity.getProjectGroup());
            row.createCell(callIndex++).setCellValue(exportEntity.getTicketer());
            row.createCell(callIndex++).setCellValue(exportEntity.getTicketNum());
            row.createCell(callIndex++).setCellValue(exportEntity.getExtraFile());
            row.createCell(callIndex++).setCellValue(exportEntity.getAuditor());
            row.createCell(callIndex++).setCellValue(auditStr(exportEntity.getAuditStatus()));
            row.createCell(callIndex++).setCellValue(transferStr(exportEntity.getTransferStatus()));
            row.createCell(callIndex++).setCellValue(checkStr(exportEntity.getCheckStatus()));
            row.createCell(callIndex++).setCellValue(exportEntity.getMoneyTotal());
//            row.createCell(callIndex++).setCellValue(exportEntity.getTotalId());


        }


        System.out.println("end:" + new Date().toString());
    }

    void sleep(Thread thread, int time) {
        try {
            if (thread == null) {
                Thread.sleep(time);
                return;
            }
            thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class assiableMergeDataThread implements Runnable {

        SXSSFSheet sheet;

        SXSSFRow row;

        int firstRow;

        int lastRow;

        int index;

        VoucherExportExcel exportEntity;

        AssiableMergeDataDTO assiableMergeDataDTO;

        public assiableMergeDataThread(SXSSFSheet sheet, SXSSFRow row, int firstRow, int lastRow, int index, VoucherExportExcel exportEntity, AssiableMergeDataDTO assiableMergeDataDTO) {
            this.sheet = sheet;
            this.row = row;
            this.firstRow = firstRow;
            this.lastRow = lastRow;
            this.index = index;
            this.exportEntity = exportEntity;
            this.assiableMergeDataDTO = assiableMergeDataDTO;
        }

        @Override
        public void run() {
            this.sheet = assiableMergeData(sheet, firstRow, lastRow);
            this.assiableMergeDataDTO.setSuccess(true);

        }
    }

    private SXSSFSheet assiableMergeData(SXSSFSheet sheet, int firstRow, int lastRow) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 1, 1);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 2, 2);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 8, 8);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 9, 9);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 10, 10);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 11, 11);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 12, 12);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 13, 13);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 14, 14);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 15, 15);
        sheet.addMergedRegion(cellRangeAddress);
//        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 16, 16);
//        sheet.addMergedRegion(cellRangeAddress);
//        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 17, 17);
//        sheet.addMergedRegion(cellRangeAddress);
//        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 18, 18);
//        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 19, 19);
        sheet.addMergedRegion(cellRangeAddress);


        return sheet;
    }

    /**
     * 获取样式
     *
     * @param wb
     * @param color
     * @return
     */
    private static CellStyle getStyle(SXSSFWorkbook wb, short color) {
        // 内容的样式
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        if (color != IndexedColors.WHITE.getIndex()) {
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置单元格颜色
            style.setBorderLeft(BorderStyle.THIN); // 单元格边框粗细
            style.setBorderRight(BorderStyle.THIN);// 单元格边框粗细
            style.setBorderTop(BorderStyle.THIN);// 单元格边框假粗细
            style.setBorderBottom(BorderStyle.THIN);// 单元格边框粗细
        }
        return style;
    }

    private static XSSFCellStyle getStyle(XSSFWorkbook wb, short color) {
        // 内容的样式
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        if (color != IndexedColors.WHITE.getIndex()) {
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置单元格颜色
            style.setBorderLeft(BorderStyle.THIN); // 单元格边框粗细
            style.setBorderRight(BorderStyle.THIN);// 单元格边框粗细
            style.setBorderTop(BorderStyle.THIN);// 单元格边框假粗细
            style.setBorderBottom(BorderStyle.THIN);// 单元格边框粗细
        }
        return style;
    }


    private String auditStr(AuditStatus auditStatus) {
        String str = "";
        switch (auditStatus) {
            case NONE:
                str = "未审核";
                break;
            case CHECK:
                str = "已审核";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    private String transferStr(TransferStatus transferStatus) {
        String str = "";
        switch (transferStatus) {
            case NONE:
                str = "未过帐";
                break;
            case CHECK:
                str = "已过帐";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    private String checkStr(CheckStatus checkStatus) {
        String str = "";
        switch (checkStatus) {
            case NONE:
                str = "未结帐";
                break;
            case CHECK:
                str = "已结帐";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public static final String[] EXCELHEAD2 = {"序号", "凭证字", "凭证日期", "一级科目",
            "二级科目", "三级科目", "借方金额", "贷方金额", "摘要", "来源", "地区", "项目名称", "项目组",
            "制单人", "票据数量", "附件"};


    @Override
    public byte[] templateExport() throws SerException {

        List<VoucherTemplateExportExcel> voucherExportVOS = new ArrayList<>();

        VoucherTemplateExportExcel excel = new VoucherTemplateExportExcel();
        excel.setNum("1");
        excel.setVoucherWord("记");
        excel.setVoucherDate(LocalDate.now());
        excel.setFirstSubject("银行存款");
        excel.setSecondSubject("test");
        excel.setThirdSubject("test");
        excel.setBorrowMoney(11d);
        excel.setLoanMoney(0d);
        excel.setSumary("test");
        excel.setArea("广州");
        excel.setProjectName("测试");
        excel.setProjectGroup("测试");
        excel.setTicketer("测试人员");
        excel.setTicketNum(1d);
        excel.setExtraFile(" ");
        excel.setTotalId("1");
        voucherExportVOS.add(excel);

        VoucherTemplateExportExcel excel2 = new VoucherTemplateExportExcel();
        excel2.setNum("1");
        excel2.setVoucherWord("记");
        excel2.setVoucherDate(LocalDate.now());
        excel2.setFirstSubject("应付职工薪酬");
        excel2.setSecondSubject("test");
        excel2.setThirdSubject("test");
        excel2.setBorrowMoney(0d);
        excel2.setLoanMoney(11d);
        excel2.setSumary("test");
        excel2.setArea("广州");
        excel2.setProjectName("测试");
        excel2.setProjectGroup("测试");
        excel2.setTicketer("测试人员");
        excel2.setTicketNum(1d);
        excel2.setExtraFile(" ");
        excel2.setTotalId("1");
        voucherExportVOS.add(excel2);

        excel = new VoucherTemplateExportExcel();
        excel.setNum("2");
        excel.setVoucherWord("付");
        excel.setVoucherDate(LocalDate.now());
        excel.setFirstSubject("其他应收款");
        excel.setSecondSubject("test");
        excel.setThirdSubject("test");
        excel.setBorrowMoney(55d);
        excel.setLoanMoney(0d);
        excel.setSumary("test");
        excel.setArea("湖南");
        excel.setProjectName("测试");
        excel.setProjectGroup("测试");
        excel.setTicketer("测试人员");
        excel.setTicketNum(0d);
        excel.setExtraFile(" ");
        excel.setTotalId("2");
        voucherExportVOS.add(excel);

        excel2 = new VoucherTemplateExportExcel();
        excel2.setNum("2");
        excel2.setVoucherWord("付");
        excel2.setVoucherDate(LocalDate.now());
        excel2.setFirstSubject("应收利息");
        excel2.setSecondSubject("test");
        excel2.setThirdSubject("test");
        excel2.setBorrowMoney(0d);
        excel2.setLoanMoney(55d);
        excel2.setSumary("test");
        excel2.setArea("湖南");
        excel2.setProjectName("测试");
        excel2.setProjectGroup("测试");
        excel2.setTicketer("测试人员");
        excel2.setTicketNum(0d);
        excel2.setExtraFile(" ");
        excel2.setTotalId("2");
        voucherExportVOS.add(excel2);

        Excel exceltemp = new Excel(0, 2);
        String tempString = exceltemp.getSheetName();
        XSSFWorkbook wb = new XSSFWorkbook();//创建一个Excel文件
        XSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
        sheet.setDefaultRowHeight((short) 300);//设置默认行高
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD2;//Excel表头
        CellStyle contentStyle = getStyle(wb, exceltemp.getContentBGColor()); // 设置标题样式
        XSSFRow row = sheet.createRow(0);//创建第一行
        row.setHeight((short) 400);//设置第一行单元格的高度

        for (int i = 0, length = excelHeader.length; i < length; i++) {
            XSSFCell cell = row.createCell(i);
            if (i == 0 || i == 4 || i == 8) {
                sheet.setColumnWidth(i, 2000); //设置单元格的宽
            } else if (i == 18) {
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            cell.setCellValue(excelHeader[i]);//设置单元格的值
            cell.setCellStyle(contentStyle);   //设置样式
        }
        if (voucherExportVOS != null && voucherExportVOS.size() > 0) {
            createRowDetail_temp(voucherExportVOS, row, sheet);//填充数据
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }


    /**
     * @param list  记账凭证信息集合
     * @param row
     * @param sheet Excel表单
     * @description 创建数据行
     */
    public void createRowDetail_temp(List<VoucherTemplateExportExcel> list, XSSFRow row, XSSFSheet sheet) {

        int firstRow = 0;
        int lastRow = 0;
        int firstCol = 0;
        int lastCol = 0;
        String totalId = list.get(0).getTotalId();
        if (list != null && list.size() > 0) {
            firstRow = 1;
            lastRow = 1;
            firstCol = 0;
            lastCol = 0;
        }
        int index = 0;
        int showFlag = 0;
        for (VoucherTemplateExportExcel exportEntity : list) {
            lastRow = index;

            if (!exportEntity.getTotalId().equals(totalId) && firstRow == lastRow) {
                //不合并
                firstRow++;
                showFlag = 1;
            } else if (exportEntity.getTotalId().equals(totalId) && firstRow == lastRow) {
                //合并
                if (index != 0 && index < list.size() - 1 && !totalId.equals(list.get(index + 1).getTotalId())) {
                    sheet = assiableMergeData_temp(sheet, lastRow + 1 - showFlag, lastRow + 1);

                }
                if (index == list.size() - 1) {
                    sheet = assiableMergeData_temp(sheet, lastRow + 1 - showFlag, lastRow + 1);
                }
                firstRow++;
                totalId = exportEntity.getTotalId();
            }

            if (exportEntity.getTotalId().equals(totalId)) {
                showFlag++;
            } else if (!exportEntity.getTotalId().equals(totalId)) {
                totalId = exportEntity.getTotalId();
            }

            ++index;
            row = sheet.createRow(index);    // 每循环一次创建一行
            int callIndex = 0;
            row.createCell(callIndex++).setCellValue(exportEntity.getNum());
            row.createCell(callIndex++).setCellValue(exportEntity.getVoucherWord());
            row.createCell(callIndex++).setCellValue(DateUtil.dateToString(exportEntity.getVoucherDate()));
            row.createCell(callIndex++).setCellValue(exportEntity.getFirstSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getSecondSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getThirdSubject());
            row.createCell(callIndex++).setCellValue(exportEntity.getBorrowMoney());
            row.createCell(callIndex++).setCellValue(exportEntity.getLoanMoney());
            row.createCell(callIndex++).setCellValue(exportEntity.getSumary());
            row.createCell(callIndex++).setCellValue(exportEntity.getArea());
            row.createCell(callIndex++).setCellValue(exportEntity.getProjectName());
            row.createCell(callIndex++).setCellValue(exportEntity.getProjectGroup());
            row.createCell(callIndex++).setCellValue(exportEntity.getTicketer());
            row.createCell(callIndex++).setCellValue(exportEntity.getTicketNum());
            row.createCell(callIndex++).setCellValue(exportEntity.getExtraFile());
//            row.createCell(callIndex++).setCellValue(exportEntity.getTotalId());


        }
    }

// public static final String[] EXCELHEAD2 = {"序号","凭证字",  "凭证日期", "一级科目",
//            "二级科目", "三级科目", "借方金额", "贷方金额", "摘要", "地区", "项目名称", "项目组",
//                    "制单人", "票据数量", "附件" };

    private XSSFSheet assiableMergeData_temp(XSSFSheet sheet, int firstRow, int lastRow) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 1, 1);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 2, 2);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 8, 8);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 9, 9);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 10, 10);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 11, 11);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 12, 12);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 13, 13);
        sheet.addMergedRegion(cellRangeAddress);
        cellRangeAddress = new CellRangeAddress(firstRow, lastRow, 14, 14);
        sheet.addMergedRegion(cellRangeAddress);

        return sheet;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO importExcel(List<VoucherGenerateTO> voucherGenerateTOs) throws SerException {
//        List<VoucherGenerate> voucherGenerate = BeanTransform.copyProperties(voucherGenerateTOs, VoucherGenerate.class, true);
//        voucherGenerate.stream().forEach(str -> {
//            str.setCreateTime(LocalDateTime.now());
//            str.setModifyTime(LocalDateTime.now());
//        });

        for (VoucherGenerateTO str : voucherGenerateTOs) {
            addVoucherGenerate(str);

//            Thread thread = new Thread(new MutImport(str));
//            thread.start();
        }

//        VoucherGenerateBO voucherGenerateBO = BeanTransform.copyProperties(new VoucherGenerate(), VoucherGenerateBO.class);
        return null;
    }

    /** 开启多线程 */
   /* class MutImport implements Runnable{

        VoucherGenerateTO to;

        MutImport(VoucherGenerateTO to){
            this.to = to;
        }

        @Override
        public void run() {
            try {
                addVoucherGenerate(to);
            } catch (SerException e) {
                e.printStackTrace();
            }
        }
    }*/
    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }

    //获取上个月的第一天
    private String getLastMonth(String month) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = dateFormat.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String lastMonth = dateFormat.format(date);
//        lastMonth = lastMonth.substring(0, lastMonth.lastIndexOf("-"));
        return lastMonth;
    }

    private Double convert(Double value) {
        long l1 = Math.round(value * 100); //四舍五入
        double ret = l1 / 100.0; //注意:使用 100.0 而不是 100
        return ret;
    }

    @Override
    public List<VoucherGenerateBO> findByCourseName() throws SerException {
        String[] feilds = new String[]{"id", "area", "projectName", "projectGroup", "sumary", "borrowMoney", "loanMoney", "firstSubject", "secondSubject", "thirdSubject"};
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("  id AS id,");
        sql.append("  area AS area,");
        sql.append("  projectName   AS projectName,");
        sql.append("  projectGroup  AS projectGroup,");
        sql.append("  sumary        AS sumary,");
        sql.append("  borrowMoney   AS borrowMoney,");
        sql.append("  loanMoney     AS loanMoney,");
        sql.append("  firstSubject  AS firstSubject,");
        sql.append("  secondSubject AS secondSubject,");
        sql.append("  thirdSubject  AS thirdSubject");
        sql.append("  FROM voucher_vouchergenerate");
        sql.append("  WHERE firstSubject = '现金' OR firstSubject = '银行存款'");
        List<VoucherGenerateBO> voucherGenerateBOS = super.findBySql(sql.toString(), VoucherGenerateBO.class, feilds);
        return voucherGenerateBOS;
    }


    @Override
    public List<FirstSubjectBO> collect(SubjectCollectsDTO dto) throws SerException {
        LocalDate[] dates = null;
        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate(dto.getStartTime());
            LocalDate endDate = DateUtil.parseDate(dto.getEndTime());
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isBlank(dto.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate(dto.getStartTime());
            LocalDate endDate = LocalDate.now();
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(dto.getEndTime());
            dates = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            dates = new LocalDate[]{startDate, endDate};
        }
        return figureCollect(dates);
    }

    @Override
    public byte[] exportExcel(ExportSubjectCollectTO to) throws SerException {
        LocalDate[] dates = null;
        String firstSubject = to.getFirstSubject();
        if (StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate(to.getStartTime());
            LocalDate endDate = DateUtil.parseDate(to.getEndTime());
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isBlank(to.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate(to.getStartTime());
            LocalDate endDate = LocalDate.now();
            dates = new LocalDate[]{startDate, endDate};
        } else if (StringUtils.isBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = DateUtil.parseDate(to.getEndTime());
            dates = new LocalDate[]{startDate, endDate};
        } else {
            LocalDate startDate = DateUtil.parseDate("1901-01-01");
            LocalDate endDate = LocalDate.now();
            dates = new LocalDate[]{startDate, endDate};
        }
        List<FirstSubjectBO> list = figureCollect(dates, firstSubject);
        List<SubjectIntroExport> subjectIntroExports = new ArrayList<>();
        Map<Integer, List<Integer>> integerListMap = new HashMap<>();
        List<Map<String, List<SubjectIntroExport>>> maps = new ArrayList<>();
        Map<String, List<SubjectIntroExport>> listMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i <= list.size(); i++) {
                List<Integer> departmentListNumber = new ArrayList<>();
                List<Integer> areaListNumber = new ArrayList<>();
                List<Integer> subjectListNumber = new ArrayList<>();
                List<Integer> allListNumber = new ArrayList<>();
                Integer subjectNumber = 0;
                Integer firstSubjectNumber = 0;
                Integer allNumber = 0;
                SubjectIntroExport subjectIntroExport = new SubjectIntroExport();
                subjectIntroExport.setFirstSubject(list.get(i).getFirstSubject());

                List<AreaSubjectBO> areaSubjectBOS = list.get(i).getAreaSubjectList();
                for (int a = 0; a < areaSubjectBOS.size(); a++) {
                    Integer areaNumber = 0;
                    List<DepartmentSubjectBO> departmentSubjectBOS = areaSubjectBOS.get(a).getDepartmentSubjectList();
                    subjectIntroExport.setArea(areaSubjectBOS.get(a).getArea());
                    for (int b = 0; b < departmentSubjectBOS.size(); b++) {
                        Integer deartpmetnNumber = 0;
                        subjectIntroExport.setDepartment(departmentSubjectBOS.get(b).getDepartment());
                        List<SubjectCollectsBO> subjectCollectsBOS = departmentSubjectBOS.get(b).getSubjectCollectList();
                        for (SubjectCollectsBO subjectCollectsBO : subjectCollectsBOS) {
                            subjectIntroExport.setProject(subjectCollectsBO.getProject());
                            subjectIntroExport.setBeginBorrowMoney(subjectCollectsBO.getBeginBorrowMoney());
                            subjectIntroExport.setBeginLoanMoney(subjectCollectsBO.getBeginLoanMoney());
                            subjectIntroExport.setCurrentBorrowMoney(subjectCollectsBO.getCurrentBorrowMoney());
                            subjectIntroExport.setCurrentLoanMoney(subjectCollectsBO.getCurrentLoanMoney());
                            subjectIntroExport.setEndBorrowMoney(subjectCollectsBO.getEndBorrowMoney());
                            subjectIntroExport.setEndLoanMoney(subjectCollectsBO.getEndLoanMoney());
                            subjectIntroExports.add(subjectIntroExport);
                        }
                        firstSubjectNumber = subjectCollectsBOS.size();
                        allNumber += firstSubjectNumber;
                        deartpmetnNumber += firstSubjectNumber;
                        departmentListNumber.add(deartpmetnNumber);
                        areaNumber += deartpmetnNumber;
                    }
                    areaListNumber.add(areaNumber);
                }
                subjectNumber = allNumber;
                subjectListNumber.add(subjectNumber);
                allListNumber.addAll(departmentListNumber);
                allListNumber.addAll(subjectListNumber);
                allListNumber.addAll(areaListNumber);
                integerListMap.put(i, allListNumber);
            }

        }

        //导出
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(subjectIntroExports, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = list.size();
            List<Field> fields = ClazzUtils.getFields(SubjectIntroExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);

            int index = 0;
            int lastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                int x = 0;
                //List<int> maxList所有子表的长度
                if (null != integerListMap.get(j) && integerListMap.get(j).size() > 0) {
//                    PositionWorkDetails mainPwd = list.get(j);
                    int mergeRowCount = integerListMap.get(j).get(j);
                    //5
                    if (mergeRowCount != 1) {
                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        //合并主表共33列
                        for (int i = 1; i < 3; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                            x = 3;
                        }
                        //合并模快
                        Map<Integer, List<Integer>> mergeMLen = integerListMap;
                        //获取规划模块合并长度数据
                        List<Integer> ghMergeLen = mergeMLen.get(j);
                        if (null != ghMergeLen && ghMergeLen.size() > 0) {
                            int mfirstRow = firstRow;
                            int mMergeRowCount = 0;
                            for (Integer mi : ghMergeLen) {
                                if (mi != 1) {
                                    int mlastRow = (firstRow - 1) + mMergeRowCount + mi;
                                    for (int z = x; z < x + 19; z++) {
                                        sheet.addMergedRegion(new CellRangeAddress(mfirstRow, mlastRow, z, z));
                                    }
                                    x += 19;
                                    mfirstRow = mlastRow + 1;
                                    mMergeRowCount = mMergeRowCount + mi;
                                }
                            }
                        }

                        lastRow--;
                    }
                }
                lastRow++;
                index = lastRow + 1;
            }


            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }


    private List<FirstSubjectBO> figureCollect(LocalDate[] dates, String firstSubject) throws SerException {
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", dates));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerateBO> boList = listNoPage(voucherGenerateDTO);
        Set<String> voucherDates = boList.stream().map(p -> p.getVoucherDate()).collect(Collectors.toSet());
        List<FirstSubjectBO> firstSubjectBOS = new ArrayList<>();
        if (StringUtils.isNotBlank(firstSubject)) {
            for (String voucherDate : voucherDates) {
                FirstSubjectBO firstSubjectBO = getFirstSubjectBO(voucherDate, firstSubject);
                firstSubjectBOS.add(firstSubjectBO);
            }
        } else {
            firstSubjectBOS = figureCollect(dates);
        }
        return firstSubjectBOS;
    }


    private List<FirstSubjectBO> figureCollect(LocalDate[] dates) throws SerException {
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", dates));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerateBO> boList = voucherGenerateAPI.listNoPage(voucherGenerateDTO);
        List<FirstSubjectBO> firstSubjectBOS = new ArrayList<>();
        if (boList != null && boList.size() > 0) {
            Set<String> firstSubjects = boList.stream().map(p -> p.getFirstSubject()).collect(Collectors.toSet());
            Set<String> voucherDates = boList.stream().map(p -> p.getVoucherDate()).collect(Collectors.toSet());
            for (String voucherDate : voucherDates) {
                for (String firstSubject : firstSubjects) {
                    FirstSubjectBO firstSubjectBO = getFirstSubjectBO(voucherDate, firstSubject);
                    firstSubjectBOS.add(firstSubjectBO);
                }
                Map<String, List<Integer>> map = new HashMap<>();
            }
        }


        return firstSubjectBOS;
    }


    private FirstSubjectBO getFirstSubjectBO(String time, String firstSubject) throws SerException {
        LocalDate voucherDate = DateUtil.parseDate(time);
        FirstSubjectBO firstSubjectBO = new FirstSubjectBO();
        VoucherGenerateDTO voucherGenerateDTO1 = new VoucherGenerateDTO();
        voucherGenerateDTO1.getConditions().add(Restrict.eq("firstSubject", firstSubject));
        voucherGenerateDTO1.getConditions().add(Restrict.eq("voucherDate", voucherDate));
        List<VoucherGenerateBO> voucherGenerateBOS = voucherGenerateAPI.listNoPage(voucherGenerateDTO1);
        Set<String> areas = voucherGenerateBOS.stream().map(p -> p.getArea()).collect(Collectors.toSet());
        List<AreaSubjectBO> areaSubjectBOS = new ArrayList<>();
        for (String area : areas) {
            AreaSubjectBO areaSubjectBO = new AreaSubjectBO();
            VoucherGenerateDTO voucherGenerateDTO5 = new VoucherGenerateDTO();
            voucherGenerateDTO5.getConditions().add(Restrict.eq("firstSubject", firstSubject));
            voucherGenerateDTO5.getConditions().add(Restrict.eq("voucherDate", voucherDate));
            voucherGenerateDTO5.getConditions().add(Restrict.eq("area", area));
            List<VoucherGenerateBO> voucherGenerateBOS4 = listNoPage(voucherGenerateDTO5);
            Set<String> departments = voucherGenerateBOS4.stream().map(p -> p.getProjectGroup()).collect(Collectors.toSet());
            List<DepartmentSubjectBO> departmentSubjectBOS = new ArrayList<>();
            for (String department : departments) {
                DepartmentSubjectBO departmentSubjectBO = new DepartmentSubjectBO();
                VoucherGenerateDTO voucherGenerateDTO2 = new VoucherGenerateDTO();
                voucherGenerateDTO2.getConditions().add(Restrict.eq("firstSubject", firstSubject));
                voucherGenerateDTO2.getConditions().add(Restrict.eq("projectGroup", department));
                voucherGenerateDTO2.getConditions().add(Restrict.eq("voucherDate", voucherDate));
                List<VoucherGenerateBO> voucherGenerateBOS1 = listVoucherGenerate(voucherGenerateDTO1);
                Set<String> projects = voucherGenerateBOS1.stream().map(p -> p.getProjectName()).collect(Collectors.toSet());
                List<SubjectCollectsBO> subjectCollectsBOS = new ArrayList<>();
                for (String project : projects) {
                    //期初借方余额
                    Double beginBorrowMoney = 0.0;
                    //期初贷方余额
                    Double beginLoanMoney = 0.0;
                    //本期借方余额
                    Double currentBorrowMoney = 0.0;
                    //本期贷方余额
                    Double currentLoanMoney = 0.0;
                    //期末借方发生额
                    Double endBorrowMoney = 0.0;
                    //期末贷方发生额
                    Double endLoanMoney = 0.0;
                    //本年借方统计
                    Double currentYearBorrowMoney = 0.0;
                    //本年贷方统计
                    Double currentYearLoanMoney = 0.0;

                    VoucherGenerateDTO voucherGenerateDTO3 = new VoucherGenerateDTO();
                    voucherGenerateDTO3.getConditions().add(Restrict.eq("firstSubject", firstSubject));
                    voucherGenerateDTO3.getConditions().add(Restrict.eq("projectGroup", department));
                    voucherGenerateDTO3.getConditions().add(Restrict.eq("projectName", project));
                    voucherGenerateDTO3.getConditions().add(Restrict.eq("voucherDate", voucherDate));
                    List<VoucherGenerateBO> voucherGenerateBOS2 = listNoPage(voucherGenerateDTO3);

                    Integer year = Integer.valueOf(time.substring(0, 4));
                    Integer month = Integer.valueOf(time.substring(5, 7));
                    LocalDate startDate = DateUtil.getStartDayOfMonth(year, month);
                    LocalDate endDate = startDate.minusDays(1);
                    VoucherGenerateDTO voucherGenerateDTO4 = new VoucherGenerateDTO();
                    voucherGenerateDTO4.getConditions().add(Restrict.eq("firstSubject", firstSubject));
                    voucherGenerateDTO4.getConditions().add(Restrict.eq("projectGroup", department));
                    voucherGenerateDTO4.getConditions().add(Restrict.eq("projectName", project));
                    voucherGenerateDTO4.getConditions().add(Restrict.eq("voucherDate", endDate));
                    List<VoucherGenerateBO> voucherGenerateBOS3 = listNoPage(voucherGenerateDTO4);

                    LocalDate startTime = DateUtil.parseDate(year + "-01-01");
                    LocalDate endTime = DateUtil.parseDate(year + "-12-31");
                    LocalDate[] times = new LocalDate[]{startTime, endTime};
                    VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
                    voucherGenerateDTO.getConditions().add(Restrict.eq("firstSubject", firstSubject));
                    voucherGenerateDTO.getConditions().add(Restrict.eq("projectGroup", department));
                    voucherGenerateDTO.getConditions().add(Restrict.eq("projectName", project));
                    voucherGenerateDTO.getConditions().add(Restrict.between("voucherDate", times));
                    List<VoucherGenerateBO> voucherGenerateBOS5 = listNoPage(voucherGenerateDTO);

                    if (voucherGenerateBOS3 != null && voucherGenerateBOS3.size() > 0) {
                        beginBorrowMoney = voucherGenerateBOS3.stream().filter(p -> p.getBorrowMoney() != null).mapToDouble(p -> p.getBorrowMoney()).sum();
                        beginLoanMoney = voucherGenerateBOS3.stream().filter(p -> p.getLoanMoney() != null).mapToDouble(p -> p.getLoanMoney()).sum();
                    }

                    if (voucherGenerateBOS2 != null && voucherGenerateBOS2.size() > 0) {
                        currentBorrowMoney = voucherGenerateBOS2.stream().filter(p -> p.getBorrowMoney() != null).mapToDouble(p -> p.getBorrowMoney()).sum();
                        currentLoanMoney = voucherGenerateBOS2.stream().filter(p -> p.getLoanMoney() != null).mapToDouble(p -> p.getLoanMoney()).sum();
                    }

                    if (voucherGenerateBOS5 != null && voucherGenerateBOS5.size() > 0) {
                        currentYearBorrowMoney = voucherGenerateBOS5.stream().filter(p -> p.getBorrowMoney() != null).mapToDouble(p -> p.getBorrowMoney()).sum();
                        currentYearLoanMoney = voucherGenerateBOS5.stream().filter(p -> p.getLoanMoney() != null).mapToDouble(p -> p.getLoanMoney()).sum();
                    }
                    endBorrowMoney = beginBorrowMoney - currentBorrowMoney;
                    endLoanMoney = beginLoanMoney - currentLoanMoney;

                    SubjectCollectsBO subjectCollectsBO = new SubjectCollectsBO();
                    subjectCollectsBO.setProject(project);
                    subjectCollectsBO.setBeginBorrowMoney(beginBorrowMoney);
                    subjectCollectsBO.setBeginLoanMoney(beginLoanMoney);
                    subjectCollectsBO.setCurrentBorrowMoney(currentBorrowMoney);
                    subjectCollectsBO.setCurrentLoanMoney(currentLoanMoney);
                    subjectCollectsBO.setEndBorrowMoney(endBorrowMoney);
                    subjectCollectsBO.setEndLoanMoney(endLoanMoney);
                    subjectCollectsBO.setCurrentYearBorrowMoney(currentYearBorrowMoney);
                    subjectCollectsBO.setCurrentLoanMoney(currentLoanMoney);
                    subjectCollectsBOS.add(subjectCollectsBO);
                }
                departmentSubjectBO.setDepartment(department);
                departmentSubjectBO.setSubjectCollectList(subjectCollectsBOS);
                departmentSubjectBOS.add(departmentSubjectBO);
            }
            areaSubjectBO.setArea(area);
            areaSubjectBO.setDepartmentSubjectList(departmentSubjectBOS);
            areaSubjectBOS.add(areaSubjectBO);
        }
        firstSubjectBO.setFirstSubject(firstSubject);
        firstSubjectBO.setAreaSubjectList(areaSubjectBOS);
        return firstSubjectBO;
    }

    @Override
    public List<String> findFirstSubject() throws SerException {
        Set<String> set = new HashSet<>();
        List<VoucherGenerate> voucherGenerates = super.findAll();
        for (VoucherGenerate voucherGenerate : voucherGenerates) {
            set.add(voucherGenerate.getFirstSubject());
        }
        return new ArrayList<>(set);
    }
}

