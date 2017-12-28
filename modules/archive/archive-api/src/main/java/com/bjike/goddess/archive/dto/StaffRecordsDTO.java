package com.bjike.goddess.archive.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 员工档案数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRecordsDTO extends BaseDTO {

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