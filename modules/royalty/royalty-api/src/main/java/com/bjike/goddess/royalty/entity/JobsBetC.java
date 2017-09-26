package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_jobsbetc")
public class JobsBetC extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;
    /**
     * 岗位间对赌表B
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "jobsBetB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位间对赌表B'")
    private JobsBetB jobsBetB;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public JobsBetB getJobsBetB() {
        return jobsBetB;
    }

    public void setJobsBetB(JobsBetB jobsBetB) {
        this.jobsBetB = jobsBetB;
    }
}