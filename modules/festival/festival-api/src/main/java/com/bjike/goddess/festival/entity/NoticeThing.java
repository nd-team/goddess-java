package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 注意事项
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_noticething")
public class NoticeThing extends BaseEntity {

    /**
     * 注意事项名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '注意事项名'")
    private String name;

    /**
     * 描述
     */
    @Column(name = "describeDetail",  columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String describeDetail;

    /**
     * 法定节假日放假方案
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "holidayProgramme_id",  columnDefinition = "VARCHAR(36)   COMMENT '法定节假日放假方案'")
    private HolidayProgramme holidayProgramme;


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

    public HolidayProgramme getHolidayProgramme() {
        return holidayProgramme;
    }

    public void setHolidayProgramme(HolidayProgramme holidayProgramme) {
        this.holidayProgramme = holidayProgramme;
    }
}