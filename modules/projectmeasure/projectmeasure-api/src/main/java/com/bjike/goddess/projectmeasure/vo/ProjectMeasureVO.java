package com.bjike.goddess.projectmeasure.vo;

import java.io.Serializable;

/**
 *
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-23 21:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectMeasureVO implements Serializable {

    /**
     * 地区
     */
    private String area;
    /**
     * 项目数量
     */
    private Integer projectCount;
    /**
     * 项目利润
     */
    private Double projectProfit;
    /**
     * 多项目多界面项目数量
     */
    private Integer mmProjectCount;
    /**
     * 多项目单界面项目数量
     */
    private Integer msProjectCount;
    /**
     * 单项目多界面项目数量
     */
    private Integer smProjectCount;
    /**
     * 单项目单界面项目数量
     */
    private Integer ssProjectCount;
    /**
     * 长期合作项目数量
     */
    private Integer longTermProjectCount;
    /**
     * 事项合作项目数量
     */
    private Integer matterProjectCount;
    /**
     * 中介项目数量
     */
    private Integer agencyProjectCount;
    /**
     * 测评结果为盈利项目数量
     */
    private Integer testProfitProjectCount;
    /**
     * 测评结果为亏损项目数量
     */
    private Integer testDeficitProjectCount;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Double getProjectProfit() {
        return projectProfit;
    }

    public void setProjectProfit(Double projectProfit) {
        this.projectProfit = projectProfit;
    }

    public Integer getMmProjectCount() {
        return mmProjectCount;
    }

    public void setMmProjectCount(Integer mmProjectCount) {
        this.mmProjectCount = mmProjectCount;
    }

    public Integer getMsProjectCount() {
        return msProjectCount;
    }

    public void setMsProjectCount(Integer msProjectCount) {
        this.msProjectCount = msProjectCount;
    }

    public Integer getSmProjectCount() {
        return smProjectCount;
    }

    public void setSmProjectCount(Integer smProjectCount) {
        this.smProjectCount = smProjectCount;
    }

    public Integer getSsProjectCount() {
        return ssProjectCount;
    }

    public void setSsProjectCount(Integer ssProjectCount) {
        this.ssProjectCount = ssProjectCount;
    }

    public Integer getLongTermProjectCount() {
        return longTermProjectCount;
    }

    public void setLongTermProjectCount(Integer longTermProjectCount) {
        this.longTermProjectCount = longTermProjectCount;
    }

    public Integer getMatterProjectCount() {
        return matterProjectCount;
    }

    public void setMatterProjectCount(Integer matterProjectCount) {
        this.matterProjectCount = matterProjectCount;
    }

    public Integer getAgencyProjectCount() {
        return agencyProjectCount;
    }

    public void setAgencyProjectCount(Integer agencyProjectCount) {
        this.agencyProjectCount = agencyProjectCount;
    }

    public Integer getTestProfitProjectCount() {
        return testProfitProjectCount;
    }

    public void setTestProfitProjectCount(Integer testProfitProjectCount) {
        this.testProfitProjectCount = testProfitProjectCount;
    }

    public Integer getTestDeficitProjectCount() {
        return testDeficitProjectCount;
    }

    public void setTestDeficitProjectCount(Integer testDeficitProjectCount) {
        this.testDeficitProjectCount = testDeficitProjectCount;
    }

}
