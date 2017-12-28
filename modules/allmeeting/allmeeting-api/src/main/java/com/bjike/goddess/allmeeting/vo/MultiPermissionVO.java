package com.bjike.goddess.allmeeting.vo;

import com.bjike.goddess.allmeeting.enums.PermissionType;

/**
 * 调阅权限表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 03:02 ]
 * @Description: [ 调阅权限表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultiPermissionVO {

    /**
     * id
     */
    private String id;
    /**
     * 员工编号
     */
    private String userNum;

    /**
     * 申请人
     */
    private String userName;

    /**
     * 调阅权限
     */
    private PermissionType type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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