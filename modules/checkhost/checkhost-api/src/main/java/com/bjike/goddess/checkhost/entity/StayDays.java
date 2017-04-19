package com.bjike.goddess.checkhost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 员工住宿天数汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkhost_staydays")
public class StayDays extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 宿舍地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '宿舍地址'")
    private String address;

    /**
     * 员工姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "num", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String num;

    /**
     * 入住时间
     */
    @Column(name = "stayTime", nullable = false, columnDefinition = "DATETIME   COMMENT '入住时间'")
    private LocalDateTime stayTime;

    /**
     * 入住床位
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '入住床位'")
    private Integer stayBed;

    /**
     * 床上3件套（件）
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '床上3件套（件）'")
    private Integer suit;

    /**
     * 被褥（件）
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被褥（件）'")
    private Integer bedding;

    /**
     * 床垫
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '床垫'")
    private Integer mattress;

    /**
     * 是否领用钥匙
     */
    @Column(name = "is_receiveKey", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否领用钥匙'", insertable = false)
    private Boolean receiveKey;

    /**
     * 离宿时间
     */
    @Column(name = "hostTime", nullable = false, columnDefinition = "DATETIME   COMMENT '离宿时间'")
    private LocalDateTime hostTime;

    /**
     * 当月统计住宿时间段
     */
    @Column(name = "stayWhen", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当月统计住宿时间段'")
    private String stayWhen;

    /**
     * 当月住宿天数
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当月住宿天数'")
    private Integer stayNum;

    /**
     * 需缴费金额
     */
    @Column(name = "expendAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '需缴费金额'")
    private Double expendAmount;

    /**
     * 员工核实是否有误
     */
    @Column(name = "is_employeeVerify", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '员工核实是否有误'", insertable = false)
    private Boolean employeeVerify;

    /**
     * 综合资源部核实
     */
    @Column(name = "comprehensiveVerify", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '综合资源部核实'")
    private String comprehensiveVerify;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public LocalDateTime getStayTime() {
        return stayTime;
    }

    public void setStayTime(LocalDateTime stayTime) {
        this.stayTime = stayTime;
    }

    public Integer getStayBed() {
        return stayBed;
    }

    public void setStayBed(Integer stayBed) {
        this.stayBed = stayBed;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getMattress() {
        return mattress;
    }

    public void setMattress(Integer mattress) {
        this.mattress = mattress;
    }

    public Boolean getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(Boolean receiveKey) {
        this.receiveKey = receiveKey;
    }

    public LocalDateTime getHostTime() {
        return hostTime;
    }

    public void setHostTime(LocalDateTime hostTime) {
        this.hostTime = hostTime;
    }

    public String getStayWhen() {
        return stayWhen;
    }

    public void setStayWhen(String stayWhen) {
        this.stayWhen = stayWhen;
    }

    public Integer getStayNum() {
        return stayNum;
    }

    public void setStayNum(Integer stayNum) {
        this.stayNum = stayNum;
    }

    public Double getExpendAmount() {
        return expendAmount;
    }

    public void setExpendAmount(Double expendAmount) {
        this.expendAmount = expendAmount;
    }

    public Boolean getEmployeeVerify() {
        return employeeVerify;
    }

    public void setEmployeeVerify(Boolean employeeVerify) {
        this.employeeVerify = employeeVerify;
    }

    public String getComprehensiveVerify() {
        return comprehensiveVerify;
    }

    public void setComprehensiveVerify(String comprehensiveVerify) {
        this.comprehensiveVerify = comprehensiveVerify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}