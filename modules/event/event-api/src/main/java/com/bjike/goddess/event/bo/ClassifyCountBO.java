package com.bjike.goddess.event.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 分类汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-11 16:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClassifyCountBO extends BaseBO{
    /**
     * 分类
     */
    private String classify;
    /**
     * 已处理
     */
    private Integer haveDeal;
    /**
     * 未处理
     */
    private Integer noDeal;
    /**
     * 逾期未处理
     */
    private Integer passNoDeal;
    /**
     * 处理中
     */
    private Integer dealing;
    /**
     * 合计
     */
    private Integer sum;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Integer getHaveDeal() {
        return haveDeal;
    }

    public void setHaveDeal(Integer haveDeal) {
        this.haveDeal = haveDeal;
    }

    public Integer getNoDeal() {
        return noDeal;
    }

    public void setNoDeal(Integer noDeal) {
        this.noDeal = noDeal;
    }

    public Integer getPassNoDeal() {
        return passNoDeal;
    }

    public void setPassNoDeal(Integer passNoDeal) {
        this.passNoDeal = passNoDeal;
    }

    public Integer getDealing() {
        return dealing;
    }

    public void setDealing(Integer dealing) {
        this.dealing = dealing;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
