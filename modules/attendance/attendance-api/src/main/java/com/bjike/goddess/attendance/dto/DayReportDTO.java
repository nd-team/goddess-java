package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 日报数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayReportDTO extends BaseDTO {
    /**
     * 姓名数组
     */
    private String[] names;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}