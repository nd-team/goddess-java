package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;

/**
* 分红管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:47 ]
* @Description:	[ 分红管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ShareOutBonusManageRep extends JpaRep<ShareOutBonusManage ,ShareOutBonusManageDTO> { 

 }