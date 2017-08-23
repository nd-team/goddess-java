package com.bjike.goddess.negotiatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 协商会议纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:49 ]
 * @Description: [ 协商会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "negotiatemeeting_summary")
public class Summary extends BaseEntity {

    /**
     * 实际会议时间
     */
    @Column(name = "actualTime", columnDefinition = "DATETIME   COMMENT '实际会议时间'")
    private LocalDateTime actualTime;

    /**
     * 实际参会人员
     */
    @Column(name = "actualAttend", columnDefinition = "VARCHAR(255)   COMMENT '实际参会人员'")
    private String actualAttend;

    /**
     * 新增参会人员
     */
    @Column(name = "addAttend", columnDefinition = "VARCHAR(255)   COMMENT '新增参会人员'")
    private String addAttend;

    /**
     * 未参会人员
     */
    @Column(name = "notAttend", columnDefinition = "VARCHAR(255)   COMMENT '未参会人员'")
    private String notAttend;

    /**
     * 参会人数
     */
    @Column(name = "num", columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer num;

    /**
     * 最终协商结果
     */
    @Column(name = "result", columnDefinition = "VARCHAR(255)   COMMENT '最终协商结果'")
    private String result;

    /**
     * 会议记录人
     */
    @Column(name = "recorder", columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recorder;

    /**
     * 协商会议组织内容信息
     */
    @Column(name = "organization_id", nullable = false, unique = true, columnDefinition = "VARCHAR(36)   COMMENT '协商会议组织内容信息'")
    private String organizationId;

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualAttend() {
        return actualAttend;
    }

    public void setActualAttend(String actualAttend) {
        this.actualAttend = actualAttend;
    }

    public String getAddAttend() {
        return addAttend;
    }

    public void setAddAttend(String addAttend) {
        this.addAttend = addAttend;
    }

    public String getNotAttend() {
        return notAttend;
    }

    public void setNotAttend(String notAttend) {
        this.notAttend = notAttend;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}