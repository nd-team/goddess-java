package com.bjike.goddess.workhandover.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workhandover.dto.TransInfoDTO;
import com.bjike.goddess.workhandover.entity.TransInfo;

/**
* 工作交接持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenyang ]
* @Date:			[  2017-11-10 05:08 ]
* @Description:	[ 工作交接持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface transinfoRep extends JpaRep<TransInfo,TransInfoDTO> {

 }