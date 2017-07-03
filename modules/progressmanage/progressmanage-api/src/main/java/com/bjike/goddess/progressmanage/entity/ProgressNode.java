package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 进度节点
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_progressnode", uniqueConstraints = {@UniqueConstraint(columnNames = {"nodeName", "project_id"}),
        @UniqueConstraint(columnNames = {"sortIndex", "project_id"})})
public class ProgressNode extends BaseEntity {

    /**
     * 所属项目
     */
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '所属项目'")
    private ProjectInfo project;

    /**
     * 表头对应值
     */
    @OneToMany(cascade ={CascadeType.REMOVE} , fetch = FetchType.EAGER, mappedBy = "progressNode")
    private Set<NodeHead> nodeHeadSet = new HashSet<NodeHead>();


    /**
     * 节点名称
     */
    @Column(name = "nodeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点名称'")
    private String nodeName;

    /**
     * 创建人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createUser;

    /**
     * 修改人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String updateUser;

    /**
     * 顺序
     */
    @Column(name = "sortIndex", nullable = false, columnDefinition = "INT(11)   COMMENT '顺序'")
    private Integer sortIndex;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'")
    private Status status;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<NodeHead> getNodeHeadSet() {
        return nodeHeadSet;
    }

    public void setNodeHeadSet(Set<NodeHead> nodeHeadSet) {
        this.nodeHeadSet = nodeHeadSet;
    }
}