package com.bjike.goddess.businesscommission.vo;

import com.bjike.goddess.businesscommission.bo.ProportionRatioBO;

import java.util.List;

/**
 * 业务提成分配比例表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProportionVO {

    /**
     * id
     */
    private String id;
    /**
     * 业务提成分配比例协商时间
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 内部项目名称
     */
    private String projectName;

    /**
     * 参与协商人
     */
    private String consultants;

    /**
     * 提成分配比例确认单是否全部确认
     */
    private Boolean confirm;

    /**
     * 已确认人
     */
    private String confirmed;

    /**
     * 未确认人
     */
    private String notConfirmed;

    /**
     * 因素集合
     */
    private List<ProportionRatioBO> proportionRatioBOs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConsultants() {
        return consultants;
    }

    public void setConsultants(String consultants) {
        this.consultants = consultants;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getNotConfirmed() {
        return notConfirmed;
    }

    public void setNotConfirmed(String notConfirmed) {
        this.notConfirmed = notConfirmed;
    }

    public List<ProportionRatioBO> getProportionRatioBOs() {
        return proportionRatioBOs;
    }

    public void setProportionRatioBOs(List<ProportionRatioBO> proportionRatioBOs) {
        this.proportionRatioBOs = proportionRatioBOs;
    }
}