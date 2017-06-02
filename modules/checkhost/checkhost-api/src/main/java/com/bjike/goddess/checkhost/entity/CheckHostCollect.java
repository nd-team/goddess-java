package com.bjike.goddess.checkhost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 汇总表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkhost_checkhostcollect")
public class CheckHostCollect extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "num", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String num;

    /**
     * 岗位
     */
    @Column(name = "jobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

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
     * 入住宿舍
     */
    @Column(name = "stayDormitory", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '入住宿舍'")
    private String stayDormitory;

    /**
     * 入住床位
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '入住床位'")
    private Integer stayBed;

    /**
     * 入住时间
     */
    @Column(name = "stayTime", nullable = false, columnDefinition = "DATE   COMMENT '入住时间'")
    private LocalDate stayTime;

    /**
     * 离宿时间
     */
    @Column(name = "hostTime", nullable = false, columnDefinition = "DATE  COMMENT '离宿时间'")
    private LocalDate hostTime;

    /**
     * 领用/归还钥匙
     */
    @Column(name = "chiave", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '领用/归还钥匙'")
    private String chiave;

    /**
     * 领用/归还床上用品
     */
    @Column(name = "bedding", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '领用/归还床上用品'")
    private String bedding;


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

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

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

    public String getStayDormitory() {
        return stayDormitory;
    }

    public void setStayDormitory(String stayDormitory) {
        this.stayDormitory = stayDormitory;
    }

    public Integer getStayBed() {
        return stayBed;
    }

    public void setStayBed(Integer stayBed) {
        this.stayBed = stayBed;
    }

    public LocalDate getStayTime() {
        return stayTime;
    }

    public void setStayTime(LocalDate stayTime) {
        this.stayTime = stayTime;
    }

    public LocalDate getHostTime() {
        return hostTime;
    }

    public void setHostTime(LocalDate hostTime) {
        this.hostTime = hostTime;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getBedding() {
        return bedding;
    }

    public void setBedding(String bedding) {
        this.bedding = bedding;
    }
}