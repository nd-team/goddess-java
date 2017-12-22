package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 现金流量分析数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashAnalyseDTO extends BaseDTO {

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