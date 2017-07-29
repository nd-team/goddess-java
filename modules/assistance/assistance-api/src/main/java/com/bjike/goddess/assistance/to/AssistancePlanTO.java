package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 补助方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistancePlanTO extends BaseTO {

    /**
     * 补助类型名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "补助类型名称不能为空")
    private String typeName;

    /**
     * 补助目的
     */
    private String targetContent;

    /**
     * 补助对象
     */
    private String helpObject;

    /**
     * 补助开始时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "补助开始时间不能为空")
    private String helpStartTime;

    /**
     * 补助结束时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "补助结束时间不能为空")
    private String helpEndTime;

    /**
     * 补助条件
     */
    private String helpCondition;

    /**
     * 条件依据明细
     */
    private String detail;

    /**
     * 数据来源
     */
    private String dataOragin;

    /**
     * 补助内容
     */
    private String helpContent;

    /**
     * 内容明细
     */
    private String contentDetail;

    /**
     * 补助发放形式
     */
    private String helpForm;

    /**
     * 补助发放时间
     */
    private String helpGiveTime;

    /**
     * 综合资源部福利模块意见
     */
    private String warefaleAdvice;

    /**
     * 运营财务部意见
     */
    private String finiceAdvice;

    /**
     * 总经办意见
     */
    private String manageAdvice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public String getHelpObject() {
        return helpObject;
    }

    public void setHelpObject(String helpObject) {
        this.helpObject = helpObject;
    }

    public String getHelpStartTime() {
        return helpStartTime;
    }

    public void setHelpStartTime(String helpStartTime) {
        this.helpStartTime = helpStartTime;
    }

    public String getHelpEndTime() {
        return helpEndTime;
    }

    public void setHelpEndTime(String helpEndTime) {
        this.helpEndTime = helpEndTime;
    }

    public String getHelpCondition() {
        return helpCondition;
    }

    public void setHelpCondition(String helpCondition) {
        this.helpCondition = helpCondition;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDataOragin() {
        return dataOragin;
    }

    public void setDataOragin(String dataOragin) {
        this.dataOragin = dataOragin;
    }

    public String getHelpContent() {
        return helpContent;
    }

    public void setHelpContent(String helpContent) {
        this.helpContent = helpContent;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getHelpForm() {
        return helpForm;
    }

    public void setHelpForm(String helpForm) {
        this.helpForm = helpForm;
    }

    public String getHelpGiveTime() {
        return helpGiveTime;
    }

    public void setHelpGiveTime(String helpGiveTime) {
        this.helpGiveTime = helpGiveTime;
    }

    public String getWarefaleAdvice() {
        return warefaleAdvice;
    }

    public void setWarefaleAdvice(String warefaleAdvice) {
        this.warefaleAdvice = warefaleAdvice;
    }

    public String getFiniceAdvice() {
        return finiceAdvice;
    }

    public void setFiniceAdvice(String finiceAdvice) {
        this.finiceAdvice = finiceAdvice;
    }

    public String getManageAdvice() {
        return manageAdvice;
    }

    public void setManageAdvice(String manageAdvice) {
        this.manageAdvice = manageAdvice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}