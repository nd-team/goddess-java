package com.bjike.goddess.moneyprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 比例分配
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyprepare_proportion")
public class Proportion extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '时间'")
    private String time;

    /**
     * 项目组
     */
    @Column(name = "projectTeam", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectTeam;

    /**
     * 分配比例
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分配比例'")
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