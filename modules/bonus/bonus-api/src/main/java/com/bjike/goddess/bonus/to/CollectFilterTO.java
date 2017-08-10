package com.bjike.goddess.bonus.to;

import java.io.Serializable;

/**
 * 汇总过滤条件传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-10 10:12]
 * @Description: [ 汇总过滤条件传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectFilterTO implements Serializable {

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
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
     * 指标名称
     */
    private String target;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
