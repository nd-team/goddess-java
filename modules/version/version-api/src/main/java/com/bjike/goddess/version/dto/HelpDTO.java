package com.bjike.goddess.version.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 帮助与解答数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HelpDTO extends BaseDTO {
    public interface LIST {
    }

    /**
     * 模块名
     */
    @NotBlank(groups = HelpDTO.LIST.class, message = "模块名不能为空")
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}