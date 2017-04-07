package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 公司放假时间安排
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_companyfestivaltime")
public class CompanyFestivalTime extends BaseEntity {

    /**
     * 节假日名称
     */
    @Column(name = "name", nullable = false, unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '节假日名称'")
    private String name;

    /**
     * 国家放假时长
     */
    @Column(name = "countryTimeLong",  columnDefinition = "VARCHAR(255)   COMMENT '国家放假时长'")
    private String countryTimeLong;

    /**
     * 公司放假时长
     */
    @Column(name = "companyTimeLong",  columnDefinition = "VARCHAR(255)   COMMENT '公司放假时长'")
    private String companyTimeLong;

    /**
     * 公司放假起始时间
     */
    @Column(name = "companyStartTime", columnDefinition = "DATE  COMMENT '公司放假起始时间'")
    private LocalDate companyStartTime;

    /**
     * 公司放假结束时间
     */
    @Column(name = "companyEndTime",  columnDefinition = "DATE   COMMENT '公司放假结束时间'")
    private LocalDate companyEndTime;

    /**
     * 调休天数
     */
    @Column(name = "offDay",  columnDefinition = "VARCHAR(255)   COMMENT '调休天数'")
    private String offDay;

    /**
     * 补休日期
     */
    @Column(name = "takeTime",  columnDefinition = "DATE  COMMENT '补休日期'")
    private LocalDate takeTime;

    /**
     * 调休日期
     */
    @Column(name = "offTime",  columnDefinition = "DATE   COMMENT '调休日期'")
    private LocalDate offTime;

    /**
     * 调休规范
     */
    @Column(name = "offStandard",  columnDefinition = "VARCHAR(255)   COMMENT '调休规范'")
    private String offStandard;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryTimeLong() {
        return countryTimeLong;
    }

    public void setCountryTimeLong(String countryTimeLong) {
        this.countryTimeLong = countryTimeLong;
    }

    public String getCompanyTimeLong() {
        return companyTimeLong;
    }

    public void setCompanyTimeLong(String companyTimeLong) {
        this.companyTimeLong = companyTimeLong;
    }

    public LocalDate getCompanyStartTime() {
        return companyStartTime;
    }

    public void setCompanyStartTime(LocalDate companyStartTime) {
        this.companyStartTime = companyStartTime;
    }

    public LocalDate getCompanyEndTime() {
        return companyEndTime;
    }

    public void setCompanyEndTime(LocalDate companyEndTime) {
        this.companyEndTime = companyEndTime;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    public LocalDate getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(LocalDate takeTime) {
        this.takeTime = takeTime;
    }

    public LocalDate getOffTime() {
        return offTime;
    }

    public void setOffTime(LocalDate offTime) {
        this.offTime = offTime;
    }

    public String getOffStandard() {
        return offStandard;
    }

    public void setOffStandard(String offStandard) {
        this.offStandard = offStandard;
    }
}