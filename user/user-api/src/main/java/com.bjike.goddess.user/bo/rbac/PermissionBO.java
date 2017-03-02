package com.bjike.goddess.user.bo.rbac;


import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资源认证
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 10:12]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionBO extends BaseBO {

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
