package com.bjike.goddess.staffing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 人工成本计划子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:57 ]
 * @Description: [ 人工成本计划子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SonBO extends BaseBO {

    /**
     * 薪资区间
     */
    private String sal;

    /**
     * 总经办计划人数
     */
    private Integer bossPlan;

    /**
     * 总经办计划成本
     */
    private Double bossPlanExpend;

    /**
     * 总经办现有总人数
     */
    private Integer bossActual;

    /**
     * 总经办现有总人工成本
     */
    private Double bossActualExpend;

    /**
     * 人工成本计划子表详细信息
     */
    private List<ExpendPlanSonDetailBO> details=new ArrayList<>();

    public List<ExpendPlanSonDetailBO> getDetails() {
        return details;
    }

    public void setDetails(List<ExpendPlanSonDetailBO> details) {
        this.details = details;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public Integer getBossPlan() {
        return bossPlan;
    }

    public void setBossPlan(Integer bossPlan) {
        this.bossPlan = bossPlan;
    }

    public Double getBossPlanExpend() {
        return bossPlanExpend;
    }

    public void setBossPlanExpend(Double bossPlanExpend) {
        this.bossPlanExpend = bossPlanExpend;
    }

    public Integer getBossActual() {
        return bossActual;
    }

    public void setBossActual(Integer bossActual) {
        this.bossActual = bossActual;
    }

    public Double getBossActualExpend() {
        return bossActualExpend;
    }

    public void setBossActualExpend(Double bossActualExpend) {
        this.bossActualExpend = bossActualExpend;
    }
}