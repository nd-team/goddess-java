package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 成本明细年汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细年汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsYeCollBO extends BaseBO {


    /**
     * 科目
     */
    private String subject;

    /**
     * 年合计
     */
    private Double combined;

    /**
     * 一月合计
     */
    private Double januaryValue;

    /**
     * 二月合计
     */
    private Double februaryValue;

    /**
     * 三月合计
     */
    private Double marchValue;

    /**
     * 四月合计
     */
    private Double aprilValue;

    /**
     * 五月合计
     */
    private Double mayValue;

    /**
     * 六月合计
     */
    private Double juneValue;

    /**
     * 七月合计
     */
    private Double julyValue;

    /**
     * 八月合计
     */
    private Double augustValue;

    /**
     * 九月合计
     */
    private Double septemberValue;

    /**
     * 十月合计
     */
    private Double octoberValue;

    /**
     * 十一月合计
     */
    private Double novemberValue;

    /**
     * 十二月合计
     */
    private Double decemberValue;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getCombined() {
        return combined;
    }

    public void setCombined(Double combined) {
        this.combined = combined;
    }

    public Double getJanuaryValue() {
        return januaryValue;
    }

    public void setJanuaryValue(Double januaryValue) {
        this.januaryValue = januaryValue;
    }

    public Double getFebruaryValue() {
        return februaryValue;
    }

    public void setFebruaryValue(Double februaryValue) {
        this.februaryValue = februaryValue;
    }

    public Double getMarchValue() {
        return marchValue;
    }

    public void setMarchValue(Double marchValue) {
        this.marchValue = marchValue;
    }

    public Double getAprilValue() {
        return aprilValue;
    }

    public void setAprilValue(Double aprilValue) {
        this.aprilValue = aprilValue;
    }

    public Double getMayValue() {
        return mayValue;
    }

    public void setMayValue(Double mayValue) {
        this.mayValue = mayValue;
    }

    public Double getJuneValue() {
        return juneValue;
    }

    public void setJuneValue(Double juneValue) {
        this.juneValue = juneValue;
    }

    public Double getJulyValue() {
        return julyValue;
    }

    public void setJulyValue(Double julyValue) {
        this.julyValue = julyValue;
    }

    public Double getAugustValue() {
        return augustValue;
    }

    public void setAugustValue(Double augustValue) {
        this.augustValue = augustValue;
    }

    public Double getSeptemberValue() {
        return septemberValue;
    }

    public void setSeptemberValue(Double septemberValue) {
        this.septemberValue = septemberValue;
    }

    public Double getOctoberValue() {
        return octoberValue;
    }

    public void setOctoberValue(Double octoberValue) {
        this.octoberValue = octoberValue;
    }

    public Double getNovemberValue() {
        return novemberValue;
    }

    public void setNovemberValue(Double novemberValue) {
        this.novemberValue = novemberValue;
    }

    public Double getDecemberValue() {
        return decemberValue;
    }

    public void setDecemberValue(Double decemberValue) {
        this.decemberValue = decemberValue;
    }

}