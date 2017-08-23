package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.allmeeting.enums.PermissionType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 调阅权限
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 03:02 ]
 * @Description: [ 调阅权限 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_multipermission")
public class MultiPermission extends BaseEntity {

    /**
     * 员工编号
     */
    @Column(name = "userNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String userNum;

    /**
     * 申请人
     */
    @Column(name = "userName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请人'")
    private String userName;

    /**
     * 调阅权限
     */
    @Column(name = "type", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '调阅权限'")
    private PermissionType type;


    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }
}