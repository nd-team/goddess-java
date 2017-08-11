package com.bjike.goddess.analysis.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 收入成本分析数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeCostAnalysisDTO extends BaseDTO {
    /**
     * 年份
     */
    @NotNull(message = "年份不能为空", groups = {VoucherGenerateDTO.TestList.class})
    private Integer year;
    /**
     * 月份
     */
    @NotNull(message = "月份不能为空", groups = {VoucherGenerateDTO.TestList.class})
    private Integer month;
    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {VoucherGenerateDTO.TestList.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空", groups = {VoucherGenerateDTO.TestList.class})
    private String projectGroup;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}