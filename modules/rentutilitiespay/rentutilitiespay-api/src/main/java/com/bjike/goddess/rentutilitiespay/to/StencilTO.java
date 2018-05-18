package com.bjike.goddess.rentutilitiespay.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
* 模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class StencilTO extends BaseTO {
    /**
     * 通报对象
     */
    @NotBlank(message = "通报对象不能为空",groups = {ADD.class, EDIT.class})
    private String  notificationPeople;

    /**
     * 通报对象邮箱
     */
    @NotBlank(message = "通报对象邮箱不能为空",groups = {ADD.class, EDIT.class})
    private String  notificationObject;

    /**
     * 通报对象时间
     */
    @NotBlank(message = "通报对象时间不能为空",groups = {ADD.class, EDIT.class})
    private String notificationDate;

    /**
     * 通报内容
     */
    @NotBlank(message = "通报内容不能为空",groups = {ADD.class, EDIT.class})
    private String  content;

    /**
     * 通报模板
     */
    @NotBlank(message = "通报模板不能为空",groups = {ADD.class, EDIT.class})
    private String  stencil;

    /**
     * 例子
     */
    @NotBlank(message = "例子不能为空",groups = {ADD.class, EDIT.class})
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