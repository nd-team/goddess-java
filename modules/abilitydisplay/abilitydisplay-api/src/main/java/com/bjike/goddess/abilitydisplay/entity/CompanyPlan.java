package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 公司规划
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 02:24 ]
 * @Description: [ 公司规划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "abilitydisplay_companyplan")
public class CompanyPlan extends BaseEntity {

    /**
     * 组织架构规划
     */
    @Column(name = "planningORG",columnDefinition = "VARCHAR(255)   COMMENT '组织架构规划'")
    private String planningORG;

    /**
     * 公司发展规划
     */
    @Column(name = "planningCD", columnDefinition = "VARCHAR(255)   COMMENT '公司发展规划'")
    private String planningCD;

    /**
     * 项目发展规划
     */
    @Column(name = "planningPD", columnDefinition = "VARCHAR(255)   COMMENT '项目发展规划'")
    private String planningPD;

    /**
     * 经营计划
     */
    @Column(name = "planningBN",columnDefinition = "VARCHAR(255)   COMMENT '经营计划'")
    private String planningBN;


    public String getPlanningORG() {
        return planningORG;
    }

    public void setPlanningORG(String planningORG) {
        this.planningORG = planningORG;
    }

    public String getPlanningCD() {
        return planningCD;
    }

    public void setPlanningCD(String planningCD) {
        this.planningCD = planningCD;
    }

    public String getPlanningPD() {
        return planningPD;
    }

    public void setPlanningPD(String planningPD) {
        this.planningPD = planningPD;
    }

    public String getPlanningBN() {
        return planningBN;
    }

    public void setPlanningBN(String planningBN) {
        this.planningBN = planningBN;
    }

    @Override
    public String toString() {
        return "CompanyPlan{" +
                "planningORG='" + planningORG + '\'' +
                ", planningCD='" + planningCD + '\'' +
                ", planningPD='" + planningPD + '\'' +
                ", planningBN='" + planningBN + '\'' +
                '}';
    }
}