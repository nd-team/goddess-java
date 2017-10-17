package com.bjike.goddess.balancecard.bo;

import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 部门月度指标设置业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartMonIndexSetBO extends BaseBO {

    /**
     * 指标名称
     */
    private String indexName;


    /**
     * 年度指标编号
     */

    private Integer indexNumber;

    /**
     * 部门年度指标编号
     */
    private Integer yearIndexNumber;

    /**
     * 部门月度指标编号
     */
    private Integer monthIndexNumber;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 总指标权重
     */
    private Double describtion;

    /**
     * 责任部门
     */
    private String department;

    /**
     * 部门年度指标权重
     */
    private Double departYearWeight;

    /**
     * 部门年度对赌值
     */
    private Double departYearWager;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 本月目标值
     */
    private Double target;

    /**
     * 对赌值
     */
    private Double wager;

    /**
     * 完成值
     */
    private Double complete;

    /**
     * 考核方式
     */
    private String examWay;

    /**
     * 是否达标
     */
    private String whetherStandar;

    /**
     * 达成率
     */
    private Double standardRate;

    /**
     * 考核得分
     */
    private Double examScore;

    /**
     * 填报人员
     */
    private String writePerson;

    /**
     * 考核部门
     */
    private String examDepart;

    /**
     * 数据来源
     */
    private String dataOrigin;

    /**
     * 考核周期
     */
    private String examDuring;

    /**
     * 部门月度指标添加人
     */
    private String yearPersion;

    /**
     * 部门月度指标添加时间
     */
    private String yearIndexTime;

    /**
     * 岗位分解状态
     */
    private SeparateStatus separateStatus;

    /**
     * 是否由分解得来状态
     */
    private SeperateComeStatus seperateComeStatus;

    /**
     * 部门年度指标设置id
     */
    private String departYearIndexSetId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Double getDescribtion() {
        return describtion;
    }

    public void setDescribtion(Double describtion) {
        this.describtion = describtion;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getDepartYearWeight() {
        return departYearWeight;
    }

    public void setDepartYearWeight(Double departYearWeight) {
        this.departYearWeight = departYearWeight;
    }

    public Double getDepartYearWager() {
        return departYearWager;
    }

    public void setDepartYearWager(Double departYearWager) {
        this.departYearWager = departYearWager;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }

    public Double getWager() {
        return wager;
    }

    public void setWager(Double wager) {
        this.wager = wager;
    }

    public Double getComplete() {
        return complete;
    }

    public void setComplete(Double complete) {
        this.complete = complete;
    }

    public String getExamWay() {
        return examWay;
    }

    public void setExamWay(String examWay) {
        this.examWay = examWay;
    }

    public String getWhetherStandar() {
        return whetherStandar;
    }

    public void setWhetherStandar(String whetherStandar) {
        this.whetherStandar = whetherStandar;
    }

    public Double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(Double standardRate) {
        this.standardRate = standardRate;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public String getWritePerson() {
        return writePerson;
    }

    public void setWritePerson(String writePerson) {
        this.writePerson = writePerson;
    }

    public String getExamDepart() {
        return examDepart;
    }

    public void setExamDepart(String examDepart) {
        this.examDepart = examDepart;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public String getExamDuring() {
        return examDuring;
    }

    public void setExamDuring(String examDuring) {
        this.examDuring = examDuring;
    }

    public String getYearPersion() {
        return yearPersion;
    }

    public void setYearPersion(String yearPersion) {
        this.yearPersion = yearPersion;
    }

    public String getYearIndexTime() {
        return yearIndexTime;
    }

    public void setYearIndexTime(String yearIndexTime) {
        this.yearIndexTime = yearIndexTime;
    }

    public SeparateStatus getSeparateStatus() {
        return separateStatus;
    }

    public void setSeparateStatus(SeparateStatus separateStatus) {
        this.separateStatus = separateStatus;
    }

    public SeperateComeStatus getSeperateComeStatus() {
        return seperateComeStatus;
    }

    public void setSeperateComeStatus(SeperateComeStatus seperateComeStatus) {
        this.seperateComeStatus = seperateComeStatus;
    }

    public String getDepartYearIndexSetId() {
        return departYearIndexSetId;
    }

    public void setDepartYearIndexSetId(String departYearIndexSetId) {
        this.departYearIndexSetId = departYearIndexSetId;
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

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Integer getYearIndexNumber() {
        return yearIndexNumber;
    }

    public void setYearIndexNumber(Integer yearIndexNumber) {
        this.yearIndexNumber = yearIndexNumber;
    }

    public Integer getMonthIndexNumber() {
        return monthIndexNumber;
    }

    public void setMonthIndexNumber(Integer monthIndexNumber) {
        this.monthIndexNumber = monthIndexNumber;
    }
}