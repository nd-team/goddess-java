package com.bjike.goddess.storage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.storage.dto.StorageUserDTO;
import com.bjike.goddess.storage.entity.StorageUser;

/**
* 存储模块用户持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ liguiqin ]
* @Date:			[  2017-03-30 09:39 ]
* @Description:	[ 存储模块用户持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StorageUserRep extends JpaRep<StorageUser ,StorageUserDTO> { 

 }