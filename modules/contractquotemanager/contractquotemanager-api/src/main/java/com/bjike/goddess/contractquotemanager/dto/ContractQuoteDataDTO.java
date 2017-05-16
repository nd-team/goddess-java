package com.bjike.goddess.contractquotemanager.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * 合同单价资料信息数据传输对象
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.313 ]
 * @Description: [ 合同单价资料信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractQuoteDataDTO extends BaseDTO {

    public interface Collect{}

    /**
     * 地区
     */
    @Size(min = 1, groups = {ContractQuoteDataDTO.Collect.class}, message = "地区不能为空,最小数目必须为1")
    private String[] area;
    /**
     * 用户名称
     */
    @NotBlank(groups = {ContractQuoteDataDTO.Collect.class}, message = "地区不能为空")
    private String customerName;

    /**
     * 开始日期
     */
    @NotBlank(groups = {ContractQuoteDataDTO.Collect.class}, message = "开始日期不能为空")
    private String startDate;
    /**
     * 结束日期
     */
    @NotBlank(groups = {ContractQuoteDataDTO.Collect.class}, message = "结束日期不能为空")
    private String endDate;

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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