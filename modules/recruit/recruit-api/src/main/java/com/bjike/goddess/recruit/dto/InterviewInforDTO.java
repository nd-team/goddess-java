package com.bjike.goddess.recruit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InterviewInforDTO extends BaseDTO {
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 岗位
     */
    private String position;
    /**
     * 姓名
     */
    private String name;
    /**
     * 初试负责人
     */
    private String firstTestPrincipal;
    /**
     * 复试负责人
     */
    private String secondTestPrincipal;
    /**
     * 是否入职
     */
    private Boolean whetherEntry;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstTestPrincipal() {
        return firstTestPrincipal;
    }

    public void setFirstTestPrincipal(String firstTestPrincipal) {
        this.firstTestPrincipal = firstTestPrincipal;
    }

    public String getSecondTestPrincipal() {
        return secondTestPrincipal;
    }

    public void setSecondTestPrincipal(String secondTestPrincipal) {
        this.secondTestPrincipal = secondTestPrincipal;
    }

    public Boolean getWhetherEntry() {
        return whetherEntry;
    }

    public void setWhetherEntry(Boolean whetherEntry) {
        this.whetherEntry = whetherEntry;
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
}
