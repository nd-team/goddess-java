package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同立项情况汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-23 11:36 ]
 * @Description: [ 合同立项情况汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MakeCaseCollectFigureBO extends BaseBO {


    /**
     * 地区
     */
    private String area;
    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 专业
     */
    private String major;
    /**
     * 总包单位名称
     */
    private String majorCompany;
    /**
     * 年月份
     */
    private String yearMonth;
    /**
     * 内部项目名称数量
     */
    private Integer innerNameNum;
    /**
     * 已立项合同单数数量
     */
    private Integer hadNum;
    /**
     * 未立项合同单数数量
     */
    private Integer noNum;
    /**
     * 不立项合同单数数量
     */
    private Integer notNum;
    /**
     * 已完工单数数量
     */
    private Integer completeNum;
    /**
     * 进行
     */
    private Integer march;

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getInnerNameNum() {
        return innerNameNum;
    }

    public void setInnerNameNum(Integer innerNameNum) {
        this.innerNameNum = innerNameNum;
    }

    public Integer getHadNum() {
        return hadNum;
    }

    public void setHadNum(Integer hadNum) {
        this.hadNum = hadNum;
    }

    public Integer getNoNum() {
        return noNum;
    }

    public void setNoNum(Integer noNum) {
        this.noNum = noNum;
    }

    public Integer getNotNum() {
        return notNum;
    }

    public void setNotNum(Integer notNum) {
        this.notNum = notNum;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getMarch() {
        return march;
    }

    public void setMarch(Integer march) {
        this.march = march;
    }
}