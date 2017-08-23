package com.bjike.goddess.courier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 快递公司信息业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourierCompanyBO extends BaseBO {

    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 联系电话
     */
    private String courierTel;


    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getCourierTel() {
        return courierTel;
    }

    public void setCourierTel(String courierTel) {
        this.courierTel = courierTel;
    }
}