package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 设置币别
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:17 ]
 * @Description: [ 设置币别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CurrencyTO extends BaseTO {

//    /**
//     * 代码
//     */
//    @NotBlank(message = "代码不能为空",groups = {ADD.class,EDIT.class})
//    private String code;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空",groups = {ADD.class,EDIT.class})
    private String name;

    /**
     * 记账汇率
     */
    private Double rate;

    /**
     * 是否本位币
     */
    @NotNull(message = "是否本位币不能为空",groups = {ADD.class,EDIT.class})
    private Boolean standardMoney;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getStandardMoney() {
        return standardMoney;
    }

    public void setStandardMoney(Boolean standardMoney) {
        this.standardMoney = standardMoney;
    }
}