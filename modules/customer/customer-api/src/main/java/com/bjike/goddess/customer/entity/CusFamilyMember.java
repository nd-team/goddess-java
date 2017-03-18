package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 客户家庭成员
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.103 ]
 * @Description: [ 客户家庭成员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_cusfamilymember")
public class CusFamilyMember extends BaseEntity {

    /**
     * 称谓
     */
    @Column(name = "title",unique = true ,columnDefinition = "VARCHAR(255)   COMMENT '称谓'")
    private String title;

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 联系方式
     */
    @Column(name = "relationWay",  columnDefinition = "VARCHAR(255)   COMMENT '联系方式'")
    private String relationWay;

    /**
     * 性格爱好
     */
    @Column(name = "charactLove",  columnDefinition = "VARCHAR(255)   COMMENT '性格爱好'")
    private String charactLove;

    /**
     * 工作单位
     */
    @Column(name = "workPlace",  columnDefinition = "VARCHAR(255)   COMMENT '工作单位'")
    private String workPlace;

    /**
     * 职位
     */
    @Column(name = "jobPost",  columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String jobPost;

    /**
     * 客户成员详细
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "customerDetail_id",  columnDefinition = "VARCHAR(36)   COMMENT '客户成员详细'")
    private CustomerDetail customerDetail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationWay() {
        return relationWay;
    }

    public void setRelationWay(String relationWay) {
        this.relationWay = relationWay;
    }

    public String getCharactLove() {
        return charactLove;
    }

    public void setCharactLove(String charactLove) {
        this.charactLove = charactLove;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }
}