package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.persistence.Column;
import java.time.LocalDate;

/**
* 银行流水分析
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-09 09:42 ]
* @Description:	[ 银行流水分析 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class BankAnalysisTO extends BaseTO {

 /**
  * 银行名称
  */
 private String bankName;
 /**
  * 日期
  */

 private LocalDate theDateOf;
 /**
  * 本月收入
  */

 private Double thisMonthIncome;

 /**
  * 上个月收入
  */

 private Double lastMonthIncome;

 /**
  * 收入差额
  */

 private Double incomeGap;

 /**
  * 收入比例
  */

 private Double incomeRatio;

 /**
  * 收入增长率
  */

 private Double revenueGrowthRate;

 /**
  * 本月支出
  */

 private Double thisMonthSpending;

 /**
  * 上个月支出
  */

 private Double expenditureLastMonth;

 /**
  * 支出比例
  */

 private Double spendingProportion;

 /**
  * 支出增长率
  */

 private Double expenditureGrowthRate;
 }