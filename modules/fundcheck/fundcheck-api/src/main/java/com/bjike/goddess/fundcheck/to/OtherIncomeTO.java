package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 其他收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherIncomeTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}

    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空",groups = {OtherIncomeTO.TestAdd.class,OtherIncomeTO.TestEdit.class})
    private String date;

    /**
     * 类型
     */
    private String type;

    /**
     * 金额
     */
    private Double money;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}