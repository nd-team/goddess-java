package com.bjike.goddess.staffwelfaremanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 感谢语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfaremanage_thankstatement", uniqueConstraints = {@UniqueConstraint(columnNames = {"createUser", "thankStatement"}),
        @UniqueConstraint(columnNames = {"is_share", "thankStatement"})})
public class ThankStatement extends BaseEntity {

    /**
     * 填写人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String createUser;

    /**
     * 感谢语
     */
    @Column(name = "thankStatement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '感谢语'")
    private String thankStatement;

    /**
     * 是否共享为公有
     */
    @Column(name = "is_share", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否共享为公有(1是,0否)'")
    private Boolean share;

    /**
     * 更新人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '更新人'")
    private String updateUser;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '数据状态'",insertable = false)
    private Status status;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }

    public Boolean getShare() {
        return share;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}