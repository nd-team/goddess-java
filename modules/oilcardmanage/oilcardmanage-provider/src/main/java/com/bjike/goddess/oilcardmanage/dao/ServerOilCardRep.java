package com.bjike.goddess.oilcardmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.oilcardmanage.dto.ServerOilCardDTO;
import com.bjike.goddess.oilcardmanage.entity.ServerOilCard;

/**
* 旧服务器上的油卡信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 09:24 ]
* @Description:	[ 旧服务器上的油卡信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ServerOilCardRep extends JpaRep<ServerOilCard ,ServerOilCardDTO> { 

 }