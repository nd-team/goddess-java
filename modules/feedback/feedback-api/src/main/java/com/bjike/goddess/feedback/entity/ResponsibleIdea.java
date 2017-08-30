package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.feedback.enums.AdoptStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 非责任相关人意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_responsibleidea")
public class ResponsibleIdea extends BaseEntity {

    /**
     * 非责任相关人意见-建议描述
     */
    @Column(name = "responsibleOpinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '非责任相关人意见-建议描述'")
    private String responsibleOpinion;

    /**
     * 意见提出人
     */
    @Column(name = "ResponsibleIdea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '意见提出人'")
    private String responsibleIdea;

    /**
     * 意见提出日期
     */
    @Column(name = "ideaDate", nullable = false, columnDefinition = "DATETIME   COMMENT '意见提出日期'")
    private LocalDateTime ideaDate;

    /**
     * 所属地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目组/部门
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组/部门'")
    private String projectGroup;

    /**
     * 是否采纳
     */
    @Column(name = "adopt", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '是否采纳'")
    private AdoptStatus adopt;


    public String getResponsibleOpinion() {
        return responsibleOpinion;
    }

    public void setResponsibleOpinion(String responsibleOpinion) {
        this.responsibleOpinion = responsibleOpinion;
    }

    public String getResponsibleIdea() {
        return responsibleIdea;
    }

    public void setResponsibleIdea(String responsibleIdea) {
        this.responsibleIdea = responsibleIdea;
    }

    public LocalDateTime getIdeaDate() {
        return ideaDate;
    }

    public void setIdeaDate(LocalDateTime ideaDate) {
        this.ideaDate = ideaDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public AdoptStatus getAdopt() {
        return adopt;
    }

    public void setAdopt(AdoptStatus adopt) {
        this.adopt = adopt;
    }
}