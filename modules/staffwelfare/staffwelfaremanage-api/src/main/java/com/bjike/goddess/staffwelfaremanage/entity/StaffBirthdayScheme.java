package com.bjike.goddess.staffwelfaremanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.staffwelfaremanage.enums.WelfareType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 员工生日福利方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfaremanage_staffbirthdayscheme")
public class StaffBirthdayScheme extends BaseEntity {

    /**
     * 方案执行日期
     */
    @Column(name = "schemeTime", nullable = false, columnDefinition = "DATETIME   COMMENT '方案执行日期'")
    private LocalDateTime schemeTime;

    /**
     * 人员地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '人员地区'")
    private String area;

    /**
     * 所属项目组部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组部门'")
    private String department;

    /**
     * 人员数量
     */
    @Column(name = "staffAmount", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '人员数量'")
    private Integer staffAmount;

    /**
     * 福利类型
     */
    @Column(name = "welfareType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '福利类型'")
    private WelfareType welfareType;

    /**
     * 福利名称
     */
    @Column(name = "welfareName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '福利名称'")
    private String welfareName;

    /**
     * 福利内容明细
     */
    @Column(name = "welfareDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '福利内容明细'")
    private String welfareDetail;

    /**
     * 发放形式
     */
    @Column(name = "sendType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发放形式'")
    private String sendType;

    /**
     * 发放时间
     */
    @Column(name = "sendTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发放时间'")
    private String sendTime;

    /**
     * 福利费用明细
     */
    @Column(name = "welfareFeeDetial", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '福利费用明细'")
    private String welfareFeeDetial;

    /**
     * 福利总该费用
     */
    @Column(name = "welfareTotalFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '福利总该费用'")
    private Double welfareTotalFee;

    /**
     * 项目经理意见
     */
    @Column(name = "pmSuggestion", columnDefinition = "VARCHAR(255)   COMMENT '项目经理意见'")
    private String pmSuggestion;

    /**
     * 项目经理审核人
     */
    @Column(name = "pmUser", columnDefinition = "VARCHAR(255)   COMMENT '项目审核人'")
    private String pmUser;

    /**
     * 运营商务部意见
     */
    @Column(name = "yyswbSuggestion", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部意见'")
    private String yyswbSuggestion;

    /**
     * 运营商务部审核人
     */
    @Column(name = "yyswbUser", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部审核人'")
    private String yyswbUser;

    /**
     * 总经办意见
     */
    @Column(name = "generalManageSug", columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String generalManageSug;

    /**
     * 总经办审核人
     */
    @Column(name = "generalManageUser", columnDefinition = "VARCHAR(255)   COMMENT '总经办审核人'")
    private String generalManageUser;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 是否领取
     */
    @Column(name = "is_receive", nullable = false, insertable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否共享为公有(1是,0否)'")
    private Boolean receive;


    public LocalDateTime getSchemeTime() {
        return schemeTime;
    }

    public void setSchemeTime(LocalDateTime schemeTime) {
        this.schemeTime = schemeTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getStaffAmount() {
        return staffAmount;
    }

    public void setStaffAmount(Integer staffAmount) {
        this.staffAmount = staffAmount;
    }

    public WelfareType getWelfareType() {
        return welfareType;
    }

    public void setWelfareType(WelfareType welfareType) {
        this.welfareType = welfareType;
    }

    public String getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(String welfareName) {
        this.welfareName = welfareName;
    }

    public String getWelfareDetail() {
        return welfareDetail;
    }

    public void setWelfareDetail(String welfareDetail) {
        this.welfareDetail = welfareDetail;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getWelfareFeeDetial() {
        return welfareFeeDetial;
    }

    public void setWelfareFeeDetial(String welfareFeeDetial) {
        this.welfareFeeDetial = welfareFeeDetial;
    }

    public Double getWelfareTotalFee() {
        return welfareTotalFee;
    }

    public void setWelfareTotalFee(Double welfareTotalFee) {
        this.welfareTotalFee = welfareTotalFee;
    }

    public String getPmSuggestion() {
        return pmSuggestion;
    }

    public void setPmSuggestion(String pmSuggestion) {
        this.pmSuggestion = pmSuggestion;
    }

    public String getPmUser() {
        return pmUser;
    }

    public void setPmUser(String pmUser) {
        this.pmUser = pmUser;
    }

    public String getYyswbSuggestion() {
        return yyswbSuggestion;
    }

    public void setYyswbSuggestion(String yyswbSuggestion) {
        this.yyswbSuggestion = yyswbSuggestion;
    }

    public String getYyswbUser() {
        return yyswbUser;
    }

    public void setYyswbUser(String yyswbUser) {
        this.yyswbUser = yyswbUser;
    }

    public String getGeneralManageSug() {
        return generalManageSug;
    }

    public void setGeneralManageSug(String generalManageSug) {
        this.generalManageSug = generalManageSug;
    }

    public String getGeneralManageUser() {
        return generalManageUser;
    }

    public void setGeneralManageUser(String generalManageUser) {
        this.generalManageUser = generalManageUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public StaffBirthdayScheme() {
    }

    public StaffBirthdayScheme(LocalDateTime schemeTime, String area, String department,
                               Integer staffAmount, WelfareType welfareType, String welfareName,
                               String welfareDetail, String sendType, String sendTime,
                               String welfareFeeDetial, Double welfareTotalFee, String pmSuggestion,
                               String pmUser, String yyswbSuggestion, String yyswbUser,
                               String generalManageSug, String generalManageUser, String remark) {
        this.schemeTime = schemeTime;
        this.area = area;
        this.department = department;
        this.staffAmount = staffAmount;
        this.welfareType = welfareType;
        this.welfareName = welfareName;
        this.welfareDetail = welfareDetail;
        this.sendType = sendType;
        this.sendTime = sendTime;
        this.welfareFeeDetial = welfareFeeDetial;
        this.welfareTotalFee = welfareTotalFee;
        this.pmSuggestion = pmSuggestion;
        this.pmUser = pmUser;
        this.yyswbSuggestion = yyswbSuggestion;
        this.yyswbUser = yyswbUser;
        this.generalManageSug = generalManageSug;
        this.generalManageUser = generalManageUser;
        this.remark = remark;
    }

    public Boolean getReceive() {
        return receive;
    }

    public void setReceive(Boolean receive) {
        this.receive = receive;
    }
}