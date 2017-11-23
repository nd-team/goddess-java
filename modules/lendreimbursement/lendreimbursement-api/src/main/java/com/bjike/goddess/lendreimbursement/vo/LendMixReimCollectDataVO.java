package com.bjike.goddess.lendreimbursement.vo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 汇总数据
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-10 11:57]
 * @Description: [汇总数据]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LendMixReimCollectDataVO implements Serializable {

    /**
     * 借款/报销人
     */
    private String username;

    /**
     * 报销金额/借款金额
     */
    private Double money;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}