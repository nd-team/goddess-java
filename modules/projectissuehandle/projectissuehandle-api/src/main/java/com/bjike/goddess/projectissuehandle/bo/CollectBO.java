package com.bjike.goddess.projectissuehandle.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Map;

/**
 * 汇总项目执行中的问题受理及处理结果业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectBO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 合同外部项目名称
     */
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 人员类
     */
    private Object person;
    /**
     * 进度类
     */
    private Object progress;
    /**
     * 交付类
     */
    private Object deliver;
    /**
     * 设备类
     */
    private Object device;
    /**
     * 初级
     */
    private Object elementary;
    /**
     * 中级
     */
    private Object emergency;
    /**
     * 紧急
     */
    private Object intermediate;
    /**
     * 运营商
     */
    private Object operator;
    /**
     * 厂家
     */
    private Object vender;
    /**
     * 集成商
     */
    private Object intergrator;
    /**
     * 政府机关
     */
    private Object goverment;
    /**
     * 内部员工
     */
    private Object innerstaff;
    /**
     * 完成
     */
    private Object complete;
    /**
     * 未完成
     */
    private Object uncomplete;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExternalContractProjectName() {
        return externalContractProjectName;
    }

    public void setExternalContractProjectName(String externalContractProjectName) {
        this.externalContractProjectName = externalContractProjectName;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public Object getPerson() {
        return person;
    }

    public void setPerson(Object person) {
        this.person = person;
    }

    public Object getProgress() {
        return progress;
    }

    public void setProgress(Object progress) {
        this.progress = progress;
    }

    public Object getDeliver() {
        return deliver;
    }

    public void setDeliver(Object deliver) {
        this.deliver = deliver;
    }

    public Object getDevice() {
        return device;
    }

    public void setDevice(Object device) {
        this.device = device;
    }

    public Object getElementary() {
        return elementary;
    }

    public void setElementary(Object elementary) {
        this.elementary = elementary;
    }

    public Object getEmergency() {
        return emergency;
    }

    public void setEmergency(Object emergency) {
        this.emergency = emergency;
    }

    public Object getIntermediate() {
        return intermediate;
    }

    public void setIntermediate(Object intermediate) {
        this.intermediate = intermediate;
    }

    public Object getOperator() {
        return operator;
    }

    public void setOperator(Object operator) {
        this.operator = operator;
    }

    public Object getVender() {
        return vender;
    }

    public void setVender(Object vender) {
        this.vender = vender;
    }

    public Object getIntergrator() {
        return intergrator;
    }

    public void setIntergrator(Object intergrator) {
        this.intergrator = intergrator;
    }

    public Object getGoverment() {
        return goverment;
    }

    public void setGoverment(Object goverment) {
        this.goverment = goverment;
    }

    public Object getInnerstaff() {
        return innerstaff;
    }

    public void setInnerstaff(Object innerstaff) {
        this.innerstaff = innerstaff;
    }

    public Object getComplete() {
        return complete;
    }

    public void setComplete(Object complete) {
        this.complete = complete;
    }

    public Object getUncomplete() {
        return uncomplete;
    }

    public void setUncomplete(Object uncomplete) {
        this.uncomplete = uncomplete;
    }
}