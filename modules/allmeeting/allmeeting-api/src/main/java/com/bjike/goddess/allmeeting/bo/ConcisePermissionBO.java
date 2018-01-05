package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.enums.PermissionType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 调阅权限业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-28 11:33 ]
 * @Description: [ 调阅权限业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConcisePermissionBO extends BaseBO {

    /**
     * 员工编号
     */
    private String userNum;

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

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }
}