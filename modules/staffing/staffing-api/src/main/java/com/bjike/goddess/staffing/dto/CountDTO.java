package com.bjike.goddess.staffing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 操作汇总数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CountDTO extends BaseDTO {
    /**
     * 时间
     */
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}