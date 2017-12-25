package com.bjike.goddess.staffwelfare.vo;

import com.bjike.goddess.staffwelfare.enums.WelfareType;

/**
 * 员工生日福利方案表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffBirthdaySchemeVO {

    /**
     * id
     */
    private String id;
    /**
     * 方案执行日期
     */
    private String schemeTime;

    /**
     * 人员地区
     */
    private String area;

    /**
     * 所属项目组部门
     */
    private String department;

    /**
     * 人员数量
     */
    private Integer staffAmount;

    /**
     * 福利类型
     */
    private WelfareType welfareType;

    /**
     * 福利名称
     */
    private String welfareName;

    /**
     * 福利内容明细
     */
    private String welfareDetail;

    /**
     * 发放形式
     */
    private String sendType;

    /**
     * 发放时间
     */
    private String sendTime;

    /**
     * 福利费用明细
     */
    private String welfareFeeDetial;

    /**
     * 福利总该费用
     */
    private Double welfareTotalFee;

    /**
     * 项目经理意见
     */
    private String pmSuggestion;

    /**
     * 项目经理审核人
     */
    private String pmUser;

    /**
     * 运营商务部意见
     */
    private String yyswbSuggestion;

    /**
     * 运营商务部审核人
     */
    private String yyswbUser;

    /**
     * 总经办意见
     */
    private String generalManageSug;

    /**
     * 总经办审核人
     */
    private String generalManageUser;

    /**
     * 备注
     */
    private String remark;

    public String getSchemeTime() {
        return schemeTime;
    }

    public void setSchemeTime(String schemeTime) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}