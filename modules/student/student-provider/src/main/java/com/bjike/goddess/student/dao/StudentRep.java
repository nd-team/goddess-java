package com.bjike.goddess.student.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.student.dto.StudentDTO;
import com.bjike.goddess.student.entity.Student;

/**
* 学生信息类持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StudentRep extends JpaRep<Student ,StudentDTO> { 

 }