package com.bjike.goddess.financeinit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.entity.Currency;

/**
* 设置币别持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 02:17 ]
* @Description:	[ 设置币别持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CurrencyRep extends JpaRep<Currency ,CurrencyDTO> { 

 }