package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.entity.ModuleType;

/**
* 模块类型持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-14 02:19 ]
* @Description:	[ 模块类型持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ModuleTypeRep extends JpaRep<ModuleType ,ModuleTypeDTO> { 

 }