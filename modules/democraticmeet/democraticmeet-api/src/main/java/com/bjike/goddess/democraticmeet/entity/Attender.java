package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 会议计划参与人员
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:29 ]
 * @Description: [ 会议计划参与人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_attender")
public class Attender extends BaseEntity {

    /**
     * 计划参与人员
     */
    @Column(name = "attenderName",   columnDefinition = "VARCHAR(255)   COMMENT '计划参与人员'")
    private String attenderName;

    /**
     * 会议组织部分内容
     */
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.REFRESH} )
    @JoinColumn(name = "meetDesign_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '会议组织部分内容'")
    private MeetDesign meetDesign;


    public String getAttenderName() {
        return attenderName;
    }

    public void setAttenderName(String attenderName) {
        this.attenderName = attenderName;
    }

    public MeetDesign getMeetDesign() {
        return meetDesign;
    }

    public void setMeetDesign(MeetDesign meetDesign) {
        this.meetDesign = meetDesign;
    }
}