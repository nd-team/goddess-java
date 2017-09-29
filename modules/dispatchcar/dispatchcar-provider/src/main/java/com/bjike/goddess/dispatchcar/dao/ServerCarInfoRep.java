package com.bjike.goddess.dispatchcar.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dispatchcar.dto.ServerCarInfoDTO;
import com.bjike.goddess.dispatchcar.entity.ServerCarInfo;

/**
* 旧服务器上的出车记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-07 06:20 ]
* @Description:	[ 旧服务器上的出车记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ServerCarInfoRep extends JpaRep<ServerCarInfo,ServerCarInfoDTO> {

 }