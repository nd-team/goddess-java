package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.feedback.entity.ResponsibleIdea;
import com.bjike.goddess.feedback.enums.AdoptStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 非责任相关人意见
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResponsibleIdeaTO extends BaseTO {
    public interface TestAdd{}

    /**
     * 非责任相关人意见-建议描述
     */
    @NotBlank(message = "非责任相关人意见-建议描述不能为空",groups = {ResponsibleIdeaTO.TestAdd.class})
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

    public AdoptStatus getAdopt() {
        return adopt;
    }

    public void setAdopt(AdoptStatus adopt) {
        this.adopt = adopt;
    }
}