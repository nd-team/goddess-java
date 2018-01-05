package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 银行流水数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordDTO extends BaseDTO {

    public interface PageList {
    }

    /**
     * 分页
     */
    private Integer page;


    /**
     * 账户Id
     */
    @NotBlank(message = "账户查询名称不能为空", groups = {BankRecordDTO.PageList.class})
    private String accountId;

    /**
     * 交易日开始日期
     *
     * @param
     * @return class
     * @version v1
     */
    private String startDate;

    /**
     * 交易日结束日期
     *
     * @param
     * @return class
     * @version v1
     */
    private String endDate;

    /**
     * 余额、借方金额、贷方金额
     *
     * @param
     * @return class
     * @version v1
     */
    private String money;

    /**
     * 摘要
     *
     * @param
     * @return class
     * @version v1
     */
    private String summary;

    /**
     * 收/付款方名称
     *
     * @param
     * @return class
     * @version v1
     */
    private String payName;

    /**
     * 收/付款方帐号
     *
     * @param
     * @return class
     * @version v1
     */
    private String payAccount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}