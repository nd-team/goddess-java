package com.bjike.goddess.abilitydisplay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 公司证书业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 02:45 ]
 * @Description: [ 公司证书业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComCertificateBO extends BaseBO {

    /**
     * 证书类型String
     */
    private String type;

    /**
     * 证书名称
     */
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
}