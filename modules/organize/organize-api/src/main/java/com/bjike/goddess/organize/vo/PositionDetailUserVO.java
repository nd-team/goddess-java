package com.bjike.goddess.organize.vo;

/**
 * 用户职位表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserVO {

    /**
     * id
     */
    private String id;
    /**
     * 用户信息
     */
    private String user_id;

    /**
     * 员工名称
     */
    private String username;

    /**
     * 员工编号
     */
    private String employeesNumber;

    /**
     * 职位
     */
    private String position;

    /**
     * 职位id
     */
    private String position_ids;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition_ids() {
        return position_ids;
    }

    public void setPosition_ids(String position_ids) {
        this.position_ids = position_ids;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(String employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}