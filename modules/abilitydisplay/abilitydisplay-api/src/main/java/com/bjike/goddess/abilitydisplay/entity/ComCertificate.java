package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司证书
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 02:45 ]
 * @Description: [ 公司证书 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_comcertificate")
public class ComCertificate extends BaseEntity {

    /**
     * 证书类型
     */
    @Column(name = "type",columnDefinition = "VARCHAR(255)   COMMENT '证书类型'")
    private String type;

    /**
     * 证书名称
     */
    @Column(name = "name",columnDefinition = "VARCHAR(255)   COMMENT '证书名称'")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ComCertificate{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}