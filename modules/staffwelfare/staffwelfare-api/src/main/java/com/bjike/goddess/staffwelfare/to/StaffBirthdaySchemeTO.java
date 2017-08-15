package com.bjike.goddess.staffwelfare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffwelfare.enums.WelfareType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 员工生日福利方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffBirthdaySchemeTO extends BaseTO {

    /**
     * 方案执行日期
     */
    @NotBlank(message = "方案执行日期不能为空!", groups = {ADD.class, EDIT.class})
    private String schemeTime;

    /**
     * 人员地区
     */
    @NotBlank(message = "人员地区不能为空!", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 所属项目组部门
     */
    @NotBlank(message = "所属项目组部门不能为空!", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 人员数量
     */
    @NotBlank(message = "人员数量不能为空!", groups = {ADD.class, EDIT.class})
    private Integer staffAmount;

    /**
     * 福利类型
     */
    @NotBlank(message = "福利类型不能为空!", groups = {ADD.class, EDIT.class})
    private WelfareType welfareType;

    /**
     * 福利名称
     */
    @NotBlank(message = "福利名称不能为空!", groups = {ADD.class, EDIT.class})
    private String welfareName;

    /**
     * 福利内容明细
     */
    @NotBlank(message = "福利内容明细不能为空!", groups = {ADD.class, EDIT.class})
    private String welfareDetail;

    /**
     * 发放形式
     */
    @NotBlank(message = "发放形式不能为空!", groups = {ADD.class, EDIT.class})
    private String sendType;

    /**
     * 发放时间
     */
    @NotBlank(message = "发放时间不能为空!", groups = {ADD.class, EDIT.class})
    private String sendTime;

    /**
     * 福利费用明细
     */
    @NotBlank(message = "福利费用明细不能为空!", groups = {ADD.class, EDIT.class})
    private String welfareFeeDetial;

    /**
     * 福利总费用
     */
    @NotNull(message = "福利总费用不能为空!", groups = {ADD.class, EDIT.class})
    private Double welfareTotalFee;

    /**
     * 项目经理意见
     */
    @NotBlank(message = "项目经理意见不能为空!", groups = {ADD.class, EDIT.class})
    private String pmSuggestion;

    /**
     * 项目经理审核人
     */
    @NotBlank(message = "项目经理审核人不能为空!", groups = {ADD.class, EDIT.class})
    private String pmUser;

    /**
     * 运营商务部意见
     */
    @NotBlank(message = "运营商务部意见不能为空!", groups = {ADD.class, EDIT.class})
    private String yyswbSuggestion;

    /**
     * 运营商务部审核人
     */
    @NotBlank(message = "运营商务部审核人不能为空!", groups = {ADD.class, EDIT.class})
    private String yyswbUser;

    /**
     * 总经办意见
     */
    @NotBlank(message = "总经办意见不能为空!", groups = {ADD.class, EDIT.class})
    private String generalManageSug;

    /**
     * 总经办审核人
     */
    @NotBlank(message = "总经办审核人不能为空!", groups = {ADD.class, EDIT.class})
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
}