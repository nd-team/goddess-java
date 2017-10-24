package com.bjike.goddess.voucher.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.api.FirstSubjectAPI;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.bo.*;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateExportDTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.entity.VoucherTotal;
import com.bjike.goddess.voucher.enums.*;
import com.bjike.goddess.voucher.excel.AccountInfoExport;
import com.bjike.goddess.voucher.excel.SonPermissionObject;
import com.bjike.goddess.voucher.excel.VoucherExportExcel;
import com.bjike.goddess.voucher.excel.VoucherTemplateExportExcel;
import com.bjike.goddess.voucher.to.AnalysisTO;
import com.bjike.goddess.voucher.to.GuidePermissionTO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
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
    private VoucherTotalSer voucherTotalSer;
    @Autowired
    private FirstSubjectAPI firstSubjectAPI;
    @Autowired
    private CategoryAPI categoryAPI;
    @Autowired
    private VoucherPermissionSer voucherPermissionSer;


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
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public VoucherGenerateBO getById(String id) throws SerException {
        VoucherGenerate vg = super.findById(id);
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
    public List<VoucherGenerateBO> antiCheckAccount(String[] ids) throws SerException {
        if (null == ids || ids.length <= 0) {
            throw new SerException("id不能为空,至少要有一个");
        }
        List<VoucherGenerate> list = new ArrayList<>(0);
        for (String id : ids) {
            VoucherGenerate vg = super.findById(id);
            vg.setCheckStatus(CheckStatus.NONE);
            vg.setModifyTime(LocalDateTime.now());
            super.update(vg);
            list.add(vg);
        }
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
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
        String month = "";
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
    public List<HistogramBO> ctReSubHistogram() throws SerException {
        String[] fields = new String[]{"borrowMoney", "loanMoney"};
        String year = String.valueOf(LocalDate.now().getYear());

        List<HistogramBO> histogramBOList = new ArrayList<>(0);
        for (int i = 1; i < 13; i++) {
            String startTime = year + "-" + i + "-01";
            String endTime = "";
            if (i <= 11) {
                int j = i + 1;
                endTime = year + "-" + j + "-01";
            } else {
                year = String.valueOf(LocalDate.now().getYear() + 1);
                endTime = year + "-01-01";
            }
            StringBuilder sql = new StringBuilder("select sum(borrowMoney) as borrowMoney, sum(loanMoney) as loanMoney from voucher_vouchergenerate ");
            sql.append(" WHERE voucherDate BETWEEN '" + startTime + "' and '" + endTime + "' ;");
            List<HistogramBO> histogramBOs = super.findBySql(sql.toString(), HistogramBO.class, fields);
            if (null != histogramBOs && histogramBOs.size() > 0) {
                HistogramBO histogramBO = histogramBOs.get(0);
                if (i < 10) {
                    histogramBO.setMonth(year + "-0" + i);
                } else if (i >= 10 && i < 12) {
                    histogramBO.setMonth(year + "-" + i);
                } else {
                    histogramBO.setMonth("2017-12");
                }
                histogramBOList.add(histogramBO);
            } else {
                HistogramBO histogramBO = new HistogramBO();
                if (i < 10) {
                    histogramBO.setMonth(year + "-0" + i);
                } else if (i >= 10 && i < 12) {
                    histogramBO.setMonth(year + "-" + i);
                } else {
                    histogramBO.setMonth("2017-12");
                }
                histogramBO.setBorrowMoney(0d);
                histogramBO.setLoanMoney(0d);
                histogramBOList.add(histogramBO);
            }
        }
        return histogramBOList;
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
        String startTime = month + "-01";
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
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
//        voucherGenerateDTO.getSorts().add("totalId=desc");
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null && listBO.size() > 0) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }


        return listBO;
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
                num = 0d;
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

        Double borrowSum = borrow.stream().mapToDouble(Double::shortValue).sum();
        Double loanSum = loan.stream().mapToDouble(Double::shortValue).sum();
        if (!borrowSum.equals(loanSum)) {
            throw new SerException("借方和贷方金额不相等，不能添加");
        }
        Double totalMoney = borrowSum;
        VoucherTotal vt = new VoucherTotal();
        vt.setMoney(totalMoney);
        vt.setCreateTime(LocalDateTime.now());
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.save(vt);

        String userName = voucherGenerateTO.getTicketer();
//        String userName = userBO.getUsername();
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

            list.add(temp);
        }
        super.save(list);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (StringUtils.isBlank(voucherGenerateTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }

        VoucherGenerate temp = super.findById(voucherGenerateTO.getId());
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("totalId", temp.getTotalId()));
        dto.getConditions().add(Restrict.ne("id", temp.getId()));
        List<VoucherGenerate> otherList = super.findByCis(dto);

        Double borrowSum = voucherGenerateTO.getBorrowMoneys().stream().mapToDouble(Double::shortValue).sum()
                + otherList.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum();
        Double loanSum = voucherGenerateTO.getLoanMoneys().stream().mapToDouble(Double::shortValue).sum()
                + otherList.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum();
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
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate voucherGenerate = super.findById(id);
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
        }
    }


    @Override
    public Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getSorts().add("totalId=desc");
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return listBO;
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
    public VoucherGenerateBO audit(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }

        VoucherGenerate vg = super.findById(id);
        vg.setAuditStatus(AuditStatus.CHECK);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }

    @Override
    public Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.NONE));
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.NONE));
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return listBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public Long posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
            throw new SerException("id数组不能为空,至少要有一个");
        }
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.in("id", voucherGenerateTO.getIds()));
        List<VoucherGenerate> vgList = super.findByCis(dto);
        vgList.stream().forEach(obj -> {
            obj.setTransferStatus(TransferStatus.CHECK);
            obj.setModifyTime(LocalDateTime.now());
        });
        super.update(vgList);
        //提示还有几张记账凭证未审核
        VoucherGenerateDTO voucherGenerateDTO = new VoucherGenerateDTO();
        return countAudit(voucherGenerateDTO);
