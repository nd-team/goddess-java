package com.bjike.goddess.assemble.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 模块
 *
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
     * 模块英文名
     */
    @NotBlank(message = "模块英文名不能为空", groups = {ADD.class, EDIT.class})
    private String moduleName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
