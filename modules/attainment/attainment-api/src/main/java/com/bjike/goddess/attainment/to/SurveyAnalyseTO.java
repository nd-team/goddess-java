package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 调研分析
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:50 ]
 * @Description: [ 调研分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyAnalyseTO extends BaseTO {

    /**
     * 调研计划id
     */
    private String plan_id;

    /**
     * 分析人
     */
    private String assayer;

    /**
     * 分析结果1
     */
    private String resultOne;

    /**
     * 分析结果2
     */
    private String resultTwo;

    /**
     * 备注
     */
    private String remark;


    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getAssayer() {
        return assayer;
    }

    public void setAssayer(String assayer) {
        this.assayer = assayer;
    }

    public String getResultOne() {
        return resultOne;
    }

    public void setResultOne(String resultOne) {
        this.resultOne = resultOne;
    }

    public String getResultTwo() {
        return resultTwo;
    }

    public void setResultTwo(String resultTwo) {
        this.resultTwo = resultTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}