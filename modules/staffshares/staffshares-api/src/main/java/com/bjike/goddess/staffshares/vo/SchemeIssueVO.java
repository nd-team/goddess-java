package com.bjike.goddess.staffshares.vo;

/**
 * 交易中心业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SchemeIssueVO {

//    /**
//     * 审核状态
//     */
//    private Status status;

//    /**
//     * 股份种类
//     */
//    private Type type;
    /**
     * id
     */
    private String id;

    /**
     * 日期
     */
    private String data;

    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 发行目的
     */
    private String aim;

    /**
     * 总资本
     */
    private Double totalCapital;

    /**
     * 总股本
     */
    private Double totalEquity;

    /**
     * 票面价值
     */
    private Double facevalue;

    /**
     * 计划筹资额
     */
    private Double planCapital;

    /**
     * 发行人
     */
    private String publisher;

    /**
     * 发行性质
     */
    private String issue;

    /**
     * 发行比例
     */
    private Double proportion;

    /**
     * 发行数量
     */
    private Long number;

    /**
     * 发行价格
     */
    private Double price;

    /**
     * 发行对象
     */
    private String object;

    /**
     * 发行方式
     */
    private String mode;

    /**
     * 发行/出售时间
     */
    private String time;

    /**
     * 出售方式
     */
    private String method;

    /**
     * 申购上下限标准
     */
    private String standards;

    /**
     * 分红核算时间
     */
    private String accountingTime;

    /**
     * 剩余出售股数
     */
    private Long sharesNum;

    /**
     * 备注
     */
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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

    public Long getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(Long sharesNum) {
        this.sharesNum = sharesNum;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}