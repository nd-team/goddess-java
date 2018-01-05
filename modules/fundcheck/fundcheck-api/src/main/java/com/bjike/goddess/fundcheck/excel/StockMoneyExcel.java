package com.bjike.goddess.fundcheck.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;
import java.util.List;

/**
 * 收到股东款表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:51 ]
 * @Description: [ 收到股东款表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StockMoneyExcel {

    /**
     * 日期
     */
    @ExcelHeader(name = "日期",notNull = true)
    private LocalDate date;

    /**
     * 股东名
     */
    @ExcelHeader(name = "股东名",notNull = true)
    private String stockName;

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

}