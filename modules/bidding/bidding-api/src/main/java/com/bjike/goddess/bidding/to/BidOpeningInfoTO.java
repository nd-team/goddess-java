package com.bjike.goddess.bidding.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 开标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.804 ]
 * @Description: [ 开标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoTO extends BaseTO {

    /**
     * 编号
     */
    private String biddingNumber;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
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
    @NotBlank(message = "竞争公司不能为空", groups = {ADD.class, EDIT.class})
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