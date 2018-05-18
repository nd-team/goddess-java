package com.bjike.goddess.archive.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 人员资质数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonnelQualificationDTO extends BaseDTO {

    /**
     * 姓名
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}