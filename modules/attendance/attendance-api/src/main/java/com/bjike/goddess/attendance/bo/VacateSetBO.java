package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 请假设置业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateSetBO extends BaseBO {

    /**
     * 请假类型
     */
    private VacateType vacateType;

    /**
     * 是否有带薪天数
     */
    private Boolean payDay;

    /**
     * 请假周期
     */
    private String cycle;

    /**
     * 最大可选天数
     */
    private Integer maxDay;

    /**
     * 总可享受带薪天数
     */
    private Integer allPayDay;

    /**
     * 带薪占比(%)
     */
    private Double proportion;

    /**
     * 是否需要附件
     */
    private Boolean attachment;

    /**
     * 附件要求
     */
    private String attachmentRequest;

    /**
     * 创建人
     */
    private String name;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态
     */
    private Status status;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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