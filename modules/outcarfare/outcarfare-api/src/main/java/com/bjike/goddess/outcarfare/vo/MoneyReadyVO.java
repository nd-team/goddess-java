package com.bjike.goddess.outcarfare.vo;

/**
 * 资金准备审核表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyVO {

    /**
     * id
     */
    private String id;
    /**
     * 类别
     */
    private String category;

    /**
     * 科目
     */
    private String subject;

    /**
     * 总准备金
     */
    private Double totalReserve;

    /**
     * 项目组
     */
    private String groupTeam;

    /**
     * 比例分配
     */
    private Double prorate;

    /**
     * 准备金
     */
    private Double reserve;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 地区
     */
    private String area;
    /**
     * 删除时间
     */
    private String delTime;

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(String groupTeam) {
        this.groupTeam = groupTeam;
    }

    public Double getProrate() {
        return prorate;
    }

    public void setProrate(Double prorate) {
        this.prorate = prorate;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}