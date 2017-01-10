package com.bjike.goddess.user.common.entity;


import com.bjike.goddess.dbs.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
@Entity
@Table(name = "demo_money")
public class Money extends BaseEntity implements Serializable{

    private String account;
    private Integer money;

    public String getAccount() {
        return account;
    }

    public Integer getMoney() {
        return money;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
