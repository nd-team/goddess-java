package com.bjike.goddess.mytest.vo;

import java.util.List;
import java.util.Set;

/**
 * 测试用类 教师表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:47 ]
 * @Description: [ 测试用类 教师表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TeacherVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 科目
     */
    private String subject;

    private List<CarVO> carVOS;

    public List<CarVO> getCarVOS() {
        return carVOS;
    }

    public void setCarVOS(List<CarVO> carVOS) {
        this.carVOS = carVOS;
    }

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "TeacherVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", carVOS=" + carVOS +
                '}';
    }
}