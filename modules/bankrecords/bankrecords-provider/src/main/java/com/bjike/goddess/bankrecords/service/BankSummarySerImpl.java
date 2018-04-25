package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.bo.BankRecordBO;
import com.bjike.goddess.bankrecords.bo.BankSummaryBO;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.entity.BankSummary;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.financeinit.api.BaseParameterAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 银行流水业务实现
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-08 10:27 ]
 * @Description: [ 银行流水业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bankrecordsSerCache")
@Service
public class BankSummarySerImpl extends ServiceImpl<BankSummary, BankSummaryDTO> implements BankSummarySer {
    @Autowired
    private BaseParameterAPI baseParameterAPI;
    @Autowired
    private UserAPI userAPI;
    /**
     * 有条件对银行流水汇总
     */
    @Override
    public List<BankSummaryBO> backfilterQuery(BankSummaryDTO dto) throws SerException {
        List<BankRecord> bankRecords=null;
        String token = RpcTransmit.getUserToken();
        String systemId = userAPI.currentSysNO();
        RpcTransmit.transmitUserToken(token);
        //String s = baseParameterAPI.findDoudap();//账套会计期间启用日期
         String s="2013-12-30";
        if (s != null) {
            LocalDate beginDateTime = LocalDate.parse(s);//这是把它转为时间类型
            if (LocalDate.parse(dto.getStartDate()).isAfter(beginDateTime)) {
                String sql = null;
                if (dto.getAccountId().equals("0")) {//这里是没有传账户id过来的
                    sql = "select ANY_VALUE(accountId) as accountId,ANY_VALUE((select number from bankrecords_bankaccountinfo where id=accountId))" +
                            "as bankName ,ANY_VALUE(recordMonth) as month,ANY_VALUE(recordYear) as year,sum(debtorCost) as expenseDebitAmount,sum(creditorCost) as" +
                            "incomeCreditAmount,sum(creditorCost-debtorCost) as" +
                            "amountOfThisMonth,ANY_VALUE(balance) as" +
                            "theBalanceOf,ANY_VALUE(date_format(recordDate,'%y-%m')) as" +
                            "theDateOf from bankrecords_bankrecord" +
                            " where recordDate>='" + dto.getStartDate() + "' and  recordDate<='" + dto.getEndDate() + "' group by accountId,recordMonth ";
                } else {

                    sql = "select ANY_VALUE(accountId) as accountId,ANY_VALUE((select number from bankrecords_bankaccountinfo where id=accountId))" +
                            "as bankName ,ANY_VALUE(recordMonth) as month,ANY_VALUE(recordYear) as year,sum(debtorCost) as expenseDebitAmount,sum(creditorCost) as" +
                            "incomeCreditAmount,sum(creditorCost-debtorCost) as" +
                            "amountOfThisMonth,ANY_VALUE(balance) as" +
                            "theBalanceOf,ANY_VALUE(date_format(recordDate,'%y-%m')) as" +
                            "theDateOf from bankrecords_bankrecord" +
                            " where  accountId='" + dto.getAccountId() + "' and recordDate>='" + dto.getStartDate() + "' and  recordDate<='" + dto.getEndDate() + "' group by accountId,recordMonth";

                }
                String[] field = new String[]{"accountId","bankName", "month", "year", "expenseDebitAmount", "incomeCreditAmount", "amountOfThisMonth", "theBalanceOf", "theDateOf"};
                List<BankSummaryBO> bos = super.findBySql(sql, BankSummaryBO.class, field);
               for(int i=0;i<bos.size();i++){
                   sql="select balance from bankrecords_bankrecord where recordMonth='"+bos.get(i).getMonth()+"' and recordYear='"+bos.get(i).getYear()+"' and accountId='"+bos.get(i).getAccountId()+"' order by recordDate desc";
                   String []ss=new String[]{"balance"};
                   bankRecords=super.findBySql(sql, BankRecord.class,ss);
                   bos.get(i).setTheBalanceOf(bankRecords.get(0).getBalance());
               }
                return bos;
            } else {
                throw new SerException("不能低于账套会计期间启用日期");
            }
        }
        return null;
    }

}