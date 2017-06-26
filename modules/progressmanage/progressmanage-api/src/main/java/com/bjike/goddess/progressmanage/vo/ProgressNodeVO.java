package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.progressmanage.entity.ProgressTable;

/**
 * 进度节点表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressNodeVO {

    /**
     * id
     */
    private String id;
    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 顺序
     */
    private Integer sortIndex;

    /**
     * 状态
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
}