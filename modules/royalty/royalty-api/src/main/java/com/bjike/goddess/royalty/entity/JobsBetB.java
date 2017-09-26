package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_jobsbetb")
public class JobsBetB extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String system;
    /**
     * 岗位间对赌表A
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "jobsBetA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位间对赌表A'")
    private JobsBetA jobsBetA;


    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public JobsBetA getJobsBetA() {
        return jobsBetA;
    }

    public void setJobsBetA(JobsBetA jobsBetA) {
        this.jobsBetA = jobsBetA;
    }
}