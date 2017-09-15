package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 发送邮件
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmailTO extends BaseTO {

    /**
     * 发送类型
     */
    private String sendType;

    /**
     * 发送内容
     */
    private String sendContent;

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
    private String[] sendObject;
    /**
     * 公邮
     */
    private String[] publicEmail;


    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
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

    public String[] getSendObject() {
        return sendObject;
    }

    public void setSendObject(String[] sendObject) {
        this.sendObject = sendObject;
    }

    public String[] getPublicEmail() {
        return publicEmail;
    }

    public void setPublicEmail(String[] publicEmail) {
        this.publicEmail = publicEmail;
    }
}