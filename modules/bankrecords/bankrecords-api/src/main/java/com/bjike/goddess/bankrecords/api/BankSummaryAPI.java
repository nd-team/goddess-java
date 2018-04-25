package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.BankSummaryBO;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.entity.BankSummary;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
* 银行流水汇总业务接口
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-08 10:27 ]
* @Description:	[ 银行流水业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankSummaryAPI  {
 /**
  *筛选查询
  */
 List<BankSummaryBO> backfilterQuery(BankSummaryDTO dto) throws SerException;

 }