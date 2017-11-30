package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contacts.enums.ContactsStatus;
import com.bjike.goddess.user.enums.SexType;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-04 09:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MobileSearchBO extends BaseBO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户头像
     */
    private String headSculpture;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户性别
     */
    private SexType sex;

    /**
     * 职位
     */
    private String position;

    /**
     * 电话
     */
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
