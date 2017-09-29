package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 驻点设置数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrestPointDTO extends BaseDTO {
    /**
     * 驻点地点
     */
    private String[] pointAreas;

    public String[] getPointAreas() {
        return pointAreas;
    }

    public void setPointAreas(String[] pointAreas) {
        this.pointAreas = pointAreas;
    }
}