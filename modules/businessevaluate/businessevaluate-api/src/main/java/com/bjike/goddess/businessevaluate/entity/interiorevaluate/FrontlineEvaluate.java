package com.bjike.goddess.businessevaluate.entity.interiorevaluate;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 一线体系评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_frontlineevaluate")
public class FrontlineEvaluate extends BaseEntity {

    /**
     * 现场实施情况
     */
    @Column(name = "spotSituation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '现场实施情况'")
    private String spotSituation;

    /**
     * 其它
     */
    @Column(name = "another", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '其它'")
    private String another;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目信息Id'")
    private String projectInfoId;


    public String getSpotSituation() {
        return spotSituation;
    }

    public void setSpotSituation(String spotSituation) {
        this.spotSituation = spotSituation;
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