package com.bjike.goddess.assistance.vo;

/**
 * 高温补助表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HotAssistVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 获得补助开始时间
     */
    private String assistStartTime;

    /**
     * 获得补助结束时间
     */
    private String assistEndTime;

    /**
     * 补助计薪周期开始时间
     */
    private String salaryStartTime;

    /**
     * 补助计薪周期结束时间
     */
    private String salaryEndTime;

    /**
     * 外出日期
     */
    private String outTime;

    /**
     * 高温补贴人员
     */
    private String empName;

    /**
     * 员工编号
     */
    private String empNumber;

    /**
     * 岗位
     */
    private String jobPosition;

    /**
     * 出车单号
     */
    private String outCarNumber;

    /**
     * 出车司机
     */
    private String outDriver;

    /**
     * 高温补贴金额(6.9元/天)(有/无)
     */
    private String moneyCondition;

    /**
     * 物资发放(有/无)
     */
    private String thingCondtion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 总条数
     */
    private Integer counts;

    /**
     * 补助金额
     */
    private Double assistMoney;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssistStartTime() {
        return assistStartTime;
    }

    public void setAssistStartTime(String assistStartTime) {
        this.assistStartTime = assistStartTime;
    }

    public String getAssistEndTime() {
        return assistEndTime;
    }

    public void setAssistEndTime(String assistEndTime) {
        this.assistEndTime = assistEndTime;
    }

    public String getSalaryStartTime() {
        return salaryStartTime;
    }

    public void setSalaryStartTime(String salaryStartTime) {
        this.salaryStartTime = salaryStartTime;
    }

    public String getSalaryEndTime() {
        return salaryEndTime;
    }

    public void setSalaryEndTime(String salaryEndTime) {
        this.salaryEndTime = salaryEndTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
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

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getOutCarNumber() {
        return outCarNumber;
    }

    public void setOutCarNumber(String outCarNumber) {
        this.outCarNumber = outCarNumber;
    }

    public String getOutDriver() {
        return outDriver;
    }

    public void setOutDriver(String outDriver) {
        this.outDriver = outDriver;
    }

    public String getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(String moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public String getThingCondtion() {
        return thingCondtion;
    }

    public void setThingCondtion(String thingCondtion) {
        this.thingCondtion = thingCondtion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Double getAssistMoney() {
        return assistMoney;
    }

    public void setAssistMoney(Double assistMoney) {
        this.assistMoney = assistMoney;
    }
}