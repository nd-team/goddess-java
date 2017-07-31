package com.bjike.goddess.projectmeasure.vo;

import com.bjike.goddess.projectmeasure.type.InterfaceSelect;

/**
 * 多项目多个界面表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectMultipleUIBVO {

    /**
     * id
     */
    private String id;


    /**
     * 界面选择
     */
    private InterfaceSelect interfaceSelect;

    /**
     * 工作量
     */
    private Integer workload;

    /**
     * 利润
     */
    private Double profit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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