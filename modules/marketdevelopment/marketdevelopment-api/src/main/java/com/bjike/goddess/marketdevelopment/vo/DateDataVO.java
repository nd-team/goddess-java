package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.DateType;

/**
 * 日期数据表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DateDataVO {

    /**
     * id
     */
    private String id;
    /**
     * 周期id
     */
    private String cycleId;

    /**
     * 日期
     */
    private String date;

    /**
     * 类型
     */
    private DateType dateType;

    /**
     * 表头集合
     */
    private FilesDataVO filesDataVO;


    /**
     * 合计
     */
    private String total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public FilesDataVO getFilesDataVO() {
        return filesDataVO;
    }

    public void setFilesDataVO(FilesDataVO filesDataVO) {
        this.filesDataVO = filesDataVO;
    }
}