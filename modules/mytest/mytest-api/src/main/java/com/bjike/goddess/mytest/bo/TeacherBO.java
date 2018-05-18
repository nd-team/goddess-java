package com.bjike.goddess.mytest.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * 测试用类 教师业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:47 ]
 * @Description: [ 测试用类 教师业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TeacherBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 科目
     */
    private String subject;


    private List<CarBO> carBOS;

    public List<CarBO> getCarBOS() {
        return carBOS;
    }

    public void setCarBOS(List<CarBO> carBOS) {
        this.carBOS = carBOS;
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
        return "TeacherBO{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", carsBO=" + carBOS +
                '}';
    }
}