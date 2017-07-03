package com.bjike.goddess.allmeeting.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.allmeeting.dto.WorkCollectPrepareDTO;
import com.bjike.goddess.allmeeting.entity.WorkCollectPrepare;

/**
* 工作汇总议题准备信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-05-31 03:44 ]
* @Description:	[ 工作汇总议题准备信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WorkCollectPrepareRep extends JpaRep<WorkCollectPrepare ,WorkCollectPrepareDTO> { 

 }