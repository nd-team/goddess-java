package com.bjike.goddess.attendance.vo.overtime;


import java.io.Serializable;

/**
 * 加班表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionAndDepartVO implements Serializable{


    /**
     * 部门
     */
    private String depart;

    /**
     * 职位
     */
    private String position;


    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}