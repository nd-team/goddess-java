package com.bjike.goddess.courier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [yewenbo]
 * @Date: [2017-04-29 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CourierCountBO extends BaseBO{
    /**
     * 地区
     */
    private String arrival;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 快递单号量
     */
    private Integer countNum;

    /**
     * 总费用合计
     */
    private Double total;

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
