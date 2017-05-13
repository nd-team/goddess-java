package com.bjike.goddess.assemble.to;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ModuleTO extends BaseTO {
    /**
     * 模块名
     */
    @NotBlank(message = "模块名不能为空", groups = {ADD.class, EDIT.class})

    private String name;
    /**
     * 选中状态
     */
    @NotNull(message = "选中状态不能为空", groups = {ADD.class, EDIT.class})
    private CheckType checkType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
