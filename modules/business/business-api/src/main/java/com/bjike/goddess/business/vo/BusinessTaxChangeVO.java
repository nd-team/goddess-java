package com.bjike.goddess.business.vo;

import com.bjike.goddess.business.enums.ChangeDataName;

/**
 * 工商税务变更表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessTaxChangeVO {

    /**
     * id
     */
    private String id;
    /**
     * 工商注册id
     */
    private String businessRegisterId;
    /**
     * 变更日期
     */
    private String changeDate;

    /**
     * 变更原因
     */
    private String changeCause;

    /**
     * 变更前内容
     */
    private String changeBeforeContent;

    /**
     * 变更后内容
     */
    private String changeAfterContent;

    /**
     * 负责经办人
     */
    private String responsibleAgent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 变更资料名称
     */
    private ChangeDataName changeDataName;

    /**
     * 负责经办人联系方式
     */
    private String responsiblePhone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessRegisterId() {
        return businessRegisterId;
    }

    public void setBusinessRegisterId(String businessRegisterId) {
        this.businessRegisterId = businessRegisterId;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeCause() {
        return changeCause;
    }

    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
    }

    public String getChangeBeforeContent() {
        return changeBeforeContent;
    }

    public void setChangeBeforeContent(String changeBeforeContent) {
        this.changeBeforeContent = changeBeforeContent;
    }

    public String getChangeAfterContent() {
        return changeAfterContent;
    }

    public void setChangeAfterContent(String changeAfterContent) {
        this.changeAfterContent = changeAfterContent;
    }

    public String getResponsibleAgent() {
        return responsibleAgent;
    }

    public void setResponsibleAgent(String responsibleAgent) {
        this.responsibleAgent = responsibleAgent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ChangeDataName getChangeDataName() {
        return changeDataName;
    }

    public void setChangeDataName(ChangeDataName changeDataName) {
        this.changeDataName = changeDataName;
    }

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }
}