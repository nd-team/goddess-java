package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.ProvideProductDTO;
import com.bjike.goddess.supplier.entity.ProvideProduct;

/**
* 针对拟为我公司提供产品持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:06 ]
* @Description:	[ 针对拟为我公司提供产品持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProvideProductRep extends JpaRep<ProvideProduct ,ProvideProductDTO> { 

 }