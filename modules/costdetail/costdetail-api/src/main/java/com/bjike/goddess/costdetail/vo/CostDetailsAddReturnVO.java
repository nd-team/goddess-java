package com.bjike.goddess.costdetail.vo;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 查看成本明细详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 查看成本明细详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsAddReturnVO extends BaseTO {

    /**
     * 分类
     */
    private String categoryName;

    /**
     * 合计
     */
    private Double sum;

    /**
     * 十日
     */
    private Double tenMoney;

    /**
     * 十五日
     */
    private Double fifMoney;

    /**
     * 二十日
     */
    private Double twethMoney;

    /**
     * 三十日
     */
    private Double thityMoney;

    /**
     * 明细
     */
    private List<CostDetailsAddReturnVO> costDetailsAddReturnBOS;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getTenMoney() {
        return tenMoney;
    }

    public void setTenMoney(Double tenMoney) {
        this.tenMoney = tenMoney;
    }

    public Double getFifMoney() {
        return fifMoney;
    }

    public void setFifMoney(Double fifMoney) {
        this.fifMoney = fifMoney;
    }

    public Double getTwethMoney() {
        return twethMoney;
    }

    public void setTwethMoney(Double twethMoney) {
        this.twethMoney = twethMoney;
    }

    public Double getThityMoney() {
        return thityMoney;
    }

    public void setThityMoney(Double thityMoney) {
        this.thityMoney = thityMoney;
    }

    public List<CostDetailsAddReturnVO> getCostDetailsAddReturnBOS() {
        return costDetailsAddReturnBOS;
    }

    public void setCostDetailsAddReturnBOS(List<CostDetailsAddReturnVO> costDetailsAddReturnBOS) {
        this.costDetailsAddReturnBOS = costDetailsAddReturnBOS;
    }
}