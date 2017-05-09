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
     * id
     */
    private String id;

    /**
     * 招标编号
     */
    private String tenderNumber;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 开标时间
     */
    private String bidOpeningTime;
    /**
     * 开标地点
     */
    private String bidOpeningPlace;
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
    private Double competitivePrice;

    /**
     * 比率(%)
     */
    private Double ratio;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getBidOpeningPlace() {
        return bidOpeningPlace;
    }

    public void setBidOpeningPlace(String bidOpeningPlace) {
        this.bidOpeningPlace = bidOpeningPlace;
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

    public Double getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(Double competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}