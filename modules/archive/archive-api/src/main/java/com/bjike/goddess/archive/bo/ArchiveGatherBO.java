package com.bjike.goddess.archive.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 档案收集业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArchiveGatherBO extends BaseBO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 员工编号
     */
    private String serialNumber;

    /**
     * 部门/项目组
     */
    private String project;

    /**
     * 岗位
     */
    private String position;

    /**
     * 自愿放弃购买社保协议书
     */
    private Boolean abandon;

    /**
     * 转正定级档案收集
     */
    private Boolean become;

    /**
     * 调动或任命
     */
    private String transfer;

    /**
     * 岗位晋升
     */
    private String promotion;

    /**
     * 技能评定
     */
    private String evaluate;

    /**
     * 特批补助
     */
    private String subsidy;

    /**
     * 技能证书
     */
    private String skill;

    /**
     * 其他档案
     */
    private String other;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getAbandon() {
        return abandon;
    }

    public void setAbandon(Boolean abandon) {
        this.abandon = abandon;
    }

    public Boolean getBecome() {
        return become;
    }

    public void setBecome(Boolean become) {
        this.become = become;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}