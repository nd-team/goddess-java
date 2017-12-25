package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 模块类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_moduletype")
public class ModuleType extends BaseEntity {

    /**
     * 模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String module;

    /**
     * 项目组/部门
     */
    @Column(name = "depart", columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'", nullable = false)
    private String depart;

    /**
     * 是否为职能模块
     */
    @Column(columnDefinition = "TINYINT(1)  COMMENT '是否为职能模块'", nullable = false)
    private Boolean position;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2)  COMMENT '状态'", nullable = false)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Boolean getPosition() {
        return position;
    }

    public void setPosition(Boolean position) {
        this.position = position;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

}