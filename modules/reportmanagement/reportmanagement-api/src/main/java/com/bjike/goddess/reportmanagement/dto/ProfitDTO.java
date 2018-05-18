package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 利润表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitDTO extends BaseDTO {
    public interface A {
    }

    /**
     * 起始时间
     */
    @NotBlank(groups = {ProfitDTO.A.class}, message = "起始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {ProfitDTO.A.class}, message = "结束时间不能为空")
    private String endTime;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 地区
     */
    private String area;

    /**
     * 是否获取最新
     */
    private boolean lastest;

    /**
     * 分类
     */
//    @NotBlank(groups = {ProfitDTO.A.class}, message = "分类不能为空")
    private String classification;


    public String getArea() {
        return area;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectNames) {
        this.projectName = projectNames;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isLastest() {
        return lastest;
    }

    public void setLastest(boolean lastest) {
        this.lastest = lastest;
    }
}