package com.bjike.goddess.projectroyalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.entity.ProjectFactors;

/**
* 项目提成分配因素持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-14 11:39 ]
* @Description:	[ 项目提成分配因素持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectFactorsRep extends JpaRep<ProjectFactors ,ProjectFactorsDTO> { 

 }