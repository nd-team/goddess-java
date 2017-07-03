package com.bjike.goddess.ticket.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 演示demo功能信息
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TicketVO{
    public interface TESTDemoList{}
    public interface TESTDemoListUP{}

    /**
     * 购买人账号
     */
    private String account;
    /**
     * 座位号 D3608-2-2D
     */
    @NotBlank(groups = TicketVO.TESTDemoList.class,message = "座位号不能为空")
    private String position;
    /**
     * 出发时间
     */
    private String offTime;
    /**
     * 购票金额
     */
    @Min(value = 10,message = "金额错误")
    private Integer money;
    /**
     * 票号
     */
    private String number;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
