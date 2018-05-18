package com.bjike.goddess.materialbuy.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialbuy.enums.AuditState;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 临时物资需求
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TempMatterDemandTO extends BaseTO {

    public interface TempMatterDemandAdd{}
    public interface TempMatterDemandEdit{}
    public interface Audit{}

    /**
     * 地区
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "项目组不能为空")
    private String projectTeam;

    /**
     * 需求人
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "需求人不能为空")
    private String needer;

    /**
     * 需求日期
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "需求日期不能为空")
    private String requiredDate;

    /**
     * 设备类型
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "设备类型不能为空")
    private String deviceType;

    /**
     * 设备名称
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "设备名称不能为空")
    private String deviceName;

    /**
     * 型号
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "型号不能为空")
    private String model;

    /**
     * 数量
     */
    @Min(value = 1, groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "数量必须大于0")
    private Integer quantity;

    /**
     * 单位
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "单位不能为空")
    private String unit;

    /**
     * 需求原因
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "需求原因不能为空")
    private String needReason;

    /**
     * 备注
     */
    private String comment;

    /**
     * 地区审核人
     */
    @NotBlank(groups = {TempMatterDemandTO.TempMatterDemandAdd.class, TempMatterDemandTO.TempMatterDemandEdit.class}, message = "地区审核人不能为空")
    private String areaAuditor;

    /**
     * 审核状态
     */
    @NotNull(groups = {TempMatterDemandTO.Audit.class},message = "审核状态不能为空")
    private AuditState auditState;

    /**
     * 审核意见
     */
    @NotBlank(groups = {TempMatterDemandTO.Audit.class},message = "审核意见不能为空")
    private String auditOpinion;


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