package com.bjike.goddess.businessprojectmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessprojectmanage.dto.CommunicationTemplateDTO;
import com.bjike.goddess.businessprojectmanage.entity.CommunicationTemplate;

/**
* 各类沟通交流模板持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-09 02:31 ]
* @Description:	[ 各类沟通交流模板持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CommunicationTemplateRep extends JpaRep<CommunicationTemplate ,CommunicationTemplateDTO> { 

 }