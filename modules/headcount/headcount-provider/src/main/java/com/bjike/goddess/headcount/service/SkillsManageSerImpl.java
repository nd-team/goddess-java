package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.headcount.dto.SkillsManageDTO;
import com.bjike.goddess.headcount.entity.SkillsManage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 技能管理等级配置业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:44:02.647 ]
* @Description:	[ 技能管理等级配置业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="skillsManageSerCache")
@Service
public class SkillsManageSerImpl extends ServiceImpl<SkillsManage, SkillsManageDTO> implements SkillsManageSer { 

 }