package com.bjike.goddess.user.entity;


import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.enums.SexType;
import com.bjike.goddess.user.enums.UserType;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 用户详情
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_detail")
public class UserDetail extends BaseEntity {

    /**
     * 性别
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '用户性别' ", nullable = false)
    private SexType sex;
    /**
     * 年龄
     */
    @Range(min = 0, max = 120)
    @Column(columnDefinition = "TINYINT COMMENT '年龄' ")
    private Integer age;

    /**
     * 地址
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地址' ")

    private String address;
    /**
     * 真实姓名
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '真实姓名' ")
    private String realName;
    /**
     * 出生年月
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '出生年月' ")
    private LocalDate birthday;
    /**
     * 身份证
     */
    @Column(unique = true, columnDefinition = "VARCHAR(255) COMMENT '身份证' ")
    private String idCard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false, unique = true, columnDefinition = "VARCHAR(36) COMMENT '所属用户' ")
    private User user;

    /**
     * 所在部门
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "department_id", columnDefinition = "VARCHAR(36) COMMENT '所在部门' ")
    private Department department;

    /**
     * 所在用户组
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id", columnDefinition = "VARCHAR(36) COMMENT '所在用户组' ")
    private Group group;

    /**
     * 职位
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "position_id", columnDefinition = "VARCHAR(36) COMMENT '职位' ")
    private Position position;

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
