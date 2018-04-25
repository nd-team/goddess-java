package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialbuy.bo.BankOfTheWaterBO;
import com.bjike.goddess.materialbuy.bo.ExcelTitleBO;
import com.bjike.goddess.materialbuy.dto.BankOfTheWaterDTO;
import com.bjike.goddess.materialbuy.to.BankOfTheWaterTO;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
* 银行流水业务接口
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-04 09:39 ]
* @Description:	[ 银行流水业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankOfTheWaterAPI  {
 /**
  * 分页查询
  */
  List<BankOfTheWaterBO> listPath(BankOfTheWaterDTO dto)throws SerException;
 /**
  * 检查导入的Excel标题
  *
  * @version v1
  */
 List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException;

 /**
  * 导入银行流水
  */
 void upload(BankOfTheWaterTO to) throws SerException;
 /**
  * 银行流水的汇总
  *
  */
 List<BankOfTheWaterBO> bankSummary(BankOfTheWaterDTO dto) throws SerException;
 /**
  * 银行流水call
  *
  */
 List<BankOfTheWaterBO> banksGlobally()throws SerException;

}