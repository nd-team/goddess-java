package com.bjike.goddess.festival.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 节假日礼品福利业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:17 ]
 * @Description: [ 节假日礼品福利业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WelfareBO extends BaseBO {

    /**
     * 节日名称
     */
    private String festivalName;

    /**
     * 礼品类型
     */
    private String giftType;

    /**
     * 节日礼品
     */
    private String gift;

    /**
     * 单价(元)
     */
    private Double price;

    /**
     * 数量(人)
     */
    private Double number;

    /**
     * 总数量(单位)
     */
    private Double totalNum;

    /**
     * 总金额(元)
     */
    private Double totalMoney;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


}