package com.bjike.goddess.assemble.bo;

import com.bjike.goddess.assemble.entity.Module;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 模块应用业务传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleApplyBO extends BaseBO {

    /**
     * 模块
     */
    private Module module;

    /**
     * 公司
     */
    private String company;


    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}