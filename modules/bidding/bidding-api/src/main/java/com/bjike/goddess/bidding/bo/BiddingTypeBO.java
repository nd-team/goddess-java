package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 招投标类型业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingTypeBO extends BaseBO {

    /**
     * 招投标类型
     */
    private String biddingType;


    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }
}