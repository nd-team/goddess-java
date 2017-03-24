package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 开标信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.802 ]
 * @Description: [ 开标信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BidOpeningInfoBO extends BaseBO {

    /**
     * 招标编号
     */
    private String tenderNumber;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 开标时间
     */
    private String bidOpeningTime;

    /**
     * 委托人
     */
    private String principal;

    /**
     * 记录人
     */
    private String recorder;

    /**
     * 竞争公司
     */
    private String competitive;

    /**
     * 地市
     */
    private String cities;

    /**
     * 竞争公司报价
     */
    private String competitivePrice;

    /**
     * 比率(%)
     */
    private String ratio;
    /**
     * 个数
     */
    private int counts;
    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 数据库枚举转换
     */
    private int enumConvert;
    /**
     * 地区汇总集合
     */
    private List<Map<String, String>> areaMap;

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

    public List<Map<String, String>> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(List<Map<String, String>> areaMap) {
        this.areaMap = areaMap;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(String competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}