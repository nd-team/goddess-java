package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 投资条件-债权投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CreditorsInvestTO extends BaseTO {

    /**
     * 投资人
     */
    @NotBlank(message = "投资人不能为空",groups = {ADD.class, EDIT.class})
    private String investor;

    /**
     * 固定资产名称
     */
    @NotBlank(message = "固定资产名称不能为空",groups = {ADD.class, EDIT.class})
    private String fixedAssetName;

    /**
     * 固定资产价值
     */
    @NotBlank(message = "固定资产价值不能为空",groups = {ADD.class, EDIT.class})
    private String fixedAssetValue;

    /**
     * 流动资金
     */
    @NotNull(message = "流动资金不能为空",groups = {ADD.class, EDIT.class})
    private Double liquidity;

    /**
     * 担保人
     */
    @NotBlank(message = "担保人不能为空",groups = {ADD.class, EDIT.class})
    private String bondsman;

    /**
     * 备注
     */
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getFixedAssetName() {
        return fixedAssetName;
    }

    public void setFixedAssetName(String fixedAssetName) {
        this.fixedAssetName = fixedAssetName;
    }

    public String getFixedAssetValue() {
        return fixedAssetValue;
    }

    public void setFixedAssetValue(String fixedAssetValue) {
        this.fixedAssetValue = fixedAssetValue;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Double liquidity) {
        this.liquidity = liquidity;
    }

    public String getBondsman() {
        return bondsman;
    }

    public void setBondsman(String bondsman) {
        this.bondsman = bondsman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}