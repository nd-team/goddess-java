package com.bjike.goddess.competitormanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 竞争对手汇总业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectionTotalBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 通信类
     */
    private Long communicate;
    /**
     * 软件类
     */
    private Long software;
    /**
     * 营销策划类
     */
    private Long marketingplan;
    /**
     * 智能化类
     */
    private Long intelligentize;
    /**
     * 电子商务类
     */
    private Long electroniccommerce;
    /**
     * 房地产类
     */
    private Long realty;
    /**
     * 理财类
     */
    private Long financial;
    /**
     * 食品
     */
    private Long food;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getCommunicate() {
        return communicate;
    }

    public void setCommunicate(Long communicate) {
        this.communicate = communicate;
    }

    public Long getSoftware() {
        return software;
    }

    public void setSoftware(Long software) {
        this.software = software;
    }

    public Long getMarketingplan() {
        return marketingplan;
    }

    public void setMarketingplan(Long marketingplan) {
        this.marketingplan = marketingplan;
    }

    public Long getIntelligentize() {
        return intelligentize;
    }

    public void setIntelligentize(Long intelligentize) {
        this.intelligentize = intelligentize;
    }

    public Long getElectroniccommerce() {
        return electroniccommerce;
    }

    public void setElectroniccommerce(Long electroniccommerce) {
        this.electroniccommerce = electroniccommerce;
    }

    public Long getRealty() {
        return realty;
    }

    public void setRealty(Long realty) {
        this.realty = realty;
    }

    public Long getFinancial() {
        return financial;
    }

    public void setFinancial(Long financial) {
        this.financial = financial;
    }

    public Long getFood() {
        return food;
    }

    public void setFood(Long food) {
        this.food = food;
    }

    public CollectionTotalBO() {

    }

    public CollectionTotalBO(String area, Long communicate, Long software,
                             Long marketingplan, Long intelligentize, Long electroniccommerce,
                             Long realty, Long financial, Long food) {
        this.area = area;
        this.communicate = communicate;
        this.software = software;
        this.marketingplan = marketingplan;
        this.intelligentize = intelligentize;
        this.electroniccommerce = electroniccommerce;
        this.realty = realty;
        this.financial = financial;
        this.food = food;
    }
}