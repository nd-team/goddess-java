package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 借款审核直接修改
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:09 ]
 * @Description: [ 借款审核直接修改 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_lendeditcontent")
public class LendEditContent extends BaseEntity {

    /**
     * 审核人
     */
    @Column(name = "auditor",columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 内容
     */
    @Column(name = "content" , columnDefinition = "VARCHAR(255)   COMMENT '内容'")
    private String content;


    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}