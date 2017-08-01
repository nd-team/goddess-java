package com.bjike.goddess.moneyprepare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 比例分配业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionBO extends BaseBO {

    /**
     * 时间
     */
    private String time;

    /**
     * 项目组
     */
    private String projectTeam;

    /**
     * 分配比例
     */
    private Double ratio;

    /**
     * 各个项目组准备金
     */
    private Double eachFund;

    /**
     * 资金准备id
     */
    private String fundId;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getEachFund() {
        return eachFund;
    }

    public void setEachFund(Double eachFund) {
        this.eachFund = eachFund;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }
}