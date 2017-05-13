package com.bjike.goddess.assemble.vo;

import com.bjike.goddess.assemble.type.CheckType;

/**
 * 演示demo功能信息
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ModuleVO {

    /**
     * id
     */
    private String id;
    /**
     * 模块名
     */
    private String name;
    /**
     * 选中状态
     */
    private CheckType checkType;

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

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
