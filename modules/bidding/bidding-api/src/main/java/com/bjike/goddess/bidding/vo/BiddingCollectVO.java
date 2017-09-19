package com.bjike.goddess.bidding.vo;

/**
 * 招投标流程进度管理汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-15 11:44]
 * @Description: [招投标流程进度管理汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BiddingCollectVO {
    /**
     * 招投标业务类型
     */
    private String businessType;
    /**
     * 年份
     */
    private Integer year;

    /**
     * 招标的信息数量
     */
    private Integer biddinginfoNum;
    /**
     * 可项目测算数量
     */
    private Integer projectEstimatesNum;
    /**
     * 可投标规模数量
     */
    private Integer scaleNum;
    /**
     * 可投标项目数量
     */
    private Integer biddingProjectNum;
    /**
     * 竞争对手
     */
    private Integer contendNum;

    /**
     * 招标价格
     */
    private Double biddingPrice;

    /**
     * 标书制作数
     */
    private Integer bidNum;
    /**
     * 中标数量
     */
    private Integer winNum;
    /**
     * 保证金金额
     */
    private Double marginPrice;
    /**
     * 退回保证金金额
     */
    private Double returnMarginPrice;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getBiddinginfoNum() {
        return biddinginfoNum;
    }

    public void setBiddinginfoNum(Integer biddinginfoNum) {
        this.biddinginfoNum = biddinginfoNum;
    }

    public Integer getProjectEstimatesNum() {
        return projectEstimatesNum;
    }

    public void setProjectEstimatesNum(Integer projectEstimatesNum) {
        this.projectEstimatesNum = projectEstimatesNum;
    }

    public Integer getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(Integer scaleNum) {
        this.scaleNum = scaleNum;
    }

    public Integer getBiddingProjectNum() {
        return biddingProjectNum;
    }

    public void setBiddingProjectNum(Integer biddingProjectNum) {
        this.biddingProjectNum = biddingProjectNum;
    }

    public Integer getContendNum() {
        return contendNum;
    }

    public void setContendNum(Integer contendNum) {
        this.contendNum = contendNum;
    }

    public Double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(Double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public Integer getBidNum() {
        return bidNum;
    }

    public void setBidNum(Integer bidNum) {
        this.bidNum = bidNum;
    }

    public Integer getWinNum() {
        return winNum;
    }

    public void setWinNum(Integer winNum) {
        this.winNum = winNum;
    }

    public Double getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(Double marginPrice) {
        this.marginPrice = marginPrice;
    }

    public Double getReturnMarginPrice() {
        return returnMarginPrice;
    }

    public void setReturnMarginPrice(Double returnMarginPrice) {
        this.returnMarginPrice = returnMarginPrice;
    }
}
