package com.bjike.goddess.staffing.vo;

/**
 * 操作汇总表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-09 02:00 ]
 * @Description: [ 操作汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CountVO {

    /**
     * id
     */
    private String id;
    /**
     * 导航名
     */
    private String navigation;

    /**
     * 功能名
     */
    private String function;

    /**
     * 用户
     */
    private String user;

    /**
     * 操作时间
     */
    private String time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}