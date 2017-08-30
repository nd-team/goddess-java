package com.bjike.goddess.courier.vo;

/**
 * 快递公司信息表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourierCompanyVO {

    /**
     * id
     */
    private String id;
    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 联系电话
     */
    private String courierTel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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