package com.bjike.goddess.subjectcollect.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 科目汇总表数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectDTO extends BaseDTO {
    /**
     * 地区
     */
    private String[] area;

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }
}