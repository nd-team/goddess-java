package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectprocing.enums.SummTableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 各类沟通模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:26 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_communicationtemple")
public class CommunicationTemple extends BaseEntity {

    /**
     * 汇总表名
     */
    @Column(name = "", nullable = false, columnDefinition = "INT(2)   COMMENT '汇总表名'")
    private SummTableName summTableName;

    /**
     * 创建/修改人
     */
    @Column(name = "modifier", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建/修改人'")
    private String modifier;

    /**
     * 发送频率
     */
    @Column(name = "", nullable = false, columnDefinition = "INT(2)   COMMENT '发送频率'")
    private SendFrequency sendFrequency;

    /**
     * 发送时间节点
     */
    @Column(name = "sendTimeNode", nullable = false, columnDefinition = "DATE   COMMENT '发送时间节点'")
    private LocalDate sendTimeNode;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 是否发送项目组/部门全部人
     */
    @Column(name = "is_sendSectoral", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否发送项目组/部门全部人'", insertable = false)
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