package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 标书资料数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.308 ]
 * @Description: [ 标书资料数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TenderInfoDTO extends BaseDTO {
    /**
     * 项目名称
     */
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}