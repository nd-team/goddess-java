package com.bjike.goddess.checkhost.to;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 员工住宿天数汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StayDaysTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 宿舍地址
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "宿舍地址不能为空")
    private String address;

    /**
     * 员工姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工编号不能为空")
    private String num;

    /**
     * 入住时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "入住时间不能为空")
    private String stayTime;

    /**
     * 入住床位
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "入住床位不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "入住床位不能小于0")
    private Integer stayBed;

    /**
     * 床上3件套（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "床上3件套（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "床上3件套（件）不能小于0")
    private Integer suit;

    /**
     * 被褥（件）
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "被褥（件）不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "被褥（件）不能小于0")
    private Integer bedding;

    /**
     * 床垫
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "床垫不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "床垫不能小于0")
    private Integer mattress;

    /**
     * 是否领用钥匙
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否领用钥匙不能为空")
    private Boolean receiveKey;
    /**
     * 当月是否离宿
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "当月是否离宿不能为空")
    private Boolean bed;

    /**
     * 离宿时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "离宿时间不能为空")
    private String hostTime;

    /**
     * 当月统计住宿时间段
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "当月统计住宿时间段不能为空")
    private String stayWhen;

    /**
     * 当月住宿天数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "当月住宿天数不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "当月住宿天数不能小于0")
    private Integer stayNum;

    /**
     * 需缴费金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "需缴费金额不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "需缴费金额不能小于0")
    private Double expendAmount;

    /**
     * 员工核实是否有误
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "员工核实是否有误不能为空")
    private Boolean employeeVerify;

//    /**
//     * 综合资源部核实
//     */
//    @NotBlank(groups = {ADD.class, EDIT.class}, message = "综合资源部核实不能为空")
//    private String comprehensiveVerify;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态
     */
    private CheckStatus checkStatus;

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public Integer getStayBed() {
        return stayBed;
    }

    public void setStayBed(Integer stayBed) {
        this.stayBed = stayBed;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getMattress() {
        return mattress;
    }

    public void setMattress(Integer mattress) {
        this.mattress = mattress;
    }

    public Boolean getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(Boolean receiveKey) {
        this.receiveKey = receiveKey;
    }

    public void setBed(Boolean bed) {
        this.bed = bed;
    }

    public Boolean getBed() {
        return bed;
    }

    public String getHostTime() {
        return hostTime;
    }

    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }

    public String getStayWhen() {
        return stayWhen;
    }

    public void setStayWhen(String stayWhen) {
        this.stayWhen = stayWhen;
    }

    public Integer getStayNum() {
        return stayNum;
    }

    public void setStayNum(Integer stayNum) {
        this.stayNum = stayNum;
    }

    public Double getExpendAmount() {
        return expendAmount;
    }

    public void setExpendAmount(Double expendAmount) {
        this.expendAmount = expendAmount;
    }

    public Boolean getEmployeeVerify() {
        return employeeVerify;
    }

    public void setEmployeeVerify(Boolean employeeVerify) {
        this.employeeVerify = employeeVerify;
    }

//    public String getComprehensiveVerify() {
//        return comprehensiveVerify;
//    }
//
//    public void setComprehensiveVerify(String comprehensiveVerify) {
//        this.comprehensiveVerify = comprehensiveVerify;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}