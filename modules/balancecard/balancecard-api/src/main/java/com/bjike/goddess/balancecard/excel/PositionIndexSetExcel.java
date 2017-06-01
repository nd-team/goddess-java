package com.bjike.goddess.balancecard.excel;

import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 岗位指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 岗位指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionIndexSetExcel extends BaseTO {

    /**
     * 指标名称
     */
    @ExcelHeader(name = "指标名称",notNull = true)
    private String indexName;

    /**
     * 年份
     */
    @ExcelHeader(name = "年份",notNull = true)
    private String year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份",notNull = true)
    private String month;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "指标类型",notNull = true)
    private String indexType;

    /**
     * 维度
     */
    @ExcelHeader(name = "维度",notNull = true)
    private String dimension;

    /**
     * 总指标权重
     */
    @ExcelHeader(name = "总指标权重",notNull = true)
    private Double describtion;

    /**
     * 责任部门
     */
    @ExcelHeader(name = "责任部门",notNull = true)
    private String department;

    /**
     * 部门年度指标权重
     */
    @ExcelHeader(name = "部门年度指标权重",notNull = true)
    private Double departYearWeight;

    /**
     * 部门月度对赌值
     */
    @ExcelHeader(name = "部门月度对赌值",notNull = true)
    private Double departYearWager;

    /**
     * 责任岗位
     */
    @ExcelHeader(name = "责任岗位",notNull = true)
    private String position;

    /**
     * 责任人
     */
    @ExcelHeader(name = "责任人",notNull = true)
    private String positioner;

    /**
     * 岗位指标权重
     */
    @ExcelHeader(name = "岗位指标权重",notNull = true)
    private Double weight;

    /**
     * 岗位指标权重之和
     */
    @ExcelHeader(name = "岗位指标权重之和",notNull = true)
    private Double weightSum;

    /**
     * 岗位指标目标值
     */
    @ExcelHeader(name = "岗位指标目标值",notNull = true)
    private Double target;

    /**
     * 对赌值
     */
    @ExcelHeader(name = "对赌值",notNull = true)
    private Double wager;

    /**
     * 完成值
     */
    @ExcelHeader(name = "完成值",notNull = true)
    private Double complete;

    /**
     * 考核方式
     */
    @ExcelHeader(name = "考核方式",notNull = true)
    private String examWay;

    /**
     * 是否达标
     */
    @ExcelHeader(name = "是否达标",notNull = true)
    private String whetherStandar;

    /**
     * 达成率
     */
    @ExcelHeader(name = "达成率",notNull = true)
    private Double standardRate;

    /**
     * 考核得分
     */
    @ExcelHeader(name = "考核得分",notNull = true)
    private Double examScore;

    /**
     * 填报人员
     */
    @ExcelHeader(name = "填报人员",notNull = true)
    private String writePerson;

    /**
     * 考核部门
     */
    @ExcelHeader(name = "考核部门",notNull = true)
    private String examDepart;

    /**
     * 数据来源
     */
    @ExcelHeader(name = "数据来源",notNull = true)
    private String dataOrigin;

    /**
     * 考核周期
     */
    @ExcelHeader(name = "考核周期",notNull = true)
    private String examDuring;

    /**
     * 岗位指标添加人
     */
    @ExcelHeader(name = "岗位指标添加人",notNull = true)
    private String posionIndexPersion;

    /**
     * 岗位指标添加时间
     */
    @ExcelHeader(name = "岗位指标添加时间",notNull = true)
    private String posionIndexTime;

    /**
     * 是否由分解得来状态
     */
    @ExcelHeader(name = "是否由分解得来状态",notNull = true)
    private SeperateComeStatus seperateComeStatus;

    /**
     * 部门月度指标设置id
     */
    @ExcelHeader(name = "部门月度指标设置id",notNull = true)
    private String departMonIndexSetId;

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
}
