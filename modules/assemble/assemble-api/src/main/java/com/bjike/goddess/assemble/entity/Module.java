package com.bjike.goddess.assemble.entity;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "module_table")
public class Module extends BaseEntity{

    /**
     * 模块名
     */
     @Column(name = "name")
    private String name;
    /**
     * 选中状态
     */
    @Column(name = "checkType")
    private CheckType checkType = CheckType.NONE;

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
