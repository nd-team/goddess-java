package com.bjike.goddess.balancecard.bo;

import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 岗位指标设置业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionIndexSetBO extends BaseBO {

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

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
     * 部门月度对赌值
     */
    private Double departYearWager;

    /**
     * 责任岗位
     */
    private String position;

    /**
     * 责任人
     */
    private String positioner;

    /**
     * 岗位指标权重
     */
    private Double weight;

    /**
     * 岗位指标权重之和
     */
    private Double weightSum;

    /**
     * 岗位指标目标值
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
     * 岗位指标添加人
     */
    private String posionIndexPersion;

    /**
     * 岗位指标添加时间
     */
    private String posionIndexTime;

    /**
     * 是否由分解得来状态
     */
    private SeperateComeStatus seperateComeStatus;

    /**
     * 部门月度指标设置id
     */
    private String departMonIndexSetId;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositioner() {
        return positioner;
    }

    public void setPositioner(String positioner) {
        this.positioner = positioner;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(Double weightSum) {
        this.weightSum = weightSum;
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

    public String getPosionIndexPersion() {
        return posionIndexPersion;
    }

    public void setPosionIndexPersion(String posionIndexPersion) {
        this.posionIndexPersion = posionIndexPersion;
    }

    public String getPosionIndexTime() {
        return posionIndexTime;
    }

    public void setPosionIndexTime(String posionIndexTime) {
        this.posionIndexTime = posionIndexTime;
    }

    public SeperateComeStatus getSeperateComeStatus() {
        return seperateComeStatus;
    }

    public void setSeperateComeStatus(SeperateComeStatus seperateComeStatus) {
        this.seperateComeStatus = seperateComeStatus;
    }

    public String getDepartMonIndexSetId() {
        return departMonIndexSetId;
    }

    public void setDepartMonIndexSetId(String departMonIndexSetId) {
        this.departMonIndexSetId = departMonIndexSetId;
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