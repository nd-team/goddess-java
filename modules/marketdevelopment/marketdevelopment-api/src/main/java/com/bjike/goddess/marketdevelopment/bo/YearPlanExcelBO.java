//package com.bjike.goddess.marketdevelopment.bo;
//
//import com.bjike.goddess.common.api.bo.BaseBO;
//import com.bjike.goddess.common.utils.excel.ExcelHeader;
//
///**
// * 年计划业务传输对象
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 05:57 ]
// * @Description: [ 年计划业务传输对象 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public class YearPlanExcelBO extends BaseBO {
//
//    /**
//     * 年份
//     */
//    @ExcelHeader(name = "年份")
//    private Integer year;
//
//    /**
//     * 业务类型
//     */
//    @ExcelHeader(name = "业务类型")
//    private String type;
//
//    /**
//     * 工作量权重
//     */
//    @ExcelHeader(name = "工作量权重")
//    private Double workloadWeight;
//
//    /**
//     * 业务方向科目
//     */
//    @ExcelHeader(name = "业务方向科目")
//    private String course;
//
//    /**
//     * 可发展对象
//     */
//    @ExcelHeader(name = "可发展对象")
//    private Integer development;
//
//    /**
//     * 同一类业务类型中占比
//     */
//    @ExcelHeader(name = "同一类业务类型中占比")
//    private Double businessAccounted;
//
//    /**
//     * 各业务科目年度占比
//     */
//    @ExcelHeader(name = "各业务科目年度占比")
//    private Double courseAccounted;
//
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
//    public Double getWorkloadWeight() {
//        return workloadWeight;
//    }
//
//    public void setWorkloadWeight(Double workloadWeight) {
//        this.workloadWeight = workloadWeight;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public Integer getDevelopment() {
//        return development;
//    }
//
//    public void setDevelopment(Integer development) {
//        this.development = development;
//    }
//
//    public Double getBusinessAccounted() {
//        return businessAccounted;
//    }
//
//    public void setBusinessAccounted(Double businessAccounted) {
//        this.businessAccounted = businessAccounted;
//    }
//
//    public Double getCourseAccounted() {
//        return courseAccounted;
//    }
//
//    public void setCourseAccounted(Double courseAccounted) {
//        this.courseAccounted = courseAccounted;
//    }
////
////    public Double getQuota() {
////        return quota;
////    }
////
////    public void setQuota(Double quota) {
////        this.quota = quota;
////    }
//}