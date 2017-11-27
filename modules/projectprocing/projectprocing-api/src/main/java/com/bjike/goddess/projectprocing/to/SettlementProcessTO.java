package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 结算流程存储记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettlementProcessTO extends BaseTO {

    /**
     * 外包单位
     */
    @NotBlank(message = "外包单位不能为空", groups = {ADD.class, EDIT.class})
    private String outUnit;

    /**
     * 是否有组织框架
     */
    @NotNull(message = "外包单位不能为空", groups = {ADD.class, EDIT.class})
    private Boolean organizatioanFrame;

    /**
     * 是否有结算流程
     */
    @NotNull(message = "是否有结算流程不能为空", groups = {ADD.class, EDIT.class})
    private Boolean clearProcess;

    /**
     * 收集制作人
     */
    private String gatherer;

    /**
     * 制作时间
     */
    private String makingDate;

    /**
     * 备注
     */
    private String remark;

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public Boolean getOrganizatioanFrame() {
        return organizatioanFrame;
    }

    public void setOrganizatioanFrame(Boolean organizatioanFrame) {
        this.organizatioanFrame = organizatioanFrame;
    }

    public Boolean getClearProcess() {
        return clearProcess;
    }

    public void setClearProcess(Boolean clearProcess) {
        this.clearProcess = clearProcess;
    }

    public String getGatherer() {
        return gatherer;
    }

    public void setGatherer(String gatherer) {
        this.gatherer = gatherer;
    }

    public String getMakingDate() {
        return makingDate;
    }

    public void setMakingDate(String makingDate) {
        this.makingDate = makingDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}