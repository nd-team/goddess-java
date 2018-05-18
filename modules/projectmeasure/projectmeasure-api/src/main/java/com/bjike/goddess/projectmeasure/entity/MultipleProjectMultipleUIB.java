package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;

import javax.persistence.*;


/**
 * 多项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_multipleprojectmultipleuib")
public class MultipleProjectMultipleUIB extends BaseEntity {


    /**
     * 界面选择
     */
    @Column(name = "interfaceSelect", nullable = false, columnDefinition = "TINYINT(2) COMMENT '界面选择'")
    private InterfaceSelect interfaceSelect;

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
     * 多项目多界面
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "multipleProjectMultipleUI_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '多项目多界面'")
    private MultipleProjectMultipleUI multipleProjectMultipleUI;

    public MultipleProjectMultipleUI getMultipleProjectMultipleUI() {
        return multipleProjectMultipleUI;
    }

    public void setMultipleProjectMultipleUI(MultipleProjectMultipleUI multipleProjectMultipleUI) {
        this.multipleProjectMultipleUI = multipleProjectMultipleUI;
    }

    public InterfaceSelect getInterfaceSelect() {
        return interfaceSelect;
    }

    public void setInterfaceSelect(InterfaceSelect interfaceSelect) {
        this.interfaceSelect = interfaceSelect;
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

}