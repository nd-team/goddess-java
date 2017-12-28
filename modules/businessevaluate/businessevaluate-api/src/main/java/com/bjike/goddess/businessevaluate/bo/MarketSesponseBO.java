package com.bjike.goddess.businessevaluate.bo;

import com.bjike.goddess.businessevaluate.enums.ChannelType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 市场反应和创新能力业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 11:33 ]
 * @Description: [ 市场反应和创新能力业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketSesponseBO extends BaseBO {

    /**
     * 能力成长类型
     */
    private ChannelType channelType;

    /**
     * 客户保持率
     */
    private Double customerKeepRate;

    /**
     * 客户满意度
     */
    private Double customerSatisfaction;

    /**
     * 客户满意度扣分事项
     */
    private String deductionDetail;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;


    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public Double getCustomerKeepRate() {
        return customerKeepRate;
    }

    public void setCustomerKeepRate(Double customerKeepRate) {
        this.customerKeepRate = customerKeepRate;
    }

    public Double getCustomerSatisfaction() {
        return customerSatisfaction;
    }

    public void setCustomerSatisfaction(Double customerSatisfaction) {
        this.customerSatisfaction = customerSatisfaction;
    }

    public String getDeductionDetail() {
        return deductionDetail;
    }

    public void setDeductionDetail(String deductionDetail) {
        this.deductionDetail = deductionDetail;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}