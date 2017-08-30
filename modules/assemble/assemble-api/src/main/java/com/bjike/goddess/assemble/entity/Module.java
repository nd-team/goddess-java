package com.bjike.goddess.assemble.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 模块
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "module_table")
public class Module extends BaseEntity {

    /**
     * 模块名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '模块名'")
    private String name;

    /**
     * 对应模块名
     */
    @Column(name = "moduleName", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '对应模块名'")
    private String moduleName;


    /**
     * 是否公开
     */
    @Column(name = "is_open", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否公开'", insertable = false)
    private String open;

    /**
     * 序列
     */
    @Column(name = "seq", nullable = false, columnDefinition = "INT(5) DEFAULT 0 COMMENT '序列'")
    private Integer seq;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
