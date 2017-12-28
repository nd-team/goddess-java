package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.progressmanage.enums.HeadType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 进度节点表头
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadTO extends BaseTO {

    /**
     * 表头名
     */
    @NotBlank(message = "表头名不能为空", groups = {ADD.class, EDIT.class})
    private String headName;

    /**
     * 节点Id
     */
    @NotBlank(message = "节点Id不能为空", groups = {ADD.class, EDIT.class})
    private String nodeId;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private HeadType headType;

    /**
     * 是否必填
     */
    @NotNull(message = "是否必填不能为空", groups = {ADD.class, EDIT.class})
    private Boolean required;

    /**
     * 备注
     */
    private String remark;

    /**
     * 顺序
     */
    @NotNull(message = "顺序不能为空", groups = {ADD.class, EDIT.class})
    private Integer sortIndex;


    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public HeadType getHeadType() {
        return headType;
    }

    public void setHeadType(HeadType headType) {
        this.headType = headType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }
}