package com.bjike.goddess.task.bo.custmize;

import com.bjike.goddess.task.bo.collect.TaskCollect;
import com.bjike.goddess.task.bo.collect.TomorrowCollect;
import org.apache.poi.ss.formula.functions.Today;

import java.util.List;
import java.util.Map;

/**定制汇总项目
 * @Author: [liguiqin]
 * @Date: [2017-09-28 15:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomProject {
    private String name; //项目名
    private List<CustomTable> tables; //包含表
    private Map<String,String> collects;//汇总
    private String department;//部门名
    private List <TaskCollect>taskCollects; //今天任务
    private List <TomorrowCollect> tomorrowCollects;//明天任务

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomTable> getTables() {
        return tables;
    }

    public void setTables(List<CustomTable> tables) {
        this.tables = tables;
    }

    public Map<String, String> getCollects() {
        return collects;
    }

    public void setCollects(Map<String, String> collects) {
        this.collects = collects;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<TaskCollect> getTaskCollects() {
        return taskCollects;
    }

    public void setTaskCollects(List<TaskCollect> taskCollects) {
        this.taskCollects = taskCollects;
    }

    public List<TomorrowCollect> getTomorrowCollects() {
        return tomorrowCollects;
    }

    public void setTomorrowCollects(List<TomorrowCollect> tomorrowCollects) {
        this.tomorrowCollects = tomorrowCollects;
    }
}
