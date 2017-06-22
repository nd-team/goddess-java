package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadValueBO;
import com.bjike.goddess.progressmanage.entity.TableHeadValue;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;

import java.util.List;

/**
* 进度表表头对应Value业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-06-19 04:48 ]
* @Description:	[ 进度表表头对应Value业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface TableHeadValueSer extends Ser<TableHeadValue, TableHeadValueDTO> {

    List<TableHeadValueBO> pageList(TableHeadValueDTO dto) throws SerException;


}