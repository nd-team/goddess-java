package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.qualifications.enums.HandleStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 资质办理管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_handle")
public class QualificationsHandle extends BaseEntity {

    /**
     * 资质类别
     */
    @Column(name = "type", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '资质类别'")
    private String type;

    /**
     * 是否办理成功
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '是否办理成功'", insertable = false)
    private HandleStatus status;

    /**
     * 预算的费用
     */
    @Column(name = "cost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '预算的费用'")
    private Double cost;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 人员信息资料
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "qualifications_handle_personnel", joinColumns = {@JoinColumn(name = "handle_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "personnel_id", nullable = false)})
    private Set<PersonnelInformation> personnelSet = new HashSet<>(0);

    /**
     * 设备信息资料
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "qualifications_handle_facility", joinColumns = {@JoinColumn(name = "handle_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "facility_id", nullable = false)})
    private Set<FacilityInformation> facilitySet = new HashSet<>(0);

    /**
     * 公司基本信息
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "qualifications_handle_company", joinColumns = {@JoinColumn(name = "handle_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "company_id", nullable = false)})
    private Set<CompanyInfo> companySet = new HashSet<>(0);

    /**
     * 财务资料
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "qualifications_handle_finance", joinColumns = {@JoinColumn(name = "handle_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "finance_id", nullable = false)})
    private Set<FinanceInfo> financeSet = new HashSet<>(0);

    /**
     * 审核资料
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "qualifications_handle_material", joinColumns = {@JoinColumn(name = "handle_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "material_id", nullable = false)})
    private Set<AuditMaterial> materialSet = new HashSet<>(0);


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HandleStatus getStatus() {
        return status;
    }

    public void setStatus(HandleStatus status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<PersonnelInformation> getPersonnelSet() {
        return personnelSet;
    }

    public void setPersonnelSet(Set<PersonnelInformation> personnelSet) {
        this.personnelSet = personnelSet;
    }

    public Set<FacilityInformation> getFacilitySet() {
        return facilitySet;
    }

    public void setFacilitySet(Set<FacilityInformation> facilitySet) {
        this.facilitySet = facilitySet;
    }

    public Set<CompanyInfo> getCompanySet() {
        return companySet;
    }

    public void setCompanySet(Set<CompanyInfo> companySet) {
        this.companySet = companySet;
    }

    public Set<FinanceInfo> getFinanceSet() {
        return financeSet;
    }

    public void setFinanceSet(Set<FinanceInfo> financeSet) {
        this.financeSet = financeSet;
    }

    public Set<AuditMaterial> getMaterialSet() {
        return materialSet;
    }

    public void setMaterialSet(Set<AuditMaterial> materialSet) {
        this.materialSet = materialSet;
    }
}