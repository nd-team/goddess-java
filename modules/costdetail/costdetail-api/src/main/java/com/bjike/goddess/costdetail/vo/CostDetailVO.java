package com.bjike.goddess.costdetail.vo;

import java.util.List;

/**
 * 成本明细表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-25 04:09 ]
 * @Description: [ 成本明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailVO {

    private String date;

    private String department;

    /**
     * id
     */
    private String id;
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

    /**
     * 第三十日
     */


    private Double thirtiethDay;

    private List<SonCostDetailVO> costDetailVOS;

    public List<SonCostDetailVO> getCostDetailVOS() {
        return costDetailVOS;
    }

    public void setCostDetailVOS(List<SonCostDetailVO> costDetailVOS) {
        this.costDetailVOS = costDetailVOS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}