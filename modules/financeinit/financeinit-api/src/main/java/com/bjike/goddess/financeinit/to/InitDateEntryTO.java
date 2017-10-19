package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 初始化数据录入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InitDateEntryTO extends BaseTO {

    /**
     * 代码
     */
    @NotBlank(message = "代码不能为空",groups = {EDIT.class})
    private String code;

    /**
     * 会计科目名称
     */
    @NotBlank(message = "会计科目名称不能为空",groups = {EDIT.class})
    private String accountanName;

    /**
     * 本年借方累计数
     */
    @NotNull(message = "本年借方累计数不能为空",groups = {EDIT.class})
    private Double yearBorrowerNum;

    /**
     * 本年贷方累计数
     */
    @NotNull(message = "本年贷方累计数不能为空",groups = {EDIT.class})
    private Double yearLenderNum;

    /**
     * 方向
     */
    @NotNull(message = "方向数不能为空",groups = {EDIT.class})
    private BalanceDirection balanceDirection;

    /**
     * 期初余额
     */
    @NotNull(message = "期初余额数不能为空",groups = {EDIT.class})
    private Double begingBalance;

    /**
     * 本年损益类累计数
     */
    @NotNull(message = "本年损益类累计数不能为空",groups = {EDIT.class})
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
}