package com.bjike.goddess.marketdevelopment.vo;

/**
 * 市场挖掘表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:15 ]
 * @Description: [ 市场挖掘表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketChannelVO {

    /**
     * id
     */
    private String id;
    /**
     * 业务类型
     */
    private String type;

    /**
     * 业务对象
     */
    private String course;

    /**
     * 人员
     */
    private String staff;

    /**
     * 资质
     */
    private String qualification;

    /**
     * 其他
     */
    private String other;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}