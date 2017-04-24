package com.bjike.goddess.function.vo;

/**
 * 用户功能表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class UserFunctionVO {

    /**
     * id
     */
    private String id;
    /**
     * 功能
     */
    private String functionId;

    /**
     * 所属人
     */
    private String userId;

    /**
     * 排序序列
     */
    private Integer seq;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}