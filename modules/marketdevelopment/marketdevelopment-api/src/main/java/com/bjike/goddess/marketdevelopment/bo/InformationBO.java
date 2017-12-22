package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-14 14:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InformationBO extends BaseBO{

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职务
     */
    private String position;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
