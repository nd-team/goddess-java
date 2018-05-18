package com.bjike.goddess.employeecontract.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.employeecontract.dto.CommunicationTemplateDTO;
import com.bjike.goddess.employeecontract.entity.CommunicationTemplate;

/**
* 各类交流沟通模板持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CommunicationTemplateRep extends JpaRep<CommunicationTemplate ,CommunicationTemplateDTO> { 

 }