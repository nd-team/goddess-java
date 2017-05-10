package com.bjike.goddess.assemble.bo;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ModuleBO extends BaseBO {
    /**
     * 模块名称
     */
    private String name;
    /**
     * 选中状态
     */
    private CheckType checkType = CheckType.NONE;
    /**
     * 包含的模块
     */
    private List<ModuleBO> contains = new ArrayList<>();

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

    public List<ModuleBO> getContains() {
        return contains;
    }

    public void setContains(List<ModuleBO> contains) {
        this.contains = contains;
    }
}
