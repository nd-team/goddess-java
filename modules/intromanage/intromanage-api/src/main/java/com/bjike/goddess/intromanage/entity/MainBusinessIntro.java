package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 主业介绍
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:41 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_mainbusinessintro")
public class MainBusinessIntro extends BaseEntity {

    /**
     * 公司记录id
     */
    @Column(name = "firmId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司记录id'")
    private String firmId;

    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "VARCHAR(255) COMMENT '业务类型'")
    private String businessType;

    /**
     * 项目科目
     */
    @Column(name = "projectSubject", columnDefinition = "VARCHAR(255) COMMENT '项目科目'")
    private String projectSubject;

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProjectSubject() {
        return projectSubject;
    }

    public void setProjectSubject(String projectSubject) {
        this.projectSubject = projectSubject;
    }
}