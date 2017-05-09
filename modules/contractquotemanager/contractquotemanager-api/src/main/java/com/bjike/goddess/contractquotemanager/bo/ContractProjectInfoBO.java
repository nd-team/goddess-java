package com.bjike.goddess.contractquotemanager.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)业务传输对象
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractProjectInfoBO extends BaseBO {

    /**
     * 所属用户
     */
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 项目内部结构
     */
    private String projectInner;

    /**
     * 派工项目名称
     */
    private String projectDispatch;

    /**
     * 派工单编号
     */
    private String dispatchCode;

    /**
     * 派工合同号
     */
    private String dispatchContraCode;

    /**
     * 开工时间
     */
    private String startTime;

    /**
     * 完工时间
     */
    private String endTime;

    /**
     * 完工时间(单位：个月)
     */
    private Integer completeTime;

    /**
     * 合同金额(单位:万元)
     */
    private Double contractMoney;

    /**
     * 派工单位
     */
    private String dispatchUnit;

    /**
     * 合同规模数
     */
    private String contractScale;

    /**
     * 回款周期
     */
    private Double receivableNum;

    /**
     * 重点性
     */
    private String emphasis;

    /**
     * 难易程度
     */
    private String difficulty;


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

    public String getDispatchContraCode() {
        return dispatchContraCode;
    }

    public void setDispatchContraCode(String dispatchContraCode) {
        this.dispatchContraCode = dispatchContraCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getDispatchUnit() {
        return dispatchUnit;
    }

    public void setDispatchUnit(String dispatchUnit) {
        this.dispatchUnit = dispatchUnit;
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