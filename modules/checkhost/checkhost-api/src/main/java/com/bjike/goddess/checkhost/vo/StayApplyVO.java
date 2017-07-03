package com.bjike.goddess.checkhost.vo;

/**
 * 住宿申请表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StayApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 新入职员工姓名
     */
    private String name;

    /**
     * 申请入住日期
     */
    private String stayDate;

    /**
     * 申请入住地区
     */
    private String area;

    /**
     * 申请入住宿舍
     */
    private String stayDormitory;

    /**
     * 申请入住原因
     */
    private String stayCause;

    /**
     * 住宿负责人
     */
    private String stayHead;

    /**
     * 福利模块负责人
     */
    private String headAudit;
    /**
     * 福利模块负责人审核
     */
    private String headAuditPass;

    /**
     * 新员工确认入住
     */
    private Boolean stay;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStayDate() {
        return stayDate;
    }

    public void setStayDate(String stayDate) {
        this.stayDate = stayDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStayDormitory() {
        return stayDormitory;
    }

    public void setStayDormitory(String stayDormitory) {
        this.stayDormitory = stayDormitory;
    }

    public String getStayCause() {
        return stayCause;
    }

    public void setStayCause(String stayCause) {
        this.stayCause = stayCause;
    }

    public String getStayHead() {
        return stayHead;
    }

    public void setStayHead(String stayHead) {
        this.stayHead = stayHead;
    }

    public String getHeadAudit() {
        return headAudit;
    }

    public void setHeadAudit(String headAudit) {
        this.headAudit = headAudit;
    }

    public String getHeadAuditPass() {
        return headAuditPass;
    }

    public void setHeadAuditPass(String headAuditPass) {
        this.headAuditPass = headAuditPass;
    }

    public Boolean getStay() {
        return stay;
    }

    public void setStay(Boolean stay) {
        this.stay = stay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}