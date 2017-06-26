package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 客户邮件发送定制业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.877 ]
 * @Description: [ 客户邮件发送定制业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusEmailTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 会议编号
     */
    @NotBlank(groups = {CusEmailTO.TestAdd.class} , message = "会议编号不能为空")
    private String meetNumber;

    /**
     * 层面（公司层面/模块）
     */
    @NotBlank(groups = {CusEmailTO.TestAdd.class} , message = "层面（公司层面/模块）不能为空")
    private String meetLevel;



    /**
     * 发送对象
     */
    @NotNull(groups = {CusEmailTO.TestAdd.class} , message = "层面（公司层面/模块）不能为空")
    private String[] sendObject;



    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public String getMeetLevel() {
        return meetLevel;
    }

    public void setMeetLevel(String meetLevel) {
        this.meetLevel = meetLevel;
    }

    public String[] getSendObject() {
        return sendObject;
    }

    public void setSendObject(String[] sendObject) {
        this.sendObject = sendObject;
    }

}