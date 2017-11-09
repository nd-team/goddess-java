package com.bjike.goddess.staffmove.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员调动实施业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveCollectBO extends BaseBO {


    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 人员编制状态
     */
    private String status;

    /**
     * 目前在职人数
     */
    private Integer currentNum;

    /**
     * 超编人数
     */
    private Integer exceedNum;

    /**
     * 缺编人数
     */
    private Integer vacancyNum;
    /**
     * 调动需求人数
     */
    private Integer transferNum;
    /**
     * 服从全国范围内调动人数
     */
    private Integer obeyPlanNum;
    /**
     * 以解决调动需求人数
     */
    private Integer solveTransferNum;
    /**
     * 被调动人数
     */
    private Integer mobilizedNum;

    /**
     * 调动需求频率
     */
    private Integer staffFrequency;

    /**
     * 被调动频率
     */
    private Integer mobilizedFrequency;

    /**
     * 项目需求调动次数
     */
    private Integer projectMobilizedNum;

    /**
     * 公司发展需求调动次数
     */
    private Integer companyMobilizedNum;

    /**
     * 个人需求调动次数
     */
    private Integer staffMobilizedNum;

    public Integer getMobilizedNum() {
        return mobilizedNum;
    }

    public void setMobilizedNum(Integer mobilizedNum) {
        this.mobilizedNum = mobilizedNum;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getExceedNum() {
        return exceedNum;
    }

    public void setExceedNum(Integer exceedNum) {
        this.exceedNum = exceedNum;
    }

    public Integer getVacancyNum() {
        return vacancyNum;
    }

    public void setVacancyNum(Integer vacancyNum) {
        this.vacancyNum = vacancyNum;
    }

    public Integer getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(Integer transferNum) {
        this.transferNum = transferNum;
    }

    public Integer getObeyPlanNum() {
        return obeyPlanNum;
    }

    public void setObeyPlanNum(Integer obeyPlanNum) {
        this.obeyPlanNum = obeyPlanNum;
    }

    public Integer getSolveTransferNum() {
        return solveTransferNum;
    }

    public void setSolveTransferNum(Integer solveTransferNum) {
        this.solveTransferNum = solveTransferNum;
    }

    public Integer getStaffFrequency() {
        return staffFrequency;
    }

    public void setStaffFrequency(Integer staffFrequency) {
        this.staffFrequency = staffFrequency;
    }

    public Integer getMobilizedFrequency() {
        return mobilizedFrequency;
    }

    public void setMobilizedFrequency(Integer mobilizedFrequency) {
        this.mobilizedFrequency = mobilizedFrequency;
    }

    public Integer getProjectMobilizedNum() {
        return projectMobilizedNum;
    }

    public void setProjectMobilizedNum(Integer projectMobilizedNum) {
        this.projectMobilizedNum = projectMobilizedNum;
    }

    public Integer getCompanyMobilizedNum() {
        return companyMobilizedNum;
    }

    public void setCompanyMobilizedNum(Integer companyMobilizedNum) {
        this.companyMobilizedNum = companyMobilizedNum;
    }

    public Integer getStaffMobilizedNum() {
        return staffMobilizedNum;
    }

    public void setStaffMobilizedNum(Integer staffMobilizedNum) {
        this.staffMobilizedNum = staffMobilizedNum;
    }
}