package com.bjike.goddess.rentutilitiespay.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 模板业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class StencilBO extends BaseBO {
    /**
     * 通报对象
     */
    private String  notificationPeople;

    /**
     * 通报对象邮箱
     */
    private String  notificationObject;

    /**
     * 通报对象时间
     */
    private String notificationDate;

    /**
     * 通报内容
     */
    private String  content;

    /**
     * 通报模板
     */
    private String  stencil;

    /**
     * 例子
     */
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

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
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