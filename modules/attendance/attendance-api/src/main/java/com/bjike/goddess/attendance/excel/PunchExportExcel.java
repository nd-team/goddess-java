package com.bjike.goddess.attendance.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 打卡导出
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchExportExcel extends BaseBO {

    /**
     * 日期
     */
    @ExcelHeader(name = "日期", notNull = true)
//    private LocalDate date;
    private String date;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 打卡时间
     */
    @ExcelHeader(name = "打卡时间", notNull = true)
//    private LocalDateTime punchTime;
    private String punchTime;

    /**
     * 打卡地点
     */
    @ExcelHeader(name = "打卡地点")
    private String area;

    /**
     * 打卡来源
     */
    @ExcelHeader(name = "打卡来源", notNull = true)
//    private PunchSource punchSource;
    private String punchSource;

//    /**
//     * 打卡父id
//     */
//    @ExcelHeader(name = "打卡父id", notNull = true)
//    private String punchId;

    /**
     * 打卡类型
     */
    @ExcelHeader(name = "打卡类型", notNull = true)
//    private PunchType punchType;
    private String punchType;


    /**
     * 打卡状态
     */
    @ExcelHeader(name = "打卡状态", notNull = true)
//    private PunchStatus punchStatus;
    private String punchStatus;

//    /**
//     * 父id
//     */
//    @ExcelHeader(name = "父id", notNull = true)
//    private String punchSonId;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(String punchStatus) {
        this.punchStatus = punchStatus;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPunchSource() {
        return punchSource;
    }

    public void setPunchSource(String punchSource) {
        this.punchSource = punchSource;
    }

    public String getPunchType() {
        return punchType;
    }

    public void setPunchType(String punchType) {
        this.punchType = punchType;
    }
}