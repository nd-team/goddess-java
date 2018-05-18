package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 合计
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 02:38 ]
 * @Description: [ 合计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FilesDataTotalTO extends BaseTO {

    /**
     * 日期id
     */
    private String dateDataId;

    /**
     * 合计
     */
    private String total;


    public String getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(String dateDataId) {
        this.dateDataId = dateDataId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}