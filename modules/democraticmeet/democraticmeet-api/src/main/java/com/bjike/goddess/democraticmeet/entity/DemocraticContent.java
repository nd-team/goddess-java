package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 民主生活会议组织内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_democraticcontent")
public class DemocraticContent extends BaseEntity {

    /**
     * 会议类型
     */
    @Column(name = "meetType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议类型'")
    private String meetType;

    /**
     * 会议议题
     */
    @Column(name = "meetTitle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议议题'")
    private String meetTitle;

    /**
     * 会议内容
     */
    @Column(name = "meetContent", nullable = false, columnDefinition = "TEXT   COMMENT '会议内容'")
    private String meetContent;

    /**
     * 会议组织人
     */
    @Column(name = "organizationMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议组织人'")
    private String organizationMan;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    /**
     * 会议纪要
     */
    @OneToOne(cascade = CascadeType.REFRESH,mappedBy = "democraticContent", fetch = FetchType.LAZY)
    private Summary summary;


    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getMeetContent() {
        return meetContent;
    }

    public void setMeetContent(String meetContent) {
        this.meetContent = meetContent;
    }

    public String getOrganizationMan() {
        return organizationMan;
    }

    public void setOrganizationMan(String organizationMan) {
        this.organizationMan = organizationMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
}