package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.DateType;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 日期数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DateDataCopyTO extends BaseTO {

    /**
     * 日期
     */
    @NotNull(message = "日期不能为空", groups = {ADD.class, EDIT.class})
    private String date;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private DateType dateType;


    /**
     * 表头字段
     */
    List<FilesDataTO> filesDataTOs;


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

    public List<FilesDataTO> getFilesDataTOs() {
        return filesDataTOs;
    }

    public void setFilesDataTOs(List<FilesDataTO> filesDataTOs) {
        this.filesDataTOs = filesDataTOs;
    }
}