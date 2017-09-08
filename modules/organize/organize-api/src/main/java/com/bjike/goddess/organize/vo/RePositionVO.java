package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * Created by ike on 17-9-7.
 */
public class RePositionVO {
    /**
     * id
     */
    private String id;
    /**
     * 资源池
     */
    private String pool;
    /**
     * 岗位名称
     */
    private String position;
    /**
     * 岗位编号
     */
    private String serialNumber;
    /**
     * 人员编制数
     */
    private Integer staff;
    /**
     * 当前人数
     */
    private String current;
    /**
     * 主职状态人员
     */
    private String main;
    /**
     * 兼任状态人员
     */
    private String part;
    /**
     * 代理岗位人员
     */
    private String agent;
    /**
     * 是否有岗位说明书
     */
    private Boolean book;
    /**
     * 状态
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Boolean getBook() {
        return book;
    }

    public void setBook(Boolean book) {
        this.book = book;
    }
}
