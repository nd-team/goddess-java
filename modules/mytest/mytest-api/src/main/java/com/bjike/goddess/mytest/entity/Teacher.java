package com.bjike.goddess.mytest.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.naming.Name;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * 测试用类 教师
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:47 ]
 * @Description: [ 测试用类 教师 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "mytest_teacher")
public class Teacher extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subject;

    @OneToMany
    @JoinColumn(name = "t_id")
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", cars=" + cars +
                '}';
    }
}