package com.bjike.goddess.costdetail.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.costdetail.dto.DetailTypeDTO;
import com.bjike.goddess.costdetail.entity.DetailType;

/**
* 明细分类持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-07-07 09:43 ]
* @Description:	[ 明细分类持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DetailTypeRep extends JpaRep<DetailType ,DetailTypeDTO> { 

 }