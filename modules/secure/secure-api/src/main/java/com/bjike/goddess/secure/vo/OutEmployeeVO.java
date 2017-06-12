package com.bjike.goddess.secure.vo;

/**
 * 离职名单表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutEmployeeVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 离职时间
     */
    private String endTime;

    /**
     * 是否继续购买
     */
    private Boolean isAgain;

    /**
     * 意见
     */
    private String advice;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Boolean isAgain) {
        this.isAgain = isAgain;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}