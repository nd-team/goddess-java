package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectSingleUITO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 工作量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "工作量不能为空")
    private Integer workload;

    /**
     * 利润
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "利润不能为空")
    private Double profit;



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


}