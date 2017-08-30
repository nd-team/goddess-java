package com.bjike.goddess.progressmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.progressmanage.dto.NodeHeadRowSignDTO;
import com.bjike.goddess.progressmanage.entity.NodeHeadRowSign;

/**
* 节点表头对应值的行标记持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-26 10:01 ]
* @Description:	[ 节点表头对应值的行标记持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NodeHeadRowSignRep extends JpaRep<NodeHeadRowSign ,NodeHeadRowSignDTO> { 

 }