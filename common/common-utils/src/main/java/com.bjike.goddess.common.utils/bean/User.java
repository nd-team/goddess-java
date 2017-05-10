package com.bjike.goddess.common.utils.bean;

import com.bjike.goddess.common.utils.excel.ExcelTitle;
import com.sun.istack.internal.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-10 09:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class User {
    @NotNull
    @ExcelTitle(name = "姓名")
    private String name = "黎贵钦";
    @ExcelTitle(name = "手机")
    private String phone = "13457910241";
    @ExcelTitle(name = "性别")
    private Integer sex = 1;
    @ExcelTitle(name = "创建时间")
    private LocalDateTime createTime = LocalDateTime.now();
    @ExcelTitle(name = "日期")
    private LocalDate date = LocalDate.now();
    @ExcelTitle(name = "时间")
    private LocalTime time = LocalTime.now();
    @ExcelTitle(name = "资产")
    private Double money = 20.22;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
