package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 回款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空",groups = {BackTO.TestAdd.class,BackTO.TestEdit.class})
    private String date;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {BackTO.TestAdd.class,BackTO.TestEdit.class})
    private String area;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空",groups = {BackTO.TestAdd.class,BackTO.TestEdit.class})
    private String innerName;

    /**
     * 到账金额
     */
    @NotNull(message = "到账金额不能为空",groups = {BackTO.TestAdd.class,BackTO.TestEdit.class})
    private Double accountMoney;

    /**
     * 税金
     */
    @NotNull(message = "税金不能为空",groups = {BackTO.TestAdd.class,BackTO.TestEdit.class})
    private Double taxes;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }
}