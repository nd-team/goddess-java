package com.bjike.goddess.version.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.entity.Answer;

/**
* 答案持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-08-04 03:10 ]
* @Description:	[ 答案持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AnswerRep extends JpaRep<Answer ,AnswerDTO> { 

 }