package com.bjike.goddess.negotiatemeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 协商会议纪要子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummarySonBO extends BaseBO {

    /**
     * 参会人
     */
    private String attend;

    /**
     * 协商意见
     */
    private String opinion;


    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}