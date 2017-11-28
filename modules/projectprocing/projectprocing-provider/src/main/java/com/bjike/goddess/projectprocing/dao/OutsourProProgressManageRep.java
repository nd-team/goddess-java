package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.OutsourProProgressManage;

/**
* 外包,半外包项目结算进度管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:03 ]
* @Description:	[ 外包,半外包项目结算进度管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OutsourProProgressManageRep extends JpaRep<OutsourProProgressManage ,OutsourProProgressManageDTO> { 

 }