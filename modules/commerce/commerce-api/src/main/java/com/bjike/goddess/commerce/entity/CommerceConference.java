package com.bjike.goddess.commerce.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 商务会议
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "commerce_conference")
public class CommerceConference extends BaseEntity {

    /**
     * 会议类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议类型'")
    private String type;

    /**
     * 会议编号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'", unique = true)
    private String serialNumber;

    /**
     * 会议实际时间
     */
    @Column(name = "conferenceTime", nullable = false, columnDefinition = "DATETIME   COMMENT '会议实际时间'")
    private LocalDateTime conferenceTime;

    /**
     * 会议形式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议形式'")
    private String way;

    /**
     * 会议地点
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议地点'")
    private String area;

    /**
     * 参会人员
     */
    @Column(name = "personnel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参会人员'")
    private String personnel;

    /**
     * 参会人数
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer quantity;

    /**
     * 会议组织人
     */
    @Column(name = "organization", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议组织人'")
    private String organization;

    /**
     * 会议主持人
     */
    @Column(name = "emcee", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String emcee;

    /**
     * 会议记录人
     */
    @Column(name = "recorder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recorder;

    /**
     * 会议内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议内容'")
    private String content;

    /**
     * 对外通报内容
     */
    @Column(name = "bulletin", columnDefinition = "VARCHAR(255)   COMMENT '对外通报内容'")
    private String bulletin;

    /**
     * 最终协商结果
     */
    @Column(name = "consult", columnDefinition = "VARCHAR(255)   COMMENT '最终协商结果'")
    private String consult;

    /**
     * 最终谈判结果
     */
    @Column(name = "negotiation", columnDefinition = "VARCHAR(255)   COMMENT '最终谈判结果'")
    private String negotiation;

    /**
     * 最终合作结果
     */
    @Column(name = "cooperation", columnDefinition = "VARCHAR(255)   COMMENT '最终合作结果'")
    private String cooperation;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '状态'", insertable = false)
    private Status status;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getConferenceTime() {
        return conferenceTime;
    }

    public void setConferenceTime(LocalDateTime conferenceTime) {
        this.conferenceTime = conferenceTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmcee() {
        return emcee;
    }

    public void setEmcee(String emcee) {
        this.emcee = emcee;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBulletin() {
        return bulletin;
    }

    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }

    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}