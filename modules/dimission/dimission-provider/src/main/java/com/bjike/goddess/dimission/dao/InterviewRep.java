package com.bjike.goddess.dimission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dimission.dto.InterviewDTO;
import com.bjike.goddess.dimission.entity.Interview;

/**
* 离职管理面谈持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 02:39 ]
* @Description:	[ 离职管理面谈持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InterviewRep extends JpaRep<Interview ,InterviewDTO> { 

 }