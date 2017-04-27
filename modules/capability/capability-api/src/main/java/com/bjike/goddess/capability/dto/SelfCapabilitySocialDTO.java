package com.bjike.goddess.capability.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 个人能力展示数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SelfCapabilitySocialDTO extends BaseDTO {
    public interface TestSocial{}

    /**
     * 个人能力id
     */
    @NotBlank(groups = {SelfCapabilitySocialDTO.TestSocial.class} , message = "个人能力id不能为空")
    private String selfCapabilityId;

    public String getSelfCapabilityId() {
        return selfCapabilityId;
    }

    public void setSelfCapabilityId(String selfCapabilityId) {
        this.selfCapabilityId = selfCapabilityId;
    }
}