//        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO antiAudit(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate vg = super.findById(id);
        vg.setAuditStatus(AuditStatus.NONE);
        vg.setTransferStatus(TransferStatus.NONE);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }


    @Override
    public List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and auditStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and auditStatus = 1 ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else {
            throw new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(group)) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
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
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
    }

    @Override
    public Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.NONE));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getSorts().add("totalId=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return listBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO antiPosting(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate vg = super.findById(id);
        vg.setTransferStatus(TransferStatus.NONE);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
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
        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and transferStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney , loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
    }

    @Override
    public Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws
            SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO
                                                        voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
        voucherGenerateDTO.getSorts().add("voucherDate=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }


        return listBO;
    }

    @Override
    public List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and checkStatus = 1 ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
    }

    @Override
    public Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getSorts().add("totalId=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        List<VoucherGenerateBO> listBO = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        if (listBO != null) {
            for (VoucherGenerateBO str : listBO) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());
            }
        }

        return listBO;
    }

    @Override
    public List<VoucherGenerateBO> ctReSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {

            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" from voucher_vouchergenerate where 1=1   ");
            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            sql.append(" group by firstSubject ");
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'   ");

            if (StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank(voucherGenerateDTO.getEndTime())) {
                sql.append(" and voucherDate between '" + voucherGenerateDTO.getStartTime() + "' and '" + voucherGenerateDTO.getEndTime() + "' ");

            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney , loanMoney ")

                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
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
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject ,  borrowMoney ,  loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
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

        List<VoucherGenerateBO> voucherGenerateBOs = BeanTransform.copyProperties(list, VoucherGenerateBO.class);
        voucherGenerateBOs.stream().forEach(obj -> {
            obj.setBorrowMoneyTotal(borrowMoneyTotal);
            obj.setLoanMoneyTotal(loanMoneyTotal);
        });
        return voucherGenerateBOs;
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
//        List<String> list = firstSubjectAPI.listAllFirst();
//        return list;
        //获取带有科目编号的一级科目
        List<String> list = firstSubjectAPI.listAllFirstAndCode();
        return list;
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        CategoryDTO cdto = new CategoryDTO();
        firstSub = firstSub.substring(firstSub.indexOf(":") + 1, firstSub.length());
        cdto.setFirstSubjectName(firstSub);
        List<String> list = categoryAPI.getSecondSubject(cdto);
        return list;
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        CategoryDTO cdto = new CategoryDTO();
        firstSub = firstSub.substring(firstSub.indexOf(":") + 1, firstSub.length());
        cdto.setFirstSubjectName(firstSub);
        cdto.setSecondSubject(secondSub);
        List<String> list = categoryAPI.getThirdSubject(cdto);
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
        List<AccountInfoBO> boList1 = null;
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
            //todo 从财务初始化根据财务会计科目拿取方向，新的财务初始化会有这个字段，暂时用remark来代替
            String sql = " SELECT remark AS direction FROM financeinit_firstsubject WHERE name='" + accountInfoBO.getFirstSubject() + "' ";
            String[] field = new String[]{"direction"};
            boList1 = super.findBySql(sql, AccountInfoBO.class, field);
            if (boList1 != null && !boList1.isEmpty()) {
                accountInfoBO.setDirection(boList1.get(0).getDirection());
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
        List<AccountInfoBO> boList = new ArrayList<>();
        List<AccountInfoBO> boList1 = null;
        List<VoucherGenerate> list = super.findByCis(dto);
        List<AccountInfoBO> accountInfoBOS = BeanTransform.copyProperties(list, AccountInfoBO.class);
        for (AccountInfoBO accountInfoBO : accountInfoBOS) {
            String sql = " SELECT remark AS direction FROM financeinit_firstsubject WHERE name='" + accountInfoBO.getFirstSubject() + "' ";
            String[] field = new String[]{"direction"};
            boList1 = super.findBySql(sql, AccountInfoBO.class, field);
            if (boList1 != null && !boList1.isEmpty()) {
                accountInfoBO.setDirection(boList1.get(0).getDirection());
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
        String[] fields = new String[]{"firstSubject"};
        String sql = "SELECT firstSubject AS firstSubject FROM voucher_vouchergenerate WHERE transferStatus=1 GROUP BY firstSubject ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, fields);
        List<String> firstSubjectList = list.stream().map(VoucherGenerate::getFirstSubject).collect(Collectors.toList());
        return firstSubjectList;
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
        if (StringUtils.isNotBlank(dto.getFirstSubject())) {
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
        voucherGenerateDTO.getSorts().add("totalId=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO);
        System.out.println(JSON.toJSON(list));

        List<VoucherExportExcel> firstSubjectExports = BeanTransform.copyProperties(list, VoucherExportExcel.class);
        XSSFWorkbook wb = null;//创建一个Excel文件
        if (firstSubjectExports != null && firstSubjectExports.size() > 0) {
            for (VoucherExportExcel str : firstSubjectExports) {
                VoucherTotal vt = voucherTotalSer.findById(str.getTotalId());
                str.setMoneyTotal(vt.getMoney());

            }
        }
        Excel excel = new Excel(0, 2);
        String tempString = excel.getSheetName();
        wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(tempString);//创建报销明细工作薄
        sheet.setDefaultRowHeight((short) 300);//设置默认行高
        // 设置execl工作簿中的列名
        String[] excelHeader = EXCELHEAD;//Excel表头
        XSSFCellStyle contentStyle = getStyle(wb, excel.getContentBGColor()); // 设置标题样式
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
    public void createRowDetail(List<VoucherExportExcel> list, XSSFRow row, XSSFSheet sheet) {

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

                }
                if (index == list.size() - 1) {
                    sheet = assiableMergeData(sheet, lastRow + 1 - showFlag, lastRow + 1);
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
            row.createCell(callIndex++).setCellValue(exportEntity.getAuditor());
            row.createCell(callIndex++).setCellValue(auditStr(exportEntity.getAuditStatus()));
            row.createCell(callIndex++).setCellValue(transferStr(exportEntity.getTransferStatus()));
            row.createCell(callIndex++).setCellValue(checkStr(exportEntity.getCheckStatus()));
            row.createCell(callIndex++).setCellValue(exportEntity.getMoneyTotal());
//            row.createCell(callIndex++).setCellValue(exportEntity.getTotalId());


        }
    }

    private XSSFSheet assiableMergeData(XSSFSheet sheet, int firstRow, int lastRow) {
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
            "二级科目", "三级科目", "借方金额", "贷方金额", "摘要", "地区", "项目名称", "项目组",
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
        XSSFCellStyle contentStyle = getStyle(wb, exceltemp.getContentBGColor()); // 设置标题样式
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
        }

        VoucherGenerateBO voucherGenerateBO = BeanTransform.copyProperties(new VoucherGenerate(), VoucherGenerateBO.class);
        return voucherGenerateBO;
    }

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
        lastMonth = lastMonth.substring(0, lastMonth.lastIndexOf("-"));
        return lastMonth;
    }

    private Double convert(Double value) {
        long l1 = Math.round(value * 100); //四舍五入
        double ret = l1 / 100.0; //注意:使用 100.0 而不是 100
        return ret;
    }

}