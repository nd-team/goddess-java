package com.bjike.goddess.function.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.function.enums.FunctionType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 模块功能
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FunctionTO extends BaseTO {

    /**
     * 功能名
     */
    @NotBlank(message = "功能名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 图标
     */
    @NotBlank(message = "功能图标不能为空", groups = {ADD.class, EDIT.class})
    private String icon;
    /**
     * 功能链接
     */
    @NotBlank(message = "功能链接不能为空", groups = {ADD.class, EDIT.class})
    private String url;

    /**
     * 功能类型
     */
    @NotNull(message = "功能类型不能为空", groups = {ADD.class, EDIT.class})
    private FunctionType type;

    /**
     * 是否开启
     */
    @NotNull(message = "功能是否开启不能为空", groups = {ADD.class, EDIT.class})
    private Boolean enable;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FunctionType getType() {
        return type;
    }

    public void setType(FunctionType type) {
        this.type = type;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}