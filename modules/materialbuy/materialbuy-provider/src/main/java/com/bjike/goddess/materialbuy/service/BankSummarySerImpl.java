package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialbuy.bo.BankOfTheWaterBO;
import com.bjike.goddess.materialbuy.dto.BankSummaryDTO;
import com.bjike.goddess.materialbuy.entity.BankSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 对银行流水汇总业务实现
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-02 02:21 ]
* @Description:	[ 对银行流水汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialbuySerCache")
@Service
public class BankSummarySerImpl extends ServiceImpl<BankSummary, BankSummaryDTO> implements BankSummarySer {
  @Autowired
   private BankOfTheWaterSerImpl bankOfTheWaterSer;
 @Override
 public void summaryData() throws SerException {
    }
}