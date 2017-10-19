package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 主营业务收入
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MainIncomeTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}

    /**
     * 时间
     */
    @NotBlank(message = "时间不能为空",groups = {MainIncomeTO.TestAdd.class,MainIncomeTO.TestEdit.class})
    private String time;

    /**
     * 主营业务收入
     */

    @NotBlank(message = "名字不能为空",groups = {MainIncomeTO.TestAdd.class,MainIncomeTO.TestEdit.class})
    private String name;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空",groups = {MainIncomeTO.TestAdd.class,MainIncomeTO.TestEdit.class})
    private Double money;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}