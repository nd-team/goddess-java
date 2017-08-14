package com.bjike.goddess.system.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 平台分类数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlatformClassifyDTO extends BaseDTO {
    /**
     * 平台名称
     */
    private String[] platformName;

    public String[] getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String[] platformName) {
        this.platformName = platformName;
    }
}