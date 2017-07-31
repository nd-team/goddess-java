package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;

import javax.persistence.*;


/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_multipleprojectsingleui")
public class MultipleProjectSingleUI extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;
    /**
     * 工作量
     */
    @Column(name = "workload", nullable = false, columnDefinition = "INT(11) COMMENT '工作量'")
    private Integer workload;

    /**
     * 利润
     */
    @Column(name = "profit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '利润'")
    private Double profit;

    /**
     * 项目比重
     */
    @Column(name = "projectRatio", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目比重'")
    private String projectRatio;

    /**
     * 多个项目单个界面
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "multipleProjectSingleUIB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '多个项目单个界面'")
    private MultipleProjectSingleUIB multipleProjectSingleUIB;

    public MultipleProjectSingleUIB getMultipleProjectSingleUIB() {
        return multipleProjectSingleUIB;
    }

    public void setMultipleProjectSingleUIB(MultipleProjectSingleUIB multipleProjectSingleUIB) {
        this.multipleProjectSingleUIB = multipleProjectSingleUIB;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getProjectRatio() {
        return projectRatio;
    }

    public void setProjectRatio(String projectRatio) {
        this.projectRatio = projectRatio;
    }
}