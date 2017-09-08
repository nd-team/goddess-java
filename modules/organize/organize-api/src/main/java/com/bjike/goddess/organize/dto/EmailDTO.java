package com.bjike.goddess.organize.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * Created by ike on 17-9-7.
 */
public class EmailDTO extends BaseDTO {
    public interface EMAIL {
    }

    /**
     * 姓名
     */
    @NotNull(groups = EmailDTO.EMAIL.class, message = "姓名不能为空")
    private String[] names;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
