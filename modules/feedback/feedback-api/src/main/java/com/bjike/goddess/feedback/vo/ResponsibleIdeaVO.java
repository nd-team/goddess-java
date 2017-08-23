package com.bjike.goddess.feedback.vo;

/**
 * 非责任相关人意见表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResponsibleIdeaVO {

    /**
     * id
     */
    private String id;
    /**
     * 非责任相关人意见-建议描述
     */
    private String responsibleOpinion;

    /**
     * 意见提出人
     */
    private String responsibleIdea;

    /**
     * 意见提出日期
     */
    private String ideaDate;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String projectGroup;

    /**
     * 是否采纳
     */
    private Boolean adopt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getIdeaDate() {
        return ideaDate;
    }

    public void setIdeaDate(String ideaDate) {
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

    public Boolean getAdopt() {
        return adopt;
    }

    public void setAdopt(Boolean adopt) {
        this.adopt = adopt;
    }
}