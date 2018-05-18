package com.bjike.goddess.mytest.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.mytest.dto.TeacherDTO;
import com.bjike.goddess.mytest.entity.Teacher;

/**
* 测试用类 教师持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-15 01:47 ]
* @Description:	[ 测试用类 教师持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TeacherRep extends JpaRep<Teacher ,TeacherDTO> { 

 }