package com.bjike.goddess.staffentry.vo;

/**
 * 证书情况表现层对象
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 10:35]
 * @Description: [证书情况表现层对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CredentialVO {

    /**
     * id
     */
    private String id;

    /**
     * 证书名称
     */
    private String name;

    /**
     * 获得证书时间
     */
    private String obtainTime;

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

    public String getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(String obtainTime) {
        this.obtainTime = obtainTime;
    }
}
