package com.bjike.goddess.businessevaluate.vo;

/**
 * 汇总合计
 *
 * @Author: [Jason]
 * @Date: [17-3-30 下午4:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EvaluateCollectTotalVO {

    /**
     * 地区
     */
    private String area;
    /**
     * 项目
     */
    private String project;
    /**
     * 项目问题数量
     */
    private Double problemCount;
    /**
     * 长期合作项目数量
     */
    private Double longtermCount;
    /**
     * 事项合作项目数量
     */
    private Double itemCount;
    /**
     * 中介合作项目数量
     */
    private Double agencyCount;
    /**
     * 项目成本
     */
    private Double projectCost;
    /**
     * 项目费用
     */
    private Double projectFee;
    /**
     * 项目税金
     */
    private Double projectTaxes;
    /**
     * 项目管理费
     */
    private Double projectManageFee;
    /**
     * 项目利润
     */
    private Double projectProfit;
    /**
     * 项目总金额
     */
    private Double projectTotalAmount;

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

    public Double getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Double problemCount) {
        this.problemCount = problemCount;
    }

    public Double getLongtermCount() {
        return longtermCount;
    }

    public void setLongtermCount(Double longtermCount) {
        this.longtermCount = longtermCount;
    }

    public Double getItemCount() {
        return itemCount;
    }

    public void setItemCount(Double itemCount) {
        this.itemCount = itemCount;
    }

    public Double getAgencyCount() {
        return agencyCount;
    }

    public void setAgencyCount(Double agencyCount) {
        this.agencyCount = agencyCount;
    }

    public Double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Double projectCost) {
        this.projectCost = projectCost;
    }

    public Double getProjectFee() {
        return projectFee;
    }

    public void setProjectFee(Double projectFee) {
        this.projectFee = projectFee;
    }

    public Double getProjectTaxes() {
        return projectTaxes;
    }

    public void setProjectTaxes(Double projectTaxes) {
        this.projectTaxes = projectTaxes;
    }

    public Double getProjectManageFee() {
        return projectManageFee;
    }

    public void setProjectManageFee(Double projectManageFee) {
        this.projectManageFee = projectManageFee;
    }

    public Double getProjectProfit() {
        return projectProfit;
    }

    public void setProjectProfit(Double projectProfit) {
        this.projectProfit = projectProfit;
    }

    public Double getProjectTotalAmountt() {
        return projectTotalAmount;
    }

    public void setProjectTotalAmountt(Double projectTotalAmountt) {
        this.projectTotalAmount = projectTotalAmountt;
    }
}
