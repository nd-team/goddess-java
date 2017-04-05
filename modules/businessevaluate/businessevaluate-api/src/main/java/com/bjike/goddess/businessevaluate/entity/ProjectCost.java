package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目费用
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_projectcost")
public class ProjectCost extends BaseEntity {

    /**
     * 服务费用
     */
    @Column(name = "serviceCost", columnDefinition = "VARCHAR(255)   COMMENT '服务费用'")
    private Double serviceCost;

    /**
     * 招待费用
     */
    @Column(name = "entertainCost", columnDefinition = "DECIMAL(10,2)   COMMENT '招待费用'")
    private Double entertainCost;

    /**
     * 提成
     */
    @Column(name = "commission", columnDefinition = "VARCHAR(255)   COMMENT '提成'")
    private Double commission;

    /**
     * 其它
     */
    @Column(name = "another", columnDefinition = "VARCHAR(255)   COMMENT '其它'")
    private Double another;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目信息Id'")
    private String projectInfoId;

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Double getEntertainCost() {
        return entertainCost;
    }

    public void setEntertainCost(Double entertainCost) {
        this.entertainCost = entertainCost;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}