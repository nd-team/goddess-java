package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.InProjctDTO;
import com.bjike.goddess.capability.entity.InProjct;

/**
* 尚在进行中项目数持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:23 ]
* @Description:	[ 尚在进行中项目数持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InProjctRep extends JpaRep<InProjct ,InProjctDTO> { 

 }