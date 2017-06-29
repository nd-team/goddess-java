package com.bjike.goddess.contractquotemanager.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 合同项目基本信息出(临时表存放数据商务模块获取数据)
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: []
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractProjectInfoExcel extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectGroup;

    /**
     * 项目内部结构
     */
    @ExcelHeader(name = "项目内部结构", notNull = true)
    private String projectInner;

    /**
     * 派工项目名称
     */
    @ExcelHeader(name = "派工项目名称", notNull = true)
    private String dispatchProject;

    /**
     * 派工单编号
     */
    @ExcelHeader(name = "派工单编号", notNull = true)
    private String dispatchNum;

    /**
     * 派工合同号
     */
    @ExcelHeader(name = "派工合同号", notNull = true)
    private String outProjectNum;

    /**
     * 开工时间
     */
    @ExcelHeader(name = "开工时间", notNull = true)
    private String startProjectTime;

    /**
     * 完工时间
     */
    @ExcelHeader(name = "完工时间", notNull = true)
    private String endProjectTime;

    /**
     * 完工时间(单位：个月)
     */
    @ExcelHeader(name = "完工时间(单位：个月)", notNull = true)
    private Integer completeTime;

    /**
     * 合同金额(单位:万元)
     */
    @ExcelHeader(name = "合同金额(单位:万元)", notNull = true)
    private Double money;

    /**
     * 派工单位
     */
    @ExcelHeader(name = "派工单位", notNull = true)
    private String majorCompany;

    /**
     * 合同规模数
     */
    @ExcelHeader(name = "合同规模数", notNull = true)
    private String contractScale;

    /**
     * 回款周期
     */
    @ExcelHeader(name = "回款周期", notNull = true)
    private Double receivableNum;

    /**
     * 重点性
     */
    @ExcelHeader(name = "重点性", notNull = true)
    private String emphasis;

    /**
     * 难易程度
     */
    @ExcelHeader(name = "难易程度", notNull = true)
    private String difficulty;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectInner() {
        return projectInner;
    }

    public void setProjectInner(String projectInner) {
        this.projectInner = projectInner;
    }

    public String getDispatchProject() {
        return dispatchProject;
    }

    public void setDispatchProject(String dispatchProject) {
        this.dispatchProject = dispatchProject;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getOutProjectNum() {
        return outProjectNum;
    }

    public void setOutProjectNum(String outProjectNum) {
        this.outProjectNum = outProjectNum;
    }

    public String getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(String startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public String getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(String endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getContractScale() {
        return contractScale;
    }

    public void setContractScale(String contractScale) {
        this.contractScale = contractScale;
    }

    public Double getReceivableNum() {
        return receivableNum;
    }

    public void setReceivableNum(Double receivableNum) {
        this.receivableNum = receivableNum;
    }

    public String getEmphasis() {
        return emphasis;
    }

    public void setEmphasis(String emphasis) {
        this.emphasis = emphasis;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}