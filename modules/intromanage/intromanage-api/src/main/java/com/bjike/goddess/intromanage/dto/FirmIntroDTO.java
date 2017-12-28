package com.bjike.goddess.intromanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 公司简介数据传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroDTO extends BaseDTO {
    /**
     * 公司名称
     */
    private String firmName;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }
}