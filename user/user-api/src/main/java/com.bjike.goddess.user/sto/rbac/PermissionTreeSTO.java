package com.bjike.goddess.user.sto.rbac;

import com.bjike.goddess.common.api.sto.BaseSTO;

/**
 * 权限树业务传输
 * @Author: [liguiqin]
 * @Date: [2017-02-28 17:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionTreeSTO  extends BaseSTO {
    /**
     * 认证名
     */
    private String name;
    /**
     * 认证资源
     */
    private String resource;

    /**
     * 是否是父节点
     */
    private boolean parent;

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

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }
}
