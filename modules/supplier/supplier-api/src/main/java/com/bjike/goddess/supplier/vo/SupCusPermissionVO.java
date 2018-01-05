package com.bjike.goddess.supplier.vo;

import com.bjike.goddess.supplier.enums.SupCusPermissionType;

import java.util.List;

/**
 * 客户权限配置表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupCusPermissionVO {
    /**
     * id
     */
    private String id;

    /**
     * 辅助id
     */
    private String idFlag;
    /**
     * 描述
     */
    private String description;

    /**
     * 操作对象
     */
    private String operator;
    /**
     * 类型
     */
    private SupCusPermissionType type;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * list返回的操作对象
     */
    private List<SupCusOperateVO> cusOperateVO;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public SupCusPermissionType getType() {
        return type;
    }

    public void setType(SupCusPermissionType type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<SupCusOperateVO> getCusOperateVO() {
        return cusOperateVO;
    }

    public void setCusOperateVO(List<SupCusOperateVO> cusOperateVO) {
        this.cusOperateVO = cusOperateVO;
    }
}
