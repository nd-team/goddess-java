package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 岗位轮换统计
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RotationStatisticsTO extends BaseTO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 岗位层级
     */
    private String arrangementId;

    /**
     * 补助周期开始时间
     */
    private String subsidyStart;

    /**
     * 开始担任时间
     */
    private String occupyStart;

    /**
     * 补助周期结束时间
     */
    private String subsidyEnd;

    /**
     * 结束担任时间
     */
    private String occupyEnd;

    /**
     * 周期内补贴天数
     */
    private Integer subsidy;


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

    public String getOccupyEnd() {
        return occupyEnd;
    }

    public void setOccupyEnd(String occupyEnd) {
        this.occupyEnd = occupyEnd;
    }

    public Integer getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Integer subsidy) {
        this.subsidy = subsidy;
    }

    public String getOccupyStart() {
        return occupyStart;
    }

    public void setOccupyStart(String occupyStart) {
        this.occupyStart = occupyStart;
    }
}