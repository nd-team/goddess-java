package com.bjike.goddess.problemhandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.problemhandle.enums.ProPermissionType;

import java.util.List;

/**
 * 问题权限配置业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProPermissionBO extends BaseBO {

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
    private ProPermissionType type;
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
    private List<ProOperateBO> proOperateBO;

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

    public ProPermissionType getType() {
        return type;
    }

    public void setType(ProPermissionType type) {
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

    public List<ProOperateBO> getProOperateBO() {
        return proOperateBO;
    }

    public void setProOperateBO(List<ProOperateBO> proOperateBO) {
        this.proOperateBO = proOperateBO;
    }
}