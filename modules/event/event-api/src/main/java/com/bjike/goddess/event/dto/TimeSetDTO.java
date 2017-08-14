package com.bjike.goddess.event.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.event.enums.Permissions;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 提醒间隔时间设置数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeSetDTO extends BaseDTO {
    public interface ONE {
    }

    /**
     * 设置人
     */
    @NotBlank(groups = TimeSetDTO.ONE.class, message = "设置人不能为空")
    private String name;
    /**
     * 权限
     */
    @NotNull(groups = TimeSetDTO.ONE.class, message = "权限不能为空")
    private Permissions permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
}