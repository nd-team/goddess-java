package com.bjike.goddess.market.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.market.enums.MarketCollectUnit;
import com.bjike.goddess.market.enums.MarketSendUnit;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;
import java.util.Map;

/**
 * 市场邮件发送定制业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.877 ]
 * @Description: [ 市场邮件发送定制业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketEmailBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

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
    private MarketSendUnit marketSendUnit;

    /**
     * 汇总间隔
     */
    private MarketCollectUnit marketCollectUnit;

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
     * 是否为有效信息(0是，1否)
     */
    private Boolean effective;

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    /**
     * 个数
     */
    private int counts;

    /**
     * 数据库枚举转换
     */
    private int enumConvert;

    /**
     * 地区汇总集合
     */
    private List<Map<String, String>> areaMap;

    /**
     * 行业市场级别汇总集合
     */
    private List<Map<String, String>> workMap;

    /**
     * 项目性质市场类别汇总集合
     */
    private List<Map<String, String>> natureMap;

    /**
     * 项目级别市场状态汇总集合
     */
    private List<Map<String, String>> scaleMap;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public MarketSendUnit getMarketSendUnit() {
        return marketSendUnit;
    }

    public void setMarketSendUnit(MarketSendUnit customerSendUnit) {
        this.marketSendUnit = marketSendUnit;
    }

    public MarketCollectUnit getMarketCollectUnit() {
        return marketCollectUnit;
    }

    public void setMarketCollectUnit(MarketCollectUnit customerCollectUnit) {
        this.marketCollectUnit = marketCollectUnit;
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

    public int getEnumConvert() {
        return enumConvert;
    }

    public void setEnumConvert(int enumConvert) {
        this.enumConvert = enumConvert;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<Map<String, String>> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(List<Map<String, String>> areaMap) {
        this.areaMap = areaMap;
    }

    public List<Map<String, String>> getWorkMap() {
        return workMap;
    }

    public void setWorkMap(List<Map<String, String>> workMap) {
        this.workMap = workMap;
    }

    public List<Map<String, String>> getNatureMap() {
        return natureMap;
    }

    public void setNatureMap(List<Map<String, String>> natureMap) {
        this.natureMap = natureMap;
    }

    public List<Map<String, String>> getScaleMap() {
        return scaleMap;
    }

    public void setScaleMap(List<Map<String, String>> scaleMap) {
        this.scaleMap = scaleMap;
    }
}