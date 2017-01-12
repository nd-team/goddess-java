package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:45]
 * @Description: [部门数据传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentDTO extends BaseDTO {
    private String parent_id; //以此节点作为父亲节点

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
