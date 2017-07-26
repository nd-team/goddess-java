package com.bjike.goddess.contractcommunicat.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 客户邮件发送定制数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.878 ]
 * @Description: [ 客户邮件发送定制数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailDTO extends BaseDTO {

    public interface TestArea {
    }

    public interface TestFirstCompany {
    }

    /**
     * 地区数组
     */
    @NotNull(groups ={CollectEmailDTO.TestArea.class} , message = "地区数组不能为空")
    private String[] areas;

    /**
     * 甲方数组
     */
    @NotNull(groups ={CollectEmailDTO.TestFirstCompany.class} , message = "甲方数组不能为空")
    private String[] firstCompany;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String[] firstCompany) {
        this.firstCompany = firstCompany;
    }
}