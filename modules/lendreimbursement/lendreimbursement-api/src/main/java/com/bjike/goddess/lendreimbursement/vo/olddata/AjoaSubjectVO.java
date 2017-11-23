package com.bjike.goddess.lendreimbursement.vo.olddata;

/**
 * 老系统的科目表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 10:33 ]
 * @Description: [ 老系统的科目表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AjoaSubjectVO {

    /**
     * id
     */
    private String id;
    /**
     * 类别
     */
    private String sort;

    /**
     * 分类
     */
    private String classify;

    /**
     * 一级科目
     */
    private String subject;

    /**
     * 二级科目
     */
    private String detail;

    /**
     * 节点
     */
    private String node;

    /**
     * 说明
     */
    private String define;

    /**
     * 流程
     */
    private String flow;

    /**
     * 状态
     */
    private int status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}