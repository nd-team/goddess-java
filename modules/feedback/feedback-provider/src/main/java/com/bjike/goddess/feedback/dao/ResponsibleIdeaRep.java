package com.bjike.goddess.feedback.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.entity.ResponsibleIdea;

/**
* 非责任相关人意见持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-08-02 06:47 ]
* @Description:	[ 非责任相关人意见持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ResponsibleIdeaRep extends JpaRep<ResponsibleIdea,ResponsibleIdeaDTO> {

 }