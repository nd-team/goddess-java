package com.bjike.goddess.materialsummary.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialsummary.type.CollectSendUnit;
import com.bjike.goddess.materialsummary.type.CollectUnit;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 物质购买发送邮件
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SendEmailTO extends BaseTO {

    /**
     * 汇总模块
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总模块不能为空")
    private ModuleType moduleType;

    /**
     * 汇总类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总类型不能为空")
    private SummaryType summaryType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    @Range(min = 0,max = 10000)
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送间隔不能为空")
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送单位不能为空")
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇总间隔不能为空")
    private CollectUnit collectUnit;

    /**
     * 发送对象
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发送对象不能为空")
    private List<String> sendObject;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 创建人
     */
    private String createPersion;


    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public SummaryType getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(SummaryType summaryType) {
        this.summaryType = summaryType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSendNum() {
        return sendNum;
    }

    public void setSendNum(Double sendNum) {
        this.sendNum = sendNum;
    }

    public String getSendNumAndUnit() {
        return sendNumAndUnit;
    }

    public void setSendNumAndUnit(String sendNumAndUnit) {
        this.sendNumAndUnit = sendNumAndUnit;
    }

    public CollectSendUnit getCollectSendUnit() {
        return collectSendUnit;
    }

    public void setCollectSendUnit(CollectSendUnit collectSendUnit) {
        this.collectSendUnit = collectSendUnit;
    }

    public CollectUnit getCollectUnit() {
        return collectUnit;
    }

    public void setCollectUnit(CollectUnit collectUnit) {
        this.collectUnit = collectUnit;
    }

    public List<String> getSendObject() {
        return sendObject;
    }

    public void setSendObject(List<String> sendObject) {
        this.sendObject = sendObject;
    }

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }
}