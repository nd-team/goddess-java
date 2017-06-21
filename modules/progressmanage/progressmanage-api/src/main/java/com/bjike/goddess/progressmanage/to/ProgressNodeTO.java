package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 进度节点
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressNodeTO extends BaseTO {

    /**
     * 节点名称
     */
    @NotBlank(message = "节点名称不能为空", groups = {ADD.class, EDIT.class})
    private String nodeName;

    /**
     * 所属项目
     */
    @NotBlank(message = "所属项目不能为空", groups = {ADD.class, EDIT.class})
    private String projectId;

    /**
     * 顺序
     */
    @NotNull(message = "顺序不能为空", groups = {ADD.class, EDIT.class})
    private Integer sortIndex;


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }
}