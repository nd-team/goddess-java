package com.bjike.goddess.archive.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-19 13:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffNameBO implements Serializable {
    /**
     * 员工id
     */
    private String id;
    /**
     * 员工姓名
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
