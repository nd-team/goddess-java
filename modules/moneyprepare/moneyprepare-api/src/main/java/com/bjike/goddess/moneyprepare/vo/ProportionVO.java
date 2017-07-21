package com.bjike.goddess.moneyprepare.vo;

/**
 * 比例分配表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionVO {

    /**
     * id
     */
    private String id;
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
     * 各个项目组资金准备
     */
    private Double eachFund;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}