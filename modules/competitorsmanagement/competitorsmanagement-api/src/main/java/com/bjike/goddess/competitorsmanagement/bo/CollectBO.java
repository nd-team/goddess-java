package com.bjike.goddess.competitorsmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 竞争对手管理汇总业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 04:55 ]
 * @Description: [ 竞争对手管理汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectBO extends BaseBO {

    /**
     * 跟市场关联的竞争对手
     */
    private Integer MAcompetitor;

    /**
     * 竞争对象数量
     */
    private Integer COnumber;

    /**
     * 已完善竞争对象信息
     */
    private Integer CIcompetitior;

    /**
     * 未完善竞争对象信息
     */
    private Integer UNCIcompetitior;

    /**
     * 影响区域数
     */
    private Integer AFareaNum;

    /**
     * 影响业务数
     */
    private Integer AFbusinessNum;


    public Integer getMAcompetitor() {
        return MAcompetitor;
    }

    public void setMAcompetitor(Integer MAcompetitor) {
        this.MAcompetitor = MAcompetitor;
    }

    public Integer getCOnumber() {
        return COnumber;
    }

    public void setCOnumber(Integer COnumber) {
        this.COnumber = COnumber;
    }

    public Integer getCIcompetitior() {
        return CIcompetitior;
    }

    public void setCIcompetitior(Integer CIcompetitior) {
        this.CIcompetitior = CIcompetitior;
    }

    public Integer getUNCIcompetitior() {
        return UNCIcompetitior;
    }

    public void setUNCIcompetitior(Integer UNCIcompetitior) {
        this.UNCIcompetitior = UNCIcompetitior;
    }

    public Integer getAFareaNum() {
        return AFareaNum;
    }

    public void setAFareaNum(Integer AFareaNum) {
        this.AFareaNum = AFareaNum;
    }

    public Integer getAFbusinessNum() {
        return AFbusinessNum;
    }

    public void setAFbusinessNum(Integer AFbusinessNum) {
        this.AFbusinessNum = AFbusinessNum;
    }

    @Override
    public String toString() {
        return "CollectBO{" +
                "MAcompetitor=" + MAcompetitor +
                ", COnumber=" + COnumber +
                ", CIcompetitior=" + CIcompetitior +
                ", UNCIcompetitior=" + UNCIcompetitior +
                ", AFareaNum=" + AFareaNum +
                ", AFbusinessNum=" + AFbusinessNum +
                '}';
    }
}