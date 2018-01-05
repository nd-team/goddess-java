package com.bjike.goddess.balancecard.to;

import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.enums.SeperateComeStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 岗位指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionIndexSetTO extends BaseTO {

    public interface TestAdd {
    }

    /**
     * 指标名称
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "指标名称不能为空")
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
     * 岗位指标编号
     */
    private Integer postIndexNumber;



    /**
     * 年份
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "年份不能为空")
    private Integer year;

    /**
     * 月份
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "月份不能为空")
    private Integer month;

    /**
     * 指标类型
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "指标类型不能为空")
    private String indexType;

    /**
     * 维度
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "维度不能为空")
    private String dimension;

    /**
     * 总指标权重
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "总指标权重不能为空")
    private Double describtion;

    /**
     * 责任部门
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "责任部门不能为空")
    private String department;


    /**
     * 部门年度指标权重
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "部门年度指标权重不能为空")
    private Double departYearWeight;

    /**
     * 部门月度对赌值
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "部门月度对赌值不能为空")
    private Double departYearWager;

    /**
     * 责任岗位
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "责任岗位不能为空")
    private String position;

    /**
     * 责任人
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "责任人不能为空")
    private String positioner;

    /**
     * 责任人工号
     */
    @NotBlank(groups = {PositionIndexSetTO.TestAdd.class}, message = "责任人工号不能为空")
    private String positionerNumber;
    /**
     * 岗位指标权重
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "岗位指标权重不能为空")
    private Double weight;

    /**
     * 岗位指标权重之和
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "岗位指标权重之和不能为空")
    private Double weightSum;

    /**
     * 岗位指标目标值
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "岗位指标目标值不能为空")
    private Double target;

    /**
     * 对赌值
     */
    @NotNull(groups = {PositionIndexSetTO.TestAdd.class}, message = "对赌值不能为空")
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

    public Integer getPostIndexNumber() {
        return postIndexNumber;
    }

    public void setPostIndexNumber(Integer postIndexNumber) {

        this.postIndexNumber = postIndexNumber;
    }

    public String getPositionerNumber() {
        return positionerNumber;
    }

    public void setPositionerNumber(String positionerNumber) {
        this.positionerNumber = positionerNumber;
    }
}