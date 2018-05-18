package com.bjike.goddess.staffmove.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 人员调动意愿情况数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveIntendCaseDTO extends BaseDTO {
    /**
     * 姓名
     */
    private String[] name;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}