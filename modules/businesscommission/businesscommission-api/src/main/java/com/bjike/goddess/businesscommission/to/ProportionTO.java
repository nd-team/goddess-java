package com.bjike.goddess.businesscommission.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 业务提成分配比例表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionTO extends BaseTO {

    public interface TestAdd {
    }

    public interface TestEdit {
    }

    /**
     * 地区
     */
    @NotNull(groups = {ProportionTO.TestAdd.class}, message = "地区不能为空")
    private String area;

    /**
     * 部门/项目组
     */
    @NotNull(groups = {ProportionTO.TestAdd.class}, message = "部门/项目组不能为空")
    private String department;

    /**
     * 内部项目名称
     */
    @NotNull(groups = {ProportionTO.TestAdd.class}, message = "内部项目名称不能为空")
    private String projectName;

    /**
     * 参与协商人
     */
    @NotNull(groups = {ProportionTO.TestAdd.class}, message = "参与协商人不能为空")
    private String consultants;

//    /**
//     * 确认
//     */
//    @NotNull(groups = {ProportionTO.TestEdit.class}, message = "确认不能为空")
//    private Boolean confirm;

    /**
     * 因素集合
     */
    private List<ProportionRatioTO> proportionRatioTOs;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

//    public Boolean getConfirm() {
//        return confirm;
//    }
//
//    public void setConfirm(Boolean confirm) {
//        this.confirm = confirm;
//    }

    public List<ProportionRatioTO> getProportionRatioTOs() {
        return proportionRatioTOs;
    }

    public void setProportionRatioTOs(List<ProportionRatioTO> proportionRatioTOs) {
        this.proportionRatioTOs = proportionRatioTOs;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}