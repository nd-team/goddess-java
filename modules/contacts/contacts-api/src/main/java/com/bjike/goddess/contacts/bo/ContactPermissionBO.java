package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.contacts.enums.ContactPermissionType;

import java.util.List;

/**
 * 客户权限配置业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContactPermissionBO extends BaseBO {

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
    private ContactPermissionType type;
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
    private List<ContactOperateBO> cusOperateBO;

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

    public ContactPermissionType getType() {
        return type;
    }

    public void setType(ContactPermissionType type) {
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

    public List<ContactOperateBO> getCusOperateBO() {
        return cusOperateBO;
    }

    public void setCusOperateBO(List<ContactOperateBO> cusOperateBO) {
        this.cusOperateBO = cusOperateBO;
    }
}