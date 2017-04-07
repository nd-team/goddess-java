package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 洽谈详情
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TalkDetailTO extends BaseTO {

    /**
     * 需求方公司
     */
    @NotBlank(message = "需求方公司不为空")
    private String demandCompany;

    /**
     * 合作对象公司名称
     */
    @NotBlank(message = "合作对象公司名称不为空")
    private String cooperCompany;

    /**
     * 合作方式
     */
    @NotBlank(message = "合作方式不为空")
    private String cooperWay;

    /**
     * 合作时间
     */
    private String cooperTime;

    /**
     * 地区
     */
    @NotBlank(message = "地区不为空")
    private String area;

    /**
     * 业务方向
     */
    private String businessTarget;

    /**
     * 价格
     */
    private String price;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 规模
     */
    private String size;

    /**
     * 人工
     */
    private String artificial;

    /**
     * 服务费用
     */
    private String serviceFee;

    /**
     * 中介费用
     */
    private String middleFee;

    /**
     * 是否达成合作
     */
    private String cooperCondition;

    /**
     * 备注
     */
    private String remark;


    public String getDemandCompany() {
        return demandCompany;
    }

    public void setDemandCompany(String demandCompany) {
        this.demandCompany = demandCompany;
    }

    public String getCooperCompany() {
        return cooperCompany;
    }

    public void setCooperCompany(String cooperCompany) {
        this.cooperCompany = cooperCompany;
    }

    public String getCooperWay() {
        return cooperWay;
    }

    public void setCooperWay(String cooperWay) {
        this.cooperWay = cooperWay;
    }

    public String getCooperTime() {
        return cooperTime;
    }

    public void setCooperTime(String cooperTime) {
        this.cooperTime = cooperTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getMiddleFee() {
        return middleFee;
    }

    public void setMiddleFee(String middleFee) {
        this.middleFee = middleFee;
    }

    public String getCooperCondition() {
        return cooperCondition;
    }

    public void setCooperCondition(String cooperCondition) {
        this.cooperCondition = cooperCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}