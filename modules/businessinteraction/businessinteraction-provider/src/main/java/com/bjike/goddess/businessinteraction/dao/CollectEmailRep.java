package com.bjike.goddess.businessinteraction.dao;

import com.bjike.goddess.businessinteraction.dto.CollectEmailDTO;
import com.bjike.goddess.businessinteraction.entity.CollectEmail;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
* 商务项目合同邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-16T19:08:18.889 ]
* @Description:	[ 商务项目合同邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectEmailRep extends JpaRep<CollectEmail,CollectEmailDTO> {

 }