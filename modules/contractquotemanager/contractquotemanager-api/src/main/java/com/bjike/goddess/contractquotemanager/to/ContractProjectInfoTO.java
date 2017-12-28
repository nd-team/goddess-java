package com.bjike.goddess.contractquotemanager.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractProjectInfoTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属项目组不能为空")
    private String projectGroup;

    /**
     * 项目内部结构
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属项目内部结构不能为空")
    private String projectInner;

    /**
     * 派工项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属派工项目名称不能为空")
    private String dispatchProject;

    /**
     * 派工单编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属派工单编号不能为空")
    private String dispatchNum;

    /**
     * 派工合同号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属派工合同号不能为空")
    private String outProjectNum;

    /**
     * 开工时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属开工时间不能为空")
    private String startProjectTime;

    /**
     * 完工时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属完工时间不能为空")
    private String endProjectTime;

    /**
     * 完工时间(单位：个月)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "完工时间(单位:个月)不能为空")
    private Integer completeTime;

    /**
     * 合同金额(单位:万元)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "合同金额(单位:万元)")
    private Double money;

    /**
     * 派工单位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "派工单位不能为空")
    private String majorCompany;

    /**
     * 合同规模数
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "合同规模数不能为空")
    private String contractScale;

    /**
     * 回款周期
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "回款周期不能为空")
    private Double receivableNum;

    /**
     * 重点性
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "重点性不能为空")
    private String emphasis;

    /**
     * 难易程度
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "难易程度不能为空")
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