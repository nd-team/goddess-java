package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 会议纪要
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_summary")
public class Summary extends BaseEntity {

    /**
     * 会议类型
     */
    @Column(name = "typeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议类型'")
    private String typeName;

    /**
     * 会议编号
     */
    @Column(name = "meetNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetNumber;

    /**
     * 实际会议时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME  COMMENT '实际会议时间'")
    private LocalDateTime actualTime;

    /**
     * 实际参会人员
     */
    @Column(name = "actualPerson", columnDefinition = "MEDIUMTEXT   COMMENT '实际参会人员'")
    private String actualPerson;

    /**
     * 新增参会人员
     */
    @Column(name = "newPerson", columnDefinition = "MEDIUMTEXT   COMMENT '新增参会人员'")
    private String newPerson;

    /**
     * 未参会人员
     */
    @Column(name = "nonePerson", columnDefinition = "MEDIUMTEXT   COMMENT '未参会人员'")
    private String nonePerson;

    /**
     * 会议记录人
     */
    @Column(name = "recorder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recorder;

    /**
     * 参会人数
     */
    @Column(name = "personNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '参会人数'")
    private Double personNum;

    /**
     * 参会人
     */
    @Column(name = "people", columnDefinition = "MEDIUMTEXT   COMMENT '参会人'")
    private String people;

    /**
     * 自我批评的内容
     */
    @Column(name = "selfCritic", columnDefinition = "MEDIUMTEXT   COMMENT '自我批评的内容'")
    private String selfCritic;

    /**
     * 自我总结
     */
    @Column(name = "selfsummery", columnDefinition = "MEDIUMTEXT   COMMENT '自我总结'")
    private String selfsummery;

    /**
     * 民主生活会议组织内容
     */
    @OneToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinColumn(name = "democraticContent_id", columnDefinition = "VARCHAR(36)   COMMENT '民主生活会议组织内容'")
    private DemocraticContent democraticContent;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPerson() {
        return actualPerson;
    }

    public void setActualPerson(String actualPerson) {
        this.actualPerson = actualPerson;
    }

    public String getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(String newPerson) {
        this.newPerson = newPerson;
    }

    public String getNonePerson() {
        return nonePerson;
    }

    public void setNonePerson(String nonePerson) {
        this.nonePerson = nonePerson;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Double getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Double personNum) {
        this.personNum = personNum;
    }

    public String getSelfCritic() {
        return selfCritic;
    }

    public void setSelfCritic(String selfCritic) {
        this.selfCritic = selfCritic;
    }

    public String getSelfsummery() {
        return selfsummery;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setSelfsummery(String selfsummery) {
        this.selfsummery = selfsummery;
    }

    public DemocraticContent getDemocraticContent() {
        return democraticContent;
    }

    public void setDemocraticContent(DemocraticContent democraticContent) {
        this.democraticContent = democraticContent;
    }
}