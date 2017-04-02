package com.bjike.goddess.businessevaluate.entity.interiorevaluate;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_businessprincipalevaluate")
public class BusinessPrincipalEvaluate extends BaseEntity {

    /**
     * 评价项目负责人工作情况
     */
    @Column(name = "projectprincipal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '评价项目负责人工作情况'")
    private String projectprincipal;

    /**
     * 客户关系发展情况
     */
    @Column(name = "cusDevSituation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户关系发展情况'")
    private String cusDevSituation;

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


    public String getProjectprincipal() {
        return projectprincipal;
    }

    public void setProjectprincipal(String projectprincipal) {
        this.projectprincipal = projectprincipal;
    }

    public String getCusDevSituation() {
        return cusDevSituation;
    }

    public void setCusDevSituation(String cusDevSituation) {
        this.cusDevSituation = cusDevSituation;
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