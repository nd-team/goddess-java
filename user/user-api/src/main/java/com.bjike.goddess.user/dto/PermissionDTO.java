package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 认证资源数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionDTO extends BaseDTO {
    /**
     * 父亲节点
     */
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
