package com.bjike.goddess.progressmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;

/**
* 进度表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-06-09 04:46 ]
* @Description:	[ 进度表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProgressTableRep extends JpaRep<ProgressTable ,ProgressTableDTO> { 

 }