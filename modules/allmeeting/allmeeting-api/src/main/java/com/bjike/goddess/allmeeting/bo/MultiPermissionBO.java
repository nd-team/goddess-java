package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.enums.PermissionType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 调阅权限业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 03:02 ]
 * @Description: [ 调阅权限业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultiPermissionBO extends BaseBO {

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