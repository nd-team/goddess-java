package com.bjike.goddess.receivable.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 回款进度业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackProgressCollectBO extends BaseBO {

    /**
     * 运营商名称
     */
    private String operatorName;

    /**
     * 地区
     */
    private String area;

    /**
     * 外包单位
     */
    private String contractor;

    /**
     * 类型
     */
    private String type;

    /**
     * 专业
     */
    private String major;

    /**
     * 派工名称
     */
    private String taskName;

    /**
     * 厂家
     */
    private String factory;

    /**
     * 销售合同号
     */
    private String salesContractNum;

    /**
     * 外包合同号
     */
    private String outsourceContractNum;

    /**
     * 派工情况
     */
    private String taskCase;

    /**
     * 派工金额
     */
    private Double taskMoney;

    /**
     * 实际完工状态
     */
    private String completeStatus;

    /**
     * 可结算金额
     */
    private Double clearingMoney;

    /**
     * 已到账金额
     */
    private Double accountMoney;


    /**
     * 总规模数
     */
    private Double scaleNum;

    /**
     * 是否可制作申请结算
     */
    private Boolean applyClearing;

    /**
     * 是否影响结算
     */
    private Boolean influenceClearing;

    /**
     * 结算计划
     */
    private String clearingPlan;

    /**
     * 正在执行项目
     */
    private String performProject;

    /**
     * 归属
     */
    private String ascription;



    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSalesContractNum() {
        return salesContractNum;
    }

    public void setSalesContractNum(String salesContractNum) {
        this.salesContractNum = salesContractNum;
    }

    public String getOutsourceContractNum() {
        return outsourceContractNum;
    }

    public void setOutsourceContractNum(String outsourceContractNum) {
        this.outsourceContractNum = outsourceContractNum;
    }

    public String getTaskCase() {
        return taskCase;
    }

    public void setTaskCase(String taskCase) {
        this.taskCase = taskCase;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }


    public Double getClearingMoney() {
        return clearingMoney;
    }

    public void setClearingMoney(Double clearingMoney) {
        this.clearingMoney = clearingMoney;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }


    public Double getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(Double scaleNum) {
        this.scaleNum = scaleNum;
    }

    public Boolean getApplyClearing() {
        return applyClearing;
    }

    public void setApplyClearing(Boolean applyClearing) {
        this.applyClearing = applyClearing;
    }

    public Boolean getInfluenceClearing() {
        return influenceClearing;
    }

    public void setInfluenceClearing(Boolean influenceClearing) {
        this.influenceClearing = influenceClearing;
    }

    public String getClearingPlan() {
        return clearingPlan;
    }

    public void setClearingPlan(String clearingPlan) {
        this.clearingPlan = clearingPlan;
    }

    public String getPerformProject() {
        return performProject;
    }

    public void setPerformProject(String performProject) {
        this.performProject = performProject;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

}