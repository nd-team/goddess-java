package com.bjike.goddess.shareholdersmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 股权赠与数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityGiftDTO extends BaseDTO {
    /**
     * 地区
     */
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}