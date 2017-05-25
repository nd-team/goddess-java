package com.bjike.goddess.coststandard.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 费用标准表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostStandardVO {

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 标准
     */
    private String standard;

    /**
     * 描述
     */
    private String description;

    /**
     * 适用部门或项目组
     */
    private String department;

    /**
     * 制定日期
     */
    private String draftDate;

    /**
     * 使用状态
     */
    private Status status;


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(String draftDate) {
        this.draftDate = draftDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}