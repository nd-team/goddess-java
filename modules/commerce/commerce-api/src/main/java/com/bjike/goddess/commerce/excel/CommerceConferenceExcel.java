package com.bjike.goddess.commerce.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 商务会议
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommerceConferenceExcel extends BaseEntity {

    /**
     * 会议类型
     */
    @ExcelHeader(name = "会议类型" , notNull = true)
    private String type;

    /**
     * 会议编号
     */
    @ExcelHeader(name = "会议编号" , notNull = true)
    private String serialNumber;

    /**
     * 会议实际时间
     */
    @ExcelHeader(name = "会议实际时间" , notNull = true)
    private LocalDateTime conferenceTime;

    /**
     * 会议形式
     */
    @ExcelHeader(name = "会议形式" , notNull = true)
    private String way;

    /**
     * 会议地点
     */
    @ExcelHeader(name = "会议地点" , notNull = true)
    private String area;

    /**
     * 参会人员
     */
    @ExcelHeader(name = "参会人员" , notNull = true)
    private String personnel;

    /**
     * 参会人数
     */
    @ExcelHeader(name = "参会人数" , notNull = true)
    private Integer quantity;

    /**
     * 会议组织人
     */
    @ExcelHeader(name = "会议组织人" , notNull = true)
    private String organization;

    /**
     * 会议主持人
     */
    @ExcelHeader(name = "会议主持人" , notNull = true)
    private String emcee;

    /**
     * 会议记录人
     */
    @ExcelHeader(name = "会议记录人" , notNull = true)
    private String recorder;

    /**
     * 会议内容
     */
    @ExcelHeader(name = "会议内容" , notNull = true)
    private String content;

    /**
     * 对外通报内容
     */
    @ExcelHeader(name = "对外通报内容" , notNull = false)
    private String bulletin;

    /**
     * 最终协商结果
     */
    @ExcelHeader(name = "最终协商结果" , notNull = false)
    private String consult;

    /**
     * 最终谈判结果
     */
    @ExcelHeader(name = "最终谈判结果" , notNull = false)
    private String negotiation;

    /**
     * 最终合作结果
     */
    @ExcelHeader(name = "最终合作结果" , notNull = false)
    private String cooperation;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注" , notNull = false)
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getConferenceTime() {
        return conferenceTime;
    }

    public void setConferenceTime(LocalDateTime conferenceTime) {
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