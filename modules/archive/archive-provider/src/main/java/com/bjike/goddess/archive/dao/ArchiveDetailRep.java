package com.bjike.goddess.archive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.entity.ArchiveDetail;

/**
* 档案明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 02:05 ]
* @Description:	[ 档案明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ArchiveDetailRep extends JpaRep<ArchiveDetail ,ArchiveDetailDTO> { 

 }