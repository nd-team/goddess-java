package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.FilesDataDTO;
import com.bjike.goddess.marketdevelopment.entity.FilesData;

/**
* 阶段表头数据持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 04:30 ]
* @Description:	[ 阶段表头数据持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface FilesDataRep extends JpaRep<FilesData ,FilesDataDTO> { 

 }