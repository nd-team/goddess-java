package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 社保卡领取登记表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_drawregister")
public class DrawRegister extends BaseEntity {

    /**
     * 领取时间
     */
    @Column(name = "drawTime", columnDefinition = "DATE   COMMENT '领取时间'")
    private LocalDate drawTime;

    /**
     * 持卡人姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '持卡人姓名'")
    private String name;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 领取机构
     */
    @Column(name = "mechanism", columnDefinition = "VARCHAR(255)   COMMENT '领取机构'")
    private String mechanism;

    /**
     * 是否可从领取机构中领取社保卡
     */
    @Column(name = "is_drawSecureCard", columnDefinition = "TINYINT(1)   COMMENT '是否可从领取机构中领取社保卡'")
    private Boolean drawSecureCard;

    /**
     * 领取内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '领取内容'")
    private String content;

    /**
     * 领取数量
     */
    @Column(name = "number", columnDefinition = "VARCHAR(255)   COMMENT '领取数量'")
    private Integer number;

    /**
     * 领取人姓名
     */
    @Column(name = "drawName", columnDefinition = "VARCHAR(255)   COMMENT '领取人姓名'")
    private String drawName;

    /**
     * 模块负责人审批意见
     */
    @Column(name = "opinion", columnDefinition = "VARCHAR(255)   COMMENT '模块负责人审批意见'")
    private String opinion;

    /**
     * 领取完成
     */
    @Column(name = "drawFinish", columnDefinition = "VARCHAR(255)   COMMENT '领取完成'")
    private String drawFinish;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 持卡人是否领取
     */
    @Column(name = "is_draw", columnDefinition = "TINYINT(1)   COMMENT '持卡人是否领取'")
    private Boolean draw;


    public LocalDate getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDate drawTime) {
        this.drawTime = drawTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public Boolean getDrawSecureCard() {
        return drawSecureCard;
    }

    public void setDrawSecureCard(Boolean drawSecureCard) {
        this.drawSecureCard = drawSecureCard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDrawFinish() {
        return drawFinish;
    }

    public void setDrawFinish(String drawFinish) {
        this.drawFinish = drawFinish;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }
}