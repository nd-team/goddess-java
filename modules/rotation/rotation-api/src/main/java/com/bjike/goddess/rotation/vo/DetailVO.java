package com.bjike.goddess.rotation.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-19 09:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DetailVO implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     * 项目组/部门
     */
    private String department;
    /**
     * 岗位层级
     */
    private String position;
    /**
     * 获取时间
     */
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
