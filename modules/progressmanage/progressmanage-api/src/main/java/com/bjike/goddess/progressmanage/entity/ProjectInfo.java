package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.enums.PermissionType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 项目信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_projectinfo")
public class ProjectInfo extends BaseEntity {


    /**
     * 进度表
     */
    @OneToOne(cascade=CascadeType.REFRESH,mappedBy = "project",fetch = FetchType.LAZY)
    private ProgressTable progressTable;

    /**
     * 进度节点
     */
    @OneToMany(cascade=CascadeType.REFRESH,mappedBy = "project",fetch = FetchType.EAGER)
    private Set<ProgressNode> progressNodeSet = new HashSet<ProgressNode>();

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 所属项目组
     */
    @Column(name = "groupId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '所属项目组'")
    private String groupId;

    /**
     * 合同外部项目名称
     */
    @Column(name = "outProject", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outProject;

    /**
     * 内部项目名称
     */
    @Column(name = "inProject", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String inProject;

    /**
     * 立项情况
     */
    @Column(name = "approval", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '立项情况'")
    private String approval;

    /**
     * 项目状态
     */
    @Column(name = "projectStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目状态'")
    private String projectStatus;

    /**
     * 派工单号
     */
    @Column(name = "taskingNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单号'")
    private String taskingNum;

    /**
     * 工作界面
     */
    @Column(name = "workScreen", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作界面'")
    private String workScreen;

    /**
     * 权限类型
     */
    @Column(name = "permissionType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '权限类型'")
    private PermissionType permissionType;

    /**
     * 权限范围
     */
    @Column(name = "permissionScope", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '权限范围'")
    private String permissionScope;

    /**
     * 创建人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createUser;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'")
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

    public ProgressTable getProgressTable() {
        return progressTable;
    }

    public void setProgressTable(ProgressTable progressTable) {
        this.progressTable = progressTable;
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

    public Set<ProgressNode> getProgressNodeSet() {
        return progressNodeSet;
    }

    public void setProgressNodeSet(Set<ProgressNode> progressNodeSet) {
        this.progressNodeSet = progressNodeSet;
    }
}