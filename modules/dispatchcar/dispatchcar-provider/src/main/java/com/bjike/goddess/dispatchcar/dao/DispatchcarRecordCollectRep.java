package com.bjike.goddess.dispatchcar.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dispatchcar.dto.DispatchcarRecordCollectDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchcarRecordCollect;

/**
* 出车记录管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DispatchcarRecordCollectRep extends JpaRep<DispatchcarRecordCollect ,DispatchcarRecordCollectDTO> { 

 }