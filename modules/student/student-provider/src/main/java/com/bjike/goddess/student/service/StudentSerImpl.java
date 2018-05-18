package com.bjike.goddess.student.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.student.dto.StudentDTO;
import com.bjike.goddess.student.entity.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 学生信息类业务实现
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="studentSerCache")
@Service
public class StudentSerImpl extends ServiceImpl<Student, StudentDTO> implements StudentSer { 

 }