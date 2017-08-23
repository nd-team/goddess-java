package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.CompleteProDTO;
import com.bjike.goddess.capability.entity.CompletePro;

/**
* 已完成项目数持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:22 ]
* @Description:	[ 已完成项目数持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompleteProRep extends JpaRep<CompletePro ,CompleteProDTO> { 

 }