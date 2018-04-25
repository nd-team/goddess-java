package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;

/**
* 对银行流水汇总业务接口
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-02 02:21 ]
* @Description:	[ 对银行流水汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankSummaryAPI  {
 /**
  *把银行流水增加到汇总
  *
  */
 void summaryData()throws SerException;

 }