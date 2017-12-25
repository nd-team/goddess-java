package com.bjike.goddess.costdetail.vo;

/**
 * 劳务成本明细表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LaborCostDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 分类名
     */
    private String typeName;

    /**
     * 劳务成本合计
     */
    private Double laborCostSum;

    /**
     * 劳务成本十日
     */
    private Double laborCostTen;

    /**
     * 劳务成本十五日
     */
    private Double laborCostFift;

    /**
     * 劳务成本二十日
     */
    private Double laborCostTwtenty;

    /**
     * 劳务成本三十日
     */
    private Double laborCostThirty;

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

    public Double getLaborCostSum() {
        return laborCostSum;
    }

    public void setLaborCostSum(Double laborCostSum) {
        this.laborCostSum = laborCostSum;
    }

    public Double getLaborCostTen() {
        return laborCostTen;
    }

    public void setLaborCostTen(Double laborCostTen) {
        this.laborCostTen = laborCostTen;
    }

    public Double getLaborCostFift() {
        return laborCostFift;
    }

    public void setLaborCostFift(Double laborCostFift) {
        this.laborCostFift = laborCostFift;
    }

    public Double getLaborCostTwtenty() {
        return laborCostTwtenty;
    }

    public void setLaborCostTwtenty(Double laborCostTwtenty) {
        this.laborCostTwtenty = laborCostTwtenty;
    }

    public Double getLaborCostThirty() {
        return laborCostThirty;
    }

    public void setLaborCostThirty(Double laborCostThirty) {
        this.laborCostThirty = laborCostThirty;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}