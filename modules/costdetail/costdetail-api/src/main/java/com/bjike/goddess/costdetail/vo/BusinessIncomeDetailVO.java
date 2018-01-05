package com.bjike.goddess.costdetail.vo;

/**
 * 主营业务收入明细表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:27 ]
 * @Description: [ 主营业务收入明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessIncomeDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 分类名
     */
    private String typeName;

    /**
     * 主营业务收入合计
     */
    private Double businessIncomeSum;

    /**
     * 主营业务收入十日
     */
    private Double businessIncomeTen;

    /**
     * 主营业务收入十五日
     */
    private Double businessIncomeFift;

    /**
     * 主营业务收入二十日
     */
    private Double businessIncomeTwtenty;

    /**
     * 主营业务收入三十日
     */
    private Double businessIncomeThirty;

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

    public Double getBusinessIncomeSum() {
        return businessIncomeSum;
    }

    public void setBusinessIncomeSum(Double businessIncomeSum) {
        this.businessIncomeSum = businessIncomeSum;
    }

    public Double getBusinessIncomeTen() {
        return businessIncomeTen;
    }

    public void setBusinessIncomeTen(Double businessIncomeTen) {
        this.businessIncomeTen = businessIncomeTen;
    }

    public Double getBusinessIncomeFift() {
        return businessIncomeFift;
    }

    public void setBusinessIncomeFift(Double businessIncomeFift) {
        this.businessIncomeFift = businessIncomeFift;
    }

    public Double getBusinessIncomeTwtenty() {
        return businessIncomeTwtenty;
    }

    public void setBusinessIncomeTwtenty(Double businessIncomeTwtenty) {
        this.businessIncomeTwtenty = businessIncomeTwtenty;
    }

    public Double getBusinessIncomeThirty() {
        return businessIncomeThirty;
    }

    public void setBusinessIncomeThirty(Double businessIncomeThirty) {
        this.businessIncomeThirty = businessIncomeThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}