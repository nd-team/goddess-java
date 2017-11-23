package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-20 14:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PhoneNumberBO extends BaseBO {
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系电话1
     */
    private String phoneNumberA;
    /**
     * 联系电话2
     */
    private String phoneNumberB;

    /**
     * 联系电话3
     */
    private String phoneNumberC;

    /**
     * 联系电话4
     */
    private String phoneNumberD;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneNumberA() {
        return phoneNumberA;
    }

    public void setPhoneNumberA(String phoneNumberA) {
        this.phoneNumberA = phoneNumberA;
    }

    public String getPhoneNumberB() {
        return phoneNumberB;
    }

    public void setPhoneNumberB(String phoneNumberB) {
        this.phoneNumberB = phoneNumberB;
    }

    public String getPhoneNumberC() {
        return phoneNumberC;
    }

    public void setPhoneNumberC(String phoneNumberC) {
        this.phoneNumberC = phoneNumberC;
    }

    public String getPhoneNumberD() {
        return phoneNumberD;
    }

    public void setPhoneNumberD(String phoneNumberD) {
        this.phoneNumberD = phoneNumberD;
    }
}

