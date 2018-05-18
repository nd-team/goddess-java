package com.bjike.goddess.rotation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;
import com.bjike.goddess.rotation.entity.RotationRecord;

/**
* 岗位轮换记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2018-01-08 09:29 ]
* @Description:	[ 岗位轮换记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RotationRecordRep extends JpaRep<RotationRecord ,RotationRecordDTO> { 

 }