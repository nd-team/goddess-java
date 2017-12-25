package com.bjike.goddess.secure.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 挂靠数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AttachedDTO extends BaseDTO {
    /**
     * 挂靠人姓名
     */
    private String attachedName;
    /**
     * 挂靠城市
     */
    private String city;
    /**
     * 是否挂靠到期
     */
    private Boolean affiliated;

    public String getAttachedName() {
        return attachedName;
    }

    public void setAttachedName(String attachedName) {
        this.attachedName = attachedName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }
}