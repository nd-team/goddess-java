package com.bjike.goddess.costdetail.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;


/**
 * 成本明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

@Entity
@Table(name = "costdetail_costdetail")
public class CostDetail extends BaseEntity {
    public CostDetail() {

    }

    public CostDetail(String date, String department,String type) {
        this.date = date;
        this.department = department;
        this.type = type;
    }

    /**
     * 日期
     */
    @Column
    private String date;

    /**
     * 部门
     */
    @Column
    private String department;

    /**
     * 分类
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String type;

    /**
     * 合计
     */
    @Column(name = "total", columnDefinition = "DECIMAL(10,2) default 0.00  COMMENT '合计'")
    private Double total;

    /**
     * 第十日
     */
    @Column(name = "tenthDay", columnDefinition = "DECIMAL(10,2) default 0.00  COMMENT '第十日'")
    private Double tenthDay;

    /**
     * 第十五日
     */
    @Column(name = "fifteenthDay", columnDefinition = "DECIMAL(10,2) default 0.00  COMMENT '第十五日'")
    private Double fifteenthDay;

    /**
     * 第二十日
     */
    @Column(name = "twentiethDay", columnDefinition = "DECIMAL(10,2) default 0.00  COMMENT '第二十日'")
    private Double twentiethDay;

    /**
     * 第三十日
     */
    @Column(name = "thirtiethDay", columnDefinition = "DECIMAL(10,2) default 0.00  COMMENT '第三十日'")
    private Double thirtiethDay;

    /**
     *
     */
    @OneToMany(cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "costDetailId")
    private List<SonCostDetail> sonCostDetails;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SonCostDetail> getSonCostDetails() {
        return sonCostDetails;
    }

    public void setSonCostDetails(List<SonCostDetail> sonCostDetails) {
        this.sonCostDetails = sonCostDetails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTenthDay() {
        return tenthDay;
    }

    public void setTenthDay(Double tenthDay) {
        this.tenthDay = tenthDay;
    }

    public Double getFifteenthDay() {
        return fifteenthDay;
    }

    public void setFifteenthDay(Double fifteenthDay) {
        this.fifteenthDay = fifteenthDay;
    }

    public Double getTwentiethDay() {
        return twentiethDay;
    }

    public void setTwentiethDay(Double twentiethDay) {
        this.twentiethDay = twentiethDay;
    }

    public Double getThirtiethDay() {
        return thirtiethDay;
    }

    public void setThirtiethDay(Double thirtiethDay) {
        this.thirtiethDay = thirtiethDay;
    }

    @Override
    public String toString() {
        return "CostDetail{" +
                "date='" + date + '\'' +
                ", department='" + department + '\'' +
                ", type='" + type + '\'' +
                ", total=" + total +
                ", tenthDay=" + tenthDay +
                ", fifteenthDay=" + fifteenthDay +
                ", twentiethDay=" + twentiethDay +
                ", thirtiethDay=" + thirtiethDay +
                ", sonCostDetails=" + sonCostDetails +
                '}';
    }
}