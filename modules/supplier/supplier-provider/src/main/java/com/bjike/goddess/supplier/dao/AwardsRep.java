package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.AwardsDTO;
import com.bjike.goddess.supplier.entity.Awards;

/**
* 获奖情况持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:00 ]
* @Description:	[ 获奖情况持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AwardsRep extends JpaRep<Awards ,AwardsDTO> { 

 }