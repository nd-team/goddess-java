package com.bjike.goddess.user.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 部门树业务传送对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-18 15:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentTreeBO extends BaseBO {
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
