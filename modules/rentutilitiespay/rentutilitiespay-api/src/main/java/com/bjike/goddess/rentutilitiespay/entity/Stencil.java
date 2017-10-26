package com.bjike.goddess.rentutilitiespay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;
import java.time.LocalDate;


/**
* 模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "rentutilitiespay_stencil")
public class Stencil extends BaseEntity {
    /**
     * 通报对象
     */
    @Column(name = "notificationPeople",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '通报对象'"  )
    private String  notificationPeople;

    /**
     * 通报对象邮箱
     */
    @Column(name = "notificationObject",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '通报对象邮箱'"  )
    private String  notificationObject;

    /**
     * 通报对象时间
     */
    @Column(name = "notificationDate",nullable = false,columnDefinition = "DATE   COMMENT '通报对象时间'"  )
    private LocalDate notificationDate;

    /**
     * 通报内容
     */
    @Column(name = "content",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '通报内容'"  )
    private String  content;

    /**
     * 通报模板
     */
    @Column(name = "stencil",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '通报模板'"  )
    private String  stencil;

    /**
     * 例子
     */
    @Column(name = "example",nullable = false,columnDefinition = "VARCHAR(255)   COMMENT '例子'"  )
    private String  example;

    public String getNotificationPeople() {
        return notificationPeople;
    }

    public void setNotificationPeople(String notificationPeople) {
        this.notificationPeople = notificationPeople;
    }

    public String getNotificationObject() {
        return notificationObject;
    }

    public void setNotificationObject(String notificationObject) {
        this.notificationObject = notificationObject;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDate notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStencil() {
        return stencil;
    }

    public void setStencil(String stencil) {
        this.stencil = stencil;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}