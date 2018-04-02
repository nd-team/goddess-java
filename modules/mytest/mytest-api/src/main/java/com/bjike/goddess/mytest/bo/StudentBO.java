package com.bjike.goddess.mytest.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 测试用类 学生业务传输对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StudentBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 爱好
     */
    private String hobby;

    private TeacherBO teacherBO;

    public TeacherBO getTeacherBO() {
        return teacherBO;
    }

    public void setTeacherBO(TeacherBO teacherBO) {
        this.teacherBO = teacherBO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "StudentBO{" +
                "name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                ", teacherBO=" + teacherBO +
                '}';
    }
}