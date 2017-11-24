package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 结算流程存储记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_settlementprocess")
public class SettlementProcess extends BaseEntity {

    /**
     * 外包单位
     */
    @Column(name = "outUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String outUnit;

    /**
     * 是否有组织框架
     */
    @Column(name = "is_organizatioanFrame", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否有组织框架'")
    private Boolean organizatioanFrame;

    /**
     * 是否有结算流程
     */
    @Column(name = "is_clearProcess", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否有结算流程'")
    private Boolean clearProcess;

    /**
     * 收集制作人
     */
    @Column(name = "gatherer", columnDefinition = "VARCHAR(255)   COMMENT '收集制作人'")
    private String gatherer;

    /**
     * 制作时间
     */
    @Column(name = "makingDate", columnDefinition = "DATE   COMMENT '制作时间'")
    private LocalDate makingDate;

    /**
     * 是否有结算流程附件
     */
    @Column(name = "is_settProceAttach", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有结算流程附件'", insertable = false)
    private Boolean settProceAttach;

    /**
     * 更新时间
     */
    @Column(name = "updateDate", nullable = false, columnDefinition = "DATE   COMMENT '更新时间'")
    private LocalDate updateDate;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public Boolean getOrganizatioanFrame() {
        return organizatioanFrame;
    }

    public void setOrganizatioanFrame(Boolean organizatioanFrame) {
        this.organizatioanFrame = organizatioanFrame;
    }

    public Boolean getClearProcess() {
        return clearProcess;
    }

    public void setClearProcess(Boolean clearProcess) {
        this.clearProcess = clearProcess;
    }

    public String getGatherer() {
        return gatherer;
    }

    public void setGatherer(String gatherer) {
        this.gatherer = gatherer;
    }

    public LocalDate getMakingDate() {
        return makingDate;
    }

    public void setMakingDate(LocalDate makingDate) {
        this.makingDate = makingDate;
    }

    public Boolean getSettProceAttach() {
        return settProceAttach;
    }

    public void setSettProceAttach(Boolean settProceAttach) {
        this.settProceAttach = settProceAttach;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}