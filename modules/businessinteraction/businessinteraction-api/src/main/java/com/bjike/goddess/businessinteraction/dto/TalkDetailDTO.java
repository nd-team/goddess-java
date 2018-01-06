package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 资料信息数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TalkDetailDTO extends BaseDTO {
    /**
     * 对象公司业务类型
     */
    private String businessTarget;

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }
}