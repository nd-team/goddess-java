package com.bjike.goddess.feedback.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.feedback.enums.CollectSendUnit;
import com.bjike.goddess.feedback.enums.Type;

import java.util.List;

/**
 * 发送邮件业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-05 10:27 ]
 * @Description: [ 发送邮件业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SendEmailBO extends BaseBO {

    /**
     * 发送间隔
     */
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    private CollectSendUnit collectSendUnit;

    /**
     * 发送对象
     */
    private String sendObject;
    /**
     * 发送对象数组
     */
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
     * 类型
     */
    private Type type;

    /**
     * 备注
     */
    private String remark;


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

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public List<String> getSendObjectList() {
        return sendObjectList;
    }

    public void setSendObjectList(List<String> sendObjectList) {
        this.sendObjectList = sendObjectList;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}