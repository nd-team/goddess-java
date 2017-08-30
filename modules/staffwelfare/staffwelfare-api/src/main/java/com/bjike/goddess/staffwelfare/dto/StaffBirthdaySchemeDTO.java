package com.bjike.goddess.staffwelfare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 员工生日福利方案数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffBirthdaySchemeDTO extends BaseDTO {

    /**
     * 方案执行日期
     */
    private String schemeTime;

    public String getSchemeTime() {
        return schemeTime;
    }

    public void setSchemeTime(String schemeTime) {
        this.schemeTime = schemeTime;
    }
}