package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户信息汇总业务传输对象
 *
 * @Author: [ lijutnao ]
 * @Date: [ 2017-03-16T09:41:13.468 ]
 * @Description: [ 客户信息汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationVO {

    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 地区
     */
    private String area;

    /**
     * 新增客户数量
     */
    private Integer newCustomerNum;

    /**
     * 待拜访
     */
    private Integer toVisitNum;

    /**
     * 已拜访
     */
    private Integer haveVisitNum;
    /**
     * 未拜访
     */
    private Integer notVisitNum;

    /**
     * 与市场无关联的客户数量
     */
    private Integer unconnectedCustomerNum;

    /**
     * 与市场信息关联的客户数量
     */
    private Integer associatedCustomerNum;


    /**
     * 需进行市场活动客户数量
     */
    private Integer marketActivitNum;

    /**
     * 已进行市场招待的客户数量
     */
    private Integer haveMaketCustomerNum;

    /**
     * 未进行市场招待的客户数量
     */
    private Integer notMaketCustomerNum;

    /**
     * 客户介绍新增客户数量
     */
    private Integer custIntrodNewCustNum;

    /**
     * 市场招待新增客户数量
     */
    private Integer marketEnNewCustNum;

    /**
     * 商务洽谈新增客户数量
     */
    private Integer bussNegotiationNum;

    /**
     * 招投标新增客户数量
     */
    private Integer biddingNewCustomerNum;

    /**
     * 网站新增客户数量
     */
    private Integer websiteNewCustomerNum;

    /**
     * 员工介绍新增客户数量
     */
    private Integer staffIntroNewCustomerNum;
    /**
     * 其他来源新增客户数量
     */
    private Integer otherResouceNewCustomerNum;

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

    public Integer getNewCustomerNum() {
        return newCustomerNum;
    }

    public void setNewCustomerNum(Integer newCustomerNum) {
        this.newCustomerNum = newCustomerNum;
    }

    public Integer getToVisitNum() {
        return toVisitNum;
    }

    public void setToVisitNum(Integer toVisitNum) {
        this.toVisitNum = toVisitNum;
    }

    public Integer getHaveVisitNum() {
        return haveVisitNum;
    }

    public void setHaveVisitNum(Integer haveVisitNum) {
        this.haveVisitNum = haveVisitNum;
    }

    public Integer getNotVisitNum() {
        return notVisitNum;
    }

    public void setNotVisitNum(Integer notVisitNum) {
        this.notVisitNum = notVisitNum;
    }

    public Integer getUnconnectedCustomerNum() {
        return unconnectedCustomerNum;
    }

    public void setUnconnectedCustomerNum(Integer unconnectedCustomerNum) {
        this.unconnectedCustomerNum = unconnectedCustomerNum;
    }

    public Integer getAssociatedCustomerNum() {
        return associatedCustomerNum;
    }

    public void setAssociatedCustomerNum(Integer associatedCustomerNum) {
        this.associatedCustomerNum = associatedCustomerNum;
    }

    public Integer getMarketActivitNum() {
        return marketActivitNum;
    }

    public void setMarketActivitNum(Integer marketActivitNum) {
        this.marketActivitNum = marketActivitNum;
    }

    public Integer getHaveMaketCustomerNum() {
        return haveMaketCustomerNum;
    }

    public void setHaveMaketCustomerNum(Integer haveMaketCustomerNum) {
        this.haveMaketCustomerNum = haveMaketCustomerNum;
    }

    public Integer getNotMaketCustomerNum() {
        return notMaketCustomerNum;
    }

    public void setNotMaketCustomerNum(Integer notMaketCustomerNum) {
        this.notMaketCustomerNum = notMaketCustomerNum;
    }

    public Integer getCustIntrodNewCustNum() {
        return custIntrodNewCustNum;
    }

    public void setCustIntrodNewCustNum(Integer custIntrodNewCustNum) {
        this.custIntrodNewCustNum = custIntrodNewCustNum;
    }

    public Integer getMarketEnNewCustNum() {
        return marketEnNewCustNum;
    }

    public void setMarketEnNewCustNum(Integer marketEnNewCustNum) {
        this.marketEnNewCustNum = marketEnNewCustNum;
    }

    public Integer getBussNegotiationNum() {
        return bussNegotiationNum;
    }

    public void setBussNegotiationNum(Integer bussNegotiationNum) {
        this.bussNegotiationNum = bussNegotiationNum;
    }

    public Integer getBiddingNewCustomerNum() {
        return biddingNewCustomerNum;
    }

    public void setBiddingNewCustomerNum(Integer biddingNewCustomerNum) {
        this.biddingNewCustomerNum = biddingNewCustomerNum;
    }

    public Integer getWebsiteNewCustomerNum() {
        return websiteNewCustomerNum;
    }

    public void setWebsiteNewCustomerNum(Integer websiteNewCustomerNum) {
        this.websiteNewCustomerNum = websiteNewCustomerNum;
    }

    public Integer getStaffIntroNewCustomerNum() {
        return staffIntroNewCustomerNum;
    }

    public void setStaffIntroNewCustomerNum(Integer staffIntroNewCustomerNum) {
        this.staffIntroNewCustomerNum = staffIntroNewCustomerNum;
    }

    public Integer getOtherResouceNewCustomerNum() {
        return otherResouceNewCustomerNum;
    }

    public void setOtherResouceNewCustomerNum(Integer otherResouceNewCustomerNum) {
        this.otherResouceNewCustomerNum = otherResouceNewCustomerNum;
    }
}