package com.bjike.goddess.mytest.vo;

/**
 * 测试用类 学生表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StudentVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 爱好
     */
    private String hobby;

    private TeacherVO teacherVO;

    public TeacherVO getTeacherVO() {
        return teacherVO;
    }

    public void setTeacherVO(TeacherVO teacherVO) {
        this.teacherVO = teacherVO;
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}