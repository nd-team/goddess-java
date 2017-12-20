package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.fundrecords.enums.GuideAddrStatus;
import com.bjike.goddess.fundrecords.excel.FundRecordExcel;
import com.bjike.goddess.fundrecords.excel.SonPermissionObject;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资金流水业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundrecordsSerCache")
@Service
public class FundRecordSerImpl extends ServiceImpl<FundRecord, FundRecordDTO> implements FundRecordSer {
    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private ModuleAPI modulesAPI;
    private static Logger logger = Logger.getLogger(FundRecordSerImpl.class);

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FundRecordBO insertModel(FundRecordTO to) throws SerException {
        FundRecord model = BeanTransform.copyProperties(to, FundRecord.class, true);
//        model.setDataSource("资金流水");
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, FundRecordBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FundRecordBO updateModel(FundRecordTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            FundRecord model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("无法更新记账凭证查询的数据!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, FundRecordBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        FundRecord model = super.findById(id);
        if (model != null) {
            super.remove(id);
        } else {
            throw new SerException("非法Id,无法删除记账凭证查询的数据!");
        }
    }

    @Override
    public List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getDataSource())) {
            dto.getConditions().add(Restrict.like("dataSource", dto.getDataSource()));
        }
        List<FundRecordBO> returnList = findAllBO(dto, new VoucherGenerateDTO());
        return sortPageList(returnList, dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FundRecordBO> findAllBO(FundRecordDTO dto, VoucherGenerateDTO generateDTO) throws SerException {

        //查询所有补填记录(即资金流水记录)和记账凭证一级科目为“银行存款"或"库存现金"的记录排序再分页
        List<FundRecord> list = super.findByCis(dto);
        List<FundRecordBO> boList = BeanTransform.copyProperties(list, FundRecordBO.class);
        List<VoucherGenerateBO> generateList = voucherGenerateAPI.findFundRecord(generateDTO);
        List<FundRecordBO> voucherBOList = new ArrayList<FundRecordBO>();
        if (generateList != null && !generateList.isEmpty()) {
            for (VoucherGenerateBO generateBO : generateList) {
                FundRecordBO bo = new FundRecordBO();
                bo.setRecordDate(generateBO.getVoucherDate());
                bo.setArea(generateBO.getArea());
                bo.setProjectGroup(generateBO.getProjectGroup());
                bo.setProject(generateBO.getProjectName());
                bo.setDigest(generateBO.getSumary());
                bo.setIncome(generateBO.getBorrowMoney());
                bo.setExpenditure(generateBO.getLoanMoney());
                bo.setCreateTime(generateBO.getCreateTime());
                bo.setDataSource("记账凭证");
                voucherBOList.add(bo);
            }
        }

        List<FundRecordBO> returnList = new ArrayList<FundRecordBO>();
        if (boList != null && !boList.isEmpty()) {
            returnList.addAll(boList);
        }
        if (voucherBOList != null && !voucherBOList.isEmpty()) {
            returnList.addAll(voucherBOList);
        }

        return returnList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MonthCollectBO month(Integer year, Integer month) throws SerException {

        //查询month月的收入支出情况
        VoucherGenerateDTO generateDTO = new VoucherGenerateDTO();
        String start = DateUtil.dateToString(DateUtil.getStartDayOfMonth(year, month));
        String end = DateUtil.dateToString(DateUtil.getEndDaYOfMonth(year, month));
        String[] condition = new String[]{start, end};

        generateDTO.setStartTime(start);
        generateDTO.setEndTime(end);

        FundRecordDTO dto = new FundRecordDTO();
        dto.getConditions().add(Restrict.between("recordDate", condition));

        List<FundRecordBO> boList = findAllBO(dto, generateDTO);
        //查询month月前的收入支出情况
        VoucherGenerateDTO lastGenerateDTO = new VoucherGenerateDTO();
        lastGenerateDTO.getConditions().add(Restrict.lt("voucherDate", start));

        FundRecordDTO lastDTO = new FundRecordDTO();
        lastDTO.getConditions().add(Restrict.lt("recordDate", start));

        List<FundRecordBO> lastBOList = findAllBO(lastDTO, lastGenerateDTO);
        //month之前的收入
        Double lastIncome = lastBOList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //month之前的支出
        Double lastExpenditure = lastBOList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();
        //month月份的收入
        Double income = boList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //month月份的支出
        Double expenditure = boList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();

        MonthCollectBO returnBO = new MonthCollectBO();
        returnBO.setYear(year);
        returnBO.setMonth(month);
        returnBO.setLastBalance(lastIncome - lastExpenditure);
        returnBO.setIncome(income);
        returnBO.setExpenditure(expenditure);
        returnBO.setCurrentBalance(lastIncome - lastExpenditure + income - expenditure);
        return returnBO;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ConditionCollectBO> condition(CollectTO to) throws SerException {

        List<FundRecordBO> boList = getByMonth(to);
        return BeanTransform.copyProperties(boList, ConditionCollectBO.class);
    }

    public List<FundRecordBO> getByMonth(CollectTO to) throws SerException {
        //查询month月的收入支出情况
        VoucherGenerateDTO generateDTO = new VoucherGenerateDTO();
        String start = DateUtil.dateToString(DateUtil.getStartDayOfMonth(to.getYear(), to.getMonth()));
        String end = DateUtil.dateToString(DateUtil.getEndDaYOfMonth(to.getYear(), to.getMonth()));
        String[] condition = new String[]{start, end};

        generateDTO.setStartTime(start);
        generateDTO.setEndTime(end);

        FundRecordDTO dto = new FundRecordDTO();
        dto.getConditions().add(Restrict.between("recordDate", condition));
        if (!StringUtils.isEmpty(to.getArea())) {
            generateDTO.getConditions().add(Restrict.eq("area", to.getArea()));
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (!StringUtils.isEmpty(to.getProjectGroup())) {
            generateDTO.getConditions().add(Restrict.eq("projectGroup", to.getProjectGroup()));
            dto.getConditions().add(Restrict.eq("projectGroup", to.getProjectGroup()));

        }
        if (!StringUtils.isEmpty(to.getProject())) {
            generateDTO.getConditions().add(Restrict.eq("projectName", to.getProject()));
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        }

        return findAllBO(dto, generateDTO);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AreaAnalyzeBO> areaAnalyze(Integer year, Integer month, String area) throws SerException {

        //查询month月数据
        StringBuilder sql = new StringBuilder();
        //查询本表数据sql
        String currentTabSql = "select area,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                + year + " and month(recordDate) = " + month;
        sql.append(currentTabSql);
        if (!StringUtils.isEmpty(area)) {
            sql.append(" and area = '" + area + "'");
        }
        String[] fileds = new String[]{"area", "income", "expenditure"};

        List<AnalyzeBO> boList = super.findBySql(sql.toString(), AnalyzeBO.class, fileds);
        List<VoucherGenerateBO> vgList = voucherGenerateAPI.areaAnalyze(year, month, area);

        //组合本月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(boList, vgList, "area");

        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();
        //查询本表数据sql
        String lastCurrentTabSql = "";

        List<VoucherGenerateBO> lastVgList = null;
        if (month == 12) {
            lastCurrentTabSql = "select area,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) =  1 ";
            lastVgList = voucherGenerateAPI.areaAnalyze(year - 1, 1, area);
        } else {
            lastCurrentTabSql = "select area,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);
            lastVgList = voucherGenerateAPI.areaAnalyze(year, month - 1, area);
        }
        lastSql.append(lastCurrentTabSql);
        if (!StringUtils.isEmpty(area)) {
            sql.append(" and area = '" + area + "'");
        }

        List<AnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), AnalyzeBO.class, fileds);

        //组合上月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(lastBOList, lastVgList, "area");

        //获取所有地区
        List<String> areas = new ArrayList<String>();
        if (boList != null && !boList.isEmpty()) {
            for (AnalyzeBO bo : boList) {
                if (!areas.contains(bo.getArea())) {
                    areas.add(bo.getArea());
                }
            }
        }

        return BeanTransform.copyProperties(calculate(areas, boList, lastBOList, "area"), AreaAnalyzeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<GroupAnalyzeBO> groupAnalyze(Integer year, Integer month, String projectGroup) throws SerException {

        //查询month月数据
        StringBuilder sql = new StringBuilder();
        //查询本表数据sql
        String currentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                + year + " and month(recordDate) = " + month;
        sql.append(currentTabSql);
        if (!StringUtils.isEmpty(projectGroup)) {
            sql.append(" and projectGroup = '" + projectGroup + "'");
        }
        String[] fileds = new String[]{"projectGroup", "income", "expenditure"};

        List<AnalyzeBO> boList = super.findBySql(sql.toString(), AnalyzeBO.class, fileds);
        List<VoucherGenerateBO> vgList = voucherGenerateAPI.groupAnalyze(year, month, projectGroup);

        //组合本月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(boList, vgList, "projectGroup");

        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();
        //查询本表数据sql
        String lastCurrentTabSql = "";

        List<VoucherGenerateBO> lastVgList = null;
        if (month == 12) {
            lastCurrentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) =  1 ";
            lastVgList = voucherGenerateAPI.groupAnalyze(year - 1, 1, projectGroup);
        } else {
            lastCurrentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);
            lastVgList = voucherGenerateAPI.groupAnalyze(year, month - 1, projectGroup);
        }
        lastSql.append(lastCurrentTabSql);
        if (!StringUtils.isEmpty(projectGroup)) {
            sql.append(" and projectGroup = '" + projectGroup + "'");
        }

        List<AnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), AnalyzeBO.class, fileds);

        //组合上月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(lastBOList, lastVgList, "projectGroup");

        //获取所有项目组
        List<String> projectGroups = new ArrayList<String>();
        if (boList != null && !boList.isEmpty()) {
            for (AnalyzeBO bo : boList) {
                if (!projectGroups.contains(bo.getProjectGroup())) {
                    projectGroups.add(bo.getProjectGroup());
                }
            }
        }

        return BeanTransform.copyProperties(calculate(projectGroups, boList, lastBOList, "projectGroup"), GroupAnalyzeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectAnalyzeBO> projectAnalyze(Integer year, Integer month, String project) throws SerException {

        //查询month月数据
        StringBuilder sql = new StringBuilder();
        //查询本表数据sql
        String currentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                + year + " and month(recordDate) = " + month;
        sql.append(currentTabSql);
        if (!StringUtils.isEmpty(project)) {
            sql.append(" and project = '" + project + "'");
        }
        String[] fileds = new String[]{"project", "income", "expenditure"};

        List<AnalyzeBO> boList = super.findBySql(sql.toString(), AnalyzeBO.class, fileds);
        List<VoucherGenerateBO> vgList = voucherGenerateAPI.projectAnalyze(year, month, project);

        //组合本月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(boList, vgList, "project");

        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();
        //查询本表数据sql
        String lastCurrentTabSql = "";

        List<VoucherGenerateBO> lastVgList = null;
        if (month == 12) {
            lastCurrentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) =  1 ";
            lastVgList = voucherGenerateAPI.projectAnalyze(year - 1, 1, project);
        } else {
            lastCurrentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);
            lastVgList = voucherGenerateAPI.projectAnalyze(year, month - 1, project);
        }
        lastSql.append(lastCurrentTabSql);
        if (!StringUtils.isEmpty(project)) {
            sql.append(" and project = '" + project + "'");
        }

        List<AnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), AnalyzeBO.class, fileds);

        //组合上月所有数据（本表数据 + 记账凭证 对应数据）
        combineRecords(lastBOList, lastVgList, "project");

        //获取所有项目名称
        List<String> projects = new ArrayList<String>();
        if (boList != null && !boList.isEmpty()) {
            for (AnalyzeBO bo : boList) {
                if (!projects.contains(bo.getProject())) {
                    projects.add(bo.getProject());
                }
            }
        }

        return BeanTransform.copyProperties(calculate(projects, boList, lastBOList, "project"), ProjectAnalyzeBO.class);
    }

    //组合两表的资金流水记录
    public List<AnalyzeBO> combineRecords(List<AnalyzeBO> boList, List<VoucherGenerateBO> vgList, String type) {
        if (vgList != null && !vgList.isEmpty()) {
            for (VoucherGenerateBO vgBO : vgList) {
                AnalyzeBO bo = new AnalyzeBO();
                if (type.equals("area")) {
                    bo.setArea(vgBO.getArea());
                } else if (type.equals("projectGroup")) {
                    bo.setProjectGroup(vgBO.getProjectGroup());
                } else {
                    bo.setProject(vgBO.getProjectName());
                }

                bo.setIncome(vgBO.getBorrowMoney());
                bo.setExpenditure(vgBO.getLoanMoney());
                boList.add(bo);
            }
        }
        return boList;
    }

    //计算收入、支出情况
    public List<AnalyzeBO> calculate(List<String> items, List<AnalyzeBO> boList,
                                     List<AnalyzeBO> lastBOList, String type) throws SerException {
        List<AnalyzeBO> analyzeList = new ArrayList<AnalyzeBO>();
        //查询所有List
        List<FundRecordBO> allList = findAllBO(new FundRecordDTO(), new VoucherGenerateDTO());

        //总收入
        Double allIncome = allList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //总支出
        Double allExpenditure = allList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();

        if (!items.isEmpty()) {
            for (String item : items) {

                Double currentIncome = null;
                Double currentExp = null;

                Double lastIncome = null;
                Double lastExp = null;

                AnalyzeBO bo = new AnalyzeBO();
                if (type.equals("area")) {
                    bo.setArea(item);

                    currentIncome = boList.stream().filter(p -> p != null &&
                            p.getArea().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    currentExp = boList.stream().filter(p -> p != null &&
                            p.getArea().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();

                    lastIncome = lastBOList.stream().filter(p -> p != null &&
                            p.getArea().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    lastExp = lastBOList.stream().filter(p -> p != null &&
                            p.getArea().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();

                } else if (type.equals("projectGroup")) {
                    bo.setProjectGroup(item);

                    currentIncome = boList.stream().filter(p -> p != null &&
                            p.getProjectGroup().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    currentExp = boList.stream().filter(p -> p != null &&
                            p.getProjectGroup().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();

                    lastIncome = lastBOList.stream().filter(p -> p != null &&
                            p.getProjectGroup().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    lastExp = lastBOList.stream().filter(p -> p != null &&
                            p.getProjectGroup().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();

                } else {
                    bo.setProject(item);

                    currentIncome = boList.stream().filter(p -> p != null &&
                            p.getProject().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    currentExp = boList.stream().filter(p -> p != null &&
                            p.getProject().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();

                    lastIncome = lastBOList.stream().filter(p -> p != null &&
                            p.getProject().equals(item) && p.getIncome() != null)
                            .mapToDouble(p -> p.getIncome()).sum();
                    lastExp = lastBOList.stream().filter(p -> p != null &&
                            p.getProject().equals(item) && p.getExpenditure() != null)
                            .mapToDouble(p -> p.getExpenditure()).sum();
                }

                setReturnBoInfo(bo, currentIncome, currentExp, lastIncome, lastExp, allIncome, allExpenditure);

                analyzeList.add(bo);
            }
        }
        return analyzeList;
    }

    //set需求格式数据
    public void setReturnBoInfo(AnalyzeBO bo, Double currentIncome, Double currentExp,
                                Double lastIncome, Double lastExp, Double allIncome, Double allExpenditure) {
        bo.setIncome(currentIncome);
        bo.setExpenditure(currentExp);
        bo.setLastIncome(lastIncome);
        bo.setLastExpenditure(lastExp);
        bo.setIncomeSubtract(currentIncome - lastIncome);
        bo.setExpenditureSubtract(currentExp - lastExp);

        if (allIncome != 0.0) {
            bo.setIncomeRate(bo.getIncome() / allIncome);
        }
        if (allExpenditure != 0.0) {
            bo.setExpenditureRate(bo.getExpenditure() / allExpenditure);
        }
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        if (bo.getLastIncome() != 0.0) {
            if (bo.getIncome() - bo.getLastIncome() == 0) {
                bo.setIncomeGrowRate("0.00%");
            } else {
                bo.setIncomeGrowRate(df.format((bo.getIncome() - bo.getLastIncome()) / bo.getLastIncome() * 100) + "%");
            }
        }
        if (bo.getLastExpenditure() != 0.0) {
            if (bo.getExpenditure() - bo.getLastExpenditure() == 0) {
                bo.setIncomeGrowRate("0.00%");
            } else {
                bo.setExpenditureGrowRate(df.format((bo.getExpenditure() - bo.getLastExpenditure()) / bo.getLastExpenditure() * 100) + "%");
            }
        }
    }

    @Override
    public void leadExcel(List<FundRecordTO> toList) throws SerException {

        List<FundRecord> list = BeanTransform.copyProperties(toList, FundRecord.class, true);
        super.save(list);
    }

    @Override
    public byte[] exportExcel(String startDate, String endDate) throws SerException {

        FundRecordDTO dto = new FundRecordDTO();
        VoucherGenerateDTO generateDTO = new VoucherGenerateDTO();
        if (!StringUtils.isEmpty(startDate)) {
            dto.getConditions().add(Restrict.gt("createTime", startDate));
            generateDTO.setStartTime(startDate);
        }
        if (!StringUtils.isEmpty(endDate)) {
            dto.getConditions().add(Restrict.lt("createTime", endDate));
            generateDTO.setEndTime(endDate);
        }

        List<FundRecordBO> boList = findAllBO(dto, generateDTO);
        List<FundRecordExcel> excelList = new ArrayList<FundRecordExcel>();
        if (!CollectionUtils.isEmpty(boList)) {
            for (FundRecordBO bo : boList) {
                FundRecordExcel excel = new FundRecordExcel();
                BeanUtils.copyProperties(bo, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new FundRecordExcel());
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcelLJT(String dataSource) throws SerException {
        FundRecordDTO dto = new FundRecordDTO();
        if (StringUtils.isNotBlank(dataSource)) {
            dto.getConditions().add(Restrict.eq("dataSource", dataSource));
        }

        List<FundRecord> list = super.findByCis(dto);
        List<FundRecordExcel> excelList = new ArrayList<FundRecordExcel>();
        if (!CollectionUtils.isEmpty(list)) {
            for (FundRecord fundRecord : list) {
                FundRecordExcel excel = new FundRecordExcel();
                BeanUtils.copyProperties(fundRecord, excel);
                excelList.add(excel);
            }
        } else {
            excelList.add(new FundRecordExcel());
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(excelList, excel);
        return bytes;
    }

    @Override
    public byte[] exportExcelModule() throws SerException {
        Excel excel = new Excel(0, 2);
        List<FundRecordExcel> list = new ArrayList<FundRecordExcel>();
        list.add(new FundRecordExcel());
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("fundrecord");
        obj.setDescribesion("资金流水记录");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case DELETE:
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
            case MONTH:
                flag = guideSeeIdentity();
                break;
            case CONDITION:
                flag = guideSeeIdentity();
                break;
            case AREA:
                flag = guideSeeIdentity();
                break;
            case PROJECT:
                flag = guideSeeIdentity();
                break;
            case GROUP:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    //排序并分页数据
    public List<FundRecordBO> sortPageList(List<FundRecordBO> boList, FundRecordDTO dto) {
        Collections.sort(boList, new Comparator<FundRecordBO>() {
            @Override
            public int compare(FundRecordBO bo1, FundRecordBO bo2) {
                return bo1.getCreateTime().compareTo(bo2.getCreateTime());
            }
        });
        //重新截取分页
        Integer pageSize = dto.getLimit();
        Integer page = dto.getPage();
        Integer pageHead = pageSize * page;

        Integer pageTrail = pageSize + pageSize * page;
        if (pageTrail >= boList.size()) {
            pageTrail = boList.size();
        }
        return boList.subList(pageHead, pageTrail);
    }

    @Override
    public List<String> sourceAccountValue() throws SerException {
        List<VoucherGenerateBO> voucherGenerateBOS = voucherGenerateAPI.findByCourseName();
        List<String> sourceAccounts = new ArrayList<>();
        if (voucherGenerateBOS != null && voucherGenerateBOS.size() > 0) {
            for (VoucherGenerateBO voucherGenerateBO : voucherGenerateBOS) {
                String sourceAccount = voucherGenerateBO.getFirstSubject();
                if (StringUtils.isNotBlank(voucherGenerateBO.getSecondSubject())) {
                    sourceAccount += voucherGenerateBO.getSecondSubject();
                }
                if (StringUtils.isNotBlank(voucherGenerateBO.getThirdSubject())) {
                    sourceAccount += voucherGenerateBO.getThirdSubject();
                }
                sourceAccounts.add(sourceAccount);
            }
        }
        return sourceAccounts;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void exportFund() throws SerException {
        List<VoucherGenerateBO> voucherGenerateBOS = new ArrayList<>();
        if (modulesAPI.isCheck("voucher")) {
            voucherGenerateBOS = voucherGenerateAPI.findByCourseName();
        }
//        logger.info("voucherGenerateBOS.size()"+ voucherGenerateBOS.size());
        if (voucherGenerateBOS != null && voucherGenerateBOS.size() > 0) {
            List<FundRecord> fundRecordList = new ArrayList<>();
            for (VoucherGenerateBO voucherGenerateBO : voucherGenerateBOS) {
                FundRecord fundRecord = new FundRecord();
                String str = voucherGenerateBO.getFirstSubject();
                if (StringUtils.isNotBlank(voucherGenerateBO.getSecondSubject())) {
                    str += "-" + voucherGenerateBO.getSecondSubject();
                }
                if (StringUtils.isNotBlank(voucherGenerateBO.getThirdSubject())) {
                    str += "-" + voucherGenerateBO.getThirdSubject();
                }
                Double amount = 0d;
                if (modulesAPI.isCheck("financeinit")) {
                    amount = accountAPI.findTotalAmount();
                }
                fundRecord.setRecordDate(LocalDate.now());
                fundRecord.setArea(voucherGenerateBO.getArea());//地区
                fundRecord.setProjectGroup(voucherGenerateBO.getProjectGroup());//项目组
                fundRecord.setProject(voucherGenerateBO.getProjectName());//项目名称
                fundRecord.setDigest(voucherGenerateBO.getSumary());//摘要
                fundRecord.setIncome(voucherGenerateBO.getBorrowMoney());//收入
                fundRecord.setExpenditure(voucherGenerateBO.getLoanMoney());//支出
                fundRecord.setDataSource(str);//数据来源
                fundRecord.setAmount(amount + voucherGenerateBO.getBorrowMoney() - voucherGenerateBO.getLoanMoney());//金额
                fundRecordList.add(fundRecord);
//                logger.info("fundRecord"+ fundRecord);
            }
//            logger.info("fundRecordList"+ fundRecordList);
            super.save(fundRecordList);
        }
    }

    @Override
    public MonthCollectBO monthSumma(Integer year, Integer month) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String lastStartDate = DateUtil.dateToString(DateUtil.parseDate(startDate).minusMonths(1));
        String lastEndDate = DateUtil.dateToString(DateUtil.parseDate(endDate).minusMonths(1));
        List<FundRecord> fundRecordList1 = findByDate(new String[]{startDate, endDate});//查询月数据
        List<FundRecord> fundRecordList2 = findByDate(new String[]{lastStartDate, lastEndDate});//查询月上月数据

        MonthCollectBO monthCollectBO = new MonthCollectBO();
        if (fundRecordList1 != null && fundRecordList1.size() > 0) {
            Double lastBalance = fundRecordList1.stream().mapToDouble(FundRecord::getAmount).sum();//本月余额
            Double currentBalance = 0d;//本月余额
            if (fundRecordList2 != null && fundRecordList2.size() > 0) {
                currentBalance = fundRecordList2.stream().mapToDouble(FundRecord::getAmount).sum();//上月余额
            }
            Double income = fundRecordList1.stream().mapToDouble(FundRecord::getIncome).sum();//收入
            Double expenditure = fundRecordList1.stream().mapToDouble(FundRecord::getExpenditure).sum();//支出
            monthCollectBO.setYear(year);
            monthCollectBO.setMonth(month);
            monthCollectBO.setLastBalance(lastBalance);
            monthCollectBO.setCurrentBalance(currentBalance);
            monthCollectBO.setIncome(income);
            monthCollectBO.setExpenditure(expenditure);
            monthCollectBO.setIncurredAmount(income - expenditure);
        }
        return monthCollectBO;
    }

    public List<FundRecord> findByDate(String[] recordDates) throws SerException {
        FundRecordDTO fundRecordDTO = new FundRecordDTO();
        fundRecordDTO.getConditions().add(Restrict.between("recordDate", recordDates));
        List<FundRecord> fundRecordList = super.findByCis(fundRecordDTO);
        return fundRecordList;
    }

    @Override
    public ConditionCollectBO areaSumma(Integer year, Integer month, String area) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        FundRecordDTO fundRecordDTO = new FundRecordDTO();
        fundRecordDTO.getConditions().add(Restrict.between("recordDate", new String[]{startDate, endDate}));
        fundRecordDTO.getConditions().add(Restrict.eq("area", area));
        List<FundRecord> fundRecordList = super.findByCis(fundRecordDTO);
        ConditionCollectBO conditionCollectBO = new ConditionCollectBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            conditionCollectBO.setRecordDate(year + "-" + month);
            conditionCollectBO.setArea(area);
            conditionCollectBO.setIncome(income);
            conditionCollectBO.setExpenditure(expenditure);
        }
        return conditionCollectBO;
    }

    @Override
    public ConditionCollectBO projectSumma(Integer year, Integer month, String project) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        FundRecordDTO fundRecordDTO = new FundRecordDTO();
        fundRecordDTO.getConditions().add(Restrict.between("recordDate", new String[]{startDate, endDate}));
        fundRecordDTO.getConditions().add(Restrict.eq("projectGroup", project));
        List<FundRecord> fundRecordList = super.findByCis(fundRecordDTO);
        ConditionCollectBO conditionCollectBO = new ConditionCollectBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            conditionCollectBO.setRecordDate(year + "-" + month);
            conditionCollectBO.setProjectGroup(project);
            conditionCollectBO.setIncome(income);
            conditionCollectBO.setExpenditure(expenditure);
        }
        return conditionCollectBO;
    }

    @Override
    public ConditionCollectBO projectNameSumma(Integer year, Integer month, String projectName) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        FundRecordDTO fundRecordDTO = new FundRecordDTO();
        fundRecordDTO.getConditions().add(Restrict.between("recordDate", new String[]{startDate, endDate}));
        fundRecordDTO.getConditions().add(Restrict.eq("project", projectName));
        List<FundRecord> fundRecordList = super.findByCis(fundRecordDTO);
        ConditionCollectBO conditionCollectBO = new ConditionCollectBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            conditionCollectBO.setRecordDate(year + "-" + month);
            conditionCollectBO.setProject(projectName);
            conditionCollectBO.setIncome(income);
            conditionCollectBO.setExpenditure(expenditure);
        }
        return conditionCollectBO;
    }

    @Override
    public List<String> findAllArea() throws SerException {
        List<FundRecord> fundRecordList = super.findAll();
        if (CollectionUtils.isEmpty(fundRecordList)) {
            return Collections.emptyList();
        }
        return fundRecordList.stream().map(FundRecord::getArea).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findAllDataSource() throws SerException {
        List<FundRecord> fundRecordList = super.findAll();
        if (CollectionUtils.isEmpty(fundRecordList)) {
            return Collections.emptyList();
        }
        return fundRecordList.stream().map(FundRecord::getDataSource).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findAllProjectGroup() throws SerException {
        List<FundRecord> fundRecordList = super.findAll();
        if (CollectionUtils.isEmpty(fundRecordList)) {
            return Collections.emptyList();
        }
        return fundRecordList.stream().map(FundRecord::getProjectGroup).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findAllProjectName() throws SerException {
        List<FundRecord> fundRecordList = super.findAll();
        if (CollectionUtils.isEmpty(fundRecordList)) {
            return Collections.emptyList();
        }
        return fundRecordList.stream().map(FundRecord::getProject).distinct().collect(Collectors.toList());

    }

    @Override
    public AreaAnalyzeBO areaAnalysis(Integer year, Integer month, String area) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String lastStartDate = DateUtil.dateToString(DateUtil.parseDate(startDate).minusMonths(1));
        String lastEndDate = DateUtil.dateToString(DateUtil.parseDate(endDate).minusMonths(1));
        String[] fields = new String[]{"recordDate", "area", "projectGroup", "project", "digest", "income", "expenditure", "amount", "dataSource"};
        String sql1 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "' and area = '" + area + "'";
        List<FundRecord> fundRecordList = super.findBySql(sql1, FundRecord.class, fields);
        String sql2 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "' and area = '" + area + "'";
        List<FundRecord> fundRecordList2 = super.findBySql(sql2, FundRecord.class, fields);
        String sql3 = "SELECT ifnull(sum(income),0) as totalIncome FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "'";
        List<Object> income_obj = super.findBySql(sql3);
        Double totalIncome = Double.parseDouble(String.valueOf(income_obj.get(0)));
        String sql4 = "SELECT ifnull(sum(expenditure),0) as totalExpenditure FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "'";
        List<Object> totalExpenditure_obj = super.findBySql(sql4);
        Double totalExpenditure = Double.parseDouble(String.valueOf(totalExpenditure_obj.get(0)));

        AreaAnalyzeBO areaAnalyzeBO = new AreaAnalyzeBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            Double lastIncome = 0d;
            Double lastExpenditure = 0d;
            if (fundRecordList2 != null && fundRecordList2.size() > 0) {
                lastIncome = fundRecordList2.stream().mapToDouble(FundRecord::getIncome).sum();
                lastExpenditure = fundRecordList2.stream().mapToDouble(FundRecord::getExpenditure).sum();
            }
            areaAnalyzeBO.setRecordDate(year + "-" + month);
            areaAnalyzeBO.setArea(area);
            areaAnalyzeBO.setIncome(income);
            areaAnalyzeBO.setExpenditure(expenditure);
            areaAnalyzeBO.setLastIncome(lastIncome);
            areaAnalyzeBO.setLastExpenditure(lastExpenditure);
            areaAnalyzeBO.setIncomeSubtract(income - lastIncome);
            areaAnalyzeBO.setExpenditureSubtract(expenditure - lastExpenditure);
            areaAnalyzeBO.setIncomeRate(totalIncome == 0d ? 0d : income / totalIncome);
            areaAnalyzeBO.setExpenditureRate(totalExpenditure == 0d ? 0d : expenditure / totalExpenditure);
            areaAnalyzeBO.setIncomeGrowRate(areaAnalyzeBO.getIncomeSubtract() == 0d ? 0d + "%" : lastIncome / areaAnalyzeBO.getIncomeSubtract() + "%");
            areaAnalyzeBO.setExpenditureGrowRate(areaAnalyzeBO.getExpenditureSubtract() == 0d ? 0d + "%" : lastExpenditure / areaAnalyzeBO.getExpenditureSubtract() + "%");
        }
        return areaAnalyzeBO;
    }

    @Override
    public GroupAnalyzeBO projectAnalysis(Integer year, Integer month, String project) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String lastStartDate = DateUtil.dateToString(DateUtil.parseDate(startDate).minusMonths(1));
        String lastEndDate = DateUtil.dateToString(DateUtil.parseDate(endDate).minusMonths(1));
        String[] fields = new String[]{"recordDate", "area", "projectGroup", "project", "digest", "income", "expenditure", "amount", "dataSource"};
        String sql1 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "' and projectGroup = '" + project + "'";
        List<FundRecord> fundRecordList = super.findBySql(sql1, FundRecord.class, fields);
        String sql2 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "' and projectGroup = '" + project + "'";
        List<FundRecord> fundRecordList2 = super.findBySql(sql2, FundRecord.class, fields);
        String sql3 = "SELECT ifnull(sum(income),0) as totalIncome FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "'";
        List<Object> income_obj = super.findBySql(sql3);
        Double totalIncome = Double.parseDouble(String.valueOf(income_obj.get(0)));
        String sql4 = "SELECT ifnull(sum(expenditure),0) as totalExpenditure FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "'";
        List<Object> totalExpenditure_obj = super.findBySql(sql4);
        Double totalExpenditure = Double.parseDouble(String.valueOf(totalExpenditure_obj.get(0)));

        GroupAnalyzeBO groupAnalyzeBO = new GroupAnalyzeBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            Double lastIncome = 0d;
            Double lastExpenditure = 0d;
            if (fundRecordList2 != null && fundRecordList2.size() > 0) {
                lastIncome = fundRecordList2.stream().mapToDouble(FundRecord::getIncome).sum();
                lastExpenditure = fundRecordList2.stream().mapToDouble(FundRecord::getExpenditure).sum();
            }
            groupAnalyzeBO.setRecordDate(year + "-" + month);
            groupAnalyzeBO.setProjectGroup(project);
            groupAnalyzeBO.setIncome(income);
            groupAnalyzeBO.setExpenditure(expenditure);
            groupAnalyzeBO.setLastIncome(lastIncome);
            groupAnalyzeBO.setLastExpenditure(lastExpenditure);
            groupAnalyzeBO.setIncomeSubtract(income - lastIncome);
            groupAnalyzeBO.setExpenditureSubtract(expenditure - lastExpenditure);
            groupAnalyzeBO.setIncomeRate(totalIncome == 0d ? 0d : income / totalIncome);
            groupAnalyzeBO.setExpenditureRate(totalExpenditure == 0d ? 0d : expenditure / totalExpenditure);
            groupAnalyzeBO.setIncomeGrowRate(groupAnalyzeBO.getIncomeSubtract() == 0d ? 0d + "%" : lastIncome / groupAnalyzeBO.getIncomeSubtract() + "%");
            groupAnalyzeBO.setExpenditureGrowRate(groupAnalyzeBO.getExpenditureSubtract() == 0d ? 0d + "%" : lastExpenditure / groupAnalyzeBO.getExpenditureSubtract() + "%");
        }
        return groupAnalyzeBO;
    }

    @Override
    public ProjectAnalyzeBO projectNameAnalysis(Integer year, Integer month, String projectName) throws SerException {
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String lastStartDate = DateUtil.dateToString(DateUtil.parseDate(startDate).minusMonths(1));
        String lastEndDate = DateUtil.dateToString(DateUtil.parseDate(endDate).minusMonths(1));
        String[] fields = new String[]{"recordDate", "area", "projectGroup", "project", "digest", "income", "expenditure", "amount", "dataSource"};
        String sql1 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "' and project = '" + projectName + "'";
        List<FundRecord> fundRecordList = super.findBySql(sql1, FundRecord.class, fields);
        String sql2 = "SELECT recordDate,area,projectGroup,project,digest,income,expenditure,amount,dataSource FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "' and project = '" + projectName + "'";
        List<FundRecord> fundRecordList2 = super.findBySql(sql2, FundRecord.class, fields);
        String sql3 = "SELECT ifnull(sum(income),0) as totalIncome FROM fundrecords_fundrecord where recordDate BETWEEN '" + startDate + "' and '" + endDate + "'";
        List<Object> income_obj = super.findBySql(sql3);
        Double totalIncome = Double.parseDouble(String.valueOf(income_obj.get(0)));
        String sql4 = "SELECT ifnull(sum(expenditure),0) as totalExpenditure FROM fundrecords_fundrecord where recordDate BETWEEN '" + lastStartDate + "' and '" + lastEndDate + "'";
        List<Object> totalExpenditure_obj = super.findBySql(sql4);
        Double totalExpenditure = Double.parseDouble(String.valueOf(totalExpenditure_obj.get(0)));

        ProjectAnalyzeBO projectAnalyzeBO = new ProjectAnalyzeBO();
        if (fundRecordList != null && fundRecordList.size() > 0) {
            Double income = fundRecordList.stream().mapToDouble(FundRecord::getIncome).sum();
            Double expenditure = fundRecordList.stream().mapToDouble(FundRecord::getExpenditure).sum();
            Double lastIncome = 0d;
            Double lastExpenditure = 0d;
            if (fundRecordList2 != null && fundRecordList2.size() > 0) {
                lastIncome = fundRecordList2.stream().mapToDouble(FundRecord::getIncome).sum();
                lastExpenditure = fundRecordList2.stream().mapToDouble(FundRecord::getExpenditure).sum();
            }
            projectAnalyzeBO.setRecordDate(year + "-" + month);
            projectAnalyzeBO.setProject(projectName);
            projectAnalyzeBO.setIncome(income);
            projectAnalyzeBO.setExpenditure(expenditure);
            projectAnalyzeBO.setLastIncome(lastIncome);
            projectAnalyzeBO.setLastExpenditure(lastExpenditure);
            projectAnalyzeBO.setIncomeSubtract(income - lastIncome);
            projectAnalyzeBO.setExpenditureSubtract(expenditure - lastExpenditure);
            projectAnalyzeBO.setIncomeRate(totalIncome == 0d ? 0d : income / totalIncome);
            projectAnalyzeBO.setExpenditureRate(totalExpenditure == 0d ? 0d : expenditure / totalExpenditure);
            projectAnalyzeBO.setIncomeGrowRate(projectAnalyzeBO.getIncomeSubtract() == 0d ? 0d + "%" : lastIncome / projectAnalyzeBO.getIncomeSubtract() + "%");
            projectAnalyzeBO.setExpenditureGrowRate(projectAnalyzeBO.getExpenditureSubtract() == 0d ? 0d + "%" : lastExpenditure / projectAnalyzeBO.getExpenditureSubtract() + "%");
        }
        return projectAnalyzeBO;
    }
}