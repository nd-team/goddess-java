package com.bjike.goddess.archive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.entity.ArchiveGather;

/**
* 档案收集持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 02:22 ]
* @Description:	[ 档案收集持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ArchiveGatherRep extends JpaRep<ArchiveGather ,ArchiveGatherDTO> { 

 }