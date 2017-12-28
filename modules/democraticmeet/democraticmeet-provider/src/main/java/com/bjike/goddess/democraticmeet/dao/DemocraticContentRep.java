package com.bjike.goddess.democraticmeet.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.democraticmeet.dto.DemocraticContentDTO;
import com.bjike.goddess.democraticmeet.entity.DemocraticContent;

/**
* 民主生活会议组织内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-02 11:20 ]
* @Description:	[ 民主生活会议组织内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DemocraticContentRep extends JpaRep<DemocraticContent ,DemocraticContentDTO> { 

 }