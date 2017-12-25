package com.bjike.goddess.businessevaluate.bo;

import com.bjike.goddess.businessevaluate.enums.GroupUpType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 能力成长业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AbilityGrowUpBO extends BaseBO {

    /**
     * 能力成长类型
     */
    private GroupUpType groupUpType;

    /**
     * 一月
     */
    private Double januaryMoney;

    /**
     * 二月
     */
    private Double februaryMoney;

    /**
     * 三月
     */
    private Double marchMoney;

    /**
     * 四月
     */
    private Double aprilMoney;

    /**
     * 五月
     */
    private Double mayMoney;

    /**
     * 六月
     */
    private Double juneMoney;

    /**
     * 七月
     */
    private Double julyMoney;

    /**
     * 八月
     */
    private Double augustMoney;

    /**
     * 九月
     */
    private Double septemberMoney;

    /**
     * 十月
     */
    private Double octoberMoney;

    /**
     * 十一月
     */
    private Double novemberMoney;

    /**
     * 十二月
     */
    private Double decemberMoney;

    /**
     * 一月与上月差额
     */
    private Double januaryGapMoney;

    /**
     * 二月与上月差额
     */
    private Double februaryGapMoney;

    /**
     * 三月与上月差额
     */
    private Double marchGapMoney;

    /**
     * 四月与上月差额
     */
    private Double aprilGapMoney;

    /**
     * 五月与上月差额
     */
    private Double mayGapMoney;

    /**
     * 六月与上月差额
     */
    private Double juneGapMoney;

    /**
     * 七月与上月差额
     */
    private Double julyGapMoney;

    /**
     * 八月与上月差额
     */
    private Double augustGapMoney;

    /**
     * 九月与上月差额
     */
    private Double septemberGapMoney;

    /**
     * 十月与上月差额
     */
    private Double octoberGapMoney;

    /**
     * 十一月与上月差额
     */
    private Double novemberGapMoney;

    /**
     * 十二月与上月差额
     */
    private Double decemberGapMoney;

    /**
     * 差额增速最快月份
     */
    private String fastMonth;

    /**
     * 差额增速最慢月份
     */
    private String slowMonth;

    /**
     * 收入最多月份
     */
    private String maximumMonth;

    /**
     * 收入最少月份
     */
    private String leastMonth;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    public GroupUpType getGroupUpType() {
        return groupUpType;
    }

    public void setGroupUpType(GroupUpType groupUpType) {
        this.groupUpType = groupUpType;
    }

    public Double getJanuaryMoney() {
        return januaryMoney;
    }

    public void setJanuaryMoney(Double januaryMoney) {
        this.januaryMoney = januaryMoney;
    }

    public Double getFebruaryMoney() {
        return februaryMoney;
    }

    public void setFebruaryMoney(Double februaryMoney) {
        this.februaryMoney = februaryMoney;
    }

    public Double getMarchMoney() {
        return marchMoney;
    }

    public void setMarchMoney(Double marchMoney) {
        this.marchMoney = marchMoney;
    }

    public Double getAprilMoney() {
        return aprilMoney;
    }

    public void setAprilMoney(Double aprilMoney) {
        this.aprilMoney = aprilMoney;
    }

    public Double getMayMoney() {
        return mayMoney;
    }

    public void setMayMoney(Double mayMoney) {
        this.mayMoney = mayMoney;
    }

    public Double getJuneMoney() {
        return juneMoney;
    }

    public void setJuneMoney(Double juneMoney) {
        this.juneMoney = juneMoney;
    }

    public Double getJulyMoney() {
        return julyMoney;
    }

    public void setJulyMoney(Double julyMoney) {
        this.julyMoney = julyMoney;
    }

    public Double getAugustMoney() {
        return augustMoney;
    }

    public void setAugustMoney(Double augustMoney) {
        this.augustMoney = augustMoney;
    }

    public Double getSeptemberMoney() {
        return septemberMoney;
    }

    public void setSeptemberMoney(Double septemberMoney) {
        this.septemberMoney = septemberMoney;
    }

    public Double getOctoberMoney() {
        return octoberMoney;
    }

    public void setOctoberMoney(Double octoberMoney) {
        this.octoberMoney = octoberMoney;
    }

    public Double getNovemberMoney() {
        return novemberMoney;
    }

    public void setNovemberMoney(Double novemberMoney) {
        this.novemberMoney = novemberMoney;
    }

    public Double getDecemberMoney() {
        return decemberMoney;
    }

    public void setDecemberMoney(Double decemberMoney) {
        this.decemberMoney = decemberMoney;
    }

    public Double getJanuaryGapMoney() {
        return januaryGapMoney;
    }

    public void setJanuaryGapMoney(Double januaryGapMoney) {
        this.januaryGapMoney = januaryGapMoney;
    }

    public Double getFebruaryGapMoney() {
        return februaryGapMoney;
    }

    public void setFebruaryGapMoney(Double februaryGapMoney) {
        this.februaryGapMoney = februaryGapMoney;
    }

    public Double getMarchGapMoney() {
        return marchGapMoney;
    }

    public void setMarchGapMoney(Double marchGapMoney) {
        this.marchGapMoney = marchGapMoney;
    }

    public Double getAprilGapMoney() {
        return aprilGapMoney;
    }

    public void setAprilGapMoney(Double aprilGapMoney) {
        this.aprilGapMoney = aprilGapMoney;
    }

    public Double getMayGapMoney() {
        return mayGapMoney;
    }

    public void setMayGapMoney(Double mayGapMoney) {
        this.mayGapMoney = mayGapMoney;
    }

    public Double getJuneGapMoney() {
        return juneGapMoney;
    }

    public void setJuneGapMoney(Double juneGapMoney) {
        this.juneGapMoney = juneGapMoney;
    }

    public Double getJulyGapMoney() {
        return julyGapMoney;
    }

    public void setJulyGapMoney(Double julyGapMoney) {
        this.julyGapMoney = julyGapMoney;
    }

    public Double getAugustGapMoney() {
        return augustGapMoney;
    }

    public void setAugustGapMoney(Double augustGapMoney) {
        this.augustGapMoney = augustGapMoney;
    }

    public Double getSeptemberGapMoney() {
        return septemberGapMoney;
    }

    public void setSeptemberGapMoney(Double septemberGapMoney) {
        this.septemberGapMoney = septemberGapMoney;
    }

    public Double getOctoberGapMoney() {
        return octoberGapMoney;
    }

    public void setOctoberGapMoney(Double octoberGapMoney) {
        this.octoberGapMoney = octoberGapMoney;
    }

    public Double getNovemberGapMoney() {
        return novemberGapMoney;
    }

    public void setNovemberGapMoney(Double novemberGapMoney) {
        this.novemberGapMoney = novemberGapMoney;
    }

    public Double getDecemberGapMoney() {
        return decemberGapMoney;
    }

    public void setDecemberGapMoney(Double decemberGapMoney) {
        this.decemberGapMoney = decemberGapMoney;
    }

    public String getFastMonth() {
        return fastMonth;
    }

    public void setFastMonth(String fastMonth) {
        this.fastMonth = fastMonth;
    }

    public String getSlowMonth() {
        return slowMonth;
    }

    public void setSlowMonth(String slowMonth) {
        this.slowMonth = slowMonth;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMaximumMonth() {
        return maximumMonth;
    }

    public void setMaximumMonth(String maximumMonth) {
        this.maximumMonth = maximumMonth;
    }

    public String getLeastMonth() {
        return leastMonth;
    }

    public void setLeastMonth(String leastMonth) {
        this.leastMonth = leastMonth;
    }
}