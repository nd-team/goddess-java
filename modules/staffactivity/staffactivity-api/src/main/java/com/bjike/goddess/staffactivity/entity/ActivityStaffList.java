package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 活动人员名单
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activitystafflist")
public class ActivityStaffList extends BaseEntity {

    /**
     * 人员姓名
     */
    @Column(name = "staffName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '人员姓名'")
    private String staffName;

    /**
     * 所属地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '所属地区'")
    private String area;

    /**
     * 是否参加活动
     */
    @Column(name = "ifAttend", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否参加活动'")
    private Boolean ifAttend;

    /**
     * 放弃原因
     */
    @Column(name = "abandonReason", columnDefinition = "VARCHAR(255) COMMENT '放弃原因'")
    private String abandonReason;

    /**
     * 活动申请信息id
     */
    @Column(name = "applyInforId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动申请信息id'")
    private String applyInforId;


    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Boolean getIfAttend() {
        return ifAttend;
    }

    public void setIfAttend(Boolean ifAttend) {
        this.ifAttend = ifAttend;
    }

    public String getAbandonReason() {
        return abandonReason;
    }

    public void setAbandonReason(String abandonReason) {
        this.abandonReason = abandonReason;
    }

    public String getApplyInforId() {
        return applyInforId;
    }

    public void setApplyInforId(String applyInforId) {
        this.applyInforId = applyInforId;
    }
}