package com.bjike.goddess.fundrecords.excel;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundRecordExcel extends BaseTO {

    /**
     * 流水日期
     */
    @ExcelHeader(name = "流水日期", notNull = true)
    private String recordDate;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组", notNull = true)
    private String projectGroup;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String project;

    /**
     * 摘要
     */
    @ExcelHeader(name = "摘要", notNull = true)
    private String digest;

    /**
     * 收入
     */
    @ExcelHeader(name = "收入(借方)", notNull = true)
    private Double income;

    /**
     * 支出
     */
    @ExcelHeader(name = "支出(贷方)", notNull = true)
    private Double expenditure;
    /**
     * 金额
     */
    @ExcelHeader(name = "金额", notNull = true)
    private Double amount;

    /**
     * 数据来源
     */
    @ExcelHeader(name = "数据来源", notNull = true)
    private String dataSource;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}