package com.bjike.goddess.costdetail.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 成本明细年汇总中间转换业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细年汇总中间转换业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearColloBO extends BaseBO {

    /**
     * 科目
     */
    private String subject;

    /**
     * 年合计
     */
    private Double monthColl;

    /**
     * 时间
     */
    private String costTime;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getMonthColl() {
        return monthColl;
    }

    public void setMonthColl(Double monthColl) {
        this.monthColl = monthColl;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }
}