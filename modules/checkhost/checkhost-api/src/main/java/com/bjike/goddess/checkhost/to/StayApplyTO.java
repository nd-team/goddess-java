package com.bjike.goddess.checkhost.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 住宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StayApplyTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    public interface TestAudit{}

    /**
     * 新入职员工姓名
     */
    @NotBlank(message = "新入职员工姓名不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String name;

    /**
     * 申请入住日期
     */
    @NotBlank(message = "申请入住日期不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String stayDate;

    /**
     * 申请入住地区
     */
    @NotBlank(message = "申请入住地区不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String area;

    /**
     * 申请入住宿舍
     */
    @NotBlank(message = "申请入住宿舍不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String stayDormitory;

    /**
     * 申请入住原因
     */
    @NotBlank(message = "申请入住原因不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String stayCause;

    /**
     * 住宿负责人
     */
    @NotBlank(message = "住宿负责人不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String stayHead;

    /**
     * 福利模块负责人
     */
    private String headAudit;
    /**
     * 福利模块负责人审核(通过/不通过)
     */
    @NotBlank(message = "福利模块负责人审核(通过/不通过)不能为空",groups = {StayApplyTO.TestAudit.class})
    private String headAuditPass;

    /**
     * 新员工确认入住
     */
    private Boolean stay;

    /**
     * 备注
     */
    private String remark;


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