package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 发送邮件业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmailBO extends BaseBO {

    /**
     * 发送类型
     */
    private String sendType;


    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String founder;

    /**
     * 发送对象
     */
    private String sendObject;


    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }
}