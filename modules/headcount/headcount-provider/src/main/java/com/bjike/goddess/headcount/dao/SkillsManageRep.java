package com.bjike.goddess.headcount.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.headcount.dto.SkillsManageDTO;
import com.bjike.goddess.headcount.entity.SkillsManage;

/**
* 技能管理等级配置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:44:02.647 ]
* @Description:	[ 技能管理等级配置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SkillsManageRep extends JpaRep<SkillsManage ,SkillsManageDTO> { 

 }