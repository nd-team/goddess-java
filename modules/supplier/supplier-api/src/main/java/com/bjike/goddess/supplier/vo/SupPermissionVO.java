package com.bjike.goddess.supplier.vo;

import com.bjike.goddess.supplier.enums.SupPermissionType;

import java.util.List;

/**
 * 供应商权限配置表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 10:43 ]
 * @Description: [ 供应商权限配置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupPermissionVO {

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
    private SupPermissionType type;


    /**
     * list返回的操作对象
     */
    private List<SupOperateVO> supOperateVO;


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

    public SupPermissionType getType() {
        return type;
    }

    public void setType(SupPermissionType type) {
        this.type = type;
    }

    public List<SupOperateVO> getSupOperateVO() {
        return supOperateVO;
    }

    public void setSupOperateVO(List<SupOperateVO> supOperateVO) {
        this.supOperateVO = supOperateVO;
    }
}