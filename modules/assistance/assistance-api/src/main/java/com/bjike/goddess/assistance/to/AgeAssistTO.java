package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 工龄补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AgeAssistTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String empName;

    /**
     * 员工编号
     */
    private String empNumber;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String jobPosition;

    /**
     * 入职日期
     */
    private String entryTime;

    /**
     * 开始发放补助日期
     */
    private String giveAssistTime;

    /**
     * 本公司工龄(月)
     */
    @NotNull(message = "本公司工龄(月)不能为空")
    private Double jobAge;

    /**
     * 应获补助
     */
    private String assisCondition;

    /**
     * 状态(离职/在职)
     */
    @NotBlank(message = "状态(离职/在职)不能为空")
    private String empStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getGiveAssistTime() {
        return giveAssistTime;
    }

    public void setGiveAssistTime(String giveAssistTime) {
        this.giveAssistTime = giveAssistTime;
    }

    public Double getJobAge() {
        return jobAge;
    }

    public void setJobAge(Double jobAge) {
        this.jobAge = jobAge;
    }

    public String getAssisCondition() {
        return assisCondition;
    }

    public void setAssisCondition(String assisCondition) {
        this.assisCondition = assisCondition;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}