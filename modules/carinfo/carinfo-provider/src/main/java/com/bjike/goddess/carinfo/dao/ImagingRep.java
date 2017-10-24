package com.bjike.goddess.carinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.carinfo.dto.ImagingDTO;
import com.bjike.goddess.carinfo.entity.Imaging;

/**
* 图形化持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-12 02:30 ]
* @Description:	[ 图形化持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ImagingRep extends JpaRep<Imaging ,ImagingDTO> { 

 }