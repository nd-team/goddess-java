package com.bjike.goddess.market.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 市场信息汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-03-21T11:00:01.544 ]
 * @Description: [ 市场信息汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationAreaBO extends BaseBO {

    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 地区
     */
    private String area;

    /**
     * 现有市场信息
     */
    private Integer exisMarketInfoNum;

    /**
     * 招投标数量
     */
    private Integer biddingNum;

    /**
     * 市场招待获取的信息数量
     */
    private Integer marketForNum;

    /**
     * 介绍数量
     */
    private Integer introduceNum;

    /**
     * 商务洽谈获取的市场信息数量
     */
    private Integer bussNegotiaNum;

    /**
     * 有效信息
     */
    private Integer vilidityNum;

    /**
     * 无效信息
     */
    private Integer invilidNum;

    /**
     * 与商机关联的客户数量
     */
    private Integer bussOppCustomNum;

    /**
     * 竞争对象数量
     */
    private Integer competingNum;

    /**
     * 已转换商机
     */
    private Integer bussOppNum;

    /**
     * 预估转正金额
     */
    private Double estTransferAmount;

    /**
     * 预估市场亏损金额
     */
    private Double estMarketLossAmount;

    /**
     * 已初步分析数
     */
    private Integer prelimAnalyNum;

    /**
     * 转换为市场招待数
     */
    private Integer convertMarketNum;

    /**
     * 转换为商务洽谈数
     */
    private Integer convertBussNegotiaNum;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getExisMarketInfoNum() {
        return exisMarketInfoNum;
    }

    public void setExisMarketInfoNum(Integer exisMarketInfoNum) {
        this.exisMarketInfoNum = exisMarketInfoNum;
    }

    public Integer getBiddingNum() {
        return biddingNum;
    }

    public void setBiddingNum(Integer biddingNum) {
        this.biddingNum = biddingNum;
    }

    public Integer getMarketForNum() {
        return marketForNum;
    }

    public void setMarketForNum(Integer marketForNum) {
        this.marketForNum = marketForNum;
    }

    public Integer getIntroduceNum() {
        return introduceNum;
    }

    public void setIntroduceNum(Integer introduceNum) {
        this.introduceNum = introduceNum;
    }

    public Integer getBussNegotiaNum() {
        return bussNegotiaNum;
    }

    public void setBussNegotiaNum(Integer bussNegotiaNum) {
        this.bussNegotiaNum = bussNegotiaNum;
    }

    public Integer getVilidityNum() {
        return vilidityNum;
    }

    public void setVilidityNum(Integer vilidityNum) {
        this.vilidityNum = vilidityNum;
    }

    public Integer getInvilidNum() {
        return invilidNum;
    }

    public void setInvilidNum(Integer invilidNum) {
        this.invilidNum = invilidNum;
    }

    public Integer getBussOppCustomNum() {
        return bussOppCustomNum;
    }

    public void setBussOppCustomNum(Integer bussOppCustomNum) {
        this.bussOppCustomNum = bussOppCustomNum;
    }

    public Integer getCompetingNum() {
        return competingNum;
    }

    public void setCompetingNum(Integer competingNum) {
        this.competingNum = competingNum;
    }

    public Integer getBussOppNum() {
        return bussOppNum;
    }

    public void setBussOppNum(Integer bussOppNum) {
        this.bussOppNum = bussOppNum;
    }

    public Double getEstTransferAmount() {
        return estTransferAmount;
    }

    public void setEstTransferAmount(Double estTransferAmount) {
        this.estTransferAmount = estTransferAmount;
    }

    public Double getEstMarketLossAmount() {
        return estMarketLossAmount;
    }

    public void setEstMarketLossAmount(Double estMarketLossAmount) {
        this.estMarketLossAmount = estMarketLossAmount;
    }

    public Integer getPrelimAnalyNum() {
        return prelimAnalyNum;
    }

    public void setPrelimAnalyNum(Integer prelimAnalyNum) {
        this.prelimAnalyNum = prelimAnalyNum;
    }

    public Integer getConvertMarketNum() {
        return convertMarketNum;
    }

    public void setConvertMarketNum(Integer convertMarketNum) {
        this.convertMarketNum = convertMarketNum;
    }

    public Integer getConvertBussNegotiaNum() {
        return convertBussNegotiaNum;
    }

    public void setConvertBussNegotiaNum(Integer convertBussNegotiaNum) {
        this.convertBussNegotiaNum = convertBussNegotiaNum;
    }
}