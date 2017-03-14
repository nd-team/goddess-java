package com.bjike.goddess.recruit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "recruit_notentryreason")
public class NotEntryReason extends BaseEntity {

    /**
     * 未入职原因类型
     */
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) COMMENT '未入职原因类型' ")
    private String notEntryReasonType;

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注' ")
    private String comment;

    public String getNotEntryReasonType() {
        return notEntryReasonType;
    }

    public void setNotEntryReasonType(String notEntryReasonType) {
        this.notEntryReasonType = notEntryReasonType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
