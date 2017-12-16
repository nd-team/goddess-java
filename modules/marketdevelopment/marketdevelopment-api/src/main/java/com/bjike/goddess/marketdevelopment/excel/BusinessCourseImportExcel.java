package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 业务方向导入
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessCourseImportExcel extends BaseBO {

//    /**
//     * 业务方向编号
//     */
//    private String businessNum;

    /**
     * 业务方向分类
     */
    @ExcelHeader(name = "业务方向分类", notNull = true)
    private String businessType;

//    /**
//     * 科目编号
//     */
//    private String subjectNum;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目", notNull = true)
    private String course;

    /**
     * 所属类别
     */
    @ExcelHeader(name = "所属类别", notNull = true)
    private String type;

    /**
     * 可以做的具体业务
     */
    private String business;

//    /**
//     * 状态
//     */
//    private Status status;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}