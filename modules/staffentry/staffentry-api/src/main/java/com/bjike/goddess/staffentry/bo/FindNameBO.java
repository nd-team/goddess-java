package com.bjike.goddess.staffentry.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-18 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FindNameBO implements Serializable {
    private String userId;
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
