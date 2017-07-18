package com.bjike.goddess.costdetail.vo;

/**
 * 实收资本明细表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 07:06 ]
 * @Description: [ 实收资本明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PaidCapitalDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 分类名
     */
    private String typeName;

    /**
     * 实收资本合计
     */
    private Double paidCapitalSum;

    /**
     * 实收资本十日
     */
    private Double paidCapitalTen;

    /**
     * 实收资本十五日
     */
    private Double paidCapitalFift;

    /**
     * 实收资本二十日
     */
    private Double paidCapitalTwtenty;

    /**
     * 实收资本三十日
     */
    private Double paidCapitalThirty;

    /**
     * 成本明细表id
     */
    private String costId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getPaidCapitalSum() {
        return paidCapitalSum;
    }

    public void setPaidCapitalSum(Double paidCapitalSum) {
        this.paidCapitalSum = paidCapitalSum;
    }

    public Double getPaidCapitalTen() {
        return paidCapitalTen;
    }

    public void setPaidCapitalTen(Double paidCapitalTen) {
        this.paidCapitalTen = paidCapitalTen;
    }

    public Double getPaidCapitalFift() {
        return paidCapitalFift;
    }

    public void setPaidCapitalFift(Double paidCapitalFift) {
        this.paidCapitalFift = paidCapitalFift;
    }

    public Double getPaidCapitalTwtenty() {
        return paidCapitalTwtenty;
    }

    public void setPaidCapitalTwtenty(Double paidCapitalTwtenty) {
        this.paidCapitalTwtenty = paidCapitalTwtenty;
    }

    public Double getPaidCapitalThirty() {
        return paidCapitalThirty;
    }

    public void setPaidCapitalThirty(Double paidCapitalThirty) {
        this.paidCapitalThirty = paidCapitalThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}