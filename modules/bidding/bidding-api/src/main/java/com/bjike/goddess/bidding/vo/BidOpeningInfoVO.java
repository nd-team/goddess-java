package com.bjike.goddess.bidding.vo;


/**
 * 开标信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.805 ]
 * @Description: [ 开标信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoVO {

    /**
     * 招标编号
     */
    private String tenderNumber;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 开标时间String
     */
    private String bidOpeningTime;

    /**
     * 委托人
     */
    private String principal;

    /**
     * 记录人
     */
    private String recorder;

    /**
     * 竞争公司
     */
    private String competitive;

    /**
     * 地市
     */
    private String cities;

    /**
     * 竞争公司报价
     */
    private String competitivePrice;

    /**
     * 比率(%)
     */
    private String ratio;


    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(String competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}