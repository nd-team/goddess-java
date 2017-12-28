package com.bjike.goddess.progressmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.progressmanage.dto.ProgressNodeDTO;
import com.bjike.goddess.progressmanage.entity.ProgressNode;

/**
* 进度节点持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 05:21 ]
* @Description:	[ 进度节点持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProgressNodeRep extends JpaRep<ProgressNode ,ProgressNodeDTO> { 

 }