package com.bjike.goddess.businessproject.vo;

import com.bjike.goddess.businessproject.enums.CollectSendUnit;
import com.bjike.goddess.businessproject.enums.CollectUnit;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;
import java.util.Map;

/**
 * 商务项目合同邮件表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.880 ]
 * @Description: [ 商务项目合同邮件表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectEmailVO {

    /**
     * id
     */
    private String id;
    /**
     * 行业
     */
    private String type;
    /**
     * 汇总条件
     */
    private String condi;
    /**
     * 备注
     */
    private String remark;

    /**
     * 发送间隔
     */
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    private CollectUnit collectUnit;

    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 发送对象数组
     */
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
     * 汇总金额
     */
    private Double money;
    /**
     * 个数
     */
    private int counts;

    /**
     * 数据库枚举转换
     */
    private int enumConvert;
    /**
     * 业务类型汇总集合
     */
    private Object busTypeMap;

    /**
     * 合作方式汇总集合
     */
    private Object cooperWaysMap;


    /**
     * 合同属性汇总集合
     */
    private Object contractPropertyMap;

    /**
     * 立项情况汇总集合
     */
    private Object makeProjectMap;

    /**
     * 合同签订情况汇总集合
     */
    private Object signMap;

    /**
     * 派工单个数
     */
    private Integer dispatchProjectCount;

    /**
     * 已完工个数
     */
    private Integer complete;

    /**
     * 未完工个数
     */
    private Integer notComplete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondi() {
        return condi;
    }

    public void setCondi(String condi) {
        this.condi = condi;
    }

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

    public CollectSendUnit getCollectSendUnit() {
        return collectSendUnit;
    }

    public void setCollectSendUnit(CollectSendUnit collectSendUnit) {
        this.collectSendUnit = collectSendUnit;
    }

    public CollectUnit getCollectUnit() {
        return collectUnit;
    }

    public void setCollectUnit(CollectUnit collectUnit) {
        this.collectUnit = collectUnit;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public Object getBusTypeMap() {
        return busTypeMap;
    }

    public void setBusTypeMap(Object busTypeMap) {
        this.busTypeMap = busTypeMap;
    }

    public Object getCooperWaysMap() {
        return cooperWaysMap;
    }

    public void setCooperWaysMap(Object cooperWaysMap) {
        this.cooperWaysMap = cooperWaysMap;
    }

    public Object getContractPropertyMap() {
        return contractPropertyMap;
    }

    public void setContractPropertyMap(Object contractPropertyMap) {
        this.contractPropertyMap = contractPropertyMap;
    }

    public Object getMakeProjectMap() {
        return makeProjectMap;
    }

    public void setMakeProjectMap(Object makeProjectMap) {
        this.makeProjectMap = makeProjectMap;
    }

    public Object getSignMap() {
        return signMap;
    }

    public void setSignMap(Object signMap) {
        this.signMap = signMap;
    }

    public Integer getDispatchProjectCount() {
        return dispatchProjectCount;
    }

    public void setDispatchProjectCount(Integer dispatchProjectCount) {
        this.dispatchProjectCount = dispatchProjectCount;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Integer getNotComplete() {
        return notComplete;
    }

    public void setNotComplete(Integer notComplete) {
        this.notComplete = notComplete;
    }
    
    
}