package com.bjike.goddess.socialinsurance.to;

import com.bjike.goddess.common.api.entity.DEL;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 社保缴费汇总传输对象
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 17:44]
 * @Description: [ 社保缴费汇总传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SocialInsuranceVoucherTO extends BaseTO{

    public interface Add {};
    /**
     * 年份
     */
    private int year;

    /**
     * 月份
     */
    private int month;

    /**
     * 姓名
     */
    private String name;

    /**
     * 单位合计
     */
    private Double companyTotal;

    /**
     * 个人合计
     */
    private Double personalTotal;

    /**
     * 应缴金额
     */
    private Double amountDue;

    /**
     * 部门
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    @NotBlank(message = "ids不能为空", groups = {SocialInsuranceVoucherTO.Add.class})
    private String[] ids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCompanyTotal() {
        return companyTotal;
    }

    public void setCompanyTotal(Double companyTotal) {
        this.companyTotal = companyTotal;
    }

    public Double getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(Double personalTotal) {
        this.personalTotal = personalTotal;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
