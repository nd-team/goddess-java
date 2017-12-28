package com.bjike.goddess.courier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [yewenbo]
 * @Date: [2017-04-29 14:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OtherBO extends BaseBO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String area;

    /**
     * 上条记录的余额
     */
    private Double remain;

    /**
     * 快递公司
     */
    private String company;

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
