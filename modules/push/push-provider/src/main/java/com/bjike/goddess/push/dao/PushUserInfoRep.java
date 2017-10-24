package com.bjike.goddess.push.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.push.dto.PushUserInfoDTO;
import com.bjike.goddess.push.entity.PushUserInfo;

/**
* 推送的用户装置信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-10-11 10:18 ]
* @Description:	[ 推送的用户装置信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PushUserInfoRep extends JpaRep<PushUserInfo ,PushUserInfoDTO> { 

 }