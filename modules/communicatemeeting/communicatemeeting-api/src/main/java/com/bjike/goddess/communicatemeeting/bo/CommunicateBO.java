package com.bjike.goddess.communicatemeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 交流讨论业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommunicateBO extends BaseBO {

    /**
     * 交流意见
     */
    private String opinion;

    /**
     * 姓名
     */
    private String name;

    /**
     * 票数
     */
    private Integer num;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}