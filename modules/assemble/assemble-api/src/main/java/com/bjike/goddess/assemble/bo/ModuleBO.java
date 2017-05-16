package com.bjike.goddess.assemble.bo;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 模块
 *
 * @Author: [liguiqin]
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
    private CheckType checkType;
    /**
     * 包含的模块
     */
    private List<ModuleBO> relations;

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

    public List<ModuleBO> getRelations() {
        return relations;
    }

    public void setRelations(List<ModuleBO> relations) {
        this.relations = relations;
    }
}
