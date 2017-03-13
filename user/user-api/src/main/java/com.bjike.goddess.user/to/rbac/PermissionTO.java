package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.to.BaseTO;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-11 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionTO extends BaseTO {

    private String name;
    private String resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


}
