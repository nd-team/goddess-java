package com.bjike.goddess.archive.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 员工档案业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecordsCollectBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 入职人数
     */
    private Integer entryNum;

    /**
     * 建立员工档案数
     */
    private Integer staffFilesNum;

    /**
     * 录入员工信息数
     */
    private Integer informationNum;

    /**
     * 离职人数
     */
    private Integer dimissionNum;

    /**
     * 冻结员工信息数
     */
    private Integer freeNum;

    /**
     * 更新招投标人员信息数
     */
    private Integer biddingNum;

    /**
     * 更新对外宣传人员信息数
     */
    private Integer externalNum;

    /**
     * 更新项目人员信息数
     */
    private Integer projectNum;

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

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Integer getStaffFilesNum() {
        return staffFilesNum;
    }

    public void setStaffFilesNum(Integer staffFilesNum) {
        this.staffFilesNum = staffFilesNum;
    }

    public Integer getInformationNum() {
        return informationNum;
    }

    public void setInformationNum(Integer informationNum) {
        this.informationNum = informationNum;
    }

    public Integer getDimissionNum() {
        return dimissionNum;
    }

    public void setDimissionNum(Integer dimissionNum) {
        this.dimissionNum = dimissionNum;
    }

    public Integer getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(Integer freeNum) {
        this.freeNum = freeNum;
    }

    public Integer getBiddingNum() {
        return biddingNum;
    }

    public void setBiddingNum(Integer biddingNum) {
        this.biddingNum = biddingNum;
    }

    public Integer getExternalNum() {
        return externalNum;
    }

    public void setExternalNum(Integer externalNum) {
        this.externalNum = externalNum;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }
}