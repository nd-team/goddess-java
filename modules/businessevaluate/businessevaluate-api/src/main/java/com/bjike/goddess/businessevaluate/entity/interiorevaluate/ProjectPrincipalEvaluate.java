package com.bjike.goddess.businessevaluate.entity.interiorevaluate;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 項目负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_projectprincipalevaluate")
public class ProjectPrincipalEvaluate extends BaseEntity {

    /**
     * 评价现场工作人员情况
     */
    @Column(name = "evaluateFrontline", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '评价现场工作人员情况'")
    private String evaluateFrontline;

    /**
     * 评价商务部工作情况
     */
    @Column(name = "evaluateBusiness", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '评价商务部工作情况'")
    private String evaluateBusiness;

    /**
     * 其他
     */
    @Column(name = "another", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '其他'")
    private String another;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目信息Id'")
    private String projectInfoId;


    public String getEvaluateFrontline() {
        return evaluateFrontline;
    }

    public void setEvaluateFrontline(String evaluateFrontline) {
        this.evaluateFrontline = evaluateFrontline;
    }

    public String getEvaluateBusiness() {
        return evaluateBusiness;
    }

    public void setEvaluateBusiness(String evaluateBusiness) {
        this.evaluateBusiness = evaluateBusiness;
    }

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}