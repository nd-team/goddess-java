package com.bjike.goddess.card.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 业务对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 09:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CardBO extends BaseBO {
    /**
     * 卡号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 卡余额
     */
    private Long money;
    /**
     * 卡创建时间
     */
    private String createTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
