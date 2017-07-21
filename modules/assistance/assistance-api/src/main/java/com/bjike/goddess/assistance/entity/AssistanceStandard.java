package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 补助标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_assistancestandard")
public class AssistanceStandard extends BaseEntity {

    /**
     * 补助类型名称
     */
    @Column(name = "name", nullable = false , columnDefinition = "VARCHAR(255)   COMMENT '补助类型名称'")
    private String name;

    /**
     * 标准
     */
    @Column(name = "standardForm",  columnDefinition = "VARCHAR(255)   COMMENT '标准'")
    private String standardForm;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandardForm() {
        return standardForm;
    }

    public void setStandardForm(String standardForm) {
        this.standardForm = standardForm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}