package com.bjike.goddess.rotation.vo;

/**
 * 岗位轮换统计表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RotationStatisticsVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 岗位层级数据id
     */
    private String arrangementId;

    /**
     * 岗位层级
     */
    private String arrangementName;

    /**
     * 补助周期开始时间
     */
    private String subsidyStart;

    /**
     * 补助周期结束时间
     */
    private String subsidyEnd;

    /**
     * 开始担任时间
     */
    private String occupyStart;

    /**
     * 结束担任时间
     */
    private String occupyEnd;

    /**
     * 周期天数
     */
    private Integer cycle;

    /**
     * 周期内补贴天数
     */
    private Integer subsidy;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(String arrangementId) {
        this.arrangementId = arrangementId;
    }

    public String getArrangementName() {
        return arrangementName;
    }

    public void setArrangementName(String arrangementName) {
        this.arrangementName = arrangementName;
    }

    public String getSubsidyStart() {
        return subsidyStart;
    }

    public void setSubsidyStart(String subsidyStart) {
        this.subsidyStart = subsidyStart;
    }

    public String getSubsidyEnd() {
        return subsidyEnd;
    }

    public void setSubsidyEnd(String subsidyEnd) {
        this.subsidyEnd = subsidyEnd;
    }

    public String getOccupyStart() {
        return occupyStart;
    }

    public void setOccupyStart(String occupyStart) {
        this.occupyStart = occupyStart;
    }

    public String getOccupyEnd() {
        return occupyEnd;
    }

    public void setOccupyEnd(String occupyEnd) {
        this.occupyEnd = occupyEnd;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Integer subsidy) {
        this.subsidy = subsidy;
    }
}