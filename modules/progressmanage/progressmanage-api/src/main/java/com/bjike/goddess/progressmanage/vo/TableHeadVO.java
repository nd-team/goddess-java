package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.enums.HeadType;

/**
 * 进度表表头表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadVO {

    /**
     * id
     */
    private String id;
    /**
     * 表头名
     */
    private String headName;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
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