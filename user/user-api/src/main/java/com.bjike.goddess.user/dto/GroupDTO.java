package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 用户组数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:45]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupDTO extends BaseDTO {
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
