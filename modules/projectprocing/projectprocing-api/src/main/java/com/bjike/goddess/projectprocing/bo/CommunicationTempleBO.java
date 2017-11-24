package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectprocing.entity.SendFrequency;
import com.bjike.goddess.projectprocing.entity.SummTableName;

import java.time.LocalDate;

/**
 * 各类沟通模板业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:26 ]
 * @Description: [ 各类沟通模板业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommunicationTempleBO extends BaseBO {

    /**
     * 汇总表名
     */
    private SummTableName summTableName;

    /**
     * 创建/修改人
     */
    private String modifier;

    /**
     * 发送频率
     */
    private SendFrequency sendFrequency;

    /**
     * 发送时间节点
     */
    private LocalDate sendTimeNode;

    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 是否发送项目组/部门全部人
     */
    private Boolean sendSectoral;


    public SummTableName getSummTableName() {
        return summTableName;
    }

    public void setSummTableName(SummTableName summTableName) {
        this.summTableName = summTableName;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public SendFrequency getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(SendFrequency sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public LocalDate getSendTimeNode() {
        return sendTimeNode;
    }

    public void setSendTimeNode(LocalDate sendTimeNode) {
        this.sendTimeNode = sendTimeNode;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public Boolean getSendSectoral() {
        return sendSectoral;
    }

    public void setSendSectoral(Boolean sendSectoral) {
        this.sendSectoral = sendSectoral;
    }
}