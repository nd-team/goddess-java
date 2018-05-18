package com.bjike.goddess.dimission.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 离职办理节点情况数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SituationDTO extends BaseDTO {

    /**
     * 姓名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}