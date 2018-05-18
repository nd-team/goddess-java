package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectTO extends BaseTO {
    /**
     * 项目名
     */
    @NotBlank(message = "项目名不能为空", groups = {ADD.class, EDIT.class})
    private String name;
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;
    /**
     * 执行状态
     */
    private ExecStatus execStatus;
    /**
     * 个人可见范围
     */
    private String[] users;
    /**
     * 部门可见范围
     */
    private String[] departments;
    /**
     * 项目组可见范围
     */
    private String[] groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }
}
