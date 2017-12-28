package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.MarPermissionType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 市场计划进度管理权限配置
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarPermissionTO extends BaseTO {

    /**
     * 辅助id
     */
    private String idFlag;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 操作对象数组
     */
    private String[] operators;

    /**
     * 操作对象
     */
    private String operator;
    /**
     * 类型
     */
    private MarPermissionType type;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


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

    public MarPermissionType getType() {
        return type;
    }

    public void setType(MarPermissionType type) {
        this.type = type;
    }

    public String[] getOperators() {
        return operators;
    }

    public void setOperators(String[] operators) {
        this.operators = operators;
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
}