package com.bjike.goddess.voucher.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 记账凭证生成表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VoucherTemplateImportExcel implements Serializable{


    /**
     * 序号
     */
    @ExcelHeader(name = "序号",notNull = true)
    private String num;
    /**
     * 凭证字
     */
    @ExcelHeader(name = "凭证字",notNull = true)
    private String voucherWord;


    /**
     * 凭证日期
     */
    @ExcelHeader(name = "凭证日期",notNull = true)
    private LocalDate voucherDate;

    /**
     * 一级科目
     */
    @ExcelHeader(name = "一级科目",notNull = true)
    private String firstSubject;

    /**
     * 二级科目
     */
    @ExcelHeader(name = "二级科目",notNull = true)
    private String secondSubject;

    /**
     * 三级科目
     */
    @ExcelHeader(name = "三级科目")
    private String thirdSubject;

    /**
     * 借方金额
     */
    @ExcelHeader(name = "借方金额",notNull = true)
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    @ExcelHeader(name = "贷方金额",notNull = true)
    private Double loanMoney;

    /**
     * 摘要
     */
    @ExcelHeader(name = "摘要",notNull = true)
    private String sumary;

    /**
     * 来源
     */
    @ExcelHeader(name = "来源", notNull = true)
    private String source;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projectName;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String projectGroup;

    /**
     * 制单人
     */
    @ExcelHeader(name = "制单人",notNull = true)
    private String ticketer;

    /**
     * 票据数量
     */
    @ExcelHeader(name = "票据数量",notNull = true)
    private Double ticketNum;

    /**
     * 附件
     */
    @ExcelHeader(name = "附件" )
    private String extraFile;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVoucherWord() {
        return voucherWord;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

    public LocalDate getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(LocalDate voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getTicketer() {
        return ticketer;
    }

    public void setTicketer(String ticketer) {
        this.ticketer = ticketer;
    }

    public Double getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Double ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getExtraFile() {
        return extraFile;
    }

    public void setExtraFile(String extraFile) {
        this.extraFile = extraFile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}