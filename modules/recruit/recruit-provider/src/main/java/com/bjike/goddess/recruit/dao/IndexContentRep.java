package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.IndexContentDTO;
import com.bjike.goddess.recruit.entity.IndexContent;

/**
* 考核指标内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-14 02:46 ]
* @Description:	[ 考核指标内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface IndexContentRep extends JpaRep<IndexContent ,IndexContentDTO> { 

 }