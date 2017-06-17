package com.bjike.goddess.projectmeasure.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 其他需求界面数据传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOtherDemandDTO extends BaseDTO {

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