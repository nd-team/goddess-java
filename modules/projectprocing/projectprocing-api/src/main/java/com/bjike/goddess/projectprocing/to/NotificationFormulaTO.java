package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectprocing.entity.SendFrequency;
import com.bjike.goddess.projectprocing.enums.SummTableName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 通报机制制定
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NotificationFormulaTO extends BaseTO {

    /**
     * 汇总表名
     */
    @NotNull(message = "汇总表名不能为空",groups = {ADD.class, EDIT.class})
    private SummTableName summTableName;

    /**
     * 发送频率
     */
    @NotNull(message = "发送频率不能为空",groups = {ADD.class, EDIT.class})
    private SendFrequency sendFrequency;

    /**
     * 发送时间节点
     */
    @NotNull(message = "发送时间节点不能为空",groups = {ADD.class, EDIT.class})
    private String sendTimeNode;

    /**
     * 发送对象
     */
    private List<String> sendObjects;

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

    public SendFrequency getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(SendFrequency sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public String getSendTimeNode() {
        return sendTimeNode;
    }

    public void setSendTimeNode(String sendTimeNode) {
        this.sendTimeNode = sendTimeNode;
    }

    public List<String> getSendObjects() {
        return sendObjects;
    }

    public void setSendObjects(List<String> sendObjects) {
        this.sendObjects = sendObjects;
    }

    public Boolean getSendSectoral() {
        return sendSectoral;
    }

    public void setSendSectoral(Boolean sendSectoral) {
        this.sendSectoral = sendSectoral;
    }
}