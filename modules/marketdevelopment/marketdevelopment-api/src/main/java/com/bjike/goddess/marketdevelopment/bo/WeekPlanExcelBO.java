//package com.bjike.goddess.marketdevelopment.bo;
//
//import com.bjike.goddess.common.api.bo.BaseBO;
//import com.bjike.goddess.common.utils.excel.ExcelHeader;
//
///**
// * 周计划业务传输对象
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 06:49 ]
// * @Description: [ 周计划业务传输对象 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public class WeekPlanExcelBO extends BaseBO {
//
//    /**
//     * 年份
//     */
//    @ExcelHeader(name = "年份")
//    private Integer year;
//
//    /**
//     * 月份
//     */
//    @ExcelHeader(name = "月份")
//    private String type;
//
//    /**
//     * 业务方向科目
//     */
//    @ExcelHeader(name = "业务方向科目")
//    private String course;
//
//    /**
//     * 总任务量
//     */
//    @ExcelHeader(name = "总任务量")
//    private Double monthTotal;
//
//    /**
//     * 周期
//     */
//    @ExcelHeader(name = "周期")
//    private String cycle;
//
//    /**
//     * 各周工作量在整月占比
//     */
//    @ExcelHeader(name = "各周工作量在整月占比")
//    private Double accounted;
//
//    /**
//     * 查询
//     */
//    @ExcelHeader(name = "查询")
//    private Double inquire;
//
//    /**
//     * 了解
//     */
//    @ExcelHeader(name = "了解")
//    private Double know;
//
//    /**
//     * 接触
//     */
//    @ExcelHeader(name = "接触")
//    private Double contact;
//
//    /**
//     * 拜访
//     */
//    @ExcelHeader(name = "拜访")
//    private Double visit;
//
//    /**
//     * 活动
//     */
//    @ExcelHeader(name = "活动")
//    private Double activity;
//
//    /**
//     * 合计
//     */
//    @ExcelHeader(name = "合计")
//    private Double total;
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public Double getMonthTotal() {
//        return monthTotal;
//    }
//
//    public void setMonthTotal(Double monthTotal) {
//        this.monthTotal = monthTotal;
//    }
//
//    public Double getAccounted() {
//        return accounted;
//    }
//
//    public void setAccounted(Double accounted) {
//        this.accounted = accounted;
//    }
//
//    public Double getInquire() {
//        return inquire;
//    }
//
//    public void setInquire(Double inquire) {
//        this.inquire = inquire;
//    }
//
//    public Double getKnow() {
//        return know;
//    }
//
//    public void setKnow(Double know) {
//        this.know = know;
//    }
//
//    public Double getContact() {
//        return contact;
//    }
//
//    public void setContact(Double contact) {
//        this.contact = contact;
//    }
//
//    public Double getVisit() {
//        return visit;
//    }
//
//    public void setVisit(Double visit) {
//        this.visit = visit;
//    }
//
//    public Double getActivity() {
//        return activity;
//    }
//
//    public void setActivity(Double activity) {
//        this.activity = activity;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//
//    public Integer getYear() {
//        return year;
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getCycle() {
//        return cycle;
//    }
//
//    public void setCycle(String cycle) {
//        this.cycle = cycle;
//    }
//}