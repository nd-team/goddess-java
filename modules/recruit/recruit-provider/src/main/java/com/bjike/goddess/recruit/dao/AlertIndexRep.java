package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.AlertIndexDTO;
import com.bjike.goddess.recruit.entity.AlertIndex;

/**
* 弹框考核指标持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-14 02:43 ]
* @Description:	[ 弹框考核指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AlertIndexRep extends JpaRep<AlertIndex ,AlertIndexDTO> { 

 }