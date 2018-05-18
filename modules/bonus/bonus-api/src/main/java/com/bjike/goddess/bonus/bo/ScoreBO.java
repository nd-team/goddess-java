package com.bjike.goddess.bonus.bo;

import java.io.Serializable;

/**
 * 奖励处罚分数汇总传输对象
 *
 * @Author: [lijuntao]
 * @Date: [2017-04-10 10:47]
 * @Description: [ 奖励处罚分数汇总传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ScoreBO implements Serializable {

    /**
     * 奖励总分
     */
    private Double rewardTotal;

    /**
     * 处罚总分
     */
    private Double pushTotal;

    public Double getRewardTotal() {
        return rewardTotal;
    }

    public void setRewardTotal(Double rewardTotal) {
        this.rewardTotal = rewardTotal;
    }

    public Double getPushTotal() {
        return pushTotal;
    }

    public void setPushTotal(Double pushTotal) {
        this.pushTotal = pushTotal;
    }
}
