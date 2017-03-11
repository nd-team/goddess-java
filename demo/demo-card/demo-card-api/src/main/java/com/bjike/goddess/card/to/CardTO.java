package com.bjike.goddess.card.to;

import com.bjike.goddess.common.api.entity.ADD;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 11:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CardTO {

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空",groups = {ADD.class})
    private String account;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空",groups = {ADD.class})
    private String password;
    /**
     * 余额
     */
    private Long money;

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
}
