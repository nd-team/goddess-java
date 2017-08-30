package com.bjike.goddess.assemble.vo;

/**
 * 模块应用表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-17 05:41 ]
 * @Description: [ 模块应用表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 模块
     */
    private String name;

    /**
     * 公司
     */
    private String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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