package com.bjike.goddess.business.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 工商税务变更
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessTaxChangeTO extends BaseTO {

    /**
     * 变更日期
     */
    @NotBlank(message = "变更日期不能为空",groups = {ADD.class, EDIT.class})
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
    private String changeDataName;


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

    public String getChangeDataName() {
        return changeDataName;
    }

    public void setChangeDataName(String changeDataName) {
        this.changeDataName = changeDataName;
    }
}