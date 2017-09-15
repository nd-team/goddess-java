package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.entity.Modules;

/**
* 岗位工作明细表-模块表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 01:58 ]
* @Description:	[ 岗位工作明细表-模块表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ModulesRep extends JpaRep<Modules ,ModulesDTO> { 

 }