package com.bjike.goddess.assemble.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 模块应用
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleApplyTO extends BaseTO {

    /**
     * 模块
     */
    private String name;

    /**
     * 公司
     */
    private String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}