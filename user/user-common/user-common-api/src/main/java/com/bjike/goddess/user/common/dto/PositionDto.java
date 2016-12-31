package com.bjike.goddess.user.common.dto;

import com.bjike.goddess.dbs.jpa.dto.BaseDto;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:45]
 * @Description: [用户职位数据传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class PositionDto extends BaseDto {
    private String parent_id; //以此节点作为父亲节点

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
