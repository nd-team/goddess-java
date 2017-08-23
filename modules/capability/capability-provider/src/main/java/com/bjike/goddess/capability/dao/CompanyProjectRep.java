package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.CompanyProjectDTO;
import com.bjike.goddess.capability.entity.CompanyProject;

/**
* 公司参与项目数持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:20 ]
* @Description:	[ 公司参与项目数持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyProjectRep extends JpaRep<CompanyProject ,CompanyProjectDTO> { 

 }