package com.bjike.goddess.event.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 事件父表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 事件父表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FatherTO extends BaseTO {

    /**
     * 项目名称
     */
    private String project;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}