package com.bjike.goddess.voucher.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.VoucherSummanryBO;
import com.bjike.goddess.voucher.bo.VoucherTotalGoldBO;
import com.bjike.goddess.voucher.dto.VoucherSummaryDTO;
import com.bjike.goddess.voucher.dto.VoucherTotalDTO;
import com.bjike.goddess.voucher.entity.VoucherTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 记账凭证合计金额业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证合计金额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "voucherSerCache")
@Service
public class VoucherTotalSerImpl extends ServiceImpl<VoucherTotal, VoucherTotalDTO> implements VoucherTotalSer {
//    @Autowired
//    private VoucherGenerateAPI voucherGenerateAPI;
//
//    @Override
//    public VoucherTotalGoldBO vb(VoucherTotalDTO dto) throws SerException {
//        VoucherTotalGoldBO voucherTotalGoldBO=new VoucherTotalGoldBO();
//        Double borrowMoneyCount=0.00;
//        Double loanMoneyCount=0.00;
//        VoucherSummaryDTO summaryDTO=new VoucherSummaryDTO();
//        summaryDTO.setArea(dto.getArea());
//        summaryDTO.setEndTime(dto.getEndTime());
//        summaryDTO.setStartTime(dto.getStartTime());
//        summaryDTO.setProjectGroup(dto.getProjectGroup());
//        summaryDTO.setProjectName(dto.getProjectName());
//        List<VoucherSummanryBO> list=voucherGenerateAPI.summaryList(summaryDTO);
//        for(int i=0;i<list.size();i++){
//            borrowMoneyCount+=list.get(i).getBorrowMoney();
//            loanMoneyCount+=list.get(i).getLoanMoney();
//        }
//        voucherTotalGoldBO.setBorrowMoneyCount(borrowMoneyCount);
//        voucherTotalGoldBO.setLoanMoneyCount(loanMoneyCount);
//        return voucherTotalGoldBO;
    //}
}