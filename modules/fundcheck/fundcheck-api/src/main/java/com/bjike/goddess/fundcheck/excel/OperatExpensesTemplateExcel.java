package com.bjike.goddess.fundcheck.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 营业费用表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OperatExpensesTemplateExcel {

    /**
     * 日期
     */
    @ExcelHeader(name = "日期",notNull = true)
    private LocalDate date;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型",notNull = true)
    private String type;

    /**
     * 金额
     */
    @ExcelHeader(name = "金额",notNull = true)
    private Double money;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}