package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:06]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "capitalFlowRecordSerCache")
@Service
public class CapitalFlowRecordSerImpl extends ServiceImpl<FundRecord, FundRecordDTO> implements CapitalFlowRecordSer {


    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private AccountAPI accountAPI;

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;

    @Autowired
    private FundRecordSerImpl fundRecordSerImpl;


    /*
    * 获取资金流水记录列表
    * */
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void listRecord() throws SerException {

        List<FundRecord> fundList = super.findAll();
        List<VoucherGenerateBO> voucherList = new ArrayList<>();   // 记账凭证

        if (moduleAPI.isCheck("voucher")) {
            voucherList = voucherGenerateAPI.findByCourseName();
        }
        if (voucherList != null && voucherList.size() > 0) {
            List<FundRecord> fundRecordList = new ArrayList<>();
            FundRecord fundRecord = new FundRecord();
            for (VoucherGenerateBO voucherBO : voucherList) {

                // 科目级联 - 获取id
                Boolean bool = true;
                if (fundList != null && fundList.size() > 0) {
                    for (FundRecord fundLists : fundList) {
                        if (voucherBO.getId().equals(fundLists.getVoucherGenerateId())) {
                            bool = false;
                        }
                    }
                }

                if (bool) {
                    // 科目级联 - 获取一二三级科目
                    String str = voucherBO.getFirstSubject();
                    if (StringUtils.isNotBlank(voucherBO.getSecondSubject())) {
                        str = " - " + voucherBO.getSecondSubject();
                    }
                    if (StringUtils.isNotBlank(voucherBO.getThirdSubject())) {
                        str = " - " + voucherBO.getThirdSubject();
                    }

                    // 余额计算
                    double amount = 0d;
                    if (moduleAPI.isCheck("financeinit")) {
                        amount = accountAPI.findTotalAmount(); // 第一条余额数据
                    }

                    fundRecord.setRecordDate(LocalDate.now()); // 日期可改
                    fundRecord.setArea(voucherBO.getArea());
                    fundRecord.setProject(voucherBO.getProjectName());
                    fundRecord.setProjectGroup(voucherBO.getProjectGroup());
                    fundRecord.setDigest(voucherBO.getSumary());
                    fundRecord.setIncome(voucherBO.getBorrowMoney());
                    fundRecord.setExpenditure(voucherBO.getLoanMoney());
                    fundRecord.setAmount(amount + voucherBO.getBorrowMoney() - voucherBO.getLoanMoney());
                    fundRecord.setDataSource(str);

                    fundRecordList.add(fundRecord);
                }
            }
            super.save(fundRecordList);
        }
    }

    /*
    * 删除资金流水记录
    * */
    @Override
    public void deleteFundRecord() throws SerException {
        String delSql= "delete from fundrecords_fundrecord";
        super.executeSql(delSql);
    }


    /*
   * 全局搜索分页查询资金流水记录
   * */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto,FundRecordDTO fundRecordDTO) throws SerException {
        List<FundRecordBO> fundRecordBOList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select recordDate as recordDate,area as area,project as project,projectGroup as projectGroup,digest as digest,income as income,expenditure as expenditure,amount as amount,dataSource as dataSource from fundrecords_fundrecord where 1=1");

        if (StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())) {
            sb.append(" and ( cast ( recordDate as date ) between '" + dto.getStartTime() + "' and '" + dto.getEndTime() + "') ");
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            sb.append(" and area = '" + dto.getArea() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProject())) {
            sb.append(" and project = '" + dto.getProject() + "'");
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            sb.append(" and projectGroup = '" + dto.getProjectGroup() + "'");
        }

        sb.append(" ORDER BY recordDate DESC ");
        String[] fileds = new String[]{"recordDate", "area", "project", "projectGroup", "digest", "income", "expenditure", "amount", "dataSource"};
        List<FundRecordBO> list = super.findBySql(sb.toString(), FundRecordBO.class, fileds);

        return fundRecordSerImpl.sortPageList(list,fundRecordDTO);
    }

    /*
    * 查询资金流水记录总数
    * */
    @Override
    public Long countRecord(FundRecordDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getDataSource())){
            dto.getConditions().add(Restrict.like("dataSource",dto.getDataSource()));
        }
        Long count = super.count(dto);
        return count;
    }

    /*
    * 处理数据同步记账凭证和资金流水删除地区字段相应数据
    * */
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteArea(String id)throws SerException{
//        if(StringUtils.isNotBlank(id) ){
//            throw new SerException("未选中删除的数据!");
//        }
        VoucherGenerateBO generate = voucherGenerateAPI.getById(id);
        StringBuffer sql = new StringBuffer();
        sql.append("  delete from fundrecords_fundrecord WHERE area='"+generate.getArea()+"' AND project='"+generate.getProjectName()+"'");
        super.executeSql(sql.toString());

    }

    /*
    * 资金流水记录获取第一条数据
    * */
    @Override
    public void generateData() throws SerException{

        List<FundRecord> fundList = super.findAll();
        List<VoucherGenerateBO> voucherList = new ArrayList<>();
        VoucherGenerate voucherGenerate = new VoucherGenerate();
        List<AccountBO> accountBOList = new ArrayList<>();
        Account account = new Account();

        double bor=0d;
        double loa=0d;
        double count = 0d;
        for(VoucherGenerateBO vouList:voucherList){
            for(int i= 0;i<=voucherList.size();i++){
                bor=vouList.getBorrowMoney();
                bor++;
            }
            for(int j= 0;j<=voucherList.size();j++){
                loa=vouList.getLoanMoney();
                loa++;
            }
        }
        for (AccountBO acount : accountBOList){
            for (int i=0;i<=accountBOList.size();i++){
                count  = acount.getAmount();
                count++;
            }
        }

        FundRecord fundRecord = new FundRecord();
        fundRecord.setIncome(bor);
        fundRecord.setExpenditure(loa);
        fundRecord.setAmount(count+bor-loa);
        fundRecord.setDigest(String.valueOf(account.getAmount())); // 转为String类型
        fundRecord.setDataSource(" 第一条数据为空 ");
        fundRecord.setArea(" 第一条数据为空 ");
        fundRecord.setProject(" 第一条数据为空 ");
        fundRecord.setProjectGroup(" 第一条数据为空 ");
        fundRecord.setVoucherGenerateId(" 第一条数据为空 ");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime befordate=account.getCreateTime();
        String date = df.format(befordate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(); // 获得日历
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH,-1);  // 计算前一天日期
        String getdate = sdf.format(calendar.getTime()); // 获得前一天日期
        LocalDate time=LocalDate.parse(getdate,df);  // 转为LocalDate 类型
        fundRecord.setRecordDate(time);  // 日期为开账日期前一天

        fundList.add(fundRecord);
    }



}
