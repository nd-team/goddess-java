package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.EmailDTO;
import com.bjike.goddess.managepromotion.entity.Email;

/**
* 发送邮件持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-09-14 02:16 ]
* @Description:	[ 发送邮件持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EmailRep extends JpaRep<Email ,EmailDTO> { 

 }