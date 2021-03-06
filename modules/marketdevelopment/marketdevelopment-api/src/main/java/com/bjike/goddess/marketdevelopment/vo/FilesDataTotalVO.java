package com.bjike.goddess.marketdevelopment.vo;

/**
 * 合计表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 02:38 ]
 * @Description: [ 合计表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FilesDataTotalVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期id
     */
    private String dateDataId;

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