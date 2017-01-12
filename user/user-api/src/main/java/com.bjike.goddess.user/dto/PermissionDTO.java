package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [认证资源数据传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PermissionDTO extends BaseDTO {
    private String parent_id; //以此节点作为父亲节点

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
