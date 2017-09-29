package com.bjike.goddess.businesscommission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务提成分配比例确认表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 04:13 ]
 * @Description: [ 业务提成分配比例确认表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businesscommission_proportionconfirm")
public class ProportionConfirm extends BaseEntity {

    /**
     * 业务提成分配比例表id
     */
    @Column(name = "proportionId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务提成分配比例表id'")
    private String proportionId;

    /**
     * 参与协商人
     */
    @Column(name = "consultants", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参与协商人'")
    private String consultants;

    /**
     * 是否确认
     */
    @Column(name = "is_confirm", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否确认'", insertable = false)
    private Boolean confirm;

    /**
     * 标记
     */
    @Column(name = "tar", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否标记'")
    private Boolean tar;


    public String getProportionId() {
        return proportionId;
    }

    public void setProportionId(String proportionId) {
        this.proportionId = proportionId;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public Boolean getTar() {
        return tar;
    }

    public void setTar(Boolean tar) {
        this.tar = tar;
    }
}