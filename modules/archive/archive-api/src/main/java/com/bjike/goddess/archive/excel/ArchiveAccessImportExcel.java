package com.bjike.goddess.archive.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 档案调阅业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArchiveAccessImportExcel extends BaseBO {

//    /**
//     * 姓名
//     */
//    @ExcelHeader(name = "姓名")
//    private String username;

    /**
     * 开始日期
     */
    @ExcelHeader(name = "开始日期", notNull = true)
    private LocalDate start;

    /**
     * 结束日期
     */
    @ExcelHeader(name = "结束日期", notNull = true)
    private LocalDate end;

    /**
     * 调阅人
     */
    @ExcelHeader(name = "调阅人", notNull = true)
    private String access;

    /**
     * 原因
     */
    @ExcelHeader(name = "原因", notNull = true)
    private String reason;

//    /**
//     * 福利模块
//     */
//    @ExcelHeader(name = "福利模块")
//    private String welfare;
//
//    /**
//     * 福利审核意见
//     */
//    @ExcelHeader(name = "福利审核意见")
//    private String welfareOpinion;
//
//    /**
//     * 总经办
//     */
//    @ExcelHeader(name = "总经办")
//    private String manage;
//
//    /**
//     * 总经办审核意见
//     */
//    @ExcelHeader(name = "总经办审核意见")
//    private String manageOpinion;
//
//    /**
//     * 审核状态
//     */
//    @ExcelHeader(name = "审核状态")
//    private String audit;

//    /**
//     * 是否到期
//     */
//    @ExcelHeader(name = "是否到期")
//    private String overdue;


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }


    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}