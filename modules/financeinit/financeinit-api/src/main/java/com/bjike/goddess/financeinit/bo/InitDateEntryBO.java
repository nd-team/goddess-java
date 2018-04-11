package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;

/**
 * 初始化数据录入业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InitDateEntryBO extends BaseBO {

    /**
     * 代码
     */
    private String code;

    /**
     * 会计科目名称
     */
    private String accountanName;

    /**
     * 本年借方累计数
     */
    private Double yearBorrowerNum;

    /**
     * 本年贷方累计数
     */
    private Double yearLenderNum;

    /**
     * 方向
     */
    private BalanceDirection balanceDirection;

    /**
     * 期初余额
     */
    private Double begingBalance;

    /**
     * 本年损益类累计数
     */
    private Double yearProfitLossNum;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }

    public Double getYearBorrowerNum() {
        return yearBorrowerNum;
    }

    public void setYearBorrowerNum(Double yearBorrowerNum) {
        this.yearBorrowerNum = yearBorrowerNum;
    }

    public Double getYearLenderNum() {
        return yearLenderNum;
    }

    public void setYearLenderNum(Double yearLenderNum) {
        this.yearLenderNum = yearLenderNum;
    }

    public BalanceDirection getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(BalanceDirection balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public Double getBegingBalance() {
        return begingBalance;
    }

    public void setBegingBalance(Double begingBalance) {
        this.begingBalance = begingBalance;
    }

    public Double getYearProfitLossNum() {
        return yearProfitLossNum;
    }

    public void setYearProfitLossNum(Double yearProfitLossNum) {
        this.yearProfitLossNum = yearProfitLossNum;
    }

    @Override
    public String toString() {
        return "InitDateEntryBO{" +
                "code='" + code + '\'' +
                ", accountanName='" + accountanName + '\'' +
                ", yearBorrowerNum=" + yearBorrowerNum +
                ", yearLenderNum=" + yearLenderNum +
                ", balanceDirection=" + balanceDirection +
                ", begingBalance=" + begingBalance +
                ", yearProfitLossNum=" + yearProfitLossNum +
                '}';
    }
}