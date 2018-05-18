package com.bjike.goddess.businessinteraction.excel;

import com.bjike.goddess.businessinteraction.enums.Origin;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;


/**
 * 需求信息导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandExcel extends BaseTO {

    /**
     * 需求者来源
     */
    @ExcelHeader(name = "需求者来源", notNull = true)
    private Origin origin;

    /**
     * 需求者姓名
     */
    @ExcelHeader(name = "需求者姓名", notNull = true)
    private String name;

    /**
     * 需求者职能
     */
    @ExcelHeader(name = "需求者职能")
    private String work;

    /**
     * 需求者在职公司名称
     */
    @ExcelHeader(name = "需求者在职公司名称", notNull = true)
    private String companyName;

    /**
     * 需求者在职公司类型
     */
    @ExcelHeader(name = "需求者在职公司类型")
    private String companyType;

    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式", notNull = true)
    private String contactWay;

    /**
     * 需求者所在地区
     */
    @ExcelHeader(name = "需求者所在地区", notNull = true)
    private String area;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型", notNull = true)
    private String businessTarget;

    /**
     * 规模
     */
    @ExcelHeader(name = "规模", notNull = true)
    private String size;

    /**
     * 人工
     */
    @ExcelHeader(name = "人工", notNull = true)
    private String artificial;

    /**
     * 设备
     */
    @ExcelHeader(name = "设备", notNull = true)
    private String device;

    /**
     * 服务费用
     */
    @ExcelHeader(name = "服务费用", notNull = true)
    private Double serviceFee;

    /**
     * 中介费用
     */
    @ExcelHeader(name = "中介费用", notNull = true)
    private Double intermediaryFee;

    /**
     * 专业
     */
    @ExcelHeader(name = "专业", notNull = true)
    private String profession;

    /**
     * 是否反馈需求者
     */
    @ExcelHeader(name = "是否反馈需求者", notNull = true)
    private String feedbackDemand;

    /**
     * 反馈时间
     */
    @ExcelHeader(name = "反馈时间", notNull = true)
    private LocalDate feedbackDate;

    /**
     * 市场信息编号
     */
    @ExcelHeader(name = "市场信息编号", notNull = true)
    private String markerNum;

    /**
     * 初步分析时间
     */
    @ExcelHeader(name = "初步分析时间", notNull = true)
    private LocalDate initialAnalysisDate;

    /**
     * 是否初步分析
     */
    @ExcelHeader(name = "是否初步分析", notNull = true)
    private String initialAnalysis;

    /**
     * 项目测算时间
     */
    @ExcelHeader(name = "项目测算时间", notNull = true)
    private LocalDate projectEstimatesDate;

    /**
     * 是否进行测算
     */
    @ExcelHeader(name = "是否进行测算", notNull = true)
    private String projectEstimates;

    /**
     * 测算是否通过
     */
    @ExcelHeader(name = "测算是否通过", notNull = true)
    private String projectEstimatesThrought;

    /**
     * 洽谈时间
     */
    @ExcelHeader(name = "洽谈时间", notNull = true)
    private LocalDate discussDate;

    /**
     * 是否进行洽谈
     */
    @ExcelHeader(name = "是否进行洽谈", notNull = true)
    private String discuss;

    /**
     * 我司是否达成合作
     */
    @ExcelHeader(name = "我司是否达成合作", notNull = true)
    private String reachedCoop;

    /**
     * 是否介绍给别的承包商
     */
    @ExcelHeader(name = "是否介绍给别的承包商", notNull = true)
    private String introduceContra;

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

    public String getFeedbackDemand() {
        return feedbackDemand;
    }

    public void setFeedbackDemand(String feedbackDemand) {
        this.feedbackDemand = feedbackDemand;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getMarkerNum() {
        return markerNum;
    }

    public void setMarkerNum(String markerNum) {
        this.markerNum = markerNum;
    }

    public LocalDate getInitialAnalysisDate() {
        return initialAnalysisDate;
    }

    public void setInitialAnalysisDate(LocalDate initialAnalysisDate) {
        this.initialAnalysisDate = initialAnalysisDate;
    }

    public String getInitialAnalysis() {
        return initialAnalysis;
    }

    public void setInitialAnalysis(String initialAnalysis) {
        this.initialAnalysis = initialAnalysis;
    }

    public LocalDate getProjectEstimatesDate() {
        return projectEstimatesDate;
    }

    public void setProjectEstimatesDate(LocalDate projectEstimatesDate) {
        this.projectEstimatesDate = projectEstimatesDate;
    }

    public String getProjectEstimates() {
        return projectEstimates;
    }

    public void setProjectEstimates(String projectEstimates) {
        this.projectEstimates = projectEstimates;
    }

    public String getProjectEstimatesThrought() {
        return projectEstimatesThrought;
    }

    public void setProjectEstimatesThrought(String projectEstimatesThrought) {
        this.projectEstimatesThrought = projectEstimatesThrought;
    }

    public LocalDate getDiscussDate() {
        return discussDate;
    }

    public void setDiscussDate(LocalDate discussDate) {
        this.discussDate = discussDate;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getReachedCoop() {
        return reachedCoop;
    }

    public void setReachedCoop(String reachedCoop) {
        this.reachedCoop = reachedCoop;
    }

    public String getIntroduceContra() {
        return introduceContra;
    }

    public void setIntroduceContra(String introduceContra) {
        this.introduceContra = introduceContra;
    }
}