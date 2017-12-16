package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.SubjectDataDTO;
import com.bjike.goddess.marketdevelopment.entity.SubjectData;

/**
* 业务方向科目数据持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 03:03 ]
* @Description:	[ 业务方向科目数据持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SubjectDataRep extends JpaRep<SubjectData ,SubjectDataDTO> { 

 }