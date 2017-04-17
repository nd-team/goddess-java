package com.bjike.goddess.archive.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 对外人员基本信息设置业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ForeignStaffingSetBO extends BaseBO {

    /**
     * 类型
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}