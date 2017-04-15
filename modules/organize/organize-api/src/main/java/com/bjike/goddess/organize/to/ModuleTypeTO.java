package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 模块类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleTypeTO extends BaseTO {

    /**
     * 模块
     */
    private String module;

    /**
     * 描述
     */
    private String description;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}