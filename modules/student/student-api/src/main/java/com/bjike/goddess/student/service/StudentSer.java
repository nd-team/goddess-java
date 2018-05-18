package com.bjike.goddess.student.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.student.entity.Student;
import com.bjike.goddess.student.dto.StudentDTO;

/**
* 学生信息类业务接口
* @Author:			[ chentianpin ]
* @Date:			[  2018-04-30 11:14 ]
* @Description:	[ 学生信息类业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StudentSer extends Ser<Student, StudentDTO> { 

 }