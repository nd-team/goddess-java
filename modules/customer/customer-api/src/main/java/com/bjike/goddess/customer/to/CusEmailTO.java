package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.CustomerCollectUnit;
import com.bjike.goddess.customer.enums.CustomerSendUnit;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 客户邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.879 ]
 * @Description: [ 客户邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusEmailTO extends BaseTO {


    public interface TestAdd{}

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    @NotNull(groups = {CusEmailTO.TestAdd.class}, message = "发送间隔不能为空且是double型数字")
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    @NotNull(groups = {CusEmailTO.TestAdd.class} , message = "发送单位不能为空")
    private CustomerSendUnit customerSendUnit;

    /**
     * 汇总间隔
     */
    private CustomerCollectUnit customerCollectUnit;



    /**
     * 发送对象数组
     */
    @NotNull(groups = {CusEmailTO.TestAdd.class} , message = "发送对象数组不能为空")
    private List<String> sendObjectList;

    /**
     * 上次发送时间
     */
    private String lastSendTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 创建人
     */
    private String createPersion;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 行业字符串数组
     */
    @NotNull(groups = {CusEmailTO.TestAdd.class} , message = "行业字符串数组不能为空")
    private String[] works;




    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSendNum() {
        return sendNum;
    }

    public void setSendNum(Double sendNum) {
        this.sendNum = sendNum;
    }

    public String getSendNumAndUnit() {
        return sendNumAndUnit;
    }

    public void setSendNumAndUnit(String sendNumAndUnit) {
        this.sendNumAndUnit = sendNumAndUnit;
    }

    public CustomerSendUnit getCustomerSendUnit() {
        return customerSendUnit;
    }

    public void setCustomerSendUnit(CustomerSendUnit customerSendUnit) {
        this.customerSendUnit = customerSendUnit;
    }

    public CustomerCollectUnit getCustomerCollectUnit() {
        return customerCollectUnit;
    }

    public void setCustomerCollectUnit(CustomerCollectUnit customerCollectUnit) {
        this.customerCollectUnit = customerCollectUnit;
    }


    public List<String> getSendObjectList() {
        return sendObjectList;
    }

    public void setSendObjectList(List<String> sendObjectList) {
        this.sendObjectList = sendObjectList;
    }

    public String getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(String lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String[] getWorks() {
        return works;
    }

    public void setWorks(String[] works) {
        this.works = works;
    }
}