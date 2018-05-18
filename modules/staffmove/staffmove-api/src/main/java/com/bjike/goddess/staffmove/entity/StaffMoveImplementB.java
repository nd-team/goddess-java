package com.bjike.goddess.staffmove.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 人员调动实施
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:47 ]
 * @Description: [ 人员调动实施 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmove_staffmoveimplementb")
public class StaffMoveImplementB extends BaseEntity {

    /**
     * 被调动地区
     */
    @Column(name = "mobilizedArea", columnDefinition = "VARCHAR(255)   COMMENT '被调动地区'")
    private String mobilizedArea;

    /**
     * 被调动项目组/部门
     */
    @Column(name = "mobilizedProjectGroup", columnDefinition = "VARCHAR(255)   COMMENT '被调动项目组/部门'")
    private String mobilizedProjectGroup;

    /**
     * 调动人数
     */
    @Column(name = "transferNum", columnDefinition = "INT(10)   COMMENT '调动人数'")
    private Integer transferNum;

    /**
     * 被调动人员
     */
    @Column(name = "mobilizedPeople", columnDefinition = "VARCHAR(255)   COMMENT '被调动人员'")
    private String mobilizedPeople;

    /**
     * 被调动人员主岗位
     */
    @Column(name = "mobilizedMainPosition", columnDefinition = "VARCHAR(255)   COMMENT '被调动人员主岗位'")
    private String mobilizedMainPosition;

    /**
     * 是否需要安排住宿
     */
    @Column(name = "is_arrangeHotel", columnDefinition = "TINYINT(1)    COMMENT '是否需要安排住宿'")
    private Boolean arrangeHotel;

    /**
     * 是否需要安排交通
     */
    @Column(name = "is_arrangeTraffic", columnDefinition = "TINYINT(1)    COMMENT '是否需要安排交通'")
    private Boolean arrangeTraffic;

    /**
     * 计划到岗时间
     */
    @Column(name = "planWorkTime", columnDefinition = "DATE   COMMENT '计划到岗时间'")
    private LocalDate planWorkTime;

    /**
     * 实际到岗时间
     */
    @Column(name = "realityWorkTime", columnDefinition = "DATE   COMMENT '实际到岗时间'")
    private LocalDate realityWorkTime;

    /**
     * 总经办
     */
    @Column(name = "general", columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String general;

    /**
     * 审核意见
     */
    @Column(name = "generalIdea", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String generalIdea;

    /**
     * 是否实施调动
     */
    @Column(name = "is_obeyStaff", columnDefinition = "TINYINT(1)    COMMENT '是否实施调动'")
    private Boolean obeyStaff;

    /**
     * 原决策层
     */
    @Column(name = "original", columnDefinition = "VARCHAR(255)   COMMENT '原决策层'")
    private String original;

    /**
     * 审核意见
     */
    @Column(name = "originalIdea", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String originalIdea;

    /**
     * 调往决策层
     */
    @Column(name = "transfer", columnDefinition = "VARCHAR(255)   COMMENT '调往决策层'")
    private String transfer;

    /**
     * 审核意见
     */
    @Column(name = "transferIdea", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String transferIdea;

    /**
     * 是否解决需求
     */
    @Column(name = "is_solveDemand", columnDefinition = "TINYINT(1)  COMMENT '是否解决需求'")
    private Boolean solveDemand;

    /**
     * 人员调动实施A
     */
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "staffMoveImplementA_id", columnDefinition = "VARCHAR(36)   COMMENT '人员调动实施A'")
    private StaffMoveImplementA staffMoveImplementA;

    public StaffMoveImplementA getStaffMoveImplementA() {
        return staffMoveImplementA;
    }

    public void setStaffMoveImplementA(StaffMoveImplementA staffMoveImplementA) {
        this.staffMoveImplementA = staffMoveImplementA;
    }

    public String getMobilizedArea() {
        return mobilizedArea;
    }

    public void setMobilizedArea(String mobilizedArea) {
        this.mobilizedArea = mobilizedArea;
    }

    public String getMobilizedProjectGroup() {
        return mobilizedProjectGroup;
    }

    public void setMobilizedProjectGroup(String mobilizedProjectGroup) {
        this.mobilizedProjectGroup = mobilizedProjectGroup;
    }

    public Integer getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(Integer transferNum) {
        this.transferNum = transferNum;
    }

    public String getMobilizedPeople() {
        return mobilizedPeople;
    }

    public void setMobilizedPeople(String mobilizedPeople) {
        this.mobilizedPeople = mobilizedPeople;
    }

    public String getMobilizedMainPosition() {
        return mobilizedMainPosition;
    }

    public void setMobilizedMainPosition(String mobilizedMainPosition) {
        this.mobilizedMainPosition = mobilizedMainPosition;
    }

    public Boolean getArrangeHotel() {
        return arrangeHotel;
    }

    public void setArrangeHotel(Boolean arrangeHotel) {
        this.arrangeHotel = arrangeHotel;
    }

    public Boolean getArrangeTraffic() {
        return arrangeTraffic;
    }

    public void setArrangeTraffic(Boolean arrangeTraffic) {
        this.arrangeTraffic = arrangeTraffic;
    }

    public LocalDate getPlanWorkTime() {
        return planWorkTime;
    }

    public void setPlanWorkTime(LocalDate planWorkTime) {
        this.planWorkTime = planWorkTime;
    }

    public LocalDate getRealityWorkTime() {
        return realityWorkTime;
    }

    public void setRealityWorkTime(LocalDate realityWorkTime) {
        this.realityWorkTime = realityWorkTime;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getGeneralIdea() {
        return generalIdea;
    }

    public void setGeneralIdea(String generalIdea) {
        this.generalIdea = generalIdea;
    }

    public Boolean getObeyStaff() {
        return obeyStaff;
    }

    public void setObeyStaff(Boolean obeyStaff) {
        this.obeyStaff = obeyStaff;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalIdea() {
        return originalIdea;
    }

    public void setOriginalIdea(String originalIdea) {
        this.originalIdea = originalIdea;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getTransferIdea() {
        return transferIdea;
    }

    public void setTransferIdea(String transferIdea) {
        this.transferIdea = transferIdea;
    }

    public Boolean getSolveDemand() {
        return solveDemand;
    }

    public void setSolveDemand(Boolean solveDemand) {
        this.solveDemand = solveDemand;
    }
}