package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateAuditImportBO extends BaseBO {

    /**
     * 审核时间
     */
    @ExcelHeader(name = "审核时间", notNull = false)
//    private LocalDate date;
    private String date1;

    /**
     * 审核人
     */
    @ExcelHeader(name = "审核人", notNull = true)
//    private String name;
    private String name1;

    /**
     * 审核意见
     */
    @ExcelHeader(name = "审核意见", notNull = false)
    private String advice;

    /**
     * 审核状态
     */
    @ExcelHeader(name = "审核状态", notNull = false)
    private AduitStatus aduitStatus;
//    private String aduitStatus;

//    /**
//     * 请假申请id
//     */
//    @ExcelHeader(name = "请假时间", notNull = true)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "vacate_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '请假申请id'")
//    private Vacate vacate;


    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }
}