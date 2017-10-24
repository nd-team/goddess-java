package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 合同规模数汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-23 11:36 ]
 * @Description: [ 合同规模数汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScaleContractFigureBO extends BaseBO {


    /**
     * 地区
     */
    private String area;
    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 专业
     */
    private String major;
    /**
     * 规模数量(总规模数量)
     */
    private Integer scaleContract;
    /**
     * 实际完成规模数量
     */
    private Integer finishScale;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Integer scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Integer getFinishScale() {
        return finishScale;
    }

    public void setFinishScale(Integer finishScale) {
        this.finishScale = finishScale;
    }
}