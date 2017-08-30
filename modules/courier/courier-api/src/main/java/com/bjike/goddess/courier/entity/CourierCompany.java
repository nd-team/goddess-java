package com.bjike.goddess.courier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 快递公司信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "courier_couriercompany")
public class CourierCompany extends BaseEntity {

    /**
     * 快递公司
     */
    @Column(name = "courierCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '快递公司'")
    private String courierCompany;

    /**
     * 联系电话
     */
    @Column(name = "courierTel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
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