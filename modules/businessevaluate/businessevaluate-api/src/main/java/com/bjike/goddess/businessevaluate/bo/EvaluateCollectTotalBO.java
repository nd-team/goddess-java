package com.bjike.goddess.businessevaluate.bo;

/**
 * 汇总合计
 *
 * @Author: [Jason]
 * @Date: [17-3-30 下午4:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EvaluateCollectTotalBO {

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
    private Integer problemCount;
    /**
     * 长期合作项目数量
     */
    private Integer longtermCount;
    /**
     * 事项合作项目数量
     */
    private Integer itemCount;
    /**
     * 中介合作项目数量
     */
    private Integer agencyCount;
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

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    public Integer getLongtermCount() {
        return longtermCount;
    }

    public void setLongtermCount(Integer longtermCount) {
        this.longtermCount = longtermCount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getAgencyCount() {
        return agencyCount;
    }

    public void setAgencyCount(Integer agencyCount) {
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

    public Double getProjectTotalAmount() {
        return projectTotalAmount;
    }

    public void setProjectTotalAmount(Double projectTotalAmountt) {
        this.projectTotalAmount = projectTotalAmountt;
    }

    public EvaluateCollectTotalBO() {
    }

    public EvaluateCollectTotalBO(String area, String project, Integer problemCount,
                                  Integer longtermCount, Integer itemCount, Integer agencyCount,
                                  Double projectCost, Double projectFee, Double projectTaxes, Double projectManageFee,
                                  Double projectProfit, Double projectTotalAmountt) {
        this.area = area;
        this.project = project;
        this.problemCount = problemCount;
        this.longtermCount = longtermCount;
        this.itemCount = itemCount;
        this.agencyCount = agencyCount;
        this.projectCost = projectCost;
        this.projectFee = projectFee;
        this.projectTaxes = projectTaxes;
        this.projectManageFee = projectManageFee;
        this.projectProfit = projectProfit;
        this.projectTotalAmount = projectTotalAmountt;
    }
}
