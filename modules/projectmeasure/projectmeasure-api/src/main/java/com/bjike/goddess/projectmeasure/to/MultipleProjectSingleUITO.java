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
     * 项目类别
     */
    private ProjectCategory projectCategory;

    /**
     * 界面选择
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "界面选择不能为空")
    private InterfaceSelect interfaceSelect;

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

    /**
     * 项目比重
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目比重不能为空")
    private String projectRatio;

    /**
     * 项目利润对比
     */
    private String projectProfitContrast;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
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

    public String getProjectRatio() {
        return projectRatio;
    }

    public void setProjectRatio(String projectRatio) {
        this.projectRatio = projectRatio;
    }

    public String getProjectProfitContrast() {
        return projectProfitContrast;
    }

    public void setProjectProfitContrast(String projectProfitContrast) {
        this.projectProfitContrast = projectProfitContrast;
    }
}