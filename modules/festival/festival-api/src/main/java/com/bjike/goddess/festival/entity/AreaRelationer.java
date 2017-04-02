package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 各地区紧急联系人
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_arearelationer")
public class AreaRelationer extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 联系人
     */
    @Column(name = "ralationer",  columnDefinition = "VARCHAR(255)   COMMENT '联系人'")
    private String ralationer;

    /**
     * 联系电话
     */
    @Column(name = "tel",  columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String tel;

    /**
     * 法定节假日放假方案
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "holidayProgramme_id",  columnDefinition = "VARCHAR(36)   COMMENT '法定节假日放假方案'")
    private HolidayProgramme holidayProgramme;


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

    public String getRalationer() {
        return ralationer;
    }

    public void setRalationer(String ralationer) {
        this.ralationer = ralationer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public HolidayProgramme getHolidayProgramme() {
        return holidayProgramme;
    }

    public void setHolidayProgramme(HolidayProgramme holidayProgramme) {
        this.holidayProgramme = holidayProgramme;
    }
}