package com.bjike.goddess.businsurance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 团体意外险信息管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupInsureDTO extends BaseDTO {

    /**
     * 联系人
     */
    private String contractor;

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }
}