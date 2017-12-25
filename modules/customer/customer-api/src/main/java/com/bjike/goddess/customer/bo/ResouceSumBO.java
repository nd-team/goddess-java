package com.bjike.goddess.customer.bo;

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
public class ResouceSumBO extends BaseBO {

    /**
     * 业务类型
     */
    private String businessType;

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