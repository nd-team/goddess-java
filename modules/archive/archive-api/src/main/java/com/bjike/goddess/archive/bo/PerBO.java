package com.bjike.goddess.archive.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-20 19:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PerBO implements Serializable {
    /**
     * 身份证号
     */
    private String Perid;
    /**
     * 电话号码
     */
    private String phone;

    public String getPerid() {
        return Perid;
    }

    public void setPerid(String perid) {
        Perid = perid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
