package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 薪资测算汇总表业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryTestCollectBO extends BaseBO {

    /**
     * 地区
     */
    private String  area;

    /**
     * 项目组/部门
     */
    private String  department;

    /**
     * 业务方向
     */
    private String  businessDirection;

    /**
     * 岗位
     */
    private String  position;

    /**
     * 技能
     */
    private String  skill;

    /**
     * 工作年限
     */
    private String  workAge;

    /**
     * 人数
     */
    private Integer  peopleNum;

    /**
     * 总期望
     */
    private Integer  allExpectation;

    /**
     * 平均期望
     */
    private Double  averageExpectation;

    /**
     * 参考招聘网合计
     */
    private Integer  total;

    /**
     * 参考招聘网数量
     */
    private Integer  number;

    /**
     * 参考招聘网平均
     */
    private Double  average;

    /**
     * 最后薪资测试参考标准
     */
    private Integer  consultStandard;

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getAllExpectation() {
        return allExpectation;
    }

    public void setAllExpectation(Integer allExpectation) {
        this.allExpectation = allExpectation;
    }

    public Double getAverageExpectation() {
        return averageExpectation;
    }

    public void setAverageExpectation(Double averageExpectation) {
        this.averageExpectation = averageExpectation;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getConsultStandard() {
        return consultStandard;
    }

    public void setConsultStandard(Integer consultStandard) {
        this.consultStandard = consultStandard;
    }

    public SalaryTestCollectBO(){

    }

    public SalaryTestCollectBO(Integer peopleNum,Integer allExpectation,Double averageExpectation,Integer total,Integer number,Double average,Integer consultStandard){
        this.peopleNum = peopleNum;
        this.allExpectation = allExpectation;
        this.averageExpectation = averageExpectation;
        this.total = total;
        this.number = number;
        this.average = average;
        this.consultStandard = consultStandard;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBusinessDirection() {
        return businessDirection;
    }

    public void setBusinessDirection(String businessDirection) {
        this.businessDirection = businessDirection;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }
}