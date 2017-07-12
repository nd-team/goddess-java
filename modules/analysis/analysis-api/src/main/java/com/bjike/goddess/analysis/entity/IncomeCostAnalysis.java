package com.bjike.goddess.analysis.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 收入成本分析
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "analysis_incomecostanalysis")
public class IncomeCostAnalysis extends BaseEntity{

    /**
     * 时间
     */
    @Column(name = "date", columnDefinition = "DATE   COMMENT '验收交维时间'")
    private LocalDate date;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 出车司机数
     */
    @Column(columnDefinition = "INT(5)   COMMENT '出车司机数'")
    private Integer carNum;

    /**
     * 司机出车费
     */
    @Column(name = "driverFee", columnDefinition = "DECIMAL(10,2)   COMMENT '司机出车费'")
    private Double driverFee;

    /**
     * 油卡充值
     */
    @Column(name = "oilRecharge", columnDefinition = "DECIMAL(10,2)   COMMENT '油卡充值'")
    private Double oilRecharge;

    /**
     * 房租
     */
    @Column(name = "rent", columnDefinition = "DECIMAL(10,2)   COMMENT '房租'")
    private Double rent;

    /**
     * 社保
     */
    @Column(name = "socialSecurity", columnDefinition = "DECIMAL(10,2)   COMMENT '社保'")
    private Double socialSecurity;

    /**
     * 员工工资
     */
    @Column(name = "staffWage", columnDefinition = "DECIMAL(10,2)   COMMENT '员工工资'")
    private Double staffWage;

    /**
     * 办公费
     */
    @Column(name = "office", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '办公费'")
    private Double office;

    /**
     * 市场费
     */
    @Column(name = "marketCost", columnDefinition = "DECIMAL(10,2)   COMMENT '市场费'")
    private Double marketCost;

    /**
     * 税金
     */
    @Column(name = "tax",  columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
    private Double tax;

    /**
     * 合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）
     */
    @Column(name = "total",  columnDefinition = "DECIMAL(10,2)   COMMENT '合计（司机出车费+油卡充值+房租+社保+员工工资+办公费+市场费+税金）'")
    private Double total;

    /**
     * 员工人数
     */
    @Column( columnDefinition = "INT(5)   COMMENT '员工人数'")
    private Integer staffNum;

    /**
     * 人均工资
     */
    @Column(name = "perCapitaWage", columnDefinition = "DECIMAL(10,2)   COMMENT '人均工资'")
    private Double perCapitaWage;

    /**
     * 税后余额收入
     */
    @Column(name = "incomeAfterTax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '税后余额收入'")
    private Double incomeAfterTax;

    /**
     * 差额（税后余额收入-合计）
     */
    @Column(name = "balance", columnDefinition = "DECIMAL(10,2)   COMMENT '差额（税后余额收入-合计）'")
    private Double balance;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Double getDriverFee() {
        return driverFee;
    }

    public void setDriverFee(Double driverFee) {
        this.driverFee = driverFee;
    }

    public Double getOilRecharge() {
        return oilRecharge;
    }

    public void setOilRecharge(Double oilRecharge) {
        this.oilRecharge = oilRecharge;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Double socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Double getStaffWage() {
        return staffWage;
    }

    public void setStaffWage(Double staffWage) {
        this.staffWage = staffWage;
    }

    public Double getOffice() {
        return office;
    }

    public void setOffice(Double office) {
        this.office = office;
    }

    public Double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Double marketCost) {
        this.marketCost = marketCost;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(Integer staffNum) {
        this.staffNum = staffNum;
    }

    public Double getPerCapitaWage() {
        return perCapitaWage;
    }

    public void setPerCapitaWage(Double perCapitaWage) {
        this.perCapitaWage = perCapitaWage;
    }

    public Double getIncomeAfterTax() {
        return incomeAfterTax;
    }

    public void setIncomeAfterTax(Double incomeAfterTax) {
        this.incomeAfterTax = incomeAfterTax;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}