package com.bjike.goddess.contractquotemanager.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;


/**
 * 合同节点标准信息导出
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.712 ]
 * @Description: [ 合同节点标准信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractNodeStandardImpor extends BaseBO {

    /**
     * 日期
     */
    @ExcelHeader(name = "日期",notNull = true)
    private LocalDate date;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String project;

    /**
     * 项目内部名称
     */
    @ExcelHeader(name = "项目内部名称",notNull = true)
    private String projectInner;

    /**
     * 派工项目名称
     */
    @ExcelHeader(name = "派工项目名称",notNull = true)
    private String projectDispatch;

    /**
     * 派工单号
     */
    @ExcelHeader(name = "派工单号",notNull = true)
    private String dispatchCode;

    /**
     * 类别
     */
    @ExcelHeader(name = "类别",notNull = true)
    private String type;

    /**
     * 节点
     */
    @ExcelHeader(name = "节点",notNull = true)
    private String node;

    /**
     * 单位
     */
    @ExcelHeader(name = "单位",notNull = true)
    private String unit;

    /**
     * 节点标准
     */
    @ExcelHeader(name = "节点标准",notNull = true)
    private Integer nodeStandard;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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