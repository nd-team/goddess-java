package com.bjike.goddess.fixedassets.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fixedassets.dto.BaseInfoDTO;
import com.bjike.goddess.fixedassets.entity.BaseInfo;

/**
* 基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-23 11:41 ]
* @Description:	[ 基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BaseInfoRep extends JpaRep<BaseInfo ,BaseInfoDTO> { 

 }