package com.bjike.goddess.projectroyalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectroyalty.dto.CompletionTimeDTO;
import com.bjike.goddess.projectroyalty.entity.CompletionTime;

/**
* 完工时间持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-07 09:44 ]
* @Description:	[ 完工时间持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompletionTimeRep extends JpaRep<CompletionTime ,CompletionTimeDTO> { 

 }