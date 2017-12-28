package com.bjike.goddess.contacts.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-14 15:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NameAndIdBO implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     * 姓名ID
     */
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
