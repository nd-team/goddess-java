package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 结算流程存储记录业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettlementProcessBO extends BaseBO {

    /**
     * 外包单位
     */
    private String outUnit;

    /**
     * 是否有组织框架
     */
    private Boolean organizatioanFrame;

    /**
     * 是否有结算流程
     */
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
     * 是否有结算流程附件
     */
    private Boolean settProceAttach;

    /**
     * 更新时间
     */
    private String updateDate;

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

    public Boolean getSettProceAttach() {
        return settProceAttach;
    }

    public void setSettProceAttach(Boolean settProceAttach) {
        this.settProceAttach = settProceAttach;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}