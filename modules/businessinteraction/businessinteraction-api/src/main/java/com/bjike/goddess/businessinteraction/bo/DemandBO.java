package com.bjike.goddess.businessinteraction.bo;

import com.bjike.goddess.businessinteraction.enums.Origin;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 需求信息业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandBO extends BaseBO {

    /**
     * 需求提出时间
     */
    private String demandDate;

    /**
     * 需求者来源
     */
    private Origin origin;

    /**
     * 需求者姓名
     */
    private String name;

    /**
     * 需求者职能
     */
    private String work;

    /**
     * 需求者在职公司名称
     */
    private String companyName;

    /**
     * 需求者在职公司类型
     */
    private String companyType;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 需求者所在地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessTarget;

    /**
     * 规模
     */
    private String size;

    /**
     * 人工
     */
    private String artificial;

    /**
     * 设备
     */
    private String device;

    /**
     * 服务费用
     */
    private Double serviceFee;

    /**
     * 中介费用
     */
    private Double intermediaryFee;

    /**
     * 专业
     */
    private String profession;

    /**
     * 是否反馈需求者
     */
    private Boolean feedbackDemand;

    /**
     * 反馈时间
     */
    private String feedbackDate;

    /**
     * 市场信息编号
     */
    private String markerNum;

    /**
     * 初步分析时间
     */
    private String initialAnalysisDate;

    /**
     * 是否初步分析
     */
    private Boolean initialAnalysis;

    /**
     * 项目测算时间
     */
    private String projectEstimatesDate;

    /**
     * 是否进行测算
     */
    private Boolean projectEstimates;

    /**
     * 测算是否通过
     */
    private Boolean projectEstimatesThrought;

    /**
     * 洽谈时间
     */
    private String discussDate;

    /**
     * 是否进行洽谈
     */
    private Boolean discuss;

    /**
     * 我司是否达成合作
     */
    private Boolean reachedCoop;

    /**
     * 是否介绍给别的承包商
     */
    private Boolean introduceContra;

    public String getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(String demandDate) {
        this.demandDate = demandDate;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getIntermediaryFee() {
        return intermediaryFee;
    }

    public void setIntermediaryFee(Double intermediaryFee) {
        this.intermediaryFee = intermediaryFee;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Boolean getFeedbackDemand() {
        return feedbackDemand;
    }

    public void setFeedbackDemand(Boolean feedbackDemand) {
        this.feedbackDemand = feedbackDemand;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getMarkerNum() {
        return markerNum;
    }

    public void setMarkerNum(String markerNum) {
        this.markerNum = markerNum;
    }

    public String getInitialAnalysisDate() {
        return initialAnalysisDate;
    }

    public void setInitialAnalysisDate(String initialAnalysisDate) {
        this.initialAnalysisDate = initialAnalysisDate;
    }

    public Boolean getInitialAnalysis() {
        return initialAnalysis;
    }

    public void setInitialAnalysis(Boolean initialAnalysis) {
        this.initialAnalysis = initialAnalysis;
    }

    public String getProjectEstimatesDate() {
        return projectEstimatesDate;
    }

    public void setProjectEstimatesDate(String projectEstimatesDate) {
        this.projectEstimatesDate = projectEstimatesDate;
    }

    public Boolean getProjectEstimates() {
        return projectEstimates;
    }

    public void setProjectEstimates(Boolean projectEstimates) {
        this.projectEstimates = projectEstimates;
    }

    public Boolean getProjectEstimatesThrought() {
        return projectEstimatesThrought;
    }

    public void setProjectEstimatesThrought(Boolean projectEstimatesThrought) {
        this.projectEstimatesThrought = projectEstimatesThrought;
    }

    public String getDiscussDate() {
        return discussDate;
    }

    public void setDiscussDate(String discussDate) {
        this.discussDate = discussDate;
    }

    public Boolean getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Boolean discuss) {
        this.discuss = discuss;
    }

    public Boolean getReachedCoop() {
        return reachedCoop;
    }

    public void setReachedCoop(Boolean reachedCoop) {
        this.reachedCoop = reachedCoop;
    }

    public Boolean getIntroduceContra() {
        return introduceContra;
    }

    public void setIntroduceContra(Boolean introduceContra) {
        this.introduceContra = introduceContra;
    }
}