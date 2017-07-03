package com.bjike.goddess.commerce.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务会议
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommerceConferenceTO extends BaseTO {

    /**
     * 会议实际时间
     */
    @NotBlank(message = "会议实际时间不能为空", groups = {ADD.class, EDIT.class})
    private String conferenceTime;

    /**
     * 会议形式
     */
    @NotBlank(message = "会议形式不能为空", groups = {ADD.class, EDIT.class})
    private String way;

    /**
     * 会议地点
     */
    @NotBlank(message = "会议地点不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 参会人员
     */
    @NotBlank(message = "参会人员不能为空", groups = {ADD.class, EDIT.class})
    private String personnel;

    /**
     * 参会人数
     */
    @NotNull(message = "参会人数不能为空", groups = {ADD.class, EDIT.class})
    private Integer quantity;

    /**
     * 会议组织人
     */
    @NotBlank(message = "会议组织人不能为空", groups = {ADD.class, EDIT.class})
    private String organization;

    /**
     * 会议主持人
     */
    @NotBlank(message = "会议主持人不能为空", groups = {ADD.class, EDIT.class})
    private String emcee;

    /**
     * 会议记录人
     */
    @NotBlank(message = "会议记录人不能为空", groups = {ADD.class, EDIT.class})
    private String recorder;

    /**
     * 会议内容
     */
    @NotBlank(message = "会议内容不能为空", groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 对外通报内容
     */
    private String bulletin;

    /**
     * 最终协商结果
     */
    private String consult;

    /**
     * 最终谈判结果
     */
    private String negotiation;

    /**
     * 最终合作结果
     */
    private String cooperation;

    /**
     * 备注
     */
    private String remark;

    public String getConferenceTime() {
        return conferenceTime;
    }

    public void setConferenceTime(String conferenceTime) {
        this.conferenceTime = conferenceTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmcee() {
        return emcee;
    }

    public void setEmcee(String emcee) {
        this.emcee = emcee;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBulletin() {
        return bulletin;
    }

    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }

    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public String getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(String negotiation) {
        this.negotiation = negotiation;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}