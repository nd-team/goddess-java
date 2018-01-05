package com.bjike.goddess.staffactivity.vo;

/**
 * 活动人员名单表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivityStaffListVO {

    /**
     * id
     */
    private String id;
    /**
     * 人员姓名
     */
    private String staffName;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 是否参加活动
     */
    private Boolean ifAttend;

    /**
     * 放弃原因
     */
    private String abandonReason;

    /**
     * 活动申请信息id
     */
    private String applyInforId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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