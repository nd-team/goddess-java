package com.bjike.goddess.version.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 版本信息数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VersionDTO extends BaseDTO {
    public interface LIST {
    }

    /**
     * 模块名
     */
    @NotBlank(groups = VersionDTO.LIST.class, message = "模块名不能为空")
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}