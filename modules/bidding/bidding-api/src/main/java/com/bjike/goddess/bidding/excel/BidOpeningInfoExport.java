package com.bjike.goddess.bidding.excel;


import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 开标信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.805 ]
 * @Description: [ 开标信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoExport {

    /**
     * 编号
     */
    @ExcelHeader(name = "编号",notNull = true)
    private String biddingNumber;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projectName;

    /**
     * 开标时间
     */
    @ExcelHeader(name = "开标时间",notNull = true)
    private String bidOpeningTime;
    /**
     * 开标地点
     */
    @ExcelHeader(name = "开标地点",notNull = true)
    private String bidOpeningPlace;
    /**
     * 委托人
     */
    @ExcelHeader(name = "委托人",notNull = true)
    private String principal;

    /**
     * 记录人
     */
    @ExcelHeader(name = "记录人",notNull = true)
    private String recorder;

    /**
     * 竞争公司
     */
    @ExcelHeader(name = "竞争公司",notNull = true)
    private String competitive;

    /**
     * 地市
     */
    @ExcelHeader(name = "地市",notNull = true)
    private String cities;

    /**
     * 竞争公司报价
     */
    @ExcelHeader(name = "竞争公司报价",notNull = true)
    private Double competitivePrice;

    /**
     * 比率(%)
     */
    @ExcelHeader(name = "比率(%)",notNull = true)
    private Double ratio;


    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
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