package com.bjike.goddess.staffshares.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffshares.enums.Status;
import com.bjike.goddess.staffshares.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 员工持股管理
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SchemeApplyTO extends BaseTO {

    /**
     * 股份种类
     */
    @NotNull(message = "股份种类不能为空", groups = {ADD.class, EDIT.class})
    private Type type;

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 发行目的
     */
    @NotBlank(message = "发行目的不能为空", groups = {ADD.class, EDIT.class})
    private String aim;

    /**
     * 总资本
     */
    @NotNull(message = "总资本不能为空", groups = {ADD.class, EDIT.class})
    private Double totalCapital;

    /**
     * 总股本
     */
    @NotNull(message = "总股本不能为空", groups = {ADD.class, EDIT.class})
    private Double totalEquity;

    /**
     * 票面价值
     */
    @NotNull(message = "票面价值不能为空", groups = {ADD.class, EDIT.class})
    private Double facevalue;

    /**
     * 计划筹资额
     */
    @NotNull(message = "计划筹资额不能为空", groups = {ADD.class, EDIT.class})
    private Double planCapital;

    /**
     * 发行人
     */
    @NotBlank(message = "发行人不能为空", groups = {ADD.class, EDIT.class})
    private String publisher;

    /**
     * 发行性质
     */
    @NotBlank(message = "发行性质不能为空", groups = {ADD.class, EDIT.class})
    private String issue;

    /**
     * 发行比例
     */
    @NotBlank(message = "发行比例不能为空", groups = {ADD.class, EDIT.class})
    private Double proportion;

    /**
     * 发行数量
     */
    @NotNull(message = "发行数量不能为空", groups = {ADD.class, EDIT.class})
    private int number;

    /**
     * 发行价格
     */
    @NotNull(message = "发行价格不能为空", groups = {ADD.class, EDIT.class})
    private Double price;

    /**
     * 发行对象
     */
    @NotBlank(message = "发行对象不能为空", groups = {ADD.class, EDIT.class})
    private String object;

    /**
     * 发行方式
     */
    @NotBlank(message = "发行方式不能为空", groups = {ADD.class, EDIT.class})
    private String mode;

    /**
     * 发行时间
     */
    @NotNull(message = "发行时间不能为空", groups = {ADD.class, EDIT.class})
    private String time;

    /**
     * 出售方式
     */
    @NotBlank(message = "出售方式不能为空", groups = {ADD.class, EDIT.class})
    private String method;

    /**
     * 申购上下限标准
     */
    @NotBlank(message = "申购上下限标准不能为空", groups = {ADD.class, EDIT.class})
    private String standards;

    /**
     * 分红核算时间
     */
    @NotNull(message = "分红核算时间不能为空", groups = {ADD.class, EDIT.class})
    private String accountingTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态
     */
    private Status status;

    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案制定时间
     */
    private String programmeTime;

    /**
     * 方案制定人
     */
    private String setters;

    /**
     * 总经办
     */
    private String manager;

    /**
     * 审核意见
     */
    private String opinion;

    /**
     * 剩余出售股数
     */
    private int sharesNum;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public Double getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(Double totalCapital) {
        this.totalCapital = totalCapital;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(Double facevalue) {
        this.facevalue = facevalue;
    }

    public Double getPlanCapital() {
        return planCapital;
    }

    public void setPlanCapital(Double planCapital) {
        this.planCapital = planCapital;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getAccountingTime() {
        return accountingTime;
    }

    public void setAccountingTime(String accountingTime) {
        this.accountingTime = accountingTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProgrammeTime() {
        return programmeTime;
    }

    public void setProgrammeTime(String programmeTime) {
        this.programmeTime = programmeTime;
    }

    public String getSetters() {
        return setters;
    }

    public void setSetters(String setters) {
        this.setters = setters;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(int sharesNum) {
        this.sharesNum = sharesNum;
    }
}