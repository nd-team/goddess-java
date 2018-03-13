package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 公司基本信息数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyBasicInfoDTO extends BaseDTO {

    private String systemId;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}