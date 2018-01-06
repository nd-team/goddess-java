package com.bjike.goddess.businessinteraction.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;

/**
* 资料信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2018-01-05 11:48 ]
* @Description:	[ 资料信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TalkDetailRep extends JpaRep<TalkDetail ,TalkDetailDTO> { 

 }