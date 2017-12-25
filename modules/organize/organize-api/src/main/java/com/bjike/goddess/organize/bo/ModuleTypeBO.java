package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 模块类型业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleTypeBO extends BaseBO {

    /**
     * 模块
     */
    private String module;

    /**
     * 项目组/部门
     */
    private String depart;

    /**
     * 是否为职能模块
     */
    private Boolean position;

    /**
     * 状态
     */
    private Status status;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Boolean getPosition() {
        return position;
    }

    public void setPosition(Boolean position) {
        this.position = position;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}