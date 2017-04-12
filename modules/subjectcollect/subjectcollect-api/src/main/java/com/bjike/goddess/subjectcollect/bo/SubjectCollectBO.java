package com.bjike.goddess.subjectcollect.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;
import java.util.Map;

/**
 * 科目汇总表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubjectCollectBO extends BaseBO {

    /**
     * 代码
     */
    private String code;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 期初借方余额
     */
    private Double beginningDebitAmount;

    /**
     * 期初贷方余额
     */
    private Double beginningCreditAmount;

    /**
     * 本期借方发生额
     */
    private Double issueDebitAmount;

    /**
     * 本期贷方发生额
     */
    private Double issueCreditAmount;

    /**
     * 期末借方余额
     */
    private Double endDebitAmount;

    /**
     * 期末贷方余额
     */
    private Double endCreditAmount;

    /**
     * 备注
     */
    private String remark;
    private int counts;//个数
    private int enumConvert;//数据库枚举转换
    private List<Map<String, String>> firstSubjectMap;//一级科目汇总集合

    private List<Map<String, String>> areaMap;//地区汇总集合
    private List<Map<String, String>> projectNameMap;//项目名称汇总集合
    private List<Map<String, String>> projectGroupMap;//项目组汇总集合

    private Double beginMinusMoney;//差额
    private Double issueMinusMoney;//差额
    private Double endMinusMoney;//差额


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getBeginningDebitAmount() {
        return beginningDebitAmount;
    }

    public void setBeginningDebitAmount(Double beginningDebitAmount) {
        this.beginningDebitAmount = beginningDebitAmount;
    }

    public Double getBeginningCreditAmount() {
        return beginningCreditAmount;
    }

    public void setBeginningCreditAmount(Double beginningCreditAmount) {
        this.beginningCreditAmount = beginningCreditAmount;
    }

    public Double getIssueDebitAmount() {
        return issueDebitAmount;
    }

    public void setIssueDebitAmount(Double issueDebitAmount) {
        this.issueDebitAmount = issueDebitAmount;
    }

    public Double getIssueCreditAmount() {
        return issueCreditAmount;
    }

    public void setIssueCreditAmount(Double issueCreditAmount) {
        this.issueCreditAmount = issueCreditAmount;
    }

    public Double getEndDebitAmount() {
        return endDebitAmount;
    }

    public void setEndDebitAmount(Double endDebitAmount) {
        this.endDebitAmount = endDebitAmount;
    }

    public Double getEndCreditAmount() {
        return endCreditAmount;
    }

    public void setEndCreditAmount(Double endCreditAmount) {
        this.endCreditAmount = endCreditAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public List<Map<String, String>> getFirstSubjectMap() {
        return firstSubjectMap;
    }

    public void setFirstSubjectMap(List<Map<String, String>> firstSubjectMap) {
        this.firstSubjectMap = firstSubjectMap;
    }

    public List<Map<String, String>> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(List<Map<String, String>> areaMap) {
        this.areaMap = areaMap;
    }

    public List<Map<String, String>> getProjectNameMap() {
        return projectNameMap;
    }

    public void setProjectNameMap(List<Map<String, String>> projectNameMap) {
        this.projectNameMap = projectNameMap;
    }

    public List<Map<String, String>> getProjectGroupMap() {
        return projectGroupMap;
    }

    public void setProjectGroupMap(List<Map<String, String>> projectGroupMap) {
        this.projectGroupMap = projectGroupMap;
    }

    public Double getBeginMinusMoney() {
        return beginMinusMoney;
    }

    public void setBeginMinusMoney(Double beginMinusMoney) {
        this.beginMinusMoney = beginMinusMoney;
    }

    public Double getIssueMinusMoney() {
        return issueMinusMoney;
    }

    public void setIssueMinusMoney(Double issueMinusMoney) {
        this.issueMinusMoney = issueMinusMoney;
    }

    public Double getEndMinusMoney() {
        return endMinusMoney;
    }

    public void setEndMinusMoney(Double endMinusMoney) {
        this.endMinusMoney = endMinusMoney;
    }
    public SubjectCollectBO(String area, Double beginningDebitAmount, Double beginningCreditAmount, Double beginMinusMoney, Double issueDebitAmount, Double issueCreditAmount, Double issueMinusMoney, Double endDebitAmount, Double endCreditAmount, Double endMinusMoney) {
        super();
        this.area  = area;
        this.beginningDebitAmount = beginningDebitAmount;
        this.beginningCreditAmount = beginningCreditAmount;
        this.beginMinusMoney = beginMinusMoney;
        this.issueDebitAmount = issueDebitAmount;
        this.issueCreditAmount = issueCreditAmount;
        this.issueMinusMoney = issueMinusMoney;
        this.endDebitAmount = endDebitAmount;
        this.endCreditAmount = endCreditAmount;
        this.endMinusMoney = endMinusMoney;
    }
}