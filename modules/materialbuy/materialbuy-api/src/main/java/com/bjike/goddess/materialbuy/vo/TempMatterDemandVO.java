package com.bjike.goddess.materialbuy.vo;

import com.bjike.goddess.materialbuy.type.AuditState;

/**
 * 临时物质需求表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TempMatterDemandVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectTeam;

    /**
     * 需求人
     */
    private String needer;

    /**
     * 需求日期
     */
    private String requiredDate;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 型号
     */
    private String model;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 需求原因
     */
    private String needReason;

    /**
     * 备注
     */
    private String comment;

    /**
     * 地区审核人
     */
    private String areaAuditor;

    /**
     * 审核状态
     */
    private AuditState auditState;

    /**
     * 审核意见
     */
    private String auditOpinion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getNeeder() {
        return needer;
    }

    public void setNeeder(String needer) {
        this.needer = needer;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNeedReason() {
        return needReason;
    }

    public void setNeedReason(String needReason) {
        this.needReason = needReason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAreaAuditor() {
        return areaAuditor;
    }

    public void setAreaAuditor(String areaAuditor) {
        this.areaAuditor = areaAuditor;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public void setAuditState(AuditState auditState) {
        this.auditState = auditState;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}