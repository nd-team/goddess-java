package com.bjike.goddess.archive.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 人员简历信息数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ResumeInfoDTO extends BaseDTO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 部门
     */
    private String project;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}