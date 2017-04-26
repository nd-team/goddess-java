package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 邮件发送定制数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.878 ]
 * @Description: [ 邮件发送定制数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailDTO extends BaseDTO {

    public interface TestCollect{}
    /**
     * 地区数组
     */
    @NotNull(groups = {CollectEmailDTO.TestCollect.class} , message = "地区数组不能为空")
    private String[] areas;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }
}