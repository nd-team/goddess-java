package com.bjike.goddess.attendance.vo.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.attendance.enums.OverWorkIndentity;

/**
 * 加班表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneOWIdentityVO {

    /**
     * 姓名
     */
    private String name;
    /**
     * 地区
     */
    private OverWorkIndentity overWorkIndentity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OverWorkIndentity getOverWorkIndentity() {
        return overWorkIndentity;
    }

    public void setOverWorkIndentity(OverWorkIndentity overWorkIndentity) {
        this.overWorkIndentity = overWorkIndentity;
    }
}