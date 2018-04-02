package com.bjike.goddess.mytest.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.mytest.entity.Teacher;

/**
 * 测试用类 学生
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StudentTO extends BaseTO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 爱好
     */
    private String hobby;

    private TeacherTO teacherTO;

    public TeacherTO getTeacherTO() {
        return teacherTO;
    }

    public void setTeacherTO(TeacherTO teacherTO) {
        this.teacherTO = teacherTO;
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
        return "StudentTO{" +
                "name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                ", teacherTO=" + teacherTO +
                '}';
    }
}