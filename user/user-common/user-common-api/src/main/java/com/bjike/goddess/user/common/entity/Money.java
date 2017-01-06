package com.bjike.goddess.user.common.entity;

import com.bjike.goddess.dbs.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
@Entity
@Table(name = "demo_money")
public class Money extends BaseEntity{

    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
