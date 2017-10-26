package com.bjike.goddess.oilcardmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 油卡基本信息
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:33]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.com.bjike.goddess.oilcardmanage.entity ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "oilcardmanage_basic")
public class OilCardBasic extends BaseEntity{

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 油卡编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '油卡编号'" , unique = true , nullable = false)
    private String oilCardCode;

    /**
     * 卡号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '卡号'" , unique = true , nullable = false)
    private String oilCardNumber;

    /**
     * 主卡/副卡
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '主卡/副卡'")
    private String mainOrDeputy;

    /**
     * 所属主卡
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '所属主卡'")
    private String belongMainCard;

    /**
     * 办理人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '办理人'")
    private String handlingUser;

    /**
     * 办理日期
     */
    @Column(columnDefinition = "DATE COMMENT '办理日期'")
    private LocalDate handlingDate;

    /**
     * 密码
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '密码'")
    private String cardPassWord;

    /**
     * 使用地区
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '使用地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '项目组/部门'")
    private String department;

    /**
     * 更新时间
     */
    @Column(columnDefinition = "DATE COMMENT '更新时间'")
    private LocalDate updateTime;

    /**
     * 项目名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String project;

    /**
     * 领用状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '油卡状态'", nullable = false, insertable = false)
    private OilCardStatus cardStatus;

    /**
     * 使用状态更新时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '使用状态更新时间'")
    private LocalDateTime updateStatusTime;

    /**
     * 期初金额
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '期初金额'")
    private Double cycleEarlyMoney;

    /**
     * 油卡余额
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '余额'",nullable = false)
    private Double balance;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public String getMainOrDeputy() {
        return mainOrDeputy;
    }

    public void setMainOrDeputy(String mainOrDeputy) {
        this.mainOrDeputy = mainOrDeputy;
    }

    public String getBelongMainCard() {
        return belongMainCard;
    }

    public void setBelongMainCard(String belongMainCard) {
        this.belongMainCard = belongMainCard;
    }

    public String getHandlingUser() {
        return handlingUser;
    }

    public void setHandlingUser(String handlingUser) {
        this.handlingUser = handlingUser;
    }

    public LocalDate getHandlingDate() {
        return handlingDate;
    }

    public void setHandlingDate(LocalDate handlingDate) {
        this.handlingDate = handlingDate;
    }

    public String getCardPassWord() {
        return cardPassWord;
    }

    public void setCardPassWord(String cardPassWord) {
        this.cardPassWord = cardPassWord;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public OilCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(OilCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public LocalDateTime getUpdateStatusTime() {
        return updateStatusTime;
    }

    public void setUpdateStatusTime(LocalDateTime updateStatusTime) {
        this.updateStatusTime = updateStatusTime;
    }

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }
}
