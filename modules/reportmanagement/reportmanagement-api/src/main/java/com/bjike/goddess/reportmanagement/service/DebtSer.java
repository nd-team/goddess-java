package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.entity.Debt;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;

import java.util.List;

/**
* 负债表业务接口
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-19 11:21 ]
* @Description:	[ 负债表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DebtSer extends Ser<Debt, DebtDTO> {
 /**
  * 查看负债与权益结构分析
  * @param dto
  * @return
  * @throws SerException
  */
 List<StructureBO> debtStructure(DebtDTO dto) throws SerException;
 }