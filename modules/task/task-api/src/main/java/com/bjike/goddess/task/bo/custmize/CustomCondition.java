package com.bjike.goddess.task.bo.custmize;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-28 16:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomCondition {
    private String start; //开始时间
    private String end; //结束时间
    private String projectId; //项目
    private String[] tablesId; //表
    private List<String> users ; //指定查询汇总的人
    private List<String> deptUsers ;//查询结果用户必须包含在此部门人员列表内
    private String[] fields ;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getDeptUsers() {
        return deptUsers;
    }

    public void setDeptUsers(List<String> deptUsers) {
        this.deptUsers = deptUsers;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String[] getTablesId() {
        return tablesId;
    }

    public void setTablesId(String[] tablesId) {
        this.tablesId = tablesId;
    }
}
