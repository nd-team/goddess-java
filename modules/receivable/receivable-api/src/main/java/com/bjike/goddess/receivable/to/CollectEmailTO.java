package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.receivable.enums.CollectSendUnit;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 回款管理邮件发送定制
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.879 ]
 * @Description: [ 回款管理邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 汇总类型
     */
    @NotBlank(groups = {CollectEmailTO.TestAdd.class}, message = "汇总类型不能为空")
    private String type;

    /**
     * 汇总条件
     */
    @NotNull(groups = {CollectEmailTO.TestAdd.class}, message = "汇总条件不能为空")
    private String[] condis;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    @NotNull(groups = {CollectEmailTO.TestAdd.class},message = "发送间隔不能为空，且是数字")
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    @NotNull(groups = {CollectEmailTO.TestAdd.class}, message = "发送单位不能为空")
    private CollectSendUnit collectSendUnit;


    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 发送对象数组
     */
    @NotNull(groups = {CollectEmailTO.TestAdd.class}, message = "发送对象数组不能为空")
    private List<String> sendObjectList;

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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCondis() {
        return condis;
    }

    public void setCondis(String[] condis) {
        this.condis = condis;
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


    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public List<String> getSendObjectList() {
        return sendObjectList;
    }

    public void setSendObjectList(List<String> sendObjectList) {
        this.sendObjectList = sendObjectList;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

}