package com.bjike.goddess.socialfee.vo;

/**
 * 社会缴费记账凭证记录表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-20 10:40 ]
 * @Description: [ 社会缴费记账凭证记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SocialFeeVoucherVO {

    /**
     * id
     */
    private String id;
    /**
     * 缴费所属年月
     */
    private String payTimeYear;

    /**
     * 记账凭证id
     */
    private String voucherId;

    /**
     * 应缴金额
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayTimeYear() {
        return payTimeYear;
    }

    public void setPayTimeYear(String payTimeYear) {
        this.payTimeYear = payTimeYear;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
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