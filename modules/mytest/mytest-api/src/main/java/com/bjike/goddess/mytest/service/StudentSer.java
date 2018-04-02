package com.bjike.goddess.mytest.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.mytest.dto.StudentDTO;
import com.bjike.goddess.mytest.entity.Student;

/**
 * 测试用类 学生业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StudentSer extends Ser<Student, StudentDTO> {

    Student save(Student student) throws SerException;
}