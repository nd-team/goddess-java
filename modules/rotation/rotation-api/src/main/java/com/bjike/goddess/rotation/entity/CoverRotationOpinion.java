package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位轮换自荐意见
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_coverrotationopinion")
public class CoverRotationOpinion extends BaseEntity {

    /**
     * 岗位轮换自荐
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "cover_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位轮换自荐'")
    private CoverRotation cover;

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '意见'")
    private String opinion;


    public CoverRotation getCover() {
        return cover;
    }

    public void setCover(CoverRotation cover) {
        this.cover = cover;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}