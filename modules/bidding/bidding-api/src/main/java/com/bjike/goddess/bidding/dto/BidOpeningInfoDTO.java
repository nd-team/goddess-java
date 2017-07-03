package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.persistence.Column;

/**
 * 开标信息数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.803 ]
 * @Description: [ 开标信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoDTO extends BaseDTO {
    /**
     * 竞争公司
     */
    private String competitive;
    /**
     * 项目名称
     */
    private String projectName;



    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}