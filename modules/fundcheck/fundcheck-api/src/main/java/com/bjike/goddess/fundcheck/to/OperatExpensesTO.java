package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.fundcheck.entity.OperatExpenses;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 营业费用业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OperatExpensesTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空",groups = {OperatExpensesTO.TestAdd.class,OperatExpensesTO.TestEdit.class})
    private String date;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空",groups = {OperatExpensesTO.TestAdd.class,OperatExpensesTO.TestEdit.class})
    private String type;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空",groups = {OperatExpensesTO.TestAdd.class,OperatExpensesTO.TestEdit.class})
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