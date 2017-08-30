package com.bjike.goddess.projectroyalty.vo;

/**
 * 完工时间表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:44 ]
 * @Description: [ 完工时间表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompletionTimeVO {

    /**
     * id
     */
    private String id;
    /**
     * 完工时间(月)
     */
    private Integer completion;

    /**
     * 重要性
     */
    private Double importance;

    /**
     * 提成比例
     */
    private Double rate;

    /**
     * 管理能力
     */
    private Double manage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }
}