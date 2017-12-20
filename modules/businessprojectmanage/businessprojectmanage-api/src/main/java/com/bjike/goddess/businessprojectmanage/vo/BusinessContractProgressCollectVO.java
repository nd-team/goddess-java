package com.bjike.goddess.businessprojectmanage.vo;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-12 10:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessContractProgressCollectVO {

    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 项目总规模
     */
    private String scaleContract;

    /**
     * 已立项合同规模 (“是否有合同立项”为“立项”总数)
     */
    private String makeContractAmount;

    /**
     * 预立项合同规模(“是否有合同立项”为“预立项”总数)
     */
    private String forecastContractAmount;

    /**
     * 已完工规模
     */
    private String complete;

    /**
     * 未完工规模("合同规模数量"-"规模数量")
     */
    private String notCompleteContract;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(String scaleContract) {
        this.scaleContract = scaleContract;
    }

    public String getMakeContractAmount() {
        return makeContractAmount;
    }

    public void setMakeContractAmount(String makeContractAmount) {
        this.makeContractAmount = makeContractAmount;
    }

    public String getForecastContractAmount() {
        return forecastContractAmount;
    }

    public void setForecastContractAmount(String forecastContractAmount) {
        this.forecastContractAmount = forecastContractAmount;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getNotCompleteContract() {
        return notCompleteContract;
    }

    public void setNotCompleteContract(String notCompleteContract) {
        this.notCompleteContract = notCompleteContract;
    }
}
