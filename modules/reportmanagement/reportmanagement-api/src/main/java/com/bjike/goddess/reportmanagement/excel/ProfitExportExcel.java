package com.bjike.goddess.reportmanagement.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;

/**
 * 利润表导出
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitExportExcel extends BaseBO {

    /**
     * 项目
     */
    @ExcelHeader(name = "项目",notNull = true)
    private String project;

    /**
     * 行次
     */
    @ExcelHeader(name = "行次",notNull = true)
    private Integer num;

    /**
     * 本月数
     */
    @ExcelHeader(name = "本月数",notNull = true)
    private Double currentMonthAmount;

    /**
     * 本年累计数
     */
    @ExcelHeader(name = "本年累计数",notNull = true)
    private Double currentYearAmount;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getCurrentMonthAmount() {
        return currentMonthAmount;
    }

    public void setCurrentMonthAmount(Double currentMonthAmount) {
        this.currentMonthAmount = currentMonthAmount;
    }

    public Double getCurrentYearAmount() {
        return currentYearAmount;
    }

    public void setCurrentYearAmount(Double currentYearAmount) {
        this.currentYearAmount = currentYearAmount;
    }
}