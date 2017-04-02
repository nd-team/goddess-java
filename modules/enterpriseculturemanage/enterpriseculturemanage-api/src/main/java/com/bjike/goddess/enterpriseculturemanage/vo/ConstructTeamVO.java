package com.bjike.goddess.enterpriseculturemanage.vo;

/**
 * 建设小组表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConstructTeamVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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