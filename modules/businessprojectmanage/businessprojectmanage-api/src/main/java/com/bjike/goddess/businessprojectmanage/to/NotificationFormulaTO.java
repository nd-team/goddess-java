package com.bjike.goddess.businessprojectmanage.to;

import com.bjike.goddess.businessprojectmanage.enums.CollectSendUnit;
import com.bjike.goddess.businessprojectmanage.enums.CollectUnit;
import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 通报机制制定
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NotificationFormulaTO extends BaseTO {

    /**
     * 汇总类型
     */
    private String type;

    /**
     * 发送间隔
     */
    private Double sendFrequency;

    /**
     * 发送时间节点
     */
    private String sendTimeNode;

    /**
     * 发送对象
     */
    private List<String> sendObject;

    /**
     * 是否发送项目组/部门全部人
     */
    private Boolean sendSectoral;

    /**
     * 汇总条件
     */
    private String conditions;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    private CollectUnit collectUnit;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(Double sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public String getSendTimeNode() {
        return sendTimeNode;
    }

    public void setSendTimeNode(String sendTimeNode) {
        this.sendTimeNode = sendTimeNode;
    }

    public List<String> getSendObject() {
        return sendObject;
    }

    public void setSendObject(List<String> sendObject) {
        this.sendObject = sendObject;
    }

    public Boolean getSendSectoral() {
        return sendSectoral;
    }

    public void setSendSectoral(Boolean sendSectoral) {
        this.sendSectoral = sendSectoral;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

}