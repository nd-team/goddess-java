package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 节假日礼品标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_giftstandard")
public class GiftStandard extends BaseEntity {

    /**
     * 礼品名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '礼品名'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "describeDetail",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String describeDetail;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribeDetail() {
        return describeDetail;
    }

    public void setDescribeDetail(String describeDetail) {
        this.describeDetail = describeDetail;
    }


}