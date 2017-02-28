package com.bjike.goddess.user.sto.rbac;

import com.bjike.goddess.common.api.sto.BaseSTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-02-28 11:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RoleTreeSTO extends BaseSTO {
    private String name;
    private boolean parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }
}
