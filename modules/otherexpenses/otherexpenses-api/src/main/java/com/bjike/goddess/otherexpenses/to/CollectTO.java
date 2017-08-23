package com.bjike.goddess.otherexpenses.to;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-03 15:57]
 * @Description: [ 汇总条件传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTO implements Serializable {

    /**
     * 查询开始时间
     */
    private String start;

    /**
     * 查询结束时间
     */
    private String end;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String project;

    /**
     * 类别
     */
    private String type;

    /**
     * 项目名称
     */
    private String name;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
