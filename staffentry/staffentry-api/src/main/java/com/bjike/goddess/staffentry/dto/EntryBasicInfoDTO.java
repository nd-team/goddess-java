package com.bjike.goddess.staffentry.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 入职基本信息数据传输
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 11:55]
 * @Description: [入职基本信息数据传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EntryBasicInfoDTO extends BaseDTO {

    /**
     * 用户有可能选择多个职位名称,或者选择全部职位
     */
    private String[] postNames;
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 职位名称
     */
    private String positionName;

    public String[] getPostNames() {
        return postNames;
    }

    public void setPostNames(String[] postNames) {
        this.postNames = postNames;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
