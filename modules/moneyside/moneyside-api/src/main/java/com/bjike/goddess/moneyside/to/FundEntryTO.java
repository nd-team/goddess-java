package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.moneyside.enums.PassStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资金进入申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundEntryTO extends BaseTO {
    public interface TestAdd {
    }

    public interface TestEdit {
    }
    public interface TestAudit{}

    /**
     * 投资人
     */
    @NotBlank(message = "投资人不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private String investor;

    /**
     * 资金进入方式
     */
    @NotBlank(message = "资金进入方式不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private String accessToFund;

    /**
     * 资金进入时间
     */
    @NotBlank(message = "资金进入时间不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private String fundEntryTime;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private Double money;

    /**
     * 打款账户名
     */
    @NotBlank(message = "打款账户名不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private String moneyAccountName;

    /**
     * 打款账号
     */
    @NotBlank(message = "打款账号不能为空", groups = {FundEntryTO.TestAdd.class, FundEntryTO.TestEdit.class})
    private String moneyAccount;

    /**
     * 确认人
     */
    @NotBlank(message = "确认人不能为空", groups = {FundEntryTO.TestAudit.class})
    private String confirmPeople;

    /**
     * 确认情况
     */
    @NotBlank(message = "确认情况不能为空", groups = {FundEntryTO.TestAudit.class})
    private String confirmSituation;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空", groups = {FundEntryTO.TestAudit.class})
    private PassStatus pass;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getAccessToFund() {
        return accessToFund;
    }

    public void setAccessToFund(String accessToFund) {
        this.accessToFund = accessToFund;
    }

    public String getFundEntryTime() {
        return fundEntryTime;
    }

    public void setFundEntryTime(String fundEntryTime) {
        this.fundEntryTime = fundEntryTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getMoneyAccountName() {
        return moneyAccountName;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public String getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(String moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }

    public String getConfirmSituation() {
        return confirmSituation;
    }

    public void setConfirmSituation(String confirmSituation) {
        this.confirmSituation = confirmSituation;
    }

    public PassStatus getPass() {
        return pass;
    }

    public void setPass(PassStatus pass) {
        this.pass = pass;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}