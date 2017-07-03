package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 利润表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_profit")
public class Profit extends BaseEntity {

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

//    /**
//     * 本月数
//     */
//    @Column(name = "currentMonthAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本月数'")
//    private Double currentMonthAmount;
//
//    /**
//     * 本年累计数
//     */
//    @Column(name = "currentYearAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '本年累计数'")
//    private Double currentYearAmount;

    /**
     * 利润类型
     */
    @Column(name = "profitType", columnDefinition = "TINYINT(2)   COMMENT '利润类型'")
    private ProfitType profitType;

    /**
     * 运算类型
     */
    @Column(name = "type", columnDefinition = "TINYINT(2)   COMMENT '运算类型'")
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ProfitType getProfitType() {
        return profitType;
    }

    public void setProfitType(ProfitType profitType) {
        this.profitType = profitType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

}