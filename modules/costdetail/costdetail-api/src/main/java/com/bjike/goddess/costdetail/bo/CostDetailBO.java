package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 成本明细业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailBO extends BaseBO {

    private String date;

    private String department;

    private String depict;

    /**
     * 分类
     */
    private String type;

    /**
     * 合计
     */
    private Double total;

    /**
     * 第十日
     */
    private Double tenthDay;

    /**
     * 第十五日
     */
    private Double fifteenthDay;

    /**
     * 第二十日
     */
    private Double twentiethDay;

    /**
     * 第三十日
     */
    private Double thirtiethDay;

    private List<SonCostDetailBO> sonCostDetailBOS;

    public List<SonCostDetailBO> getSonCostDetailBOS() {
        return sonCostDetailBOS;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public void setSonCostDetailBOS(List<SonCostDetailBO> sonCostDetailBOS) {
        this.sonCostDetailBOS = sonCostDetailBOS;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return "CostDetailBO{" +
                "date='" + date + '\'' +
                ", department='" + department + '\'' +
                ", depict='" + depict + '\'' +
                ", type='" + type + '\'' +
                ", total=" + total +
                ", tenthDay=" + tenthDay +
                ", fifteenthDay=" + fifteenthDay +
                ", twentiethDay=" + twentiethDay +
                ", thirtiethDay=" + thirtiethDay +
                ", sonCostDetailBOS=" + sonCostDetailBOS +
                '}';
    }
}