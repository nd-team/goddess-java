package com.bjike.goddess.moneyprepare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.persistence.Column;

/**
 * 比例分配数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionDTO extends BaseDTO {

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

}