package com.bjike.goddess.democraticmeet.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.democraticmeet.dto.AttenderDTO;
import com.bjike.goddess.democraticmeet.entity.Attender;

/**
* 会议计划参与人员持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-02 11:29 ]
* @Description:	[ 会议计划参与人员持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AttenderRep extends JpaRep<Attender ,AttenderDTO> { 

 }