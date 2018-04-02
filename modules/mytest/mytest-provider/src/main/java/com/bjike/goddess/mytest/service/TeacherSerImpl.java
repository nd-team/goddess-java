package com.bjike.goddess.mytest.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.mytest.dto.TeacherDTO;
import com.bjike.goddess.mytest.entity.Teacher;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 测试用类 教师业务实现
* @Author:			[ wanyi ]
* @Date:			[  2017-12-15 01:47 ]
* @Description:	[ 测试用类 教师业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="mytestSerCache")
@Service
public class TeacherSerImpl extends ServiceImpl<Teacher, TeacherDTO> implements TeacherSer { 

 }