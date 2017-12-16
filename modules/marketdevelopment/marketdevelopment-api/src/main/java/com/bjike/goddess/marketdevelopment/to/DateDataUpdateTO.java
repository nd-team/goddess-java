package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

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
public class DateDataUpdateTO extends BaseTO {

    /**
     * 日期id
     */
    private String dateDataId;

    /**
     * 表头数据
     */
    List<FilesDataTO> filesDataTOs;

    public String getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(String dateDataId) {
        this.dateDataId = dateDataId;
    }

    public List<FilesDataTO> getFilesDataTOs() {
        return filesDataTOs;
    }

    public void setFilesDataTOs(List<FilesDataTO> filesDataTOs) {
        this.filesDataTOs = filesDataTOs;
    }
}