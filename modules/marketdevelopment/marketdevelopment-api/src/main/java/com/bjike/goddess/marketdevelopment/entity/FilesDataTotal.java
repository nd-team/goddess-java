package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 合计
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 02:38 ]
 * @Description: [ 合计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_filesdatatotal")
public class FilesDataTotal extends BaseEntity {

    /**
     * 日期id
     */
    @Column(name = "dateDataId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '日期id'")
    private String dateDataId;

    /**
     * 合计
     */
    @Column(name = "total", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合计'")
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