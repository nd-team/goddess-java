package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.enums.PermissionType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectInfoTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 所属项目组Id
     */
    @NotBlank(message = "所属项目组Id不能为空",groups = {ADD.class, EDIT.class})
    private String groupId;

    /**
     * 合同外部项目名称
     */
    @NotBlank(message = "合同外部项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String outProject;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String inProject;

    /**
     * 立项情况
     */
    @NotBlank(message = "立项情况不能为空",groups = {ADD.class, EDIT.class})
    private String approval;

    /**
     * 项目状态
     */
    @NotBlank(message = "项目状态不能为空",groups = {ADD.class, EDIT.class})
    private String projectStatus;

    /**
     * 派工单号
     */
    @NotBlank(message = "派工单号不能为空",groups = {ADD.class, EDIT.class})
    private String taskingNum;

    /**
     * 工作界面
     */
    @NotBlank(message = "工作界面不能为空",groups = {ADD.class, EDIT.class})
    private String workScreen;

    /**
     * 表名
     */
    @NotBlank(message = "表名",groups = {ADD.class, EDIT.class})
    private String tableName;

    /**
     * 权限类型
     */
    @NotNull(message = "权限类型不能为空",groups = {ADD.class, EDIT.class})
    private PermissionType permissionType;

    /**
     * 权限范围
     */
    @NotBlank(message = "权限范围不能为空",groups = {ADD.class, EDIT.class})
    private String permissionScope;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

}