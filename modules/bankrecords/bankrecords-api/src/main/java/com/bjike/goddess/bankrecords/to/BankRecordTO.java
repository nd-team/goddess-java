package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.List;

/**
 * 银行流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordTO extends BaseTO {

    public interface Upload {
    }

    /**
     * 账号信息Id
     */
    @NotBlank(message = "账户信息不能为空!", groups = {BankRecordTO.Upload.class})
    private String accountId;


    /**
     * 借方金额下标
     */
    @NotNull(message = "借方金额下标不能为空!", groups = {BankRecordTO.Upload.class})
    private Integer debtorCostIndex;

    /**
     * 贷方金额下标
     */
    @NotNull(message = "贷方金额下标不能为空!", groups = {BankRecordTO.Upload.class})
    private Integer creditorCostIndex;

    /**
     * 余额下标
     */
    @NotNull(message = "余额下标不能为空!", groups = {BankRecordTO.Upload.class})
    private Integer balanceIndex;

    /**
     * 流水日期下标
     */
    @NotNull(message = "流水日期下标不能为空!", groups = {BankRecordTO.Upload.class})
    private Integer recordDateIndex;

    //request文件信息
    private List<InputStream> inputStreams;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getDebtorCostIndex() {
        return debtorCostIndex;
    }

    public void setDebtorCostIndex(Integer debtorCostIndex) {
        this.debtorCostIndex = debtorCostIndex;
    }

    public Integer getCreditorCostIndex() {
        return creditorCostIndex;
    }

    public void setCreditorCostIndex(Integer creditorCostIndex) {
        this.creditorCostIndex = creditorCostIndex;
    }

    public Integer getBalanceIndex() {
        return balanceIndex;
    }

    public void setBalanceIndex(Integer balanceIndex) {
        this.balanceIndex = balanceIndex;
    }

    public Integer getRecordDateIndex() {
        return recordDateIndex;
    }

    public void setRecordDateIndex(Integer recordDateIndex) {
        this.recordDateIndex = recordDateIndex;
    }

    public List<InputStream> getInputStreams() {
        return inputStreams;
    }

    public void setInputStreams(List<InputStream> inputStreams) {
        this.inputStreams = inputStreams;
    }
}