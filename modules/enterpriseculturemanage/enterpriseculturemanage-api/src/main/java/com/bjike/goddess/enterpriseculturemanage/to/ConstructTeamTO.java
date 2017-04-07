package com.bjike.goddess.enterpriseculturemanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 建设小组
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConstructTeamTO extends BaseTO {

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工编号
     */
    private String userNumber;

    /**
     * 项目组
     */
    private String group;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}