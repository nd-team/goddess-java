package com.bjike.goddess.staffwelfare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 祝福语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfare_wishesstatement",uniqueConstraints = {@UniqueConstraint(columnNames = {"createUser", "wishesStatement"})})
public class WishesStatement extends BaseEntity {

    /**
     * 填写人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String createUser;

    /**
     * 祝福语
     */
    @Column(name = "wishesStatement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '感谢语'")
    private String wishesStatement;

    /**
     * 更新人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '更新人'")
    private String updateUser;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getWishesStatement() {
        return wishesStatement;
    }

    public void setWishesStatement(String wishesStatement) {
        this.wishesStatement = wishesStatement;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}