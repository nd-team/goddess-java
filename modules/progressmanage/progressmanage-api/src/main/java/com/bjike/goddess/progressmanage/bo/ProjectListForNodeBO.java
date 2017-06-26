package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目下拉列表数据对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 项目下拉列表数据对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectListForNodeBO extends BaseBO {

    /**
     * 表名
     */
    private String area;

    /**
     * 项目组
     */
    private String groupId;

    /**
     * 项目组
     */
    private String group;

    /**
     * 合同内部岷城
     */
    private String inProject;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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