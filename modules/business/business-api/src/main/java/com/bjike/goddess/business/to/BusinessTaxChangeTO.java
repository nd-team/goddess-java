package com.bjike.goddess.business.to;

import com.bjike.goddess.business.enums.ChangeDataName;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

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
     * 工商注册id
     */
    @NotBlank(message = "工商注册id不能为空",groups = {ADD.class, EDIT.class})
    private String businessRegisterId;
    /**
     * 变更日期
     */
    @NotBlank(message = "变更日期不能为空",groups = {ADD.class, EDIT.class})
    private String changeDate;

    /**
     * 变更原因
     */
    @NotBlank(message = "变更原因不能为空",groups = {ADD.class, EDIT.class})
    private String changeCause;

    /**
     * 变更资料名称
     */
    @NotNull(message = "变更资料名称不能为空",groups = {ADD.class, EDIT.class})
    private ChangeDataName changeDataName;

    /**
     * 变更前内容
     */
    private String changeBeforeContent;

    /**
     * 变更后内容(当变更的是经营期限就要将两个文本框用-隔开合成一个字符串传入)
     */
    private String changeAfterContent;

    /**
     * 变更后内容(当变更的是股东:占股比例时传的是这个参数)
     */
    private List<ShareholdersTO> shareholdersTOList;

    /**
     * 负责经办人
     */
    @NotBlank(message = "负责经办人不能为空",groups = {ADD.class, EDIT.class})
    private String responsibleAgent;

    /**
     * 负责经办人联系方式
     */
    @NotBlank(message = "负责经办人联系方式不能为空",groups = {ADD.class, EDIT.class})
    private String responsiblePhone;

    /**
     * 备注
     */
    private String remark;



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

    public String getChangeCause() {
        return changeCause;
    }

    public void setChangeCause(String changeCause) {
        this.changeCause = changeCause;
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

    public List<ShareholdersTO> getShareholdersTOList() {
        return shareholdersTOList;
    }

    public void setShareholdersTOList(List<ShareholdersTO> shareholdersTOList) {
        this.shareholdersTOList = shareholdersTOList;
    }
}