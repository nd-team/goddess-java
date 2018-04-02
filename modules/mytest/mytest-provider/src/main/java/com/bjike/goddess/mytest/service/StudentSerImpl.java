package com.bjike.goddess.mytest.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.mytest.dto.StudentDTO;
import com.bjike.goddess.mytest.entity.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 测试用类 学生业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "mytestSerCache")
@Service
public class StudentSerImpl extends ServiceImpl<Student, StudentDTO> implements StudentSer {

    @Override
    public Student save(Student student) throws SerException {
        super.save(student);
        return student;
    }
}