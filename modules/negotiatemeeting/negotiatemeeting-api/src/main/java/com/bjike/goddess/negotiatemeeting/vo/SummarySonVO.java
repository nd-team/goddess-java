package com.bjike.goddess.negotiatemeeting.vo;

/**
 * 协商会议纪要子表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummarySonVO {

    /**
     * id
     */
    private String id;
    /**
     * 参会人
     */
    private String attend;

    /**
     * 协商意见
     */
    private String opinion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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