package com.bjike.goddess.event.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 事件汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-11 16:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCountBO extends BaseBO{
    /**
     * 地区
     */
    private String area;
    /**
     * 查看
     */
    private Integer see;
    /**
     * 分析
     */
    private Integer analyze;
    /**
     * 核对
     */
    private Integer check;
    /**
     * 确认
     */
    private Integer confirm;
    /**
     * 审核
     */
    private Integer aduit;
    /**
     * 制作
     */
    private Integer make;
    /**
     * 合计
     */
    private Integer sum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getSee() {
        return see;
    }

    public void setSee(Integer see) {
        this.see = see;
    }

    public Integer getAnalyze() {
        return analyze;
    }

    public void setAnalyze(Integer analyze) {
        this.analyze = analyze;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getAduit() {
        return aduit;
    }

    public void setAduit(Integer aduit) {
        this.aduit = aduit;
    }

    public Integer getMake() {
        return make;
    }

    public void setMake(Integer make) {
        this.make = make;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
