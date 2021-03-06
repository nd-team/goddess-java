package com.bjike.goddess.contractcommunicat.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractcommunicat.dto.CollectEmailDTO;
import com.bjike.goddess.contractcommunicat.entity.CollectEmail;

/**
* 项目商务洽谈邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-16T19:08:18.889 ]
* @Description:	[ 项目商务洽谈合同邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractCollectEmailRep extends JpaRep<CollectEmail,CollectEmailDTO> {

 }