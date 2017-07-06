package com.bjike.goddess.progressmanage.vo;

/**
 * 项目下拉列表数据对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 项目下拉列表数据对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectListForNodeVO {

    /**
     * 项目Id
     */
    private String id;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String group;

    /**
     * 合同内部名称
     */
    private String inProject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getInProject() {
        return inProject;
    }

    public void setInProject(String inProject) {
        this.inProject = inProject;
    }
}