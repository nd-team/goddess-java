package com.bjike.goddess.businsurance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 团体意外险被保险人信息管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupByInsurerDTO extends BaseDTO {

    /**
     * 团体意外险id
     */
    private String groupInsureId;

    public String getGroupInsureId() {
        return groupInsureId;
    }

    public void setGroupInsureId(String groupInsureId) {
        this.groupInsureId = groupInsureId;
    }
}