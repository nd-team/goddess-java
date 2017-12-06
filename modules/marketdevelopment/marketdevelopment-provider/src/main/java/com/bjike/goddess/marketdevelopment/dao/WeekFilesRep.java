package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.WeekFilesDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekFiles;

/**
* 周计划的表头数据持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:46 ]
* @Description:	[ 周计划的表头数据持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeekFilesRep extends JpaRep<WeekFiles ,WeekFilesDTO> { 

 }