package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FundRecordBO insertModel(FundRecordTO to) throws SerException {
        FundRecord model = BeanTransform.copyProperties(to, FundRecord.class, true);
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
        try {
            super.remove(id);
        } catch (Exception e) {
            throw new SerException("无法删除记账凭证查询的数据!");
        }

    }

    @Override
    public List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException {
        List<FundRecordBO> returnList = findAllBO(dto, new VoucherGenerateDTO());
        return sortPageList(returnList, dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FundRecordBO> findAllBO(FundRecordDTO dto, VoucherGenerateDTO generateDTO) throws SerException {
        //查询所有补填记录和记账凭证一级科目为“银行存款"或"库存现金"的记录排序再分页
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
        //查询记账凭证数据sql
        String voucherTabSql = "select area,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;

        sql.append(" select records.area,sum(records.income) as income , sum(records.expenditure) as expenditure from ");
        sql.append(" ( " + currentTabSql);
        sql.append(" UNION ALL ");
        sql.append(voucherTabSql + " ) ");
        sql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(area)) {
            sql.append(" and records.area = '" + area + "'");
        }
        sql.append(" GROUP BY records.area ");

        String[] fileds = new String[]{"area", "income", "expenditure"};

        List<AreaAnalyzeBO> boList = super.findBySql(sql.toString(), AreaAnalyzeBO.class, fileds);


        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();
        //查询本表数据sql
        String lastCurrentTabSql = "";
        //查询记账凭证数据sql
        String lastVoucherTabSql = "";

        if (month == 12) {
            lastCurrentTabSql = "select area,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) =  1 ";

            lastVoucherTabSql = "select area,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + (year - 1) + " and month(voucherDate) = 1 ";
        } else {
            lastCurrentTabSql = "select area,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);

            lastVoucherTabSql = "select area,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + year + " and month(voucherDate) = " + (month - 1);
        }


        lastSql.append(" select records.area,sum(records.income),sum(records.expenditure) from ");
        lastSql.append(" ( " + lastCurrentTabSql);
        lastSql.append(" UNION ALL ");
        lastSql.append(lastVoucherTabSql + " ) ");
        lastSql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(area)) {
            lastSql.append(" and records.area = '" + area + "'");
        }
        lastSql.append(" GROUP BY records.area ");

        //查询所有List
        List<FundRecordBO> allList = findAllBO(new FundRecordDTO(), new VoucherGenerateDTO());

        List<AreaAnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), AreaAnalyzeBO.class, fileds);

        //总收入
        Double allIncome = allList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //总支出
        Double allExpenditure = allList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();

        for (AreaAnalyzeBO bo : boList) {
            for (AreaAnalyzeBO lastBO : lastBOList) {

                if (bo.getArea().equals(lastBO.getArea())) {

                    bo.setLastIncome(lastBO.getIncome());
                    bo.setLastExpenditure(lastBO.getExpenditure());
                    bo.setIncomeSubtract(bo.getIncome() - lastBO.getIncome());
                    bo.setExpenditureSubtract(bo.getExpenditure() - lastBO.getExpenditure());
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
                    break;
                }
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<GroupAnalyzeBO> groupAnalyze(Integer year, Integer month, String group) throws SerException {

        //查询month月数据
        StringBuilder sql = new StringBuilder();
        //查询本表数据sql
        String currentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                + year + " and month(recordDate) = " + month;
        //查询记账凭证数据sql
        String voucherTabSql = "select projectGroup,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;

        sql.append(" select records.projectGroup,sum(records.income) as income , sum(records.expenditure) as expenditure from ");
        sql.append(" ( " + currentTabSql);
        sql.append(" UNION ALL ");
        sql.append(voucherTabSql + " ) ");
        sql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(group)) {
            sql.append(" and records.projectGroup = '" + group + "'");
        }
        sql.append(" GROUP BY records.projectGroup ");

        String[] fileds = new String[]{"projectGroup", "income", "expenditure"};

        List<GroupAnalyzeBO> boList = super.findBySql(sql.toString(), GroupAnalyzeBO.class, fileds);


        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();

        //查询本表数据sql
        String lastCurrentTabSql = "";
        //查询记账凭证数据sql
        String lastVoucherTabSql = "";

        if (month == 12) {
            lastCurrentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) = 1 ";

            lastVoucherTabSql = "select projectGroup,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + (year - 1) + " and month(voucherDate) = 1 ";
        } else {
            lastCurrentTabSql = "select projectGroup,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);

            lastVoucherTabSql = "select projectGroup,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + year + " and month(voucherDate) = " + (month - 1);
        }


        lastSql.append(" select records.projectGroup,sum(records.income),sum(records.expenditure) from ");
        lastSql.append(" ( " + lastCurrentTabSql);
        lastSql.append(" UNION ALL ");
        lastSql.append(lastVoucherTabSql + " ) ");
        lastSql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(group)) {
            lastSql.append(" and records.projectGroup = '" + group + "'");
        }
        lastSql.append(" GROUP BY records.projectGroup ");

        //查询所有List
        List<FundRecordBO> allList = findAllBO(new FundRecordDTO(), new VoucherGenerateDTO());

        List<GroupAnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), GroupAnalyzeBO.class, fileds);

        //总收入
        Double allIncome = allList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //总支出
        Double allExpenditure = allList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();

        for (GroupAnalyzeBO bo : boList) {
            for (GroupAnalyzeBO lastBO : lastBOList) {

                if (bo.getProjectGroup().equals(lastBO.getProjectGroup())) {

                    bo.setLastIncome(lastBO.getIncome());
                    bo.setLastExpenditure(lastBO.getExpenditure());
                    bo.setIncomeSubtract(bo.getIncome() - lastBO.getIncome());
                    bo.setExpenditureSubtract(bo.getExpenditure() - lastBO.getExpenditure());
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
                    break;
                }
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectAnalyzeBO> projectAnalyze(Integer year, Integer month, String project) throws SerException {

        //查询month月数据
        StringBuilder sql = new StringBuilder();
        //查询本表数据sql
        String currentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                + year + " and month(recordDate) = " + month;
        //查询记账凭证数据sql
        String voucherTabSql = "select projectName,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                + year + " and month(voucherDate) = " + month;

        sql.append(" select records.project,sum(records.income) as income , sum(records.expenditure) as expenditure from ");
        sql.append(" ( " + currentTabSql);
        sql.append(" UNION ALL ");
        sql.append(voucherTabSql + " ) ");
        sql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(project)) {
            sql.append(" and records.project = '" + project + "'");
        }
        sql.append(" GROUP BY records.project ");

        String[] fileds = new String[]{"project", "income", "expenditure"};

        List<ProjectAnalyzeBO> boList = super.findBySql(sql.toString(), ProjectAnalyzeBO.class, fileds);


        //查询month-1月数据
        StringBuilder lastSql = new StringBuilder();
        //查询本表数据sql
        String lastCurrentTabSql = "";
        //查询记账凭证数据sql
        String lastVoucherTabSql = "";
        if (month == 12) {
            lastCurrentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + (year - 1) + " and month(recordDate) = 1 ";

            lastVoucherTabSql = "select projectName,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + (year - 1) + " and month(voucherDate) = 1 ";
        } else {
            lastCurrentTabSql = "select project,income,expenditure from fundrecords_fundrecord where year(recordDate) = "
                    + year + " and month(recordDate) = " + (month - 1);
            lastVoucherTabSql = "select projectName,borrowmoney,loanmoney from voucher_vouchergenerate where year(voucherDate) = "
                    + year + " and month(voucherDate) = " + (month - 1);
        }


        lastSql.append(" select records.project,sum(records.income),sum(records.expenditure) from ");
        lastSql.append(" ( " + lastCurrentTabSql);
        lastSql.append(" UNION ALL ");
        lastSql.append(lastVoucherTabSql + " ) ");
        lastSql.append(" as records where 0 = 0");
        if (!StringUtils.isEmpty(project)) {
            lastSql.append(" and records.project = '" + project + "'");
        }
        lastSql.append(" GROUP BY records.project ");

        //查询所有List
        List<FundRecordBO> allList = findAllBO(new FundRecordDTO(), new VoucherGenerateDTO());

        List<ProjectAnalyzeBO> lastBOList = super.findBySql(lastSql.toString(), ProjectAnalyzeBO.class, fileds);

        //总收入
        Double allIncome = allList.stream().filter(p -> p.getIncome() != null).mapToDouble(FundRecordBO::getIncome).sum();
        //总支出
        Double allExpenditure = allList.stream().filter(p -> p.getExpenditure() != null).mapToDouble(FundRecordBO::getExpenditure).sum();

        for (ProjectAnalyzeBO bo : boList) {
            for (ProjectAnalyzeBO lastBO : lastBOList) {

                if (bo.getProject().equals(lastBO.getProject())) {

                    bo.setLastIncome(lastBO.getIncome());
                    bo.setLastExpenditure(lastBO.getExpenditure());
                    bo.setIncomeSubtract(bo.getIncome() - lastBO.getIncome());
                    bo.setExpenditureSubtract(bo.getExpenditure() - lastBO.getExpenditure());
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
                    break;
                }
            }
        }
        return boList;
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

}