package com.bjike.goddess.businesscommission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businesscommission.dto.QuotaDTO;
import com.bjike.goddess.businesscommission.entity.Quota;

/**
* 业务提成权重分配表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-26 11:38 ]
* @Description:	[ 业务提成权重分配表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface QuotaRep extends JpaRep<Quota ,QuotaDTO> { 

 }