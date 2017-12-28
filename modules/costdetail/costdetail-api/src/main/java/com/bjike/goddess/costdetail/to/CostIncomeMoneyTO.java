package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 主营业务收入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 主营业务收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostIncomeMoneyTO extends BaseTO {

    /**
     * 主营业务收入
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "主营业务收入名不能为空")
    private String costIncomeName;
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

    public String getCostIncomeName() {
        return costIncomeName;
    }

    public void setCostIncomeName(String costIncomeName) {
        this.costIncomeName = costIncomeName;
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
}