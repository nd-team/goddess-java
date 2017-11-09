package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 请假设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateSetTO extends BaseTO {

    /**
     * 请假类型
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "请假类型不能为空")
    private VacateType vacateType;

    /**
     * 是否有带薪天数
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "是否有带薪天数不能为空")
    private Boolean payDay;

    /**
     * 请假周期
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "请假周期不能为空")
    private String cycle;

    /**
     * 最大可选天数
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "最大可选天数不能为空")
    private Integer maxDay;

    /**
     * 总可享受带薪天数
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "总可享受带薪天数不能为空")
    private Integer allPayDay;

    /**
     * 带薪占比(%)
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "带薪占比不能为空")
    private Double proportion;

    /**
     * 是否需要附件
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "是否需要附件不能为空")
    private Boolean attachment;

    /**
     * 附件要求
     */
    private String attachmentRequest;

    /**
     * 创建人
     */
    @NotBlank(groups = {ADD.class},message = "创建人不能为空")
    private String name;

    /**
     * 状态
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "状态不能为空")
    private Status status;


    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public Boolean getPayDay() {
        return payDay;
    }

    public void setPayDay(Boolean payDay) {
        this.payDay = payDay;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }

    public Integer getAllPayDay() {
        return allPayDay;
    }

    public void setAllPayDay(Integer allPayDay) {
        this.allPayDay = allPayDay;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentRequest() {
        return attachmentRequest;
    }

    public void setAttachmentRequest(String attachmentRequest) {
        this.attachmentRequest = attachmentRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}