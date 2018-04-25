package com.bjike.goddess.bankrecords.action.bankrecords;

import com.bjike.goddess.bankrecords.api.BankSummaryAPI;
import com.bjike.goddess.bankrecords.dto.BankSummaryDTO;
import com.bjike.goddess.bankrecords.entity.BankRecord;
import com.bjike.goddess.bankrecords.vo.BankAccountInfoVO;
import com.bjike.goddess.bankrecords.vo.BankSummaryVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* 银行流水
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-08 10:27 ]
* @Description:	[ 银行流水 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("banksummary")
public class BankSummaryAction {
 @Autowired
 private BankSummaryAPI bankSummaryAPI;

 /**
  * 汇总
  *
  * @param dto dto
  * @version v1
  */
 @GetMapping("v1/backfilterQuery")
   public Result backfilterQuery(BankSummaryDTO dto)throws ActException{
    try {
     return ActResult.initialize(BeanTransform.copyProperties(bankSummaryAPI.backfilterQuery(dto),BankSummaryVO.class));
    } catch (Exception e) {
     throw new ActException(e.getMessage());
       }
    }
 }