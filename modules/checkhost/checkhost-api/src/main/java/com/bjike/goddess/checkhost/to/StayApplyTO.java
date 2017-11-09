package com.bjike.goddess.checkhost.to;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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
    public interface TestHostApply{}
    public interface TestHostAudit{}

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
     * 入住床位
     */
    @NotNull(message = "入住床位不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private Integer stayBed;

    /**
     * 床上3件套（件）
     */
    @NotNull(message = "床上3件套（件）舍不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private Integer suit;

    /**
     * 被褥（件）
     */
    @NotNull(message = "被褥（件）不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private Integer bedding;

    /**
     * 床垫
     */
    @NotNull(message = "床垫不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private Integer mattress;

    /**
     * 宿舍钥匙
     */
    @NotBlank(message = "宿舍钥匙不能为空",groups = {StayApplyTO.TestAdd.class,StayApplyTO.TestEdit.class})
    private String stayKey;

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
    @NotBlank(message = "福利模块负责人不能为空",groups = {StayApplyTO.TestAudit.class})
    private String headAudit;
//    /**
//     * 福利模块负责人审核(通过/不通过)
//     */
//    @NotBlank(message = "福利模块负责人审核(通过/不通过)不能为空",groups = {StayApplyTO.TestAudit.class})
//    private String headAuditPass;

    /**
     * 新员工确认入住
     */
    private Boolean stay;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态
     */
    @NotNull(message = "审核状态不能为空",groups = {StayApplyTO.TestAudit.class})
    private CheckStatus checkStatus;
    /**
     * 离宿原因
     */
    @NotBlank(message = "离宿原因不能为空",groups = {StayApplyTO.TestHostApply.class})
    private String hostCause;
    /**
     * 离宿时间
     */
    @NotBlank(message = "离宿时间不能为空",groups = {StayApplyTO.TestHostApply.class})
    private String hostTime;
    /**
     * 收费金额
     */
    @NotNull(message = "收费金额不能为空",groups = {StayApplyTO.TestHostApply.class})
    private Double tollMoney;
    /**
     * 水电费，燃气费总额
     */
    @NotNull(message = "水电费，燃气费总额不能为空",groups = {StayApplyTO.TestHostApply.class})
    private Double amount;

    /**
     * 合计总额（收费金额+水电费，燃气费总额）
     */
    private Double totalAmount;
    /**
     * 模块负责人审核
     */
    @NotBlank(message = "模块负责人审核不能为空",groups = {StayApplyTO.TestHostAudit.class})
    private String moduleAudit;
    /**
     * 模块负责人审核状态
     */
    @NotNull(message = "模块负责人审核状态不能为空",groups = {StayApplyTO.TestHostAudit.class})
    private CheckStatus moduleCheckStatus;

    public String getHostCause() {
        return hostCause;
    }

    public void setHostCause(String hostCause) {
        this.hostCause = hostCause;
    }

    public String getHostTime() {
        return hostTime;
    }

    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }

    public Double getTollMoney() {
        return tollMoney;
    }

    public void setTollMoney(Double tollMoney) {
        this.tollMoney = tollMoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModuleAudit() {
        return moduleAudit;
    }

    public void setModuleAudit(String moduleAudit) {
        this.moduleAudit = moduleAudit;
    }

    public CheckStatus getModuleCheckStatus() {
        return moduleCheckStatus;
    }

    public void setModuleCheckStatus(CheckStatus moduleCheckStatus) {
        this.moduleCheckStatus = moduleCheckStatus;
    }

    public Integer getStayBed() {
        return stayBed;
    }

    public void setStayBed(Integer stayBed) {
        this.stayBed = stayBed;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getMattress() {
        return mattress;
    }

    public void setMattress(Integer mattress) {
        this.mattress = mattress;
    }

    public String getStayKey() {
        return stayKey;
    }

    public void setStayKey(String stayKey) {
        this.stayKey = stayKey;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
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

//    public String getHeadAuditPass() {
//        return headAuditPass;
//    }
//
//    public void setHeadAuditPass(String headAuditPass) {
//        this.headAuditPass = headAuditPass;
//    }

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