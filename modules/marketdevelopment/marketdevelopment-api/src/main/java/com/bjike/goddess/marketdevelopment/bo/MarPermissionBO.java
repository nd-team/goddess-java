package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.MarPermissionType;

import java.util.List;

/**
 * 市场计划进度管理权限配置业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarPermissionBO extends BaseBO {

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
    private MarPermissionType type;

    /**
     * list返回的操作对象
     */
    private List<MarOperateBO> marOperateBOs;


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

    public List<MarOperateBO> getMarOperateBOs() {
        return marOperateBOs;
    }

    public void setMarOperateBOs(List<MarOperateBO> marOperateBOs) {
        this.marOperateBOs = marOperateBOs;
    }
}