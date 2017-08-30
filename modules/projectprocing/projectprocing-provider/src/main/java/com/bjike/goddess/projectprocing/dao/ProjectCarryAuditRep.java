package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.ProjectCarryAuditDTO;
import com.bjike.goddess.projectprocing.entity.ProjectCarryAudit;

/**
* 项目实施审核(针对没派工单情况)持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-31 06:41 ]
* @Description:	[ 项目实施审核(针对没派工单情况)持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectCarryAuditRep extends JpaRep<ProjectCarryAudit ,ProjectCarryAuditDTO> { 

 }