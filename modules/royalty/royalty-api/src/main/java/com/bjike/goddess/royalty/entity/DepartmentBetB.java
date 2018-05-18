package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbetb")
public class DepartmentBetB extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 部门对赌表A
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门对赌表A'")
    private DepartmentBetA departmentBetA;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public DepartmentBetA getDepartmentBetA() {
        return departmentBetA;
    }

    public void setDepartmentBetA(DepartmentBetA departmentBetA) {
        this.departmentBetA = departmentBetA;
    }
}