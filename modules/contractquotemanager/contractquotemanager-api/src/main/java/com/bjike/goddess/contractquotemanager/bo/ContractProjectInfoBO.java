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
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目内部名称
     */
    private String projectInner;

    /**
     * 派工项目名称
     */
    private String dispatchProject;

    /**
     * 派工单编号
     */
    private String dispatchNum;

    /**
     * 派工合同号
     */
    private String outProjectNum;

    /**
     * 开工时间
     */
    private String startProjectTime;

    /**
     * 完工时间
     */
    private String endProjectTime;

    /**
     * 完工时间(单位：个月)
     */
    private Integer completeTime;

    /**
     * 合同金额
     */
    private Double money;

    /**
     * 派工单位
     */
    private String majorCompany;

    /**
     * 合同预估规模
     */
    private Double contractScale;

    /**
     * 回款周期(天)
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
    /**
     * 合同单价
     */
    private Double contractPrice;

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

    public Double getContractScale() {
        return contractScale;
    }

    public void setContractScale(Double contractScale) {
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

    public Double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        this.contractPrice = contractPrice;
    }
}