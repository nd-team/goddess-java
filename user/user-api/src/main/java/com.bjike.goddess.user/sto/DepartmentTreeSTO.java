package com.bjike.goddess.user.sto;

import com.bjike.goddess.common.api.sto.BaseSTO;

/**
 * 部门业务传送对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentTreeSTO extends BaseSTO {
    /**
     * 部门名
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    private  boolean parent ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }
}
