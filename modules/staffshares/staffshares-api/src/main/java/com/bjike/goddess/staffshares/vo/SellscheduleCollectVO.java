package com.bjike.goddess.staffshares.vo;

/**
 * 出售记录表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SellscheduleCollectVO {

    /**
     * id
     */
    private String id;
    /**
     * 出售人
     */
    private String sellName;

//    /**
//     * 方案代码
//     */
//    private String code;
//
//    /**
//     * 方案名称
//     */
//    private String name;

    /**
     * 出售股数
     */
    private int sellNum;

//    /**
//     * 出售价格
//     */
//    private Double sellPrice;

    /**
     * 出售金额
     */
    private Double totalSellPrice;

//    /**
//     * 出售时间
//     */
//    private String sellTime;
//
//    /**
//     * 剩余出售量
//     */
//    private int number;
//
//    /**
//     * 购买人
//     */
//    private String buyName;
//
//    /**
//     * 购买股数
//     */
//    private int purchaseNum;
//
//    /**
//     * 购买时间
//     */
//    private String buyTime;
//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    public Double getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(Double totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }
}