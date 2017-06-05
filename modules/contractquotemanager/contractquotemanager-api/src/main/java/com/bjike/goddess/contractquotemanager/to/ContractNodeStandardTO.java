package com.bjike.goddess.contractquotemanager.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 合同节点标准信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.715 ]
 * @Description: [ 合同节点标准信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractNodeStandardTO extends BaseTO {

    /**
     * 日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "日期不能为空")
    private String date;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String project;

    /**
     * 项目内部名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目内部名称不能为空")
    private String projectInner;

    /**
     * 派工项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "派工项目名称不能为空")
    private String projectDispatch;

    /**
     * 派工单号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "派工单号不能为空")
    private String dispatchCode;

    /**
     * 类别
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "类别不能为空")
    private String type;

    /**
     * 节点
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "节点不能为空")
    private String node;

    /**
     * 单位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "单位不能为空")
    private String unit;

    /**
     * 节点标准
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "节点标准不能为空")
    private Integer nodeStandard;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectInner() {
        return projectInner;
    }

    public void setProjectInner(String projectInner) {
        this.projectInner = projectInner;
    }

    public String getProjectDispatch() {
        return projectDispatch;
    }

    public void setProjectDispatch(String projectDispatch) {
        this.projectDispatch = projectDispatch;
    }

    public String getDispatchCode() {
        return dispatchCode;
    }

    public void setDispatchCode(String dispatchCode) {
        this.dispatchCode = dispatchCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNodeStandard() {
        return nodeStandard;
    }

    public void setNodeStandard(Integer nodeStandard) {
        this.nodeStandard = nodeStandard;
    }

}