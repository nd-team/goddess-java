package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.enums.PermissionType;

/**
 * 项目信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectInfoBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 所属项目组Id
     */
    private String groupId;

    /**
     * 所属项目组
     */
    private String group;


    /**
     * 合同外部项目名称
     */
    private String outProject;

    /**
     * 内部项目名称
     */
    private String inProject;

    /**
     * 立项情况
     */
    private String approval;

    /**
     * 项目状态
     */
    private String projectStatus;

    /**
     * 派工单号
     */
    private String taskingNum;

    /**
     * 工作界面
     */
    private String workScreen;

    /**
     * 权限类型
     */
    private PermissionType permissionType;

    /**
     * 权限范围
     */
    private String permissionScope;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 状态
     */
    private Status status;


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

    public String getOutProject() {
        return outProject;
    }

    public void setOutProject(String outProject) {
        this.outProject = outProject;
    }

    public String getInProject() {
        return inProject;
    }

    public void setInProject(String inProject) {
        this.inProject = inProject;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getTaskingNum() {
        return taskingNum;
    }

    public void setTaskingNum(String taskingNum) {
        this.taskingNum = taskingNum;
    }

    public String getWorkScreen() {
        return workScreen;
    }

    public void setWorkScreen(String workScreen) {
        this.workScreen = workScreen;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionScope() {
        return permissionScope;
    }

    public void setPermissionScope(String permissionScope) {
        this.permissionScope = permissionScope;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}