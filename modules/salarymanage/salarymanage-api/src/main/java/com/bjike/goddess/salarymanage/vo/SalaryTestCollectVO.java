package com.bjike.goddess.salarymanage.vo;

/**
* 薪资测算汇总表表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SalaryTestCollectVO {
    /**
     * id
     */
    private String  id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}