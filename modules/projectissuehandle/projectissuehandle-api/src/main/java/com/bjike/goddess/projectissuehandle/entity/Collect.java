package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 汇总项目执行中的问题受理及处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_collect")
public class Collect extends BaseEntity {
    /**
     * 合同外部项目名称
     */
    @Column(name = "externalContractProjectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 描述
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String remark;

    public String getExternalContractProjectName() {
        return externalContractProjectName;
    }

    public void setExternalContractProjectName(String externalContractProjectName) {
        this.externalContractProjectName = externalContractProjectName;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}