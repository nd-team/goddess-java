package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.DateType;

/**
 * 日期数据业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DateDataBO extends BaseBO {

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

//    private List<FilesDataBO> filesDataVOs;

    /**
     * 表头
     */
    private FilesDataBO filesDataVO;

    /**
     * 合计
     */
    private String total;


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

    public FilesDataBO getFilesDataVO() {
        return filesDataVO;
    }

    public void setFilesDataVO(FilesDataBO filesDataVO) {
        this.filesDataVO = filesDataVO;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}