package com.bjike.goddess.negotiatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 协商会议纪要子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "negotiatemeeting_summaryson")
public class SummarySon extends BaseEntity {

    /**
     * 参会人
     */
    @Column(name = "attend", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '参会人'")
    private String attend;

    /**
     * 协商意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协商意见'")
    private String opinion;

    /**
     * 协商会议纪要信息
     */
    @Column(name = "summary_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '协商会议纪要信息'")
    private String summaryId;

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}