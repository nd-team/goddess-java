package com.bjike.goddess.progressmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.enums.HeadType;

/**
 * 进度节点表头业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadBO extends BaseBO {

    /**
     * 表头名
     */
    private String headName;

    /**
     * 所属节点
     */
    private ProgressNodeBO progressNodeBO;

    /**
     * 类型
     */
    private HeadType headType;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 备注
     */
    private String remark;

    /**
     * 顺序
     */
    private Integer sortIndex;

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public ProgressNodeBO getProgressNodeBO() {
        return progressNodeBO;
    }

    public void setProgressNodeBO(ProgressNodeBO progressNodeBO) {
        this.progressNodeBO = progressNodeBO;
